/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.ejb.CustomerService;
import hr.ejb.ManagerService;
import hr.ejb.PilotService;
import hr.ejb.ReservService;
import hr.ejb.StaffService;
import hr.model.entity.Customer;
import hr.model.entity.Manager;
import hr.model.entity.Pilot;
import hr.model.entity.Staff;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Queue;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xpan
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {
    @Resource(mappedName = "jms/dest")
    private javax.jms.Queue dest;
    @Inject
    @JMSConnectionFactory("jms/myQueue")
    private JMSContext context;
    
    private String email;
    private String password;
    private String userName;
    private String userType;
    private String msg;
    @EJB
    private CustomerService cs;
    @EJB
    private ManagerService ms;
    @EJB
    private PilotService ps;
    @EJB
    private ReservService rs;

    public LoginMB() {
    }
    
    public void sendMsg(){
        sendJMSMessageToDest(msg);
    }

    public String login() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        Customer c = cs.findLoginCustomer(email, password);
        Manager m = ms.findLoginManager(email, password);
        Pilot p = ps.findLoginPilot(email, password);
        if (c != null) {
            session.setAttribute("loggedUserId", c.getId());
            session.setAttribute("userName", c.getName());
            session.setAttribute("userType", "customer");
            userType = "customer";
            userName = c.getName();
            if (session.getAttribute("departure") != null) {
                rs.makeReservation();
                return "congrats";//redirect to reserve successfully!
            } else {
                
                return null;//redirect to customer homepage
            }
        } else if (m != null) {
            session.setAttribute("loggedUserId", m.getId());
            session.setAttribute("userName", m.getName());
            session.setAttribute("userType", "manager");
            session.setAttribute("branch", m.getBranch());
            userType = "manager";
            userName = m.getName();
            return null;//redirect to manager homepage
        } else if (p != null) {
            session.setAttribute("loggedUserId", p.getId());
            session.setAttribute("userName", p.getName());
            session.setAttribute("userType", "pilot");
            userType = "pilot";
            userName = p.getName();
            return null;//redirect to pilot homepage
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email or Password",null));
        return null;//invalid user account
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerService getCs() {
        return cs;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    public ReservService getRs() {
        return rs;
    }

    public void setRs(ReservService rs) {
        this.rs = rs;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private void sendJMSMessageToDest(String messageData) {
        context.createProducer().send(dest, messageData);
    }

}