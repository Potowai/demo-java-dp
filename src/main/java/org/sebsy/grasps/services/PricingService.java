package org.sebsy.grasps.services;

import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.beans.TypeReservation;

/**
 * Pure Fabrication / Information Expert :
 * Centralise le calcul du montant total en utilisant les données
 * de TypeReservation (montant, réduction) et de Client (premium).
 */
public class PricingService {

    public double calculerTotal(TypeReservation type, int nbPlaces, Client client) {
        double total = type.getMontant() * nbPlaces;
        if (client.isPremium()) {
            return total * (1 - type.getReductionPourcent() / 100.0);
        }
        return total;
    }
}
