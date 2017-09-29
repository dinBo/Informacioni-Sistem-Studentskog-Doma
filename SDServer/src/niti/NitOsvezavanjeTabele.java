/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.GlavnaServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bodin Todorovic
 */
public class NitOsvezavanjeTabele extends Thread{
    GlavnaServerskaForma sf;

    public NitOsvezavanjeTabele(GlavnaServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {

        while (true) {            
            sf.srediTabelu();
            
            try {
                sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitOsvezavanjeTabele.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
