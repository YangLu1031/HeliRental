/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.model.entity.Customer;
import hr.model.entity.Staff;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Xpan
 */
@Stateless
public class StaffService extends AbstractFacade<Staff> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StaffService() {
        super(Staff.class);
    }

    public Staff findStaffWithId(int id) {
        try {
            TypedQuery query = em.createNamedQuery("Staff.findStaffById", Staff.class).setParameter("id", id);
            Staff s = (Staff) query.getSingleResult();
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    public Staff findLoginStaff(String email, String password) {
        try {
            TypedQuery query = em.createNamedQuery("Staff.findLoginStaff", Staff.class).setParameter("email", email).setParameter("password", password);
            Staff s = (Staff) query.getSingleResult();
            return s;
        } catch (Exception e) {
            return null;
        }
    }
}
