/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.boundary;

import hr.boundary.AbstractFacade;
import hr.model.entity.Branch;
import hr.model.entity.Location;
import hr.model.entity.PriceTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xpan
 */
@Stateless
public class PriceTableService extends AbstractFacade<PriceTable> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PriceTableService() {
        super(PriceTable.class);
    }

    public PriceTable findPriceTableWithRoutine(Location departure, Location arrival) {
        try {
            TypedQuery query = em.createNamedQuery("PriceTable.findPriceTableByRoutine",
                    PriceTable.class).setParameter("departure", departure.getId()).setParameter("arrival", arrival.getId());
            PriceTable pt = (PriceTable) query.getSingleResult();
            return pt;
        } catch (Exception e) {
            return null;
        }
    }
}
