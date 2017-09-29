/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.inventar;

import db.DBBroker;
import domen.Inventar;
import domen.OpstiDomenskiObjekat;
import domen.Referent;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class UcitajListuInventaraSO extends OpstaSO{
    
    List<OpstiDomenskiObjekat> lista;

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        lista = DBBroker.getInstance().vratiListu((Inventar) obj);
    }

    public List<OpstiDomenskiObjekat> vratiListuInventara() {
        return lista;
    }
    
}
