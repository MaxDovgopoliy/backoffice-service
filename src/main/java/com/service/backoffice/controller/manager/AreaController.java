package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.services.AreaService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/area/add")
    public AreaDTO addArea(@RequestBody AreaDTO areaDTO) {
        return areaService.saveArea(areaDTO);

    }
    @PutMapping("/update/area/{id}")
    public AreaDTO updateArea(@PathVariable("id") long areaId, @RequestBody AreaDTO newAreaDTO) {
        return areaService.updateArea(areaId, newAreaDTO);

    }
    @GetMapping("/area/{id}")
    public AreaDTO getAreaById(@PathVariable("id") long areaId) {
        return areaService.getAreaById(areaId);

    }
}
