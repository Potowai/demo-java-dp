package org.sebsy.grasps;

import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.services.IReservationService;

public class ReservationController {

    private IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Reservation creerReservation(Params params) {
        return reservationService.creerReservation(params);
    }
}
