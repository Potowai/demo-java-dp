package org.sebsy.grasps.services;

import org.sebsy.grasps.Params;
import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.IClientDao;
import org.sebsy.grasps.daos.ITypeReservationDao;
import org.sebsy.grasps.utils.DateUtils;

import java.time.LocalDateTime;

public class ReservationService implements IReservationService {

    private IClientDao clientDao;
    private ITypeReservationDao typeReservationDao;

    public ReservationService(IClientDao clientDao, ITypeReservationDao typeReservationDao) {
        this.clientDao = clientDao;
        this.typeReservationDao = typeReservationDao;
    }

    @Override
    public Reservation creerReservation(Params params) {
        LocalDateTime dateReservation = DateUtils.toDate(params.getDateReservation());
        Client client = clientDao.extraireClient(params.getIdentifiantClient());
        TypeReservation type = typeReservationDao.extraireTypeReservation(params.getTypeReservation());
        return client.creerReservation(dateReservation, params.getNbPlaces(), type);
    }
}
