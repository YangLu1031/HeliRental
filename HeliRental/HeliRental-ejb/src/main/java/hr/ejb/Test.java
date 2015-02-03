/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ejb;

import hb.boundary.AbstractFacade;
import hr.model.entity.Branch;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hasee
 */
@Stateless
public class Test extends AbstractFacade<Branch> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;


    public void create() {
        Branch b=new Branch();
        b.setLocation("Chicago");
        create(b);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Test() {
        super(Branch.class);
    }
}
