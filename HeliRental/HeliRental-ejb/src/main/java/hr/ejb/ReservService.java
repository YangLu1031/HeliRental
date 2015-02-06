/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Reservation;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    
//    public Collection findPilotHeliByReserveDate(Date reservDate){
//        try {
//            System.out.println("query for Reservation with ReserveDate ");
//            Query userNameQuery = em.createNamedQuery("Reservations.findPilotHeliByReserveDate");
//            userNameQuery.setParameter("reservDate", reservDate);
//            Collection foundResult =  userNameQuery.getResultList();
//            return foundResult;
//        } catch (NoResultException e) {
//            System.out.println("new ReserveDate "+e);
//            return null;
//        }
//
//    }
    
    public List<Reservation> findReservationsByPilotId(int id){
        try {
            System.out.println("query for Reservations with PilotId ");
            Query userNameQuery = em.createNamedQuery("Reservations.findByPilotId");
            userNameQuery.setParameter("pilotId", id);
            List<Reservation> foundReservations =  userNameQuery.getResultList();
            return foundReservations;
        } catch (NoResultException e) {
            System.out.println("new Reservations "+e);
            return null;
        }

    }
    
}
