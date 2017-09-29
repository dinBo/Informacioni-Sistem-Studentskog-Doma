/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika.so.zaduzenje_inventara;

import db.DBBroker;
import domen.StavkaZaduzenjaInventara;
import domen.ZaduzenjeInventara;
import kontrola.Validacija;
import poslovnalogika.so.OpstaSO;

/**
 *
 * @author Bodin Todorovic
 */
public class IzmeniZaduzenjeInventaraSO extends OpstaSO{
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
        Validacija.getInstance().proveriZaduzenje((ZaduzenjeInventara) obj);
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        ZaduzenjeInventara zaduzenje = (ZaduzenjeInventara) obj;
        DBBroker.getInstance().izmeni(zaduzenje);
        int sifraStavke = DBBroker.getInstance().vratiMaxVrednostIdentifikatora(new StavkaZaduzenjaInventara());
        sifraStavke++;
        for(StavkaZaduzenjaInventara stavka : zaduzenje.getListaStavki()){
//            if(stavka.getStatus().equals("delete")){
//                DBBroker.getInstance().obrisi(stavka);
//            }else if(stavka.getStatus().equals("update")){
//                DBBroker.getInstance().izmeni(stavka);
//            } else if(stavka.getStatus().equals("insert")){
//                stavka.setSifraStavke(sifraStavke);
//                stavka.setZaduzenjeInventara(zaduzenje);
//                sifraStavke++;
//                DBBroker.getInstance().sacuvaj(stavka);
//            }
            
            if (stavka.getStatus().equals("insert")) {
                stavka.setSifraStavke(sifraStavke);
                stavka.setZaduzenjeInventara(zaduzenje);
                sifraStavke++;
                DBBroker.getInstance().sacuvaj(stavka);
            } else if(stavka.getStatus().equals("update")){
                DBBroker.getInstance().izmeni(stavka);
            } else if(stavka.getStatus().equals("delete")){
                DBBroker.getInstance().obrisi(stavka);
            }
        }
        
    }
}
