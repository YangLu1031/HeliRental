/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

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
@Named(value="Register")
@SessionScoped
public class Register implements Serializable{
    @Inject
    private CustomerService customerService;
    private Customer customer;

    //register
    private String name;
    private String email;
    private String password;
    private String passwordConf;
    private String phone;
    private String address;
    
 
    public String saveRegInfo() {
        customer = customerService.findCustomerByEmail(email);
        if (customer == null){
          //this is  new user -- do a create
          System.out.println("Registration Managed Bean creating new user ");
          customer = new Customer();
          customer.setEmail(email);
          customerService.create(customer);
      }
      else {
          //this user already exists so they can not register again
           FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
          System.out.println("Registration failed - user exists: "+customer.toString());
          
      }
      return "home";
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    
    
}
