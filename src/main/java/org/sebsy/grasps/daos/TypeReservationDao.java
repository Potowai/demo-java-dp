package org.sebsy.grasps.daos;

import org.sebsy.grasps.beans.TypeReservation;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TypeReservationDao implements ITypeReservationDao {

    private static final Logger LOG = Logger.getLogger(TypeReservationDao.class.getName());

    private static TypeReservation[] types = {new TypeReservation("TH", 150.0, 15.0), new TypeReservation("CI", 10.9, 0.0)};

    @Override
    public TypeReservation extraireTypeReservation(String type) {
        LOG.info("Recherche type réservation par code=" + type);
        TypeReservation result = List.of(types).stream()
                .filter(t -> t.getType().equals(type))
                .findAny()
                .orElse(null);
        LOG.info("Résultat type : " + (result != null ? result.getType() + " montant=" + result.getMontant() + " réduction=" + result.getReductionPourcent() : "null"));
        return result;
    }
}
