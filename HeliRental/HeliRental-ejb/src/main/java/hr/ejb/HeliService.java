/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Helicopter;
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
public class HeliService extends AbstractFacade<Helicopter> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HeliService() {
        super(Helicopter.class);
    }
    
    public List<Helicopter> findHelicoptersByBranchId(int id){
        try {
            System.out.println("query for Helicopter with BranchId " + id);
            Query userNameQuery = em.createNamedQuery("Helicopters.findByBranchId");
            userNameQuery.setParameter("id", id);
            List<Helicopter> foundHelicopters =  userNameQuery.getResultList();
            return foundHelicopters;
        } catch (NoResultException e) {
            System.out.println("new Helicopter "+e);
            return null;
        }

    }
}
