/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.HeliService;
import hr.ejb.ManagerService;
import hr.model.entity.Branch;
import hr.model.entity.Helicopter;
import hr.model.entity.Manager;
import java.io.Serializable;
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
 * @author hasee
 */
@Named(value = "helicopterMB")
@SessionScoped
public class ManageHelicopterMB implements Serializable {

    private String name;
    private Integer capacity;
    private Double fixedcost;
    private Branch branch;
    @EJB
    private HeliService hs;
    private List<Helicopter> helicopters = new ArrayList<Helicopter>();
    @EJB
    private ManagerService ms;

    public ManageHelicopterMB() {
        
    }
    
    public void setProperty(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        helicopters = hs.findAllASCWithBranch(branch);
    }
    
    public String addHelicopter() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        String msg = hs.addHelicopter(branch, name, capacity, fixedcost);
        if (msg.equals("add successfully")) {
            return null;//add successful ajax list
        }
        FacesContext.getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }

    public String deleteHelicopter(Helicopter h) {
        Date date = new Date();
        for (int i = 0; i < h.getSchedules().size(); i++) {
            if (h.getSchedules().get(i).getEndTime().after(date)) {
                return null;//cannot delete
            }
        }
        hs.remove(h);
        return null;//delete successful ajax list
    }

    public List<Helicopter> getHelicopters() {
        return helicopters;
    }

    public void setHelicopters(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getFixedcost() {
        return fixedcost;
    }

    public void setFixedcost(Double fixedcost) {
        this.fixedcost = fixedcost;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public HeliService getHs() {
        return hs;
    }

    public void setHs(HeliService hs) {
        this.hs = hs;
    }

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

}
