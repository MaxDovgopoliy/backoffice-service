package com.service.backoffice.util;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Coordinates;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import java.util.Comparator;
import java.util.List;

public class AreaDistanceFromUserComparator implements Comparator<Area> {
    private static CoordinatesDto userCoordinates;

    public AreaDistanceFromUserComparator(CoordinatesDto userCoordinates) {
        this.userCoordinates = userCoordinates;
    }

    @Override
    public int compare(Area area1, Area area2) {

        return Double.compare(getDistance(area1), getDistance(area2));
    }

    public static double getDistance(Area area) {
        final GeometryFactory gf = new GeometryFactory();
        List<Coordinates> areasCoordinates = area.getListOfCoordinates();

        Coordinate[] c = new Coordinate[areasCoordinates.size() + 1];
        for (int i = 0; i < areasCoordinates.size(); i++) {
            c[i] = new Coordinate(areasCoordinates.get(i).getLatitude(),
                    areasCoordinates.get(i).getLongitude());
        }
        c[c.length - 1] = new Coordinate(areasCoordinates.get(0).getLatitude(),
                areasCoordinates.get(0).getLongitude());

        Geometry geo = gf.createPolygon(c);

        Point p = gf.createPoint(new Coordinate(userCoordinates.getLatitude(),
                userCoordinates.getLongitude()));

        double distance = geo.distance(p);
        return distance;
    }
}
