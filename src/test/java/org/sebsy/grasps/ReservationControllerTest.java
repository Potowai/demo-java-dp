package org.sebsy.grasps;

import org.junit.Test;
import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.IClientDao;
import org.sebsy.grasps.daos.ITypeReservationDao;
import org.sebsy.grasps.services.IReservationService;
import org.sebsy.grasps.services.ReservationService;

import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ReservationControllerTest {

    private static final Logger LOG = Logger.getLogger(ReservationControllerTest.class.getName());
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

    // =============================================
    // CAS PASSANTS
    // =============================================

    @Test
    public void testCreerReservationTheatrePremium() {
        LOG.info("-- CAS PASSANT : Theatre x Client premium (reduction 15%) --");

        ReservationController controller = buildController(CLIENT_PREMIUM, TYPE_THEATRE);
        Params params = new Params();
        params.setDateReservation("20/11/2020 19:55:00");
        params.setNbPlaces(3);
        params.setIdentifiantClient("1");
        params.setTypeReservation("TH");

        Reservation reservation = controller.creerReservation(params);

        double attendu = 150.0 * 3 * (1 - 15.0 / 100.0);
        assertEquals(attendu, reservation.getTotal(), DELTA);
        LOG.info("OK Total = " + reservation.getTotal() + " (attendu " + attendu + ")");
    }

    @Test
    public void testCreerReservationTheatreNonPremium() {
        LOG.info("-- CAS PASSANT : Theatre x Client non premium (pas de reduction) --");

        ReservationController controller = buildController(CLIENT_NON_PREMIUM, TYPE_THEATRE);
        Params params = new Params();
        params.setDateReservation("20/11/2020 19:55:00");
        params.setNbPlaces(3);
        params.setIdentifiantClient("3");
        params.setTypeReservation("TH");

        Reservation reservation = controller.creerReservation(params);

        double attendu = 150.0 * 3;
        assertEquals(attendu, reservation.getTotal(), DELTA);
        LOG.info("OK Total = " + reservation.getTotal() + " (attendu " + attendu + ")");
    }

    @Test
    public void testCreerReservationCinemaPremium() {
        LOG.info("-- CAS PASSANT : Cinema x Client premium (reduction 0%) --");

        ReservationController controller = buildController(CLIENT_PREMIUM, TYPE_CINEMA);
        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(4);
        params.setIdentifiantClient("2");
        params.setTypeReservation("CI");

        Reservation reservation = controller.creerReservation(params);

        double attendu = 10.9 * 4;
        assertEquals(attendu, reservation.getTotal(), DELTA);
        LOG.info("OK Total = " + reservation.getTotal() + " (attendu " + attendu + ")");
    }

    @Test
    public void testCreerReservationCinemaNonPremium() {
        LOG.info("-- CAS PASSANT : Cinema x Client non premium --");

        ReservationController controller = buildController(CLIENT_NON_PREMIUM, TYPE_CINEMA);
        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(4);
        params.setIdentifiantClient("3");
        params.setTypeReservation("CI");

        Reservation reservation = controller.creerReservation(params);

        double attendu = 10.9 * 4;
        assertEquals(attendu, reservation.getTotal(), DELTA);
        LOG.info("OK Total = " + reservation.getTotal() + " (attendu " + attendu + ")");
    }

    @Test
    public void testCreerReservationNbPlacesZero() {
        LOG.info("-- CAS PASSANT : 0 place -> total a 0 --");

        ReservationController controller = buildController(CLIENT_NON_PREMIUM, TYPE_THEATRE);
        Params params = new Params();
        params.setDateReservation("20/11/2020 19:55:00");
        params.setNbPlaces(0);
        params.setIdentifiantClient("3");
        params.setTypeReservation("TH");

        Reservation reservation = controller.creerReservation(params);

        assertEquals(0.0, reservation.getTotal(), DELTA);
        assertEquals(0, reservation.getNbPlaces());
        LOG.info("OK Total = " + reservation.getTotal() + " pour " + reservation.getNbPlaces() + " places");
    }

    @Test
    public void testReservationAjouteeAuClient() {
        LOG.info("-- CAS PASSANT : la reservation est liee au client --");

        Client client = new Client("1", true);
        IClientDao clientDao = id -> client;
        ITypeReservationDao typeReservationDao = t -> TYPE_CINEMA;
        IReservationService service = new ReservationService(clientDao, typeReservationDao);
        ReservationController controller = new ReservationController(service);

        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(2);
        params.setIdentifiantClient("1");
        params.setTypeReservation("CI");

        Reservation reservation = controller.creerReservation(params);

        assertNotNull("La reservation doit avoir un client", reservation.getClient());
        assertEquals("1", reservation.getClient().getIdentifiantClient());
        assertTrue("Le client doit avoir la reservation dans sa liste",
                client.getReservations().contains(reservation));
        LOG.info("OK Reservation liee au client " + reservation.getClient().getIdentifiantClient());
    }

    // =============================================
    // CAS NON PASSANTS
    // =============================================

    @Test(expected = NullPointerException.class)
    public void testClientInexistant() {
        LOG.info("-- CAS NON PASSANT : client inexistant -> NullPointerException --");

        IClientDao clientDao = id -> null;
        ITypeReservationDao typeDao = t -> TYPE_CINEMA;
        IReservationService service = new ReservationService(clientDao, typeDao);
        ReservationController controller = new ReservationController(service);

        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(2);
        params.setIdentifiantClient("INCONNU");
        params.setTypeReservation("CI");

        controller.creerReservation(params);
    }

    @Test(expected = NullPointerException.class)
    public void testTypeInexistant() {
        LOG.info("-- CAS NON PASSANT : type reservation inexistant -> NullPointerException --");

        IClientDao clientDao = id -> CLIENT_PREMIUM;
        ITypeReservationDao typeDao = t -> null;
        IReservationService service = new ReservationService(clientDao, typeDao);
        ReservationController controller = new ReservationController(service);

        Params params = new Params();
        params.setDateReservation("21/11/2020 20:30:00");
        params.setNbPlaces(2);
        params.setIdentifiantClient("1");
        params.setTypeReservation("XX");

        controller.creerReservation(params);
    }

    @Test(expected = DateTimeParseException.class)
    public void testDateInvalide() {
        LOG.info("-- CAS NON PASSANT : format de date invalide -> DateTimeParseException --");

        IClientDao clientDao = id -> CLIENT_PREMIUM;
        ITypeReservationDao typeDao = t -> TYPE_CINEMA;
        IReservationService service = new ReservationService(clientDao, typeDao);
        ReservationController controller = new ReservationController(service);

        Params params = new Params();
        params.setDateReservation("2020-11-20 19:55");
        params.setNbPlaces(2);
        params.setIdentifiantClient("1");
        params.setTypeReservation("CI");

        controller.creerReservation(params);
    }
}
