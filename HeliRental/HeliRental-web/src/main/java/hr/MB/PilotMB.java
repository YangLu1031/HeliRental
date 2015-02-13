/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.CustomerService;
import hr.ejb.PilotService;
import hr.model.entity.Pschedule;
import hr.model.entity.Reservation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 984228
 */
@Named(value = "pilotMB")
@SessionScoped
public class PilotMB implements Serializable {

    @EJB
    private PilotService ps;
    private List<Pschedule> schedules = new ArrayList<Pschedule>();
    /**
     * Creates a new instance of CustomerMB
     */
    public PilotMB() {
    }
    
    public void getInformation(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        schedules = ps.findPilotWithId(id).getSchedules();
    }

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

    public List<Pschedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Pschedule> schedules) {
        this.schedules = schedules;
    }


    
}
