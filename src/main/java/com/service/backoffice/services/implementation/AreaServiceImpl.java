package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.exeption.ApiException;
import com.service.backoffice.exeption.Exceptions;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CoordinatesMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.services.AreaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepo areaRepo;

    @Override
    public List<AreaDto> getAllAreas() {
        return AreaMapper.MAPPER.toAreaDtos(areaRepo.findAll());
    }

    @Override
    public boolean deleteArea(long id) {
        try {
            areaRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public AreaDto saveArea(AreaDto areaDto) {
        Area area = areaRepo.save(AreaMapper.MAPPER.toArea(areaDto));
        return AreaMapper.MAPPER.toAreaDto(area);
    }

    @Override
    public AreaDto updateArea(long areaId, AreaDto newAreaDto) {
        Area oldArea;
        if (!areaRepo.findById(areaId).isPresent()) {
            throw new ApiException(Exceptions.AREA_NOT_FOUND);
        }
        oldArea = areaRepo.findById(areaId).get();

        oldArea.setCountry(newAreaDto.getCountry());
        oldArea.setCity(newAreaDto.getCity());
        oldArea.setListOfCoordinates(CoordinatesMapper
                .MAPPER.toListOfCoordinates(newAreaDto.getCoordinates()));

        AreaDto areaDto = AreaMapper.MAPPER.toAreaDto(areaRepo.save(oldArea));
        return areaDto;
    }

    @Override
    public AreaDto getAreaById(long areaId) {
        Optional<Area> foundArea = areaRepo.findById(areaId);
        if (foundArea.isPresent()) {
            return AreaMapper.MAPPER.toAreaDto(foundArea.get());
        }
        throw new ApiException(Exceptions.AREA_NOT_FOUND);
    }
}
