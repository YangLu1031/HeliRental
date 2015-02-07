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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Xpan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pilot.findAllASCByBranch", query = "select p from Pilot p where p.branch=:branch Order BY size(p.schedules) asc"),
    @NamedQuery(name = "Pilot.findPilotById", query = "select s from Pilot s where s.id=:id"),
    @NamedQuery(name = "Pilot.findLoginPilot", query = "select s from Pilot s where s.email=:email and s.password=:password"),})
public class Pilot extends Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.EAGER)
    private List<Pschedule> schedules;

    public Pilot() {

    }

    public List<Pschedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Pschedule> schedules) {
        this.schedules = schedules;
    }

}
