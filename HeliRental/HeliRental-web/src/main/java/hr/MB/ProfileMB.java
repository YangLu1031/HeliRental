/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import hr.ejb.CustomerService;
import hr.ejb.ManagerService;
import hr.ejb.PilotService;
import hr.model.entity.Customer;
import hr.model.entity.Manager;
import hr.model.entity.Pilot;
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
@Named(value = "profileMB")
@SessionScoped
public class ProfileMB implements Serializable {

    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    @EJB
    private ManagerService ms;
    @EJB
    private PilotService ps;
    @EJB
    private CustomerService cs;
    
    public ProfileMB() {
    }
    
    public void setProperty(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        String userType = (String)session.getAttribute("userType");
        switch (userType) {
            case "customer":
                Customer c = cs.findCutomerWithId(id);
                email = c.getEmail();
                password = c.getPassword();
                name = c.getName();
                phone = c.getPhone();
                address = c.getAddress();
                break;
            case "manager":
                Manager m = ms.findManagerWithId(id);
                email = m.getEmail();
                password = m.getPassword();
                name = m.getName();
                phone = m.getPhone();
                address = m.getAddress();
                break;
            case "pilot":
                Pilot p = ps.findPilotWithId(id);
                email = p.getEmail();
                password = p.getPassword();
                name = p.getName();
                phone = p.getPhone();
                address = p.getAddress();
                break;
        }
    }
    
    public String saveProfileInfo() throws ParseException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int)session.getAttribute("loggedUserId");
        String userType = (String)session.getAttribute("userType");
        switch (userType) {
            case "customer":
                Customer c = cs.findCutomerWithId(id);
                c.setEmail(email);
                c.setPassword(password);
                c.setName(name);
                c.setPhone(phone);
                c.setAddress(address);
                cs.edit(c);
                break;
            case "manager":
                Manager m = ms.findManagerWithId(id);
                m.setEmail(email);
                m.setPassword(password);
                m.setName(name);
                m.setPhone(phone);
                m.setAddress(address);
                ms.edit(m);
                break;
            case "pilot":
                Pilot p = ps.findPilotWithId(id);
                p.setEmail(email);
                p.setPassword(password);
                p.setName(name);               
                p.setPhone(phone);
                p.setAddress(address);
                ps.edit(p);
                break;
        }
            return "index";
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

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

    
}