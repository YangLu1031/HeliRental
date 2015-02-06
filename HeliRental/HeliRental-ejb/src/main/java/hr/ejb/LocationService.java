/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.model.entity.Branch;
import hr.model.entity.Location;
import hr.model.entity.Manager;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xpan
 */
@Stateless
public class LocationService extends AbstractFacade<Location>{

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocationService() {
        super(Location.class);
    }
    
    public Location findLocationWithName(String name){
        try {
            TypedQuery query = em.createNamedQuery("Location.findLocationByName", Location.class).setParameter("name", name);
            Location l = (Location) query.getSingleResult();
            return l;
        } catch (Exception e) {
            return null;
        }
    }
}
