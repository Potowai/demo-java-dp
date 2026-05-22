package org.sebsy.grasps.daos.impl;

import org.sebsy.grasps.beans.Client;
import org.sebsy.grasps.daos.IClientDao;

import java.util.List;
import java.util.logging.Logger;

public class ClientDaoImpl implements IClientDao {

    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class.getName());

    private static Client[] clients = {new Client("1", true), new Client("2", true), new Client("3", false)};

    @Override
    public Client extraireClient(String id) {
        LOG.info("Recherche client par id=" + id);
        Client client = List.of(clients).stream()
                .filter(c -> c.getIdentifiantClient().equals(id))
                .findAny()
                .orElse(null);
        LOG.info("Resultat client : " + (client != null ? client.getIdentifiantClient() + " premium=" + client.isPremium() : "null"));
        return client;
    }

}
