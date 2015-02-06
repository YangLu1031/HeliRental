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
import javax.persistence.OneToOne;

/**
 *
 * @author Xpan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pilot.findByEmail",
        query = "SELECT p FROM Pilot p WHERE p.email = :emailAddress"),  
    @NamedQuery(name = "Pilots.findByBranchId",
        query = "SELECT p FROM Pilot p WHERE p.branch.id = :id")         
})
public class Pilot extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;


    public Pilot(){
        
    }

    



}
