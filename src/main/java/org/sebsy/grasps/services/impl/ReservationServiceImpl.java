package org.sebsy.grasps.services.impl;

import org.sebsy.grasps.Params;
import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.Reservation;
import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.IClientDao;
import org.sebsy.grasps.daos.ITypeReservationDao;
import org.sebsy.grasps.services.IReservationService;
import org.sebsy.grasps.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ReservationServiceImpl implements IReservationService {

    private static final Logger LOG = Logger.getLogger(ReservationServiceImpl.class.getName());

    private IClientDao clientDao;
    private ITypeReservationDao typeReservationDao;

    public ReservationServiceImpl(IClientDao clientDao, ITypeReservationDao typeReservationDao) {
        this.clientDao = clientDao;
        this.typeReservationDao = typeReservationDao;
    }

    @Override
    public Reservation creerReservation(Params params) {
        LOG.info("Reception demande reservation : client=" + params.getIdentifiantClient()
                + ", date=" + params.getDateReservation()
                + ", type=" + params.getTypeReservation()
                + ", places=" + params.getNbPlaces());

        try {
            LocalDateTime dateReservation = DateUtils.toDate(params.getDateReservation());
            LOG.info("Date convertie : " + dateReservation);

            Client client = clientDao.extraireClient(params.getIdentifiantClient());
            LOG.info("Client trouve : " + (client != null ? client.getIdentifiantClient() : "null"));
            if (client == null) {
                NullPointerException e = new NullPointerException("Client introuvable : " + params.getIdentifiantClient());
                LOG.severe("Erreur : " + e.getMessage());
                throw e;
            }

            TypeReservation type = typeReservationDao.extraireTypeReservation(params.getTypeReservation());
            LOG.info("Type reservation trouve : " + (type != null ? type.getType() : "null"));
            if (type == null) {
                NullPointerException e = new NullPointerException("Type de reservation introuvable : " + params.getTypeReservation());
                LOG.severe("Erreur : " + e.getMessage());
                throw e;
            }

            return client.creerReservation(dateReservation, params.getNbPlaces(), type);

        } catch (java.time.format.DateTimeParseException e) {
            LOG.severe("Erreur format date : '" + params.getDateReservation() + "' - " + e.getMessage());
            throw e;
        } catch (NullPointerException e) {
            LOG.severe("Erreur donnees manquantes : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            LOG.severe("Erreur inattendue : " + e.getMessage());
            throw e;
        }
    }
}
