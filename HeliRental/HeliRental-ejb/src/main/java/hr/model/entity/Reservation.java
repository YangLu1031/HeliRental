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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Xpan
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date departureTime;
    @Temporal(TemporalType.DATE)
    private Date arrivalTime;
    private int passengers;
    @Temporal(TemporalType.DATE)
    private Date reservTime;
    @OneToOne
    private Pschedule pSchedule;
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
    @JoinColumn(name = "location_id")
    private Location departure;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location arrival;

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public Pschedule getpSchedule() {
        return pSchedule;
    }

    public void setpSchedule(Pschedule pSchedule) {
        this.pSchedule = pSchedule;
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

    public Date getReservTime() {
        return reservTime;
    }

    public void setReservTime(Date reservTime) {
        this.reservTime = reservTime;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getArrival() {
        return arrival;
    }

    public void setArrival(Location arrival) {
        this.arrival = arrival;
    }

}
