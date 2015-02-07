/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.*;
import hr.model.entity.*;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 吕杨
 */
@Named(value="ManagementMB")
@SessionScoped
public class ManagementMB implements Serializable{
    @Inject
    private BranchService bs;
    
    @Inject
    private ManagerService ms;
    
    @Inject
    private HeliService hs;
    
    @Inject
    private PilotService ps;
    
    private Manager manager;
    
    //helicopters
    private List<Helicopter> helicopters = new ArrayList<Helicopter>();
    private String type;
    private Integer capacity;
    private Double fixedcost;
    private int branchId;
    
    //pilots
    private List<Pilot> pilots = new ArrayList<Pilot>();
    private String pilotName;
    private String pilotEmail;
    private String pilotPassword;
    
    public ManagementMB(){
    }

    public void setProperty(Manager manager){

        
    }
    
    public String deleteHeli(Helicopter h){

        if(helicopters.contains(h))
        {
            hs.remove(h);
            helicopters.remove(h);
        }
        return null;
    }
    
    public String addHeli(){
        Branch branch = bs.find(branchId);
        Helicopter helicopter = new Helicopter();
        helicopter.setCapacity(this.capacity);
        helicopter.setFixedcost(this.fixedcost);
        helicopter.setType(this.type);
        helicopter.setBranch(branch);
        helicopters.add(helicopter);
        hs.create(helicopter);
        return null;
    }
    
    public String deletePilot(Pilot p){
        if(pilots.contains(p))
        {
            ps.remove(p);
            pilots.remove(p);
        }
        return null;
    }
    
    public String addPilot(){
        Branch branch = bs.find(branchId);
        Pilot p = new Pilot();
        p.setName(this.pilotName);
        p.setEmail(this.pilotEmail);
        p.setPassword(this.pilotPassword);
        p.setBranch(branch);
        pilots.add(p);
        ps.create(p);
        return null;
    }    

    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public void setPilotEmail(String pilotEmail) {
        this.pilotEmail = pilotEmail;
    }

    public String getPilotPassword() {
        return pilotPassword;
    }

    public void setPilotPassword(String pilotPassword) {
        this.pilotPassword = pilotPassword;
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
