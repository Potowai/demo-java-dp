package org.sebsy.grasps.daos;

import org.sebsy.grasps.beans.Client;

public interface IClientDao {
    Client extraireClient(String id);
}
