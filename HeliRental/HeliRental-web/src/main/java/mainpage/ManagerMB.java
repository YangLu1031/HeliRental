/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import hr.ejb.*;
import hr.model.entity.*;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 吕杨
 */
@Named(value="managerMB")
@SessionScoped
public class ManagerMB implements Serializable{
    @Inject
    private ManagerService ms;
    
    @Inject
    private HeliService hs;
    
    @Inject
    private PilotService ps;
    
    @ManagedProperty(value="#{loginMB.loginManager}")
    private Manager manager;
    
    private List<Helicopter> helicopters = new ArrayList<Helicopter>();
    private List<Pilot> pilots = new ArrayList<Pilot>();
    private int id;
    private Integer capacity;
    private Double fixedcost;
    
    public ManagerMB(){
        //setProperty();
    }
//        FacesContext context = FacesContext.getCurrentInstance();
//    ExternalContext ec = context.getExternalContext();
//    HttpSession s = (HttpSession) ec.getSession(true);
//    System.out.println("**************"+s.getAttribute("loggedUserId"));
//    userSession=(String) s.getAttribute("loggedUserId");
//    FacesContext context = FacesContext.getCurrentInstance();
//    ExternalContext ec = context.getExternalContext();
//    HttpSession session = (HttpSession) ec.getSession(true);
//    // session.setAttribute("loggedUserId", user.getId());
//    // session.setAttribute("loggedUserName", user.getName());
//    session.setAttribute("loggedUserId", user.getId());
//    
    public void setProperty(){
//        Helicopter helicopter = new Helicopter();
//        Helicopter helicopter1 = new Helicopter();
//        helicopter.setCapacity(4);
//        helicopter.setFixedcost(1.2);
//        helicopter.setStatus(true);
//        helicopter1.setCapacity(3);
//        helicopter1.setFixedcost(1.2);
//        helicopter1.setStatus(true);
//        helicopters.add(helicopter);
//        helicopters.add(helicopter1);
        int id = ms.findBranchIdByManagerId(manager.getId());
        helicopters = hs.findHelicoptersByBranchId(id);
        pilots = ps.findPilotsByBranchId(id);
        
    }
    
    public void deleteHeli(Helicopter h){

        if(helicopters.contains(h))
        {
            helicopters.remove(h);
        }
        //return "managerLogin";
    }
    
    public String addHeli(){
        Helicopter helicopter = new Helicopter();
        helicopter.setCapacity(this.capacity);
        helicopter.setFixedcost(this.fixedcost);
        helicopter.setId(this.id);        
        helicopters.add(helicopter);
        return "managerLogin";
    }

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

    public HeliService getHs() {
        return hs;
    }

    public void setHs(HeliService hs) {
        this.hs = hs;
    }

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
    
    

    public List<Helicopter> getHelicopters() {
        return helicopters;
    }

    public void setHelicopters(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    
}
