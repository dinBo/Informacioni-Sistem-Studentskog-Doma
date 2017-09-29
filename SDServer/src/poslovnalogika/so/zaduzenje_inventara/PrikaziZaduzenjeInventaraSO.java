/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.zaduzenje_inventara;

import db.DBBroker;
import domen.ZaduzenjeInventara;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class PrikaziZaduzenjeInventaraSO extends OpstaSO{
    
    private ZaduzenjeInventara zi = null;
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        zi = (ZaduzenjeInventara) DBBroker.getInstance().pronadji((ZaduzenjeInventara) obj);
    }
    
    public ZaduzenjeInventara vratiZaduzenje(){
        return zi;
    }
    
}
