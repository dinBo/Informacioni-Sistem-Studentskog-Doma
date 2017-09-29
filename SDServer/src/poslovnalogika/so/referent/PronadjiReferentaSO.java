/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.referent;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Referent;
import java.util.ArrayList;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class PronadjiReferentaSO extends OpstaSO{
    
    ArrayList<OpstiDomenskiObjekat> odoLista;

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        Referent referent = (Referent) obj;
        String kriterijum = referent.getKorisnickoIme() + "/" + referent.getLozinka();
        odoLista = (ArrayList<OpstiDomenskiObjekat>) DBBroker.getInstance().vratiListuSaPretragom(kriterijum, referent);
    }
    
    public OpstiDomenskiObjekat vratiReferenta() {
        if (odoLista.isEmpty()) {
            return null;
        }
        OpstiDomenskiObjekat odo  = odoLista.get(0);
        return odo;
    }
    
}
