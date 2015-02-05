/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Xpan
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {

    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
    }
    
}
