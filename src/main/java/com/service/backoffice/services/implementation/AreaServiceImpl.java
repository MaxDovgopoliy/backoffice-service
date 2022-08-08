package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.exeption.ApiException;
import com.service.backoffice.exeption.Exceptions;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CoordinatesMapper;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepo areaRepo;
    @Override
    public List<AreaDTO> getAllAreas() {
        return AreaMapper.MAPPER.toAreaDTOs(areaRepo.findAll());
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
    public AreaDTO saveArea(AreaDTO areaDTO) {
        Area area = areaRepo.save(AreaMapper.MAPPER.toArea(areaDTO));
        return AreaMapper.MAPPER.toAreaDTO(area);
    }

    @Override
    public AreaDTO updateArea(long areaId, AreaDTO newAreaDTO) {
        Area oldArea;
        if(!areaRepo.findById(areaId).isPresent()){
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
        }
        oldArea=areaRepo.findById(areaId).get();

        oldArea.setCountry(newAreaDTO.getCountry());
        oldArea.setCity(newAreaDTO.getCity());
        oldArea.setListOfCoordinates(CoordinatesMapper.MAPPER.toCoordinates(newAreaDTO.getCoordinatesDTOList()));

        AreaDTO areaDTO= AreaMapper.MAPPER.toAreaDTO(areaRepo.save(oldArea));
        return areaDTO;
    }
    @Override
    public AreaDTO getAreaById(long areaId) {
        Optional<Area> foundArea = areaRepo.findById(areaId);
        if (foundArea.isPresent()) {
            return AreaMapper.MAPPER.toAreaDTO(foundArea.get());
        }
        throw new ApiException(Exceptions.AREA_NOT_FOUND);
    }
}
