/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import hr.ejb.BranchService;
import hr.ejb.ManagerService;
import hr.ejb.PilotService;
import hr.model.entity.Branch;
import hr.model.entity.Manager;
import hr.model.entity.Pilot;
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
    @EJB
    private ManagerService ms;
    @EJB
    private PilotService ps;

    public void testRun() {
//        Branch c = new Branch();
//        c.setLocation("Dallas");
//        bs.create(c);
//        c.setLocation("New York");
//        bs.create(c);
//        c.setLocation("Chicago");
//        bs.create(c);
//        c.setLocation("Des Moines");
//        bs.create(c);
//        c.setLocation("Los Angeles");
//        bs.create(c);
//        bs.find();
//          Manager m=new Manager();
//          m.setAddress("87 Burlington, Chicago");
//          Branch b=bs.findBranchWithLocation("Chicago");
//          m.setBranch(b);
//          m.setEmail("grace.pan92gmail.com");
//          m.setName("Grace");
//          m.setPassword("123456");
//          m.setPhone("3194006824");
//          m.setPosition("Project Manager");
//          m.setSalary(5000);
//          ms.create(m);
//          
//          m.setAddress("1003 Arlin, Los Angeles");
//          b=bs.findBranchWithLocation("Los Angeles");
//          m.setBranch(b);
//          m.setEmail("xsh@gmail.com");
//          m.setName("Vienna");
//          m.setPassword("123456");
//          m.setPhone("3194006824");
//          m.setPosition("HR Manager");
//          m.setSalary(5000);
//          ms.create(m);
//        Pilot p = new Pilot();
//        p.setAddress("87 Burlington, Chicago");
//        Branch b = bs.findBranchWithLocation("Chicago");
//        p.setBranch(b);
//        p.setEmail("tom@gmail.com");
//        p.setName("Tom");
//        p.setPassword("123456");
//        p.setPhone("6412399877");
//        p.setPosition("daytime pilot");
//        p.setReservations(null);
//        p.setSalary(4000);
//        p.setStatus(true);
//        ps.create(p);
        Pilot p = new Pilot();
        p.setAddress("87 Burlington, Chicago");
        Branch b = bs.findBranchWithLocation("Chicago");
        p.setBranch(b);
        p.setEmail("Kevin@gmail.com");
        p.setName("Kevin");
        p.setPassword("123456");
        p.setPhone("6412394657");
        p.setPosition("night-time pilot");
        p.setReservations(null);
        p.setSalary(5000);
        p.setStatus(true);
        ps.create(p);

    }

    public BranchService getBs() {
        return bs;
    }

    public void setBs(BranchService bs) {
        this.bs = bs;
    }

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

    public PilotService getPs() {
        return ps;
    }

    public void setPs(PilotService ps) {
        this.ps = ps;
    }

}
