package mainpage;




import hr.ejb.*;
import hr.model.entity.*;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 984228
 */
@Named(value="loginMB")
@SessionScoped
public class LoginMB implements Serializable{
    
    @Inject
    private ManagerService ms;
    private Manager loginManager;
    
    @Inject
    private PilotService ps;
    private Pilot loginPilot;    
    @Inject
    private HeliService hs;
    
    @Inject
    private CustomerService cs;
    private Customer loginCustomer;
    
    private String email;
    private String password;
    private Date currentDate = new Date();
    
    private List<Helicopter> helicopters = new ArrayList<Helicopter>();
    private List<Pilot> pilots = new ArrayList<Pilot>();
    private int id;
    private Integer capacity;
    private Double fixedcost;
    
    public String loginUser(){
        
        if(ms.checkUser(email, password)){
            loginManager = ms.findManagerByEmail(email);
            int id = ms.findBranchIdByManagerId(loginManager.getId());
            helicopters = hs.findHelicoptersByBranchId(id);
            pilots = ps.findPilotsByBranchId(id);
            return "managerLogin";
        }else if(ps.checkUser(email, password)){
            loginPilot = ps.findPilotByEmail(email);
            return "pilotLogin";
        }else if(cs.checkUser(email, password)){
            loginCustomer = cs.findCustomerByEmail(email);
            return "customerLogin";
        }
        return null;
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

    public Manager getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(Manager loginManager) {
        this.loginManager = loginManager;
    }

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

    public Pilot getLoginPilot() {
        return loginPilot;
    }

    public void setLoginPilot(Pilot loginPilot) {
        this.loginPilot = loginPilot;
    }

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public Customer getLoginCustomer() {
        return loginCustomer;
    }

    public void setLoginCustomer(Customer loginCustomer) {
        this.loginCustomer = loginCustomer;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Helicopter> getHelicopters() {
        return helicopters;
    }

    public void setHelicopters(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
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
