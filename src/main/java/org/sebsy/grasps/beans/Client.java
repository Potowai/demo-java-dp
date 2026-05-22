package org.sebsy.grasps.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
public class Client {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    @Id
    private String identifiantClient;

    private boolean premium;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations = new ArrayList<>();

    public Client() {

    }

    public Client(String identifiantClient, boolean premium) {
        super();
        this.identifiantClient = identifiantClient;
        this.premium = premium;
    }

    /**
     * Getter
     *
     * @return the identifiantClient
     */
    public String getIdentifiantClient() {
        return identifiantClient;
    }

    /**
     * Setter
     *
     * @param identifiantClient the identifiantClient to set
     */
    public void setIdentifiantClient(String identifiantClient) {
        this.identifiantClient = identifiantClient;
    }

    /**
     * Getter
     *
     * @return the premium
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Setter
     *
     * @param premium the premium to set
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    private void addReservation(Reservation reservation) {
        reservation.setClient(this);
        this.reservations.add(reservation);
    }

    public Reservation creerReservation(LocalDateTime date, int nbPlaces, TypeReservation type) {
        LOG.info("Creation reservation : client=" + identifiantClient
                + ", date=" + date + ", places=" + nbPlaces
                + ", type=" + type.getType());
        Reservation reservation = new Reservation(date, nbPlaces);
        this.addReservation(reservation);
        reservation.calculerTotal(type);
        LOG.info("Reservation creee : total=" + reservation.getTotal());
        return reservation;
    }

    /**
     * Getter
     *
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Setter
     *
     * @param reservations the reservations to set
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
