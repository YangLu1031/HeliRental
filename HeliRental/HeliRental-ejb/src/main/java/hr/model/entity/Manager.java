/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Xpan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Manager.findByEmail",
        query = "SELECT m FROM Manager m WHERE m.email = :emailAddress"),
    @NamedQuery(name = "BranchId.findByManagerId",
        query = "SELECT m.branch.id FROM Manager m WHERE m.id = :id")
})
public class Manager extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    public Manager(){
    }
    

}
