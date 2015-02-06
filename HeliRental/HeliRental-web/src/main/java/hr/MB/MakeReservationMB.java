/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.boundary.CustomerService;
import hr.boundary.HeliService;
import hr.boundary.LocationService;
import hr.boundary.PriceTableService;
import hr.boundary.ReservService;
import hr.model.entity.Customer;
import hr.model.entity.Helicopter;
import hr.model.entity.Location;
import hr.model.entity.PriceTable;
import hr.model.entity.Reservation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Xpan
 */
@Named(value = "makeReservationMB")
@RequestScoped
public class MakeReservationMB {

    static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
    private String departure;
    private String arrival;
    private Integer passengers;
    private Integer customerid;
    private String departureTime;
    @EJB
    private CustomerService cs;
    @EJB
    private HeliService hs;
    @EJB
    private PriceTableService pts;
    @EJB
    private ReservService rs;
    @EJB
    private LocationService ls;

    public MakeReservationMB() {
    }

    public String makeReservation() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = dateFormat.parse(departureTime);
//        long time = date.getTime();
//        date.setTime((time / 1000) * 1000);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
        date = dateFormat.parse(dateFormat.format(date));
        System.out.println("============" + dateFormat.format(date));

        Reservation r = new Reservation();
        Customer c = cs.findCutomerWithId(customerid);
        r.setCustomer(c);
        r.setDepartureTime(date);
        Location depart=ls.findLocationWithName(departure);
        Location arrive=ls.findLocationWithName(arrival);
        boolean day=true;
        PriceTable pt = pts.findPriceTableWithRoutine(depart, arrive,day);
        if (pt == null) {
            System.err.println("No price-table available");
            return null;
        }
        r.setPrice(pt);
        pt.getDuration();
        long t = date.getTime();
        Date arrivalTime = new Date(t + (pt.getDuration() * ONE_MINUTE_IN_MILLIS));
        r.setArrivalTime(arrivalTime);
        List<Helicopter> helis = hs.findAllASC();
        Helicopter h = assignHeli(helis, arrivalTime, date);
        if (h != null) {
            r.setHelicopter(h);
            if (passengers <= h.getCapacity()) {
                r.setPassengers(passengers);
                date = new Date();
                long time = date.getTime();
                date.setTime((time / 1000) * 1000);
                r.setReservTime(date);
                r.setStatus("on time");
                rs.create(r);
            } else {
                System.err.println("Not enough seats");
            }
        } else {
            System.err.println("No helicopter available");
        }
        return null;
    }

    public Helicopter assignHeli(List<Helicopter> helis, Date arriveTime, Date departTime) {
        Boolean flag = false;
        for (int i = 0; i < helis.size(); i++) {
            Helicopter h = helis.get(i);
            for (int j = 0; j < h.getReservations().size(); j++) {
                Reservation r = h.getReservations().get(j);
                Date depart = r.getDepartureTime();
                Date arrive = r.getArrivalTime();
                if ((depart.before(departTime) || depart.equals(departTime)) && (arrive.after(departTime) || arrive.equals(departTime))) {
                    flag = false;
                    break;
                } else if ((depart.before(arriveTime) || depart.equals(arriveTime)) && (arrive.after(arriveTime) || arrive.equals(arriveTime))) {
                    flag = false;
                    break;
                } else if ((depart.after(departTime) || depart.equals(departTime)) && (arrive.before(arriveTime) || arrive.equals(arriveTime))) {
                    flag = false;
                    break;
                }
                flag = true;
            }
            if (flag) {
                return h;
            }
        }
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

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public HeliService getHs() {
        return hs;
    }

    public void setHs(HeliService hs) {
        this.hs = hs;
    }

    public PriceTableService getPts() {
        return pts;
    }

    public void setPts(PriceTableService pts) {
        this.pts = pts;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }

    public LocationService getLs() {
        return ls;
    }

    public void setLs(LocationService ls) {
        this.ls = ls;
    }

}
