/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Xpan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Reservations.findByPilotId",
        query = "SELECT r FROM Reservation r WHERE r.pilot.id = :pilotId")
})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    private String depart;
    
    private String arrival;
    
    private int passengers;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reservDate;
    
    @ManyToOne
    @JoinColumn(name = "helicopter_id")
    private Helicopter helicopter;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "priceTable_id")
    private PriceTable price;
    
    @ManyToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }


    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PriceTable getPrice() {
        return price;
    }

    public void setPrice(PriceTable price) {
        this.price = price;
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

    public Date getReservDate() {
        return reservDate;
    }

    public void setReservDate(Date reservDate) {
        this.reservDate = reservDate;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

}
