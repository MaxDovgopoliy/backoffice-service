package com.service.backoffice.util.distance;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Area;
import java.util.Collections;
import java.util.List;

public class DistanceCalc {
    public static List<Area> sortByDistanceFromUser(List<Area> areas,
                                                    CoordinatesDto userCoordinates) {
        Collections.sort(areas, new AreaDistanceFromUserComparator(userCoordinates));
        return areas;
    }
}
