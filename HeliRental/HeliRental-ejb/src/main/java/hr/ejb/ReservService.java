/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.ejb;

import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
import hr.boundary.AbstractFacade;
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
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xpan
 */
@Stateless
public class ReservService extends AbstractFacade<Reservation> {

    @PersistenceContext(name = "HeliRental")
    private EntityManager em;
    static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    @EJB
    private CustomerService cs;
    @EJB
    private HeliService hs;
    @EJB
    private PriceTableService pts;
    @EJB
    private LocationService ls;
    @EJB
    private PilotService ps;
    @EJB
    private PscheduleService pss;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservService() {
        super(Reservation.class);
    }
    
    public String checkFlight(String departure, String arrival, Date departureTime, Integer numberOfPassengers) throws ParseException{
        Location depart = ls.findLocationWithName(departure);
        Location arrive = ls.findLocationWithName(arrival);
        PriceTable pt = pts.findPriceTableWithRoutine(depart, arrive);
        if (pt == null) {
            System.err.println("No price-table available");
            return "this routine has not been set up yet";
        }
        Date date = departureTime;
        long t = date.getTime();
        Date arrivalTime = new Date(t + (pt.getDuration() * ONE_MINUTE_IN_MILLIS));
        Date startTime = new Date(t - (pt.getDeparture().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
        t = arrivalTime.getTime();
        Date endTime = new Date(t + (pt.getArrival().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
        List<Helicopter> helis = hs.findAllASCWithBranch(depart.getBranch());
        if (helis.isEmpty()) {
            System.err.println("No helicopter in this branch");
            return "no helicopter available";
        }
        int h;
        int passengers = (int) numberOfPassengers;
        int available = 0;//capacity check
        while (!helis.isEmpty()) {
            h = assignHeli(helis, endTime, startTime);
            if (h < helis.size()) {
                available++;
                if (passengers <= helis.get(h).getCapacity()) {
                    List<Pilot> pilots = ps.findAllASCWithBranch(depart.getBranch());
                    if (pilots.isEmpty()) {
                        System.err.println("No pilot in this branch");
                        return "no pilot available";
                    }
                    Pilot p = assignPilot(pilots, endTime, startTime);
                    if (p != null) {
                        return "available";
                    } else {
                        System.err.println("No pilot available");
                        return "no pilot available";
                    }
                } else {
                    for (int i = 0; i <= h; i++) {
                        helis.remove(0);
                    }
                }
            } else {
                if (available == 0) {
                    System.err.println("No helicopter available");
                    return "no helicopter available";
                } else {
                    System.err.println("Not enough seats");
                    return "number of passengers is over capacity";
                }
            }
        }
        System.err.println("Not enough seats");
        return "number of passengers is over capacity";
    }

    public String makeReservation() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = dateFormat.parse((String) session.getAttribute("departureTime"));
        long time = date.getTime();
        date.setTime((time / 1000) * 1000);
        System.out.println("============" + dateFormat.format(date));
        Reservation r = new Reservation();
        Customer c = cs.findCutomerWithId((int) session.getAttribute("loggedUserId"));
//        Customer c = cs.findCutomerWithId(1);
        r.setCustomer(c);
        r.setDepartureTime(date);
        Location depart = ls.findLocationWithName((String) session.getAttribute("departure"));
        Location arrive = ls.findLocationWithName((String) session.getAttribute("arrival"));
        PriceTable pt = pts.findPriceTableWithRoutine(depart, arrive);
        if (pt == null) {
            System.err.println("No price-table available");
            return "this routine has not been set up yet";
        }
        r.setPrice(pt);
        r.setSellprice(pt.getSellprice());
        pt.getDuration();
        long t = date.getTime();
        Date arrivalTime = new Date(t + (pt.getDuration() * ONE_MINUTE_IN_MILLIS));
        r.setArrivalTime(arrivalTime);
        Date startTime = new Date(t - (pt.getDeparture().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
        t = arrivalTime.getTime();
        Date endTime = new Date(t + (pt.getArrival().getPrepareTime() * ONE_MINUTE_IN_MILLIS));
        List<Helicopter> helis = hs.findAllASCWithBranch(depart.getBranch());
        if (helis.isEmpty()) {
            System.err.println("No helicopter in this branch");
            return "no helicopter available";
        }
        int h;
        int passengers = (int) session.getAttribute("numberOfPassengers");
        int available = 0;//capacity check
        while (!helis.isEmpty()) {
            h = assignHeli(helis, endTime, startTime);
            if (h < helis.size()) {
                available++;
                if (passengers <= helis.get(h).getCapacity()) {
                    List<Pilot> pilots = ps.findAllASCWithBranch(depart.getBranch());
                    if (pilots.isEmpty()) {
                        System.err.println("No pilot in this branch");
                        return "no pilot available";
                    }
                    Pilot p = assignPilot(pilots, endTime, startTime);
                    if (p != null) {
                        r.setPassengers(passengers);

                        Pschedule s = new Pschedule();
                        s.setHelicopter(helis.get(h));
                        s.setPilot(p);
                        s.setStartTime(startTime);
                        s.setEndTime(endTime);
                        date = new Date();
                        System.out.println("============" + dateFormat.format(date));
                        r.setReservTime(date);
                        r.setArrival(pt.getArrival().getName());
                        r.setDeparture(pt.getDeparture().getName());
//                    rs.create(r);
                        s.setReservation(r);
                        pss.create(s);

                        return "available";
                    } else {
                        System.err.println("No pilot available");
                        return "no pilot available";
                    }
                } else {
                    for (int i = 0; i <= h; i++) {
                        helis.remove(0);
                    }
                }
            } else {
                if (available == 0) {
                    System.err.println("No helicopter available");
                    return "no helicopter available";
                } else {
                    System.err.println("Not enough seats");
                    return "number of passengers is over capacity";
                }
            }
        }
        System.err.println("Not enough seats");
        return "number of passengers is over capacity";
    }

    public int assignHeli(List<Helicopter> helis, Date arriveTime, Date departTime) {
        Boolean flag = false;
        for (int i = 0; i < helis.size(); i++) {
            Helicopter h = helis.get(i);
            if (h.getSchedules().isEmpty()) {
                return i;
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
                return i;
            }
        }
        return helis.size() + 1;
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
