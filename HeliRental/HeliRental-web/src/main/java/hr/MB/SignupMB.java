/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import hr.ejb.CustomerService;
import hr.ejb.ReservService;
import hr.model.entity.Customer;
import java.io.Serializable;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hasee
 */
@Named(value = "signupMB")
@SessionScoped
public class SignupMB implements Serializable {

    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    @EJB
    private CustomerService cs;
    @EJB
    private ReservService rs;
    
    public SignupMB() {
    }

    public String signup() throws ParseException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        Customer c=cs.findCutomerWithEmail(email);
        if(c==null){
            c=new Customer();
            c.setEmail(email);
            c.setAddress(address);
            c.setName(name);
            c.setPassword(password);
            c.setPhone(phone);
            cs.create(c);
            session.setAttribute("loggedUserId", c.getId());
            session.setAttribute("userType", "customer");
            
            if(session.getAttribute("departure")!=null){
                rs.makeReservation();
                return null;//redirect to reserve successfully
            }
            return "index";//redirect to customer homepage
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "email already registered", null));
        return null;//email already registered
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }
    
}