package hr.MB;




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
@Named(value="HomeMB")
@SessionScoped
public class HomeMB implements Serializable{
    @Inject
    private BranchService bs;
    
    @Inject
    private ManagerService ms;
    private Manager loginManager;
    
    @Inject
    private PilotService ps;
    private Pilot loginPilot;   
    
    @Inject
    private CustomerService cs;
    private Customer loginCustomer;
    
    @Inject
    private ReservService rs;

    
    //login 
    private String email;
    private String password;
    
    //already signed in user
    private String userIdentity="customer";
    private String userName="yang";
    
    //reservation
    private String departure;
    private String arrival;
    private String passengers;
    private String departureTime;
    private Date currentDate = new Date();
    
    
    public String signInUser(){
        
        return null;
    }
    
    public String signUpUser(){
        
        return null;
    }
    
    public String editProfile(){
        
        return null;
    }
    
    public String logOut(){
        
        return null;
    }
    
    public String makeReservation(){

        return null;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    

    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
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

}
