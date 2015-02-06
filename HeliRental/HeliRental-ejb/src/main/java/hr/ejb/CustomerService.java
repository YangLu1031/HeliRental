/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.model.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 吕杨
 */
@Stateless
public class CustomerService extends AbstractFacade<Customer>{
    
    @PersistenceContext(unitName = "HeliRental")
    private EntityManager em;

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Customer findCustomerByEmail(String email){
        try {
            System.out.println("query for User with email " + email);
            Query userNameQuery = em.createNamedQuery("Customer.findByEmail");
            userNameQuery.setParameter("emailAddress", email);
            Customer foundCustomer = (Customer) userNameQuery.getSingleResult();
            return foundCustomer;
        } catch (NoResultException e) {
            System.out.println("new user "+e);
            return null;
        }

    }
    
    public boolean checkUser(String email, String password) {
        Customer f = findCustomerByEmail(email);
        if (f != null) {
            if (f.getPassword().equals(password)) {
                return true;//log in successfully
            }
        }
        return false;//invalid input
    } 
}