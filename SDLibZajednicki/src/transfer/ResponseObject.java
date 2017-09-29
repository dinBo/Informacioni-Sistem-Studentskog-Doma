/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import util.EnumResponseStatus;

/**
 *
 * @author Bodin Todorovic
 */
public class ResponseObject implements Serializable {

    private EnumResponseStatus responseStatus;
    private String message;
    private Object response;

    public ResponseObject() {
    }

    public ResponseObject(EnumResponseStatus responseStatus, String message, Object response) {
        this.responseStatus = responseStatus;
        this.message = message;
        this.response = response;
    }

   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EnumResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(EnumResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
    
}
