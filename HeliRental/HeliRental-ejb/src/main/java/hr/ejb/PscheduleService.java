/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Pschedule;
import hr.model.entity.Reservation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xpan
 */
@Stateless
public class PscheduleService extends AbstractFacade<Pschedule>{

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PscheduleService() {
        super(Pschedule.class);
    }
    
    public Pschedule findScheduleWithReservation(Reservation r){
        try {
            TypedQuery query = em.createNamedQuery("Pschedule.findScheduleByReservation", Pschedule.class).setParameter("reservation", r);
            Pschedule s = (Pschedule) query.getSingleResult();
            return s;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void cancel(Pschedule sch){
        remove(sch);
    }
}
