/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import hr.ejb.CustomerService;
import hr.model.entity.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 吕杨
 */
@Named(value="RegCustomer")
@SessionScoped
public class RegCustomer implements Serializable{
    @Inject
    private CustomerService customerService;
    private Customer customer;
    private String emailAddress;
    private String name;
    private String password;
    private String phone;
    private String address;
    
    public String saveRegInfo() {
        customer = customerService.findCustomerByEmail(emailAddress);
        if (customer == null){
          //this is  new user -- do a create
          System.out.println("Registration Managed Bean creating new user ");
          customer = new Customer();
          customer.setEmail(emailAddress);
          customer.setName(name);
          customerService.create(customer);
      }
      else {
          //this user already exists so they can not register again
           FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
          System.out.println("Registration failed - user exists: "+customer.toString());
          
      }
      return "login";
      // return "lastpage";
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
    
}
