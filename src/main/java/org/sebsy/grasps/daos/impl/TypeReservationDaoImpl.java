package org.sebsy.grasps.daos.impl;

import org.sebsy.grasps.beans.TypeReservation;
import org.sebsy.grasps.daos.ITypeReservationDao;

import java.util.List;
import java.util.logging.Logger;

public class TypeReservationDaoImpl implements ITypeReservationDao {

    private static final Logger LOG = Logger.getLogger(TypeReservationDaoImpl.class.getName());

    private static TypeReservation[] types = {new TypeReservation("TH", 150.0, 15.0), new TypeReservation("CI", 10.9, 0.0)};

    @Override
    public TypeReservation extraireTypeReservation(String type) {
        LOG.info("Recherche type reservation par code=" + type);
        TypeReservation result = List.of(types).stream()
                .filter(t -> t.getType().equals(type))
                .findAny()
                .orElse(null);
        LOG.info("Resultat type : " + (result != null ? result.getType() + " montant=" + result.getMontant() + " reduction=" + result.getReductionPourcent() : "null"));
        return result;
    }
}
