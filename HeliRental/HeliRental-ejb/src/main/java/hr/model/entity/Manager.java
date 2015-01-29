/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Xpan
 */
@Entity
public class Manager extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    public Manager(){
    }
}
