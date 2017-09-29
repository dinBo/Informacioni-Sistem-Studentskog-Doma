/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.zaduzenje_inventara;

import db.DBBroker;
import domen.Prenociste;
import domen.StavkaZaduzenjaInventara;
import domen.ZaduzenjeInventara;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class SacuvajZaduzenjeInventaraSO extends OpstaSO{
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        Validacija.getInstance().proveriZaduzenje((ZaduzenjeInventara) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        ZaduzenjeInventara zaduzenje = (ZaduzenjeInventara) obj;
        int sifraZaduzenja = DBBroker.getInstance().vratiMaxVrednostIdentifikatora(zaduzenje);
        sifraZaduzenja++;
        zaduzenje.setSifraZaduzenja(sifraZaduzenja);
        DBBroker.getInstance().sacuvaj(zaduzenje);
        
        int sifraStavke = DBBroker.getInstance().vratiMaxVrednostIdentifikatora(new StavkaZaduzenjaInventara());
        sifraStavke++;
        for(int i=0; i<zaduzenje.getListaStavki().size();i++){
            StavkaZaduzenjaInventara stavka = zaduzenje.getListaStavki().get(i);
            stavka.setSifraStavke(sifraStavke);
            stavka.setZaduzenjeInventara(zaduzenje);
            if(stavka.getStatus().equals("insert")){
                DBBroker.getInstance().sacuvaj(stavka);
                sifraStavke++;
            }
        }
    }
    
}
