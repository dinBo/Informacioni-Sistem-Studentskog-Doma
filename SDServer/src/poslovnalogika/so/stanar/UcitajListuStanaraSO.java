/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.stanar;

import db.DBBroker;
import domen.Fakultet;
import domen.OpstiDomenskiObjekat;
import domen.Stanar;
import java.util.ArrayList;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class UcitajListuStanaraSO extends OpstaSO{
    
    List<OpstiDomenskiObjekat> lista;

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        lista = DBBroker.getInstance().vratiListu((Stanar) obj);
    }

    public List<OpstiDomenskiObjekat> vratiListuStanara() {
        return lista;
    }
    
}
