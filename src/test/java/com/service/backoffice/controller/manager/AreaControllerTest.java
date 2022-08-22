package com.service.backoffice.controller.manager;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.services.AreaService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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


    private static List<Coordinates> listOfCoordinates=List.of(new Coordinates(1L,123.32,234.32),
                                                new Coordinates(1L,123.32,234.32),
                                                new Coordinates(1L,123.32,234.32));
    private static Area area = new Area(1L, "Ukraine", "Lviv",listOfCoordinates );
    private static AreaDto areaDto = AreaMapper.MAPPER.toAreaDto(area);

    @Test
    void deleteArea() throws Exception {
        when(areaService.deleteArea(1)).thenReturn(true);
        mockMvc.perform(delete("/manager/areas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(areaService).deleteArea(1);
    }

    @Test
    void addArea() throws Exception {

        when(areaService.saveArea(areaDto))
                .thenReturn(area);

        mockMvc.perform(post("/manager/areas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(areaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value(areaDto.getCountry()))
                .andExpect(jsonPath("$.city").value(areaDto.getCity()))
                .andExpect(jsonPath("$.coordinates.size()").value(areaDto.getCoordinates().size()));

        verify(areaService).saveArea(areaDto);
    }

    @Test
    void updateArea() throws Exception {

        when(areaService.updateArea(1, areaDto)).thenReturn(area);

        mockMvc.perform(put("/manager/areas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(areaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value(areaDto.getCountry()))
                .andExpect(jsonPath("$.city").value(areaDto.getCity()))
                .andExpect(jsonPath("$.coordinates.size()").value(areaDto.getCoordinates().size()));

        verify(areaService).updateArea(1,areaDto);
    }

    @Test
    void getAreaById() throws Exception {
        given(areaService.getAreaById(1)).willReturn(area);

        mockMvc.perform(get("/manager/areas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value(areaDto.getCountry()))
                .andExpect(jsonPath("$.city").value(areaDto.getCity()))
                .andExpect(jsonPath("$.coordinates.size()").value(areaDto.getCoordinates().size()));

        verify(areaService).getAreaById(1);
    }
}