/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import hr.model.entity.Reservation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xpan
 */
@Named(value = "CustomerMB")
@SessionScoped
public class CustomerMB implements Serializable {

    private List<Reservation> reserved = new ArrayList<Reservation>();
    
    public CustomerMB() {
    }

    public List<Reservation> getReserved() {
        return reserved;
    }

    public void setReserved(List<Reservation> reserved) {
        this.reserved = reserved;
    }

    
}
