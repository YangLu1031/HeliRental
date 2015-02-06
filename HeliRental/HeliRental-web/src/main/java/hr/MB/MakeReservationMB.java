/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.boundary.CustomerService;
import hr.boundary.HeliService;
import hr.boundary.LocationService;
import hr.boundary.PilotService;
import hr.boundary.PriceTableService;
import hr.boundary.PscheduleService;
import hr.boundary.ReservService;
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
    @EJB
    private PilotService ps;
    @EJB
    private PscheduleService pss;

    public MakeReservationMB() {
    }

    public String makeReservation() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = dateFormat.parse(departureTime);
        long time = date.getTime();
        date.setTime((time / 1000) * 1000);
        System.out.println("============" + dateFormat.format(date));

        Reservation r = new Reservation();
        Customer c = cs.findCutomerWithId(customerid);
        r.setCustomer(c);
        r.setDepartureTime(date);
        Location depart = ls.findLocationWithName(departure);
        Location arrive = ls.findLocationWithName(arrival);
        PriceTable pt = pts.findPriceTableWithRoutine(depart, arrive);
        if (pt == null) {
            System.err.println("No price-table available");
            return null;
        }
        r.setPrice(pt);
        pt.getDuration();
        long t = date.getTime();
        Date arrivalTime = new Date(t + (pt.getDuration() * ONE_MINUTE_IN_MILLIS));
        r.setArrivalTime(arrivalTime);
        List<Helicopter> helis = hs.findAllASCWithBranch(depart.getBranch());
        Helicopter h = assignHeli(helis, arrivalTime, date);
        List<Pilot> pilots = ps.findAllASCWithBranch(depart.getBranch());
        Pilot p = assignPilot(pilots, arrivalTime, date);
        if (h != null && p != null) {
            if (passengers <= h.getCapacity()) {
                r.setPassengers(passengers);

                Pschedule s = new Pschedule();
                s.setHelicopter(h);
                s.setPilot(p);
                    t = date.getTime();
                    Date startTime = new Date(t - (pt.getDeparture().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
                    s.setStartTime(startTime);
                    t = arrivalTime.getTime();
                    Date endTime = new Date(t + (pt.getArrival().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
                    s.setEndTime(endTime);
                    date = new Date();
                    System.out.println("============" + dateFormat.format(date));
                    r.setReservTime(date);
                    rs.create(r);
                    s.setReservation(r);
                    pss.create(s);
            } else {
                System.err.println("Not enough seats");
            }
        } else {
            System.err.println("No helicopter or pilot available");
        }
        return null;
    }

    public Helicopter assignHeli(List<Helicopter> helis, Date arriveTime, Date departTime) {
        Boolean flag = false;
        for (int i = 0; i < helis.size(); i++) {
            Helicopter h = helis.get(i);
            if (h.getSchedules().isEmpty()) {
                return h;
            }
            for (int j = 0; j < h.getSchedules().size(); j++) {
                Pschedule s = h.getSchedules().get(j);
                Date depart = s.getStartTime();
                Date arrive = s.getEndTime();
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

    public Pilot assignPilot(List<Pilot> pilots, Date arriveTime, Date departTime) {
        Boolean flag = false;
        for (int i = 0; i < pilots.size(); i++) {
            Pilot p = pilots.get(i);
            if (p.getSchedules().isEmpty()) {
                return p;
            }
            for (int j = 0; j < p.getSchedules().size(); j++) {
                Pschedule s = p.getSchedules().get(j);
                Date depart = s.getStartTime();
                Date arrive = s.getEndTime();
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
                return p;
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

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

    public PscheduleService getPss() {
        return pss;
    }

    public void setPss(PscheduleService pss) {
        this.pss = pss;
    }

}
