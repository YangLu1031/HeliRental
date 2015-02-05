/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Pilot;
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
public class PilotService extends AbstractFacade<Pilot> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PilotService() {
        super(Pilot.class);
    }
    
    public Pilot findPilotByEmail(String email){
        try {
            System.out.println("query for Pilot with email " + email);
            Query userNameQuery = em.createNamedQuery("Pilot.findByEmail");
            userNameQuery.setParameter("emailAddress", email);
            Pilot foundPilot = (Pilot) userNameQuery.getSingleResult();
            return foundPilot;
        } catch (NoResultException e) {
            System.out.println("new user "+e);
            return null;
        }

    }
    
    public boolean checkUser(String email, String password) {
        Pilot f = findPilotByEmail(email);
        if (f != null) {
            if (f.getPassword().equals(password)) {
                return true;//log in successfully
            }
        }
        return false;//invalid input
    }  
    
    public List<Pilot> findPilotsByBranchId(int id){
        try {
            System.out.println("query for Pilot with BranchId " + id);
            Query userNameQuery = em.createNamedQuery("Pilots.findByBranchId");
            userNameQuery.setParameter("id", id);
            List<Pilot> foundPilots =  userNameQuery.getResultList();
            return foundPilots;
        } catch (NoResultException e) {
            System.out.println("new Pilot "+e);
            return null;
        }

    }
}
