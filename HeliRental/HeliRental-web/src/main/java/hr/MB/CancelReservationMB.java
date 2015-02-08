/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.PscheduleService;
import hr.ejb.ReservService;
import hr.model.entity.Pschedule;
import hr.model.entity.Reservation;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author hasee
 */
@Named(value = "cancelReservationMB")
@SessionScoped
public class CancelReservationMB implements Serializable {

    private Reservation r;
    @EJB
    private PscheduleService ps;
    @EJB
    private ReservService rs;

    public CancelReservationMB() {
    }

    public String cancelReservation() {
        Date date = new Date();
        Pschedule p=ps.findScheduleWithReservation(r);
        if(p==null){
            return null;//cannot find Pschedule
        }
        if(p.getStartTime().before(date)||p.getStartTime().equals(date)){
            return null;//cannot cancel reservation
        }
        ps.remove(p);
        rs.remove(r);
        return null;//ajax updated reservlist
    }

    public Reservation getR() {
        return r;
    }

    public void setR(Reservation r) {
        this.r = r;
    }

    public PscheduleService getPs() {
        return ps;
    }

    public void setPs(PscheduleService ps) {
        this.ps = ps;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
    }
        
}
