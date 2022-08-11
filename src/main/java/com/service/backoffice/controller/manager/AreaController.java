package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.services.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class AreaController {
    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @DeleteMapping("/area/{id}")
    public boolean deleteArea(@PathVariable long id) {
        return areaService.deleteArea(id);
    }

//    @Operation(summary = "Add area", description = "This can only be done by the logged in user.", tags = {"area"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AreaDTO.class)))})
    @PostMapping("/area/add")
    public AreaDTO addArea(@RequestBody @Valid AreaDTO areaDTO) {
        return areaService.saveArea(areaDTO);

    }

    @PutMapping("/update/area/{id}")
    public AreaDTO updateArea(@PathVariable("id") long areaId, @RequestBody @Valid AreaDTO newAreaDTO) {
        return areaService.updateArea(areaId, newAreaDTO);

    }

    @GetMapping("/area/{id}")
    public AreaDTO getAreaById(@PathVariable("id") long areaId) {
        return areaService.getAreaById(areaId);

    }
}
