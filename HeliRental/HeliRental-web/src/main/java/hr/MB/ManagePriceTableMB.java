/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

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
@Named(value = "managePriceTableMB")
@SessionScoped
public class ManagePriceTableMB implements Serializable {

    private String location_1;
    private String location_2;
    private Double sellprice;
    private Double expense;
    private Integer duration;
    private Branch branch;
    @EJB
    private ManagerService ms;
    @EJB
    private PriceTableService pts;
    private List<PriceTable> tables = new ArrayList<PriceTable>();

    public ManagePriceTableMB() {
    }

    public void setProperty() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        tables = pts.findPriceTableGroupWithBranch(branch);
    }

    public String addPriceTable() {
        if (location_1.equals(location_2)) {
            return null;//please select two different locations
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("loggedUserId");
        branch = ms.findManagerWithId(id).getBranch();
        String msg = pts.addPriceTable(branch, location_1, location_2, duration, sellprice, expense);
        if (msg.equals("add successfully")) {
            return null;//add successful ajax list
        }
        FacesContext.getCurrentInstance().addMessage("id", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
        return null;
    }

    public String deletePriceTable(PriceTable pt) {
        pts.remove(pt);
        return null;//delete successful ajax list
    }

    public void updatePrice(PriceTable pt) {
        pt.setSellprice(sellprice);
        pts.edit(pt);
    }

    public List<PriceTable> getTables() {
        return tables;
    }

    public void setTables(List<PriceTable> tables) {
        this.tables = tables;
    }


    public String getLocation_1() {
        return location_1;
    }

    public void setLocation_1(String location_1) {
        this.location_1 = location_1;
    }

    public String getLocation_2() {
        return location_2;
    }

    public void setLocation_2(String location_2) {
        this.location_2 = location_2;
    }

    public Double getSellprice() {
        return sellprice;
    }

    public void setSellprice(Double sellprice) {
        this.sellprice = sellprice;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public PriceTableService getPts() {
        return pts;
    }

    public void setPts(PriceTableService pts) {
        this.pts = pts;
    }

}
