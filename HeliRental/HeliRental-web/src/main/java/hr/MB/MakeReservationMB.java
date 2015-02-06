/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import hr.ejb.CustomerService;
import hr.ejb.HeliService;
import hr.ejb.LocationService;
import hr.ejb.PilotService;
import hr.ejb.PriceTableService;
import hr.ejb.PscheduleService;
import hr.ejb.ReservService;
import hr.model.entity.Customer;
import hr.model.entity.Helicopter;
import hr.model.entity.Location;
import hr.model.entity.Pilot;
import hr.model.entity.PriceTable;
import hr.model.entity.Pschedule;
import hr.model.entity.Reservation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xpan
 */
@Named(value = "makeReservationMB")
@RequestScoped
public class MakeReservationMB {

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
        if (cs.getSession() == null) {
            System.out.println("please login or sign up");
            return "login_signup page";
        }
        rs.makeReservation();
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
