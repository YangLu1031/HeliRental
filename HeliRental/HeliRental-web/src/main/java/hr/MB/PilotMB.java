/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.model.entity.Reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 吕杨
 */
@Named(value="PilotMB")
@SessionScoped
public class PilotMB implements Serializable{
    
    
    private List<Reservation> schedule = new ArrayList<Reservation>();

    
    public List<Reservation> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Reservation> schedule) {
        this.schedule = schedule;
    }


    
}
