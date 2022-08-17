package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.services.AreaService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @DeleteMapping("/area/{id}")
    public boolean deleteArea(@PathVariable long id) {
        return areaService.deleteArea(id);
    }

    @PostMapping("/area/add")
    public AreaDto addArea(@RequestBody @Valid AreaDto areaDto) {
        return areaService.saveArea(areaDto);

    }

    @PutMapping("/update/area/{id}")
    public AreaDto updateArea(@PathVariable("id") long areaId,
                              @RequestBody @Valid AreaDto newAreaDto) {
        return areaService.updateArea(areaId, newAreaDto);

    }

    @GetMapping("/area/{id}")
    public AreaDto getAreaById(@PathVariable("id") long areaId) {
        return areaService.getAreaById(areaId);

    }
}
