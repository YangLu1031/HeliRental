/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.MB;

import hr.utility.EmailSender;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author 984228
 */
@Named(value = "contactMB")
@SessionScoped
public class ContactMB implements Serializable {

    private String title;
    private String email;
    private String text;
    /**
     * Creates a new instance of ContactMB
     */
    public ContactMB() {
    }
    
    public String submitConsult(){
        EmailSender es = new EmailSender();
        es.sendConsultEmail(title, email, text);
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
