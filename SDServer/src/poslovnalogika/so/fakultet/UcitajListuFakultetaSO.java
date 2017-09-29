/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.fakultet;

import db.DBBroker;
import domen.Fakultet;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class UcitajListuFakultetaSO extends OpstaSO{

    private List<OpstiDomenskiObjekat> lista;
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema uslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        lista = DBBroker.getInstance().vratiListu((Fakultet) obj);
    }

    public List<OpstiDomenskiObjekat> vratiListuFakulteta() {
        return lista;
    }
    
}
