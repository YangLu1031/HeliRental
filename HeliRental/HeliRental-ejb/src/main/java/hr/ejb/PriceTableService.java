/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Branch;
import hr.model.entity.Location;
import hr.model.entity.PriceTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

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

    public List<PriceTable> findPriceTableWithLocation(Location l) {
        TypedQuery<PriceTable> query = em.createNamedQuery("PriceTable.findPriceTableByLocation", PriceTable.class).setParameter("location", l);
        List<PriceTable> pts = query.getResultList();
        return pts;
    }
    
    public List<PriceTable> findPriceTableGroupWithBranch(Branch b) {
        TypedQuery<PriceTable> query = em.createNamedQuery("PriceTable.findPriceTableGroupByBranch", PriceTable.class).setParameter("branch", b);
        List<PriceTable> pts = query.getResultList();
        return pts;
    }
    
    public List<PriceTable> findPriceTableGroupWithBranch() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        Branch b=(Branch) session.getAttribute("branch");
        TypedQuery<PriceTable> query = em.createNamedQuery("PriceTable.findPriceTableGroupByBranch", PriceTable.class).setParameter("branch", b);
        List<PriceTable> pts = query.getResultList();
        return pts;
    }

    public Location findLocationWithName(String name) {
        try {
            TypedQuery query = em.createNamedQuery("Location.findLocationByName", Location.class).setParameter("name", name);
            Location l = (Location) query.getSingleResult();
            return l;
        } catch (Exception e) {
            return null;
        }
    }

    public String addPriceTable(Branch branch, String location_1, String location_2, Integer duration, Double sellprice, Double expense) {
        Location l1 = findLocationWithName(location_1);
        Location l2 = findLocationWithName(location_2);
        if (l1 != null && l2 != null) {
            PriceTable t = findPriceTableWithRoutine(l1, l2);
            if (t == null) {
                t=new PriceTable();
                t.setDeparture(l1);
                t.setArrival(l2);
                t.setDuration(duration);
                t.setExpense(expense);
                t.setSellprice(sellprice);
                create(t);
            }
            t=findPriceTableWithRoutine(l2,l1);
            if(t==null){
                t=new PriceTable();
                t.setDeparture(l2);
                t.setArrival(l1);
                t.setDuration(duration);
                t.setExpense(expense);
                t.setSellprice(sellprice);
                create(t);
                return "add successfully";
            }
            return "already exist";
        }
        return "location doesn't exist";
    }
}
