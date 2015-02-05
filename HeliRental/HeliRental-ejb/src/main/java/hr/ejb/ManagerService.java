/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Manager;
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
public class ManagerService extends AbstractFacade<Manager>{

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagerService() {
        super(Manager.class);
    }
    
    public Manager findManagerByEmail(String email){
        try {
            System.out.println("query for Manager with email " + email);
            Query userNameQuery = em.createNamedQuery("Manager.findByEmail");
            userNameQuery.setParameter("emailAddress", email);
            Manager foundManager = (Manager) userNameQuery.getSingleResult();
            return foundManager;
        } catch (NoResultException e) {
            System.out.println("new user "+e);
            return null;
        }

    }
    
    public Integer findBranchIdByManagerId(int id){
        try {
            System.out.println("query for BranchId with ManagerId " + id);
            Query userNameQuery = em.createNamedQuery("BranchId.findByManagerId");
            userNameQuery.setParameter("id", id);
            Integer foundId = (Integer) userNameQuery.getSingleResult();
            return foundId;
        } catch (NoResultException e) {
            System.out.println("new BranchId "+e);
            return null;
        }

    }
    
    public boolean checkUser(String email, String password) {
        Manager f = findManagerByEmail(email);
        if (f != null) {
            if (f.getPassword().equals(password)) {
                return true;//log in successfully
            }
        }
        return false;//invalid input
    }    
}
