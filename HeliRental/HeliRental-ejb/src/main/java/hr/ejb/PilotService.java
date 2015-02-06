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
import hr.model.entity.Helicopter;
import hr.model.entity.Pilot;
import hr.model.entity.Pschedule;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public List<Pilot> findAllASCWithBranch(Branch b){
        TypedQuery<Pilot> query = em.createNamedQuery("Pilot.findAllASCByBranch", Pilot.class).setParameter("branch", b);
        List<Pilot> pilots = query.getResultList();
        return pilots;
    }
}
