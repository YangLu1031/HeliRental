/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Branch;
import hr.model.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author hasee
 */
@Stateless
public class BranchService extends AbstractFacade<Branch> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BranchService() {
        super(Branch.class);
    }
    
    public void find(){
        Branch b=em.find(Branch.class, 1);
        System.out.println(b.getLocation());
    }
    
    public Branch findBranchWithLocation(String location){
        try {
            TypedQuery query = em.createNamedQuery("Branch.findBranchByLocation", Branch.class).setParameter("location", location);
            Branch b = (Branch) query.getSingleResult();
            return b;
        } catch (Exception e) {
            return null;
        }
    }
}
