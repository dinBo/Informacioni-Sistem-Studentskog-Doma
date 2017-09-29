/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.stanar;

import db.DBBroker;
import domen.Stanar;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class ObrisiStanaraSO extends OpstaSO{
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DBBroker.getInstance().obrisi((Stanar) obj);
    }
    
}
