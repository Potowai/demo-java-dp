package org.sebsy.grasps;

import junit.framework.TestCase;
import org.junit.Test;
import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.IClientDao;
import org.sebsy.grasps.daos.ITypeReservationDao;
import org.sebsy.grasps.services.IReservationService;
import org.sebsy.grasps.services.ReservationService;

import static org.junit.Assert.assertEquals;

public class ReservationControllerTest extends TestCase {

    private static final double DELTA = 0.0000001;

    private static final Client CLIENT_PREMIUM = new Client("1", true);
    private static final Client CLIENT_NON_PREMIUM = new Client("3", false);
    private static final TypeReservation TYPE_THEATRE = new TypeReservation("TH", 150.0, 15.0);
    private static final TypeReservation TYPE_CINEMA = new TypeReservation("CI", 10.9, 0.0);

    private ReservationController buildController(Client client, TypeReservation type) {
        IClientDao clientDao = id -> client;
        ITypeReservationDao typeReservationDao = t -> type;
        IReservationService service = new ReservationService(clientDao, typeReservationDao);
        return new ReservationController(service);
    }

    @Test
    public void testCreerReservationTheatrePremium() {
        ReservationController controller = buildController(CLIENT_PREMIUM, TYPE_THEATRE);

        Params params = new Params();
        params.setDateReservation("20/11/2020 19:55:00");
        params.setNbPlaces(3);
        params.setIdentifiantClient("1");
        params.setTypeReservation("TH");

        Reservation reservation = controller.creerReservation(params);
        assertEquals(382.5, reservation.getTotal(), DELTA);
    }

    @Test
    public void testCreerReservationTheatreNonPremium() {
        ReservationController controller = buildController(CLIENT_NON_PREMIUM, TYPE_THEATRE);

        Params params = new Params();
        params.setDateReservation("20/11/2020 19:55:00");
        params.setNbPlaces(3);
        params.setIdentifiantClient("3");
        params.setTypeReservation("TH");

        Reservation reservation = controller.creerReservation(params);
        assertEquals(450.0, reservation.getTotal(), DELTA);
    }

    @Test
    public void testCreerReservationCinemaPremium() {
        ReservationController controller = buildController(CLIENT_PREMIUM, TYPE_CINEMA);

        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(4);
        params.setIdentifiantClient("2");
        params.setTypeReservation("CI");

        Reservation reservation = controller.creerReservation(params);
        assertEquals(43.6, reservation.getTotal(), DELTA);
    }

    @Test
    public void testCreerReservationCinemaNonPremium() {
        ReservationController controller = buildController(CLIENT_NON_PREMIUM, TYPE_CINEMA);

        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(4);
        params.setIdentifiantClient("3");
        params.setTypeReservation("CI");

        Reservation reservation = controller.creerReservation(params);
        assertEquals(43.6, reservation.getTotal(), DELTA);
    }
}
