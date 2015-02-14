/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.interceptor;

import java.io.Serializable;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author 984228
 */
public class LoggingInterceptor implements Serializable{
    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {
        System.out.println("Entering "+context.getTarget().toString()+"->"+context.getMethod().getName());
        try {
            return context.proceed();
        } finally {
            System.out.println("Exiting "+context.getTarget().toString()+"->"+context.getMethod().getName());
        }

    }
}
