package mainpage;




import hr.ejb.*;
import hr.model.entity.*;
import java.io.Serializable;
import java.text.ParseException;
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
    private BranchService bs;
    
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
    
    @Inject
    private ReservService rs;

    
    //login 
    private String email;
    private String password;
    
    //search reservation
    private String depart;
    private String arrival;
    private Integer passengers;
    private Date selectDate;
    private Date currentDate = new Date();
    
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
    private List<Reservation> schedule = new ArrayList<Reservation>();
    
    //searchResult
    private Collection showResv;    
    private boolean status;
    
    
    public String loginUser(){
        
        if(ms.checkUser(email, password)){
            loginManager = ms.findManagerByEmail(email);
            branchId = ms.findBranchIdByManagerId(loginManager.getId());
            helicopters = hs.findHelicoptersByBranchId(branchId);
            pilots = ps.findPilotsByBranchId(branchId);
            return "managerLogin";
        }else if(ps.checkUser(email, password)){
            loginPilot = ps.findPilotByEmail(email);
            schedule = rs.findReservationsByPilotId(loginPilot.getId());
            return "pilotLogin";
        }else if(cs.checkUser(email, password)){
            loginCustomer = cs.findCustomerByEmail(email);
            return "customerLogin";
        }
        return null;
    }
    
    public String checkAvailablity() throws ParseException{

//        showResv = rs.findPilotHeliByReserveDate(selectDate);
        if(showResv!=null){
            status = true;
        }else{
            status = false; 
        }
        return "searchReslt";
    }
    
    public String deleteHeli(Helicopter h){

        if(helicopters.contains(h))
        {
            hs.remove(h);
            helicopters.remove(h);
        }
        return "managerLogin";
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
        return "managerLogin";
    }
    
    public String deletePilot(Pilot p){
        if(pilots.contains(p))
        {
            ps.remove(p);
            pilots.remove(p);
        }
        return "managerLogin";
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
        return "managerLogin";
    }    

    public List<Reservation> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Reservation> schedule) {
        this.schedule = schedule;
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
    
    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }


    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }

    public Collection getShowResv() {
        return showResv;
    }

    public void setShowResv(Collection showResv) {
        this.showResv = showResv;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }
    
    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
