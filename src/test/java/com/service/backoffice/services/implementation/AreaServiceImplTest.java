package com.service.backoffice.services.implementation;

import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.services.AreaService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
//    private static List<Area> areas = List.of(new Area(1L, "Ukraine", "Lviv",listOfCoordinates1 ),
//                                              new Area(2L, "Ukraine", "Kyiv",listOfCoordinates2 ));
//
//    @Test
//    void getAllAreas() {
//        List<AreaDto> expectedAreaDtos = AreaMapper.MAPPER.toAreaDtos(areas);
//
//        when(areaRepo.findAll()).thenReturn(areas);
//
//        var resultAreaDtos = areaService.getAllAreas();
//
//        verify(areaRepo).findAll();
//        assertNotNull(resultAreaDtos);
//        assertIterableEquals(expectedAreaDtos, resultAreaDtos);
//    }
//
//    @Test
//    void deleteArea() {
//        doAnswer((i)-> null).when(areaRepo).deleteById(1L);
//        boolean result=areaService.deleteArea(1L);
//
//        assertEquals(result, true);
//    }
//    @Test
//    void deleteAreaByNonExistingId() {
//        doThrow(new EmptyResultDataAccessException(1)).when(areaRepo).deleteById(1L);
//        boolean result=areaService.deleteArea(1L);
//
//        assertEquals(result, false);
//    }
//    @Test
//    void saveArea() {
//        Area areaToSave = areas.get(0);
//        AreaDto areaDtoToSave = AreaMapper.MAPPER.toAreaDto(areaToSave);
//        when(areaRepo.save(areaToSave)).thenReturn(areaToSave);
//
//        var resultAreaDto = areaService.saveArea(areaDtoToSave);
//
//        verify(areaRepo).save(areaToSave);
//        assertNotNull(resultAreaDto);
//        assertEquals(areaDtoToSave, resultAreaDto);
//    }
//
//    @Test
//    void updateArea() {
//        Area areaToUpdate= areas.get(0);
//        AreaDto newAreaDto= AreaMapper.MAPPER.toAreaDto(areas.get(1));
//        Area expectedArea= new Area(areas.get(0).getId(),areas.get(1).getCountry(),areas.get(1).getCity(),areas.get(1).getListOfCoordinates());
//
//        when(areaRepo.findById(1L)).thenReturn(Optional.of(areaToUpdate));
//        when(areaRepo.save(expectedArea)).thenReturn(expectedArea);
//
//        var resultAreaDto = areaService.updateArea(1, newAreaDto);
//
//        verify(areaRepo,times(2)).findById(1L);
//        verify(areaRepo).save(expectedArea);
//        assertNotNull(resultAreaDto);
//        assertEquals(AreaMapper.MAPPER.toAreaDto(expectedArea), resultAreaDto);
//    }
//    @Test
//    void updateAreaWithNonExistingId() {
//        AreaDto newAreaDto= AreaMapper.MAPPER.toAreaDto(areas.get(1));
//        when(areaRepo.findById(1L)).thenReturn(Optional.empty());
//
//
//        var apiException = assertThrows(ApiException.class,
//                () -> areaService.updateArea(1, newAreaDto));
//
//        //then
//        assertEquals(Exceptions.AREA_NOT_FOUND, apiException.getException());
//    }
//    @Test
//    void getAreaById() {
//        AreaDto expectedTariffDto= AreaMapper.MAPPER.toAreaDto(areas.get(0));
//        when(areaRepo.findById(1L)).thenReturn(Optional.of(areas.get(0)));
//
//        var resultAreaDto = areaService.getAreaById(1);
//
//        verify(areaRepo).findById(1L);
//        assertNotNull(resultAreaDto);
//        assertEquals(expectedTariffDto, resultAreaDto);
//    }
//    @Test
//    void getAreaByNonExistingId() {
//        //when
//        var apiException = assertThrows(ApiException.class, () -> areaService.getAreaById(1));
//
//        //then
//        assertEquals(Exceptions.AREA_NOT_FOUND, apiException.getException());
//    }
}