package org.sebsy.grasps;

import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.ClientDao;
import org.sebsy.grasps.daos.TypeReservationDao;
import org.sebsy.grasps.services.PricingService;
import org.sebsy.grasps.utils.DateConverter;

import java.time.LocalDateTime;

/**
 * Controller (GRASPS) : point d'entrée pour les événements système.
 * Délègue les responsabilités spécialisées aux services (haute cohésion)
 * et reçoit ses dépendances par constructeur (faible couplage).
 */
public class ReservationController {

    private final DateConverter dateConverter;
    private final PricingService pricingService;
    private final ClientDao clientDao;
    private final TypeReservationDao typeReservationDao;

    public ReservationController() {
        this(new DateConverter(), new PricingService(), new ClientDao(), new TypeReservationDao());
    }

    public ReservationController(DateConverter dateConverter, PricingService pricingService,
                                 ClientDao clientDao, TypeReservationDao typeReservationDao) {
        this.dateConverter = dateConverter;
        this.pricingService = pricingService;
        this.clientDao = clientDao;
        this.typeReservationDao = typeReservationDao;
    }

    public Reservation creerReservation(Params params) {
        String identifiantClient = params.getIdentifiantClient();
        LocalDateTime dateReservation = dateConverter.toLocalDateTime(params.getDateReservation());
        String typeReservation = params.getTypeReservation();
        int nbPlaces = params.getNbPlaces();

        Client client = clientDao.extraireClient(identifiantClient);
        TypeReservation type = typeReservationDao.extraireTypeReservation(typeReservation);

        Reservation reservation = new Reservation(dateReservation);
        reservation.setNbPlaces(nbPlaces);
        reservation.setClient(client);

        // Information Expert + Law of Demeter : le Client gère sa propre liste
        client.addReservation(reservation);

        // Pure Fabrication : délégation du calcul à PricingService
        double total = pricingService.calculerTotal(type, nbPlaces, client);
        reservation.setTotal(total);

        return reservation;
    }
}
