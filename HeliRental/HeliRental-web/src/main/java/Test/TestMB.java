/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import hr.boundary.BranchService;
import hr.boundary.CustomerService;
import hr.boundary.HeliService;
import hr.boundary.LocationService;
import hr.boundary.ManagerService;
import hr.boundary.PilotService;
import hr.boundary.PriceTableService;
import hr.model.entity.Branch;
import hr.model.entity.Customer;
import hr.model.entity.Helicopter;
import hr.model.entity.Location;
import hr.model.entity.Manager;
import hr.model.entity.Pilot;
import hr.model.entity.PriceTable;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @EJB
    private LocationService ls;
    @EJB
    private CustomerService cs;
    @EJB
    private HeliService hs;
    @EJB
    private PriceTableService pts;

    public void testRun() {
        Branch b = new Branch();
//        b.setName("Dallas");
//        b.setTimezone("cst");
//        bs.create(b);
//        b.setName("New York");
//        b.setTimezone("est");
//        bs.create(b);
//        try {
//            b.setName("Chicago");//validation constraints call callbacks @PrePersist
//        b.setTimezone("est");
//            bs.create(b);
//        }catch(ValidationException e){
//            System.err.println("branch name is null: " + e.getMessage());
//        }
//        b.setName("Des Moines");
//        b.setTimezone("cst");
//        bs.create(b);
//        b.setName("Los Angeles");
//        b.setTimezone("pst");
//        bs.create(b);       

//        Location l = new Location();
//        l.setName("Grace Hospital");
//        l.setPrepareTime(20);
//        Branch b=bs.findBranchWithName("New York");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("Arlin Square");
//        l.setPrepareTime(25);
//        Branch b = bs.findBranchWithName("Dallas");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("Twin Skyscraper");
//        l.setPrepareTime(20);
//        Branch b = bs.findBranchWithName("Dallas");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("Mason Hospital");
//        l.setPrepareTime(20);
//        Branch b = bs.findBranchWithName("Los Angeles");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("George Factory");
//        l.setPrepareTime(15);
//        b = bs.findBranchWithName("Chicago");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("Willis Tower");
//        l.setPrepareTime(15);
//        b = bs.findBranchWithName("Chicago");
//        l.setBranch(b);
//        ls.create(l);
//        l.setName("Jordon Creek Mall");
//        l.setPrepareTime(15);
//        b = bs.findBranchWithName("Des Moines");
//        l.setBranch(b);
//        ls.create(l);
//          Manager m=new Manager();
//          m.setAddress("87 Burlington, Chicago");
//          Branch b=bs.findBranchWithName("Chicago");
//          m.setBranch(b);
//          m.setEmail("grace.pan92gmail.com");
//          m.setName("Grace");
//          m.setPassword("123456");
//          m.setPhone("3194006824");
//          m.setPosition("Project Manager");
//          m.setSalary(5000D);
//          ms.create(m);         
//          m.setAddress("1003 Arlin, Los Angeles");
//          b=bs.findBranchWithName("Los Angeles");
//          m.setBranch(b);
//          m.setEmail("xsh@gmail.com");
//          m.setName("Vienna");
//          m.setPassword("123456");
//          m.setPhone("3194006824");
//          m.setPosition("HR Manager");
//          m.setSalary(5000D);
//          ms.create(m);
//          m.setAddress("897 Amerherst, New York");
//          b=bs.findBranchWithName("New York");
//          m.setBranch(b);
//          m.setEmail("emma@gmail.com");
//          m.setName("Emma");
//          m.setPassword("123456");
//          m.setPhone("6414006824");
//          m.setPosition("HR Manager");
//          m.setSalary(6000D);
//          ms.create(m);
//          m.setAddress("301 Jefferson, Des Moines");
//          b=bs.findBranchWithName("Des Moines");
//          m.setBranch(b);
//          m.setEmail("yang@gmail.com");
//          m.setName("Yang");
//          m.setPassword("123456");
//          m.setPhone("6414006824");
//          m.setPosition("Manager");
//          m.setSalary(6000D);
//          ms.create(m);
//          m.setAddress("457 Parkway, Dallas");
//          b=bs.findBranchWithName("Dallas");
//          m.setBranch(b);
//          m.setEmail("coco@gmail.com");
//          m.setName("Coco");
//          m.setPassword("123456");
//          m.setPhone("6414006824");
//          m.setPosition("Project Manager");
//          m.setSalary(6000D);
//          ms.create(m);
//        Pilot p = new Pilot();
//        p.setAddress("87 Burlington, Chicago");
//        Branch b = bs.findBranchWithName("Chicago");
//        p.setBranch(b);
//        p.setEmail("tom@gmail.com");
//        p.setName("Tom");
//        p.setPassword("123456");
//        p.setPhone("6412399877");
//        p.setPosition("daytime pilot");
//        p.setSalary(4000D);
//        p.setStatus(true);
//        p.setType("day duty");
//        ps.create(p);
//        p.setAddress("87 Burlington, Chicago");
//        b = bs.findBranchWithName("Chicago");
//        p.setBranch(b);
//        p.setEmail("Kevin@gmail.com");
//        p.setName("Kevin");
//        p.setPassword("123456");
//        p.setPhone("6412394657");
//        p.setPosition("night-time pilot");
//        p.setType("night duty");
//        p.setSalary(5000D);
//        p.setStatus(true);
//        ps.create(p);
//        p.setAddress("301 Jefferson, Des Moines");
//        b = bs.findBranchWithName("Des Moines");
//        p.setBranch(b);
//        p.setEmail("pan@gmail.com");
//        p.setName("Pan");
//        p.setPassword("123456");
//        p.setPhone("3194006824");
//        p.setPosition("daytime pilot");
//        p.setType("day duty");
//        p.setSalary(4000D);
//        p.setStatus(true);
//        ps.create(p);
//        p.setAddress("301 Jefferson, Des Moines");
//        b = bs.findBranchWithName("Des Moines");
//        p.setBranch(b);
//        p.setEmail("lu@gmail.com");
//        p.setName("Lu");
//        p.setPassword("123456");
//        p.setPhone("3194006824");
//        p.setPosition("night-time pilot");
//        p.setType("night duty");
//        p.setSalary(5000D);
//        p.setStatus(true);
//        ps.create(p);
//        p.setAddress("301 Jefferson, Des Moines");
//        b = bs.findBranchWithName("Des Moines");
//        p.setBranch(b);
//        p.setEmail("peter@gmail.com");
//        p.setName("peter");
//        p.setPassword("123456");
//        p.setPhone("3194006824");
//        p.setPosition("daytime pilot");
//        p.setType("day duty");
//        p.setSalary(4000D);
//        p.setStatus(true);
//        ps.create(p);
//        Customer c = new Customer();
//        c.setName("Grace");
//        c.setEmail("grace@gmail.com");
//        c.setAddress("1000 Fairfiled");
//        c.setPassword("123456");
//        c.setPhone("3194000000");
//        cs.create(c);
//
//        Helicopter h=new Helicopter();
//        h.setCapacity(5);
//        h.setFixedcost(20D);
//        h.setStatus(true);
//        h.setType("in state");
//        Branch b = bs.findBranchWithName("Des Moines");
//        h.setBranch(b);
//        hs.create(h);
//        h.setCapacity(3);
//        h.setFixedcost(20D);
//        h.setStatus(true);
//        h.setType("in state");
//        b = bs.findBranchWithName("Des Moines");
//        h.setBranch(b);
//        hs.create(h);
//        h.setCapacity(3);
//        h.setFixedcost(30D);
//        h.setStatus(true);
//        h.setType("out state");
//        b = bs.findBranchWithName("Des Moines");
//        h.setBranch(b);
//        hs.create(h);
//        
//        PriceTable pt=new PriceTable();
//        pt.setTimezoneDiff(1);
//        Location l=ls.findLocationWithName("Grace Hospital");
//        pt.setArrival(l);
//        l=ls.findLocationWithName("Jordon Creek Mall");
//        pt.setDeparture(l);
//        pt.setDay_night(true);//true for day
//        pt.setDuration(90);
//        pt.setExpense(30D);
//        pt.setPilotBonus(20D);
//        pt.setSellprice(60D);
//        pts.create(pt);
//        pt.setTimezoneDiff(1);
//        l=ls.findLocationWithName("Grace Hospital");
//        pt.setArrival(l);
//        l=ls.findLocationWithName("Jordon Creek Mall");
//        pt.setDeparture(l);
//        pt.setDay_night(false);//true for day
//        pt.setDuration(90);
//        pt.setExpense(30D);
//        pt.setPilotBonus(30D);
//        pt.setSellprice(70D);
//        pts.create(pt);
//        pt.setTimezoneDiff(0);
//        l=ls.findLocationWithName("Arlin Square");
//        pt.setArrival(l);
//        l=ls.findLocationWithName("Jordon Creek Mall");
//        pt.setDeparture(l);
//        pt.setDay_night(true);//true for day
//        pt.setDuration(120);
//        pt.setExpense(40D);
//        pt.setPilotBonus(20D);
//        pt.setSellprice(70D);
//        pts.create(pt);
//        pt.setTimezoneDiff(0);
//        l=ls.findLocationWithName("Arlin Square");
//        pt.setArrival(l);
//        l=ls.findLocationWithName("Jordon Creek Mall");
//        pt.setDeparture(l);
//        pt.setDay_night(false);//true for day
//        pt.setDuration(120);
//        pt.setExpense(40D);
//        pt.setPilotBonus(30D);
//        pt.setSellprice(80D);
//        pts.create(pt);
//        pt.setTimezoneDiff(0);
//        l=ls.findLocationWithName("Willis Tower");
//        pt.setArrival(l);
//        l=ls.findLocationWithName("George Factory");
//        pt.setDeparture(l);
//        pt.setDay_night(true);//true for day
//        pt.setDuration(20);
//        pt.setExpense(10D);
//        pt.setPilotBonus(10D);
//        pt.setSellprice(25D);
//        pts.create(pt);
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

    public LocationService getLs() {
        return ls;
    }

    public void setLs(LocationService ls) {
        this.ls = ls;
    }

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public HeliService getHs() {
        return hs;
    }

    public void setHs(HeliService hs) {
        this.hs = hs;
    }

    public PriceTableService getPts() {
        return pts;
    }

    public void setPts(PriceTableService pts) {
        this.pts = pts;
    }

}
