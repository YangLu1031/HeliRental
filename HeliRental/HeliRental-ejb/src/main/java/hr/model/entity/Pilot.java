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
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Xpan
 */
@Entity
public class Pilot extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean status;
    private String type;
    @OneToMany(mappedBy = "pilot",cascade = CascadeType. ALL,fetch=FetchType.EAGER)
    private List<Pschedule> schedules;

    public Pilot(){
        
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Pschedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Pschedule> schedules) {
        this.schedules = schedules;
    }

}
