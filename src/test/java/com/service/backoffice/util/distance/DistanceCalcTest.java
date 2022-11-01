package com.service.backoffice.util.distance;

import static org.junit.jupiter.api.Assertions.*;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Coordinates;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DistanceCalcTest {

    private final Area area1 = new Area(List.of(new Coordinates(1L, 0, 2),
            new Coordinates(2L, 0, 0),
            new Coordinates(3L, 2, 0)));
    private final Area area2 = new Area(List.of(new Coordinates(1L, 10, 12),
            new Coordinates(2L, 10, 10),
            new Coordinates(3L, 12, 12)));
    private final Area area3 = new Area(List.of(new Coordinates(1L, 10, 0),
            new Coordinates(2L, 12, 0),
            new Coordinates(3L, 12, 2)));

    CoordinatesDto userCoordinates = new CoordinatesDto(0,0);

    @Test
    void sortByDistanceFromUser() {
        List<Area> sortedAreas = new LinkedList<>();
        sortedAreas.add(area1);
        sortedAreas.add(area2);
        sortedAreas.add(area3);
        List<Area> expected = List.of(area1, area3, area2);
       sortedAreas =
                DistanceCalc.sortByDistanceFromUser(sortedAreas, userCoordinates);
        Assertions.assertIterableEquals(expected,sortedAreas);

    }
}