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

    public List<Helicopter> findAllASCWithBranch(Branch b) {
        TypedQuery<Helicopter> query = em.createNamedQuery("Helicopter.findAllASCByBranch", Helicopter.class).setParameter("branch", b);
        List<Helicopter> helis = query.getResultList();
        return helis;
    }

    public List<Helicopter> findAllHelicopterWithBranch() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        Branch b=(Branch) session.getAttribute("branch");
        TypedQuery<Helicopter> query = em.createNamedQuery("Helicopter.findAllASCByBranch", Helicopter.class).setParameter("branch", b);
        List<Helicopter> helis = query.getResultList();
        return helis;
    }

    public Helicopter findHelicopterWithNameBranch(String name, Branch b) {
        try {
            TypedQuery query = em.createNamedQuery("Helicopter.findHelicopterByNameBranch", Helicopter.class).setParameter("name", name).setParameter("branch", b);
            Helicopter h = (Helicopter) query.getSingleResult();
            return h;
        } catch (Exception e) {
            return null;
        }
    }

    public String addHelicopter(Branch branch, String name, Integer capacity, Double fixedcost) {
        Helicopter h = findHelicopterWithNameBranch(name, branch);
        if (h != null) {
            return "this helicopter already exists";
        }
        h = new Helicopter();
        h.setBranch(branch);
        h.setCapacity(capacity);
        h.setFixedcost(fixedcost);
        h.setName(name);
        create(h);
        return "add successfully";
    }
}
