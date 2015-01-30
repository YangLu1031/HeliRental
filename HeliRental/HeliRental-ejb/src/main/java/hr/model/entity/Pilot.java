/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.model.entity;

import java.io.Serializable;
import java.util.List;
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
    @OneToMany(mappedBy = "pilot",cascade = CascadeType.REFRESH)
    private List<Reservation> reservations;

    public Pilot(){
        
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
