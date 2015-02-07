/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import hr.ejb.CustomerService;
import hr.model.entity.Customer;
import hr.model.entity.Helicopter;
import hr.model.entity.Pilot;
import hr.model.entity.Reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 吕杨
 */
@Named(value="RegCustomer")
@SessionScoped
public class RegCustomer implements Serializable{
    @Inject
    private CustomerService customerService;
    private Customer customer;

    private String phone;
    private String address;
    
    //login
    private String userIdentity;
    private String userName;
    private String email;
    private String password;
    
    //register
    private String passwordConf;
    
    //reservation
    private String departure;
    private String arrival;
    private String passengers;
    private String departureTime;
    
    //schedule
    private List<Reservation> schedule = new ArrayList<Reservation>();
    
    //reservation history
    private List<Reservation> reserved = new ArrayList<Reservation>();
    
    //management
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
    
    public String makeReservation(){
        
        return null;
    }
    
    public String saveRegInfo() {
        customer = customerService.findCustomerByEmail(email);
        if (customer == null){
          //this is  new user -- do a create
          System.out.println("Registration Managed Bean creating new user ");
          customer = new Customer();
          customer.setEmail(email);
          customerService.create(customer);
      }
      else {
          //this user already exists so they can not register again
           FacesMessage facesMessage = new FacesMessage("Registration Failed - user already exists");
       facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
          System.out.println("Registration failed - user exists: "+customer.toString());
          
      }
      return "login";
      // return "lastpage";
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    public List<Reservation> getReserved() {
        return reserved;
    }

    public void setReserved(List<Reservation> reserved) {
        this.reserved = reserved;
    }

    public List<Helicopter> getHelicopters() {
        return helicopters;
    }

    public void setHelicopters(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
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

    public List<Reservation> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Reservation> schedule) {
        this.schedule = schedule;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    
    
}
