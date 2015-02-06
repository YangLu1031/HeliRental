/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Xpan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "PriceTable.findPriceTableByRoutine", query = "select pt from PriceTable pt where pt.departure.id=:departure and pt.arrival.id=:arrival"),})
public class PriceTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Location departure;
    @ManyToOne
    @JoinColumn(name = "arrival_id")
    private Location arrival;
    private boolean day_night;
    private Integer duration;
    private Double sellprice;
    private Double pilotBonus;
    private Double expense;
    private Integer timezoneDiff;

    public PriceTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDay_night() {
        return day_night;
    }

    public void setDay_night(boolean day_night) {
        this.day_night = day_night;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public Double getSellprice() {
        return sellprice;
    }

    public void setSellprice(Double sellprice) {
        this.sellprice = sellprice;
    }

    public Double getPilotBonus() {
        return pilotBonus;
    }

    public void setPilotBonus(Double pilotBonus) {
        this.pilotBonus = pilotBonus;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Integer getTimezoneDiff() {
        return timezoneDiff;
    }

    public void setTimezoneDiff(Integer timezoneDiff) {
        this.timezoneDiff = timezoneDiff;
    }

}
