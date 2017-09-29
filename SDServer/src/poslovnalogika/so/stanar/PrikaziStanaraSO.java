/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.stanar;

import db.DBBroker;
import domen.Prenociste;
import domen.Stanar;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class PrikaziStanaraSO extends OpstaSO{
    
    private Stanar stanar = null;
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        stanar = (Stanar) DBBroker.getInstance().pronadji((Stanar) obj);
    }
    
    public Stanar vratiStanara(){
        return stanar;
    }
    
}
