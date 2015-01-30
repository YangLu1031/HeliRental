/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import hr.ejb.BranchService;
import hr.model.entity.Branch;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Xpan
 */
@Named(value = "testMB")
@RequestScoped
public class TestMB implements Serializable {

    @EJB
    private BranchService bs;
    
    public void testRun() {
        Branch c=new Branch();
        c.setLocation("Dallas");
        bs.create(c);

        
    }

    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }
    
}
