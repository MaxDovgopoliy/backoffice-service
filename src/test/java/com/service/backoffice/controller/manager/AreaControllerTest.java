package com.service.backoffice.controller.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.services.AreaService;
import org.junit.jupiter.api.Test;
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
class AreaControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AreaController AreaController;

    @MockBean
    AreaService areaService;

//    private static Country country = new Country("Ukraine");
//    private static City city =new City("Lviv",500,country);
//    private static List<Area> areas =
//            List.of(new Area(240, new Address("Shevchenka",21), city),
//                    new Area(240, new Address("Shevchenka",22), city),
//                    new Area(240, new Address("Shevchenka",23), city));
//
//    private static AreaDto areaDto = AreaMapper.MAPPER.toAreaDto(areas.get(0));

    @Test
    void deleteArea() throws Exception {
//        when(areaService.deleteArea(1)).thenReturn(true);
//        mockMvc.perform(delete("/manager/areas/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//        verify(areaService).deleteArea(1);
    }

    @Test
    void addArea() throws Exception {

//        when(areaService.saveArea(areaDto))
//                .thenReturn(areas.get(0));
//
//        mockMvc.perform(post("/manager/areas")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(areaDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.countryName").value(areaDto.getCountryName()))
//                .andExpect(jsonPath("$.cityName").value(areaDto.getCityName()))
//                .andExpect(jsonPath("$.square").value(areaDto.getSquare()))
//                .andExpect(jsonPath("$.address").value(areaDto.getAddress()));
//
//        verify(areaService).saveArea(areaDto);
    }

    @Test
    void updateArea() throws Exception {

//        when(areaService.updateArea(1, areaDto)).thenReturn(areas.get(0));
//
//        mockMvc.perform(put("/manager/areas/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(areaDto)))
//                .andExpect(jsonPath("$.countryName").value(areaDto.getCountryName()))
//                .andExpect(jsonPath("$.cityName").value(areaDto.getCityName()))
//                .andExpect(jsonPath("$.square").value(areaDto.getSquare()))
//                .andExpect(jsonPath("$.address").value(areaDto.getAddress()));
//        verify(areaService).updateArea(1, areaDto);
    }

    @Test
    void getAreaById() throws Exception {
//        given(areaService.getAreaById(1)).willReturn(areas.get(0));
//
//        mockMvc.perform(get("/manager/areas/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.countryName").value(areaDto.getCountryName()))
//                .andExpect(jsonPath("$.cityName").value(areaDto.getCityName()))
//                .andExpect(jsonPath("$.square").value(areaDto.getSquare()))
//                .andExpect(jsonPath("$.address").value(areaDto.getAddress()));
//        verify(areaService).getAreaById(1);
    }
}