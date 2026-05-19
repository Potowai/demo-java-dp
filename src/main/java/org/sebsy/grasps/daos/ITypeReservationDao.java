package org.sebsy.grasps.daos;

import org.sebsy.grasps.beans.TypeReservation;

public interface ITypeReservationDao {
    TypeReservation extraireTypeReservation(String type);
}
