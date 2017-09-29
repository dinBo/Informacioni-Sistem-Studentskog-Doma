/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Bodin Todorovic
 */
public class RequestObject implements Serializable {

    private int action;
    private Object request;

    public RequestObject() {
    }

    public RequestObject(int action, Object param) {
        this.action = action;
        this.request = param;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
    
}
