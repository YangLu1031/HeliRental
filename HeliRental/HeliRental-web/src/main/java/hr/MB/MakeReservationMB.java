/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.ReservService;
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
 * @author Xpan
 */
@Named(value = "makeReservationMB")
@SessionScoped
public class MakeReservationMB implements Serializable {

    private String departure;
    private String arrival;
    private Integer passengers;
    private Integer customerid;
    private String departureTime;
    @EJB
    private ReservService rs;

    public MakeReservationMB() {
    }

    public String makeReservation() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        session.setAttribute("departure", departure);
        session.setAttribute("arrival", arrival);
        session.setAttribute("numberOfPassengers", passengers);
        session.setAttribute("departureTime", departureTime);
        CheckSessionMB cs = new CheckSessionMB();
        String msg = rs.makeReservation();
    
        if (msg.equals("reserve successfully!")) {
            if (cs.getSession() == null) {
                System.out.println("please login or sign up");
                return null;//redirect to login and signup page
            }else
                return null;//reserve successfully
        }
        FacesContext.getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }
}
