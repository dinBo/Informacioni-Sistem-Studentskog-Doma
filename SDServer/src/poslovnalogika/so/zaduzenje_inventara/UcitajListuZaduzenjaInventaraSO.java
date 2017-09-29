/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.zaduzenje_inventara;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import domen.ZaduzenjeInventara;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class UcitajListuZaduzenjaInventaraSO extends OpstaSO{
    
    List<OpstiDomenskiObjekat> lista;

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        lista = DBBroker.getInstance().vratiListu((ZaduzenjeInventara) obj);
    }
    
    public List<OpstiDomenskiObjekat> vratiZaduzenjaInventara() {
        return lista;
    }
    
}
