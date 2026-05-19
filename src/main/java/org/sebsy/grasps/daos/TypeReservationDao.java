package org.sebsy.grasps.daos;

import org.sebsy.grasps.beans.TypeReservation;

import java.util.List;
import java.util.Optional;

public class TypeReservationDao implements ITypeReservationDao {

    private static TypeReservation[] types = {new TypeReservation("TH", 150.0, 15.0), new TypeReservation("CI", 10.9, 0.0)};

    @Override
    public TypeReservation extraireTypeReservation(String type) {
        return List.of(types).stream()
                .filter(t -> t.getType().equals(type))
                .findAny()
                .orElse(null);
    }
}
