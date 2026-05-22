package org.sebsy.grasps.services;

import org.sebsy.grasps.Params;
import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.IClientDao;
import org.sebsy.grasps.daos.ITypeReservationDao;
import org.sebsy.grasps.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ReservationService implements IReservationService {

    private static final Logger LOG = Logger.getLogger(ReservationService.class.getName());

    private IClientDao clientDao;
    private ITypeReservationDao typeReservationDao;

    public ReservationService(IClientDao clientDao, ITypeReservationDao typeReservationDao) {
        this.clientDao = clientDao;
        this.typeReservationDao = typeReservationDao;
    }

    @Override
    public Reservation creerReservation(Params params) {
        LOG.info("Reception demande reservation : client=" + params.getIdentifiantClient()
                + ", date=" + params.getDateReservation()
                + ", type=" + params.getTypeReservation()
                + ", places=" + params.getNbPlaces());

        LocalDateTime dateReservation = DateUtils.toDate(params.getDateReservation());
        LOG.info("Date convertie : " + dateReservation);

        Client client = clientDao.extraireClient(params.getIdentifiantClient());
        LOG.info("Client trouve : " + (client != null ? client.getIdentifiantClient() : "null"));

        TypeReservation type = typeReservationDao.extraireTypeReservation(params.getTypeReservation());
        LOG.info("Type reservation trouve : " + (type != null ? type.getType() : "null"));

        return client.creerReservation(dateReservation, params.getNbPlaces(), type);
    }
}
