package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Area;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.util.LocationAdaptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepo areaRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private LocationAdaptor locationAdaptor;

    @Override
    public List<Area> getAllAreas(String countryName, String cityName) {
        List<Area> allAreas = areaRepo.findAll();
        if (countryName != null) {
            allAreas = allAreas
                    .stream()
                    .filter(area -> area.getCity().getCountry().getName()
                    .equalsIgnoreCase(countryName))
                    .collect(Collectors.toList());
            if (cityName != null) {
                allAreas = allAreas
                        .stream()
                        .filter(area -> area.getCity().getName()
                        .equalsIgnoreCase(cityName))
                        .collect(Collectors.toList());
            }
        }
        return allAreas;
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
    public Area saveArea(AreaDto areaDto) {
        Area area = new Area();
        area = locationAdaptor.makeAreaFromDto(area, areaDto);
        return areaRepo.save(area);
    }

    @Override
    public Area updateArea(long areaId, AreaDto newAreaDto) {
        Area oldArea;
        if (!areaRepo.findById(areaId).isPresent()) {
            throw new ApiException(Exceptions.AREA_NOT_FOUND);
        }

        oldArea = areaRepo.findById(areaId).get();

        oldArea = locationAdaptor.makeAreaFromDto(oldArea, newAreaDto);
        return areaRepo.save(oldArea);

    }

    @Override
    public Area getAreaById(long areaId) {
        Optional<Area> foundArea = areaRepo.findById(areaId);
        if (foundArea.isPresent()) {
            return foundArea.get();
        }
        throw new ApiException(Exceptions.AREA_NOT_FOUND);
    }
}
