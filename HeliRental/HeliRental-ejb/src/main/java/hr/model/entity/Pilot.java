/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Xpan
 */
@Entity
public class Pilot extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean status;
    @OneToMany(mappedBy = "reservation",cascade = CascadeType.REFRESH)
    private Reservation reservation;

    public Pilot(){
        
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
