package com.service.backoffice.services.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.util.LocationAdaptor;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AreaServiceImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AreaService areaService;
    @MockBean
    AreaRepo areaRepo;
    @MockBean
    LocationAdaptor locationAdaptor;
    private static Country country = new Country("Ukraine", 10000);
    private static City city =new City("Lviv",500,country);
    private static List<Area> areas =
            List.of(new Area(240, "Shevchenka str. 21", city),
                    new Area(240, "Shevchenka str. 22", city),
                    new Area(240, "Shevchenka str. 23", city));
    @Test
    void getAllAreas() {
        List<Area> expectedAreas = areas;

        when(areaRepo.findAll()).thenReturn(areas);

        var resultAreas = areaService.getAllAreas();

        verify(areaRepo).findAll();
        assertNotNull(resultAreas);
        assertIterableEquals(expectedAreas, resultAreas);
    }

    @Test
    void deleteArea() {
        doAnswer((i)-> null).when(areaRepo).deleteById(1L);
        boolean result=areaService.deleteArea(1L);

        assertEquals(result, true);
    }
    @Test
    void deleteAreaByNonExistingId() {
        doThrow(new EmptyResultDataAccessException(1)).when(areaRepo).deleteById(1L);
        boolean result=areaService.deleteArea(1L);

        assertEquals(result, false);
    }
    @Test
    void saveArea() {
        Area areaToSave = areas.get(0);
        AreaDto areaDtoToSave = AreaMapper.MAPPER.toAreaDto(areaToSave);

        when(locationAdaptor.makeAreaFromDto(any(Area.class),any(AreaDto.class))).thenReturn(areaToSave);
        when(areaRepo.save(areaToSave)).thenReturn(areaToSave);

        var resultAreaDto = areaService.saveArea(areaDtoToSave);

        verify(areaRepo).save(areaToSave);
        assertNotNull(resultAreaDto);
        assertEquals(areaToSave, resultAreaDto);
    }

    @Test
    void updateArea() {
        Area areaToUpdate= areas.get(0);
        AreaDto newAreaDto= AreaMapper.MAPPER.toAreaDto(areas.get(1));
        Area expectedArea= areas.get(1);

        when(areaRepo.findById(1L)).thenReturn(Optional.of(areaToUpdate));
        when(locationAdaptor.makeAreaFromDto(any(Area.class),any(AreaDto.class))).thenReturn(expectedArea);
        when(areaRepo.save(expectedArea)).thenReturn(expectedArea);

        var resultArea = areaService.updateArea(1, newAreaDto);

        verify(areaRepo,times(2)).findById(1L);
        verify(areaRepo).save(expectedArea);
        verify(locationAdaptor).makeAreaFromDto(any(Area.class),any(AreaDto.class));
        assertNotNull(resultArea);
        assertEquals(expectedArea, resultArea);
    }
    @Test
    void updateAreaWithNonExistingId() {
        AreaDto newAreaDto= AreaMapper.MAPPER.toAreaDto(areas.get(1));
        when(areaRepo.findById(1L)).thenReturn(Optional.empty());


        var apiException = assertThrows(ApiException.class,
                () -> areaService.updateArea(1, newAreaDto));

        //then
        assertEquals(Exceptions.AREA_NOT_FOUND, apiException.getException());
    }
    @Test
    void getAreaById() {
        Area area= areas.get(0);
        when(areaRepo.findById(1L)).thenReturn(Optional.of(area));

        var resultAreaDto = areaService.getAreaById(1);

        verify(areaRepo).findById(1L);
        assertNotNull(resultAreaDto);
        assertEquals(area, resultAreaDto);
    }
    @Test
    void getAreaByNonExistingId() {
        //when
        var apiException = assertThrows(ApiException.class, () -> areaService.getAreaById(1));

        //then
        assertEquals(Exceptions.AREA_NOT_FOUND, apiException.getException());
    }
}