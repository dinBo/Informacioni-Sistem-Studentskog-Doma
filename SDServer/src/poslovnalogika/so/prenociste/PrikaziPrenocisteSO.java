/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.prenociste;

import db.DBBroker;
import domen.Prenociste;
import domen.ZaduzenjeInventara;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class PrikaziPrenocisteSO extends OpstaSO{
    
    private Prenociste prenociste = null;
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        prenociste = (Prenociste) DBBroker.getInstance().pronadji((Prenociste) obj);
    }
    
    public Prenociste vratiPrenociste(){
        return prenociste;
    }
    
}
