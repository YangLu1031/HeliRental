/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import hr.ejb.BranchService;
import hr.ejb.CustomerService;
import hr.ejb.HeliService;
import hr.ejb.ManagerService;
import hr.ejb.PilotService;
import hr.model.entity.Branch;
import hr.model.entity.Customer;
import hr.model.entity.Helicopter;
import hr.model.entity.Manager;
import hr.model.entity.Pilot;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author 984228
 */
@javax.ejb.Singleton
@Startup
public class Singleton {
    @Inject
    private BranchService bs;
    private Branch branch;
    @Inject
    private ManagerService ms;
    private Manager manager;
    
    @Inject
    private PilotService ps;
    private Pilot pilot;
    
    @Inject
    private HeliService hs;
    private Helicopter helicopter;
    
    @Inject
    private CustomerService cs;
    private Customer customer;
    
    @PostConstruct
    private void initApp(){
        System.out.println("StartSingleton in initApp()");
        for (int i=1; i < 3; i++){
            branch = new Branch();
            branch.setName("branch"+i);
            this.bs.create(branch);
        }
        
        for (int i=1; i < 4; i++){
            manager = new Manager();
            manager.setName("manager"+i);
            manager.setEmail("manager"+i+"@mum.edu");
            manager.setPassword("manager"+i);
            manager.setBranch(branch);
            this.ms.create(manager);
            
            helicopter = new Helicopter();
            helicopter.setType("helicopter"+i);
            helicopter.setCapacity(i);
            helicopter.setFixedcost(5.0);
            helicopter.setBranch(branch);
            this.hs.create(helicopter);
            
            pilot = new Pilot();
            pilot.setName("pilot"+i);
            pilot.setEmail("pilot"+i+"@mum.edu");
            pilot.setPassword("pilot"+i);
            pilot.setHelicopter(helicopter);
            pilot.setBranch(branch);
            this.ps.create(pilot);
            
            
        }      
            customer = new Customer();
            customer.setName("customer");
            customer.setEmail("customer@mum.edu");
            customer.setPassword("customer");
            this.cs.create(customer);
        
    }
    
}
