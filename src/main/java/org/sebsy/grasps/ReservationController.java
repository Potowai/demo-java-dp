package org.sebsy.grasps;

import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.services.IReservationService;

import java.util.logging.Logger;

public class ReservationController {

    private static final Logger LOG = Logger.getLogger(ReservationController.class.getName());

    private IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Reservation creerReservation(Params params) {
        LOG.info("ReservationController.creerReservation appele");
        Reservation reservation = reservationService.creerReservation(params);
        LOG.info("Reservation retournee : total=" + reservation.getTotal());
        return reservation;
    }
}
