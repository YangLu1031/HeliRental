/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.CustomerService;
import hr.ejb.PscheduleService;
import hr.ejb.ReservService;
import hr.model.entity.Pschedule;
import hr.model.entity.Reservation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 984228
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {

    @EJB
    private CustomerService cs;
    private List<Reservation> reservations = new ArrayList<Reservation>();
    
    @EJB
    private PscheduleService ps;
    @EJB
    private ReservService rs;
    
    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
    }
    
    public void getInformation(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        reservations = cs.findCutomerWithId(id).getReservations();
    }

    public String cancelReservation(Reservation r) {
        Date date = new Date();
        Pschedule p=ps.findScheduleWithReservation(r);
        if(p==null){
            return null;//cannot find Pschedule
        }
        if(p.getStartTime().before(date)||p.getStartTime().equals(date)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "time out can not cancel", null));
            return null;//cannot cancel reservation
        }
        ps.remove(p);
        rs.remove(r);
        return null;//ajax updated reservlist
    }
    
    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
}
