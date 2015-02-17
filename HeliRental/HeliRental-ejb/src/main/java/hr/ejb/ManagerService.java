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
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
        public Manager findManagerWithId(int id) {
        try {
            TypedQuery query = em.createNamedQuery("Manager.findManagerById", Manager.class).setParameter("id", id);
            Manager s = (Manager) query.getSingleResult();
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    public Manager findLoginManager(String email, String password) {
        try {
            TypedQuery query = em.createNamedQuery("Manager.findLoginManager", Manager.class).setParameter("email", email).setParameter("password", password);
            Manager s = (Manager) query.getSingleResult();
            return s;
        } catch (Exception e) {
            return null;
        }
    }
}
