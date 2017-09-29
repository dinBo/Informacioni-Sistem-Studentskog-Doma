/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.prenociste;

import db.DBBroker;
import domen.Prenociste;
import domen.Stanar;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class SacuvajPrenocisteSO extends OpstaSO{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        Validacija.getInstance().proveriPrenociste((Prenociste) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DBBroker.getInstance().sacuvaj((Prenociste) obj);
    }
    
}
