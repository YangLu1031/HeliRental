/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import hr.model.entity.Helicopter;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 吕杨
 */
@Named(value="managerMB")
@SessionScoped
public class ManagerMB implements Serializable{
    private String heliName;
    private List<Helicopter> helicopters = new ArrayList<Helicopter>();
    private int id;
    private Integer capacity;
    private Double fixedcost;
    
    public ManagerMB(){
        setProperty();
    }
    public void setProperty(){
        Helicopter helicopter = new Helicopter();
        Helicopter helicopter1 = new Helicopter();
        helicopter.setCapacity(4);
        helicopter.setFixedcost(1.2);
        helicopter.setStatus(true);
        helicopter1.setCapacity(3);
        helicopter1.setFixedcost(1.2);
        helicopter1.setStatus(true);
        helicopters.add(helicopter);
        helicopters.add(helicopter1);
        
    }
    
    public void deleteHeli(Helicopter h){

        if(helicopters.contains(h))
        {
            helicopters.remove(h);
        }
        //return "managerLogin";
    }
    
    public String addHeli(){
        Helicopter helicopter = new Helicopter();
        helicopter.setCapacity(this.capacity);
        helicopter.setFixedcost(this.fixedcost);
        helicopter.setId(this.id);        
        helicopters.add(helicopter);
        return "managerLogin";
    }
    public String getHeliName() {
        return heliName;
    }

    public void setHeliName(String heliName) {
        this.heliName = heliName;
    }

    public List<Helicopter> getHelicopters() {
        return helicopters;
    }

    public void setHelicopters(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getFixedcost() {
        return fixedcost;
    }

    public void setFixedcost(Double fixedcost) {
        this.fixedcost = fixedcost;
    }

    
}
