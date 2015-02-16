/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import hr.ejb.ManagerService;
import hr.ejb.PilotService;
import hr.model.entity.Branch;
import hr.model.entity.Pilot;
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
@Named(value = "managePilotMB")
@SessionScoped
public class ManagePilotMB implements Serializable {

    private String email;
    private String name;
    private String password;
    private String position;
    private String address;
    private String phone;
    private Double salary;
    private Branch branch;
    @EJB
    private ManagerService ms;
    @EJB
    private PilotService ps;
    private List<Pilot> pilots = new ArrayList<Pilot>();
    
    public ManagePilotMB() {
    }
    
    public void setProperty() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        pilots = ps.findAllASCWithBranch(branch);
    }
    
    public String addPilot(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        String msg = ps.addPilot(branch, email, name, password,position, address, phone, salary);
        if (msg.equals("add successfully")) {
            return null;//add successful ajax list
        }
        FacesContext.getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }
    
    public String deletePilot(Pilot p) {
        Date date = new Date();
        for (int i = 0; i < p.getSchedules().size(); i++) {
            if (p.getSchedules().get(i).getEndTime().after(date)) {
                return null;//cannot delete
            }
        }
        ps.remove(p);
        return null;//delete successful ajax list
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
