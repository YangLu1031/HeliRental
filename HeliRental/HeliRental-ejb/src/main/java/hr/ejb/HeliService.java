/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.model.entity.Branch;
import hr.model.entity.Helicopter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public List<Helicopter> findAllASCWithBranch(Branch b){
        TypedQuery<Helicopter> query = em.createNamedQuery("Helicopter.findAllASCByBranch", Helicopter.class).setParameter("branch", b);
        List<Helicopter> helis = query.getResultList();
        return helis;
    }
}
