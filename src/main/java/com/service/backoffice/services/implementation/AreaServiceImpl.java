package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.model.Area;
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


}
