/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.MB;

import hr.ejb.LocationService;
import hr.ejb.ManagerService;
import hr.ejb.PriceTableService;
import hr.model.entity.Branch;
import hr.model.entity.Location;
import hr.model.entity.PriceTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hasee
 */
@Named(value = "manageLocationMB")
@SessionScoped
public class ManageLocationMB implements Serializable {

    private String name;
    private Integer prepareTime;
    private Branch branch;
    @EJB
    private ManagerService ms;
    @EJB
    private LocationService ls;
    @EJB
    private PriceTableService pts;
    private List<Location> tables = new ArrayList<Location>();
    
    public ManageLocationMB() {
    }
    
        public void setProperty() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        tables = ls.findLocationWithBranch(branch);
    }
    
    public String addLocation(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        String msg = ls.addLocation(branch, name, prepareTime);
        if (msg.equals("add successfully")) {
            return null;//add successful
        }
        FacesContext.getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }
    
    public String deleteLocation(Location l){
        List<PriceTable> ptables=pts.findPriceTableWithLocation(l);
        for(int i=0;i<ptables.size();i++){
            pts.remove(ptables.get(0));
        }
        ls.remove(l);
        return null;//delete successful ajax list
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(Integer prepareTime) {
        this.prepareTime = prepareTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

    public LocationService getLs() {
        return ls;
    }

    public void setLs(LocationService ls) {
        this.ls = ls;
    }

    public PriceTableService getPts() {
        return pts;
    }

    public void setPts(PriceTableService pts) {
        this.pts = pts;
    }

    public List<Location> getTables() {
        return tables;
    }

    public void setTables(List<Location> tables) {
        this.tables = tables;
    }
    
}
