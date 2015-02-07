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
    @NamedQuery(name = "Manager.findManagerById", query = "select s from Manager s where s.id=:id"),
    @NamedQuery(name = "Manager.findLoginManager", query = "select s from Manager s where s.email=:email and s.password=:password"),
})
public class Manager extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    public Manager(){
    }
}
