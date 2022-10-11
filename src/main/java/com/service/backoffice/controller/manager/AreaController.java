package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.util.security.Roles;
import com.service.backoffice.util.security.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @Operation(summary = "Delete area", description = "This can only be done by manager.", tags = {
            "area-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Area not found")})
    @DeleteMapping("/areas/{id}")
    public ResponseEntity<Boolean> deleteArea(@PathVariable long id,
                                              @RequestHeader(required = false)
                                              String authorization) {
        SecurityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(areaService.deleteArea(id));
    }

    @Operation(summary = "Add new area", description = "This can only be done by manager.", tags = {
            "area-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AreaDto.class))),
            @ApiResponse(responseCode = "404", description = "Area with such id already exist")})
    @PostMapping("/areas")
    public ResponseEntity<AreaDto> addArea(@RequestBody @Valid AreaDto areaDto,
                                           @RequestHeader(required = false)
                                           String authorization) {
        SecurityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(AreaMapper.MAPPER.toAreaDto(areaService.saveArea(areaDto)));
    }

    @Operation(summary = "Update area",
            description = "This can only be done by manager.",
            tags = {"area-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AreaDto.class))),
            @ApiResponse(responseCode = "404", description = "Area not found")})
    @PutMapping("/areas/{id}")
    public ResponseEntity<AreaDto> updateArea(@PathVariable("id") long areaId,
                                              @RequestBody @Valid AreaDto newAreaDto,
                                              @RequestHeader(required = false)
                                                  String authorization) {
        SecurityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(AreaMapper.MAPPER.toAreaDto(areaService.updateArea(areaId, newAreaDto)));
    }

    @Operation(summary = "Get area by id",
            description = "This can only be done by manager.",
            tags = {"area-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AreaDto.class))),
            @ApiResponse(responseCode = "404", description = "Area not found")})
    @GetMapping("/areas/{id}")
    public ResponseEntity<AreaDto> getAreaById(@PathVariable("id") long areaId,
                                               @RequestHeader(required = false)
                                               String authorization) {
        SecurityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(AreaMapper.MAPPER.toAreaDto(areaService.getAreaById(areaId)));
    }
}
