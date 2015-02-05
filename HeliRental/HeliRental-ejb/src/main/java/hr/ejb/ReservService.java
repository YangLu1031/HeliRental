/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Reservation;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Xpan
 */
@Stateless
public class ReservService extends AbstractFacade<Reservation> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservService() {
        super(Reservation.class);
    }
    
    public Reservation findReservationBySearch(String depart, String arrival, Date reservDate){
        try {
            System.out.println("query for Reservation with Search ");
            Query userNameQuery = em.createNamedQuery("Reservation.findBySearch");
            userNameQuery.setParameter("depart", depart);
            userNameQuery.setParameter("arrival", arrival);
            userNameQuery.setParameter("reservDate", reservDate);
            Reservation foundReservation = (Reservation) userNameQuery.getSingleResult();
            return foundReservation;
        } catch (NoResultException e) {
            System.out.println("new Reservation "+e);
            return null;
        }

    }
    
}
