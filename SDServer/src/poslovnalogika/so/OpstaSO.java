/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so;

import db.DBBroker;

/**
 *
 * @author Bodin Todorovic
 */
public abstract class OpstaSO {
    
    public final synchronized void izvrsenjeSO(Object obj) throws Exception {
        try {
            ucitajDriver();
            otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            commitTransakcije();
        } catch (Exception ex) {
            rollbackTransakcije();
            throw ex;
        } finally {
            zatvoriKonekciju();
        }
        
    }

    private void ucitajDriver() throws Exception {
        DBBroker.getInstance().ucitajDriver();
    }

    private void otvoriKonekciju() throws Exception {
        DBBroker.getInstance().otvoriKonekciju();
    }

    
    protected abstract void proveriPreduslov(Object obj) throws Exception;

    
    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws Exception;

    private void commitTransakcije() throws Exception {
        DBBroker.getInstance().potvrdiTransakciju();
    }

    private void rollbackTransakcije() throws Exception {
        DBBroker.getInstance().ponistiTransakciju();
    }

    private void zatvoriKonekciju() throws Exception {
        DBBroker.getInstance().zatvoriKonekciju();
    }
}
