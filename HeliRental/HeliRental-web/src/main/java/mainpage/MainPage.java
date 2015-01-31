package mainpage;




import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 984228
 */
@Named(value = "mainPage")
@SessionScoped
public class MainPage implements Serializable{
    
    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }
    
}
