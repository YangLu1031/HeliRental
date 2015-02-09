/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.BranchService;
import hr.ejb.ReservService;
import hr.model.entity.Branch;
import hr.model.entity.Location;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @EJB
    private BranchService bs;
    private List<Branch> branches;
    private List<String> brancheNames = new ArrayList<String>();
    private String selectedBranch;

    private List<String> departure = new ArrayList<String>();
    private String selectedDeparture;

    private List<String> arrival = new ArrayList<String>();
    private String selectedArrival;

    private Integer passengers;
    private Integer customerid;
    private Date departureTime;
    private Date currentDate = new Date();

    @EJB
    private ReservService rs;

    public void init() {
        branches = bs.findAll();
        for (Branch b : branches) {
            brancheNames.add(b.getName());
        }

    }
    
    public String test(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        System.out.println(session.getAttribute("loggedUserId"));
        return "index";
    }

    public String locationSelectionChanged() {
        departure.clear();
        arrival.clear();
        selectedDeparture = null;
        selectedArrival = null;
        if (this.branches != null) {
            for (Branch b : branches) {
                if (b.getName().equals(selectedBranch)) {
                    for (Location l : b.getLocations()) {
                        departure.add(l.getName());
                        arrival.add(l.getName());
                    }

                }
            }
        }

        return null;
    }

    public String makeReservation() throws ParseException {
        if (selectedDeparture.equals(selectedArrival)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "please choose different locations", null));
            return null;//please choose different locations
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        session.setAttribute("departure", selectedDeparture);
        session.setAttribute("arrival", selectedArrival);
        session.setAttribute("numberOfPassengers", passengers);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        long time = departureTime.getTime();
        departureTime.setTime((time / 1000) * 1000);
        String t = dateFormat.format(departureTime);
        session.setAttribute("departureTime", t);
        CheckSessionMB cs = new CheckSessionMB();
        String msg;
        if (cs.getSession() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "please login or sign up first", null));
            System.out.println("please login or sign up");
            return null;//redirect to login and signup page
        } else {
            msg = rs.makeReservation();
            if (msg.equals("available")) {
                return "congrats";//reserve successfully
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }

    public String check() throws ParseException {
        if (selectedDeparture.equals(selectedArrival)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "please choose different locations", null));
            return null;//please choose different locations
        }
        String msg = rs.checkFlight(selectedDeparture, selectedArrival, departureTime, passengers);
        if (msg.equals("available")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        }
        return null;
    }

    public List<String> getBrancheNames() {
        branches = bs.findAll();
        brancheNames.clear();
        for (Branch b : branches) {
            brancheNames.add(b.getName());
        }
        return brancheNames;
    }

    public void setBrancheNames(List<String> brancheNames) {
        this.brancheNames = brancheNames;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<String> getDeparture() {
        return departure;
    }

    public void setDeparture(List<String> departure) {
        this.departure = departure;
    }

    public List<String> getArrival() {
        return arrival;
    }

    public void setArrival(List<String> arrival) {
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

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }

    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }

    public String getSelectedBranch() {
        return selectedBranch;
    }

    public void setSelectedBranch(String selectedBranch) {
        this.selectedBranch = selectedBranch;
    }

    public String getSelectedDeparture() {
        return selectedDeparture;
    }

    public void setSelectedDeparture(String selectedDeparture) {
        this.selectedDeparture = selectedDeparture;
    }

    public String getSelectedArrival() {
        return selectedArrival;
    }

    public void setSelectedArrival(String selectedArrival) {
        this.selectedArrival = selectedArrival;
    }
}
