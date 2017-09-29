/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.prenociste;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class PronadjiPrenocistaSO extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> lista;
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        //nema preduslova
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        List<Object> parametri = (List<Object>) obj;
        lista = DBBroker.getInstance().vratiListuSaPretragom((String)parametri.get(0),(OpstiDomenskiObjekat)parametri.get(1));
    }
    
    public List<OpstiDomenskiObjekat> vratiListuPrenocista(){
        return lista;
    }
    
}
