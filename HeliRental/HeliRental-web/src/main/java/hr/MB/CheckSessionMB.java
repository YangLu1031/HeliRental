/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.utility.SessionChecker;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Xpan
 */
@Named(value = "checkSessionMB")
@SessionScoped
public class CheckSessionMB implements Serializable {

    private String session;
    private SessionChecker sc;
    private String name;

    public CheckSessionMB() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession s = (HttpSession) ec.getSession(true);
        if (s.getAttribute("loggedUserId")!=null) {
            this.session = (String) s.getAttribute("userType");
            this.name=(String) s.getAttribute("userName");
        } else {
            this.session = null;
        }
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public SessionChecker getSc() {
        return sc;
    }

    public void setSc(SessionChecker sc) {
        this.sc = sc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
