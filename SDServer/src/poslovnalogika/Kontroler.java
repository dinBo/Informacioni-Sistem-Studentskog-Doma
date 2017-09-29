/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnalogika;

import db.DBBroker;
import domen.Fakultet;
import domen.Inventar;
import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import domen.Referent;
import domen.Stanar;
import domen.ZaduzenjeInventara;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poslovnalogika.so.OpstaSO;
import poslovnalogika.so.fakultet.UcitajListuFakultetaSO;
import poslovnalogika.so.inventar.UcitajListuInventaraSO;
import poslovnalogika.so.prenociste.IzmeniPrenocisteSO;
import poslovnalogika.so.prenociste.PrikaziPrenocisteSO;
import poslovnalogika.so.prenociste.PronadjiPrenocistaSO;
import poslovnalogika.so.prenociste.SacuvajPrenocisteSO;
import poslovnalogika.so.referent.PronadjiReferentaSO;
import poslovnalogika.so.referent.UcitajListuReferenata;
import poslovnalogika.so.stanar.IzmeniStanaraSO;
import poslovnalogika.so.stanar.ObrisiStanaraSO;
import poslovnalogika.so.stanar.PrikaziStanaraSO;
import poslovnalogika.so.stanar.PronadjiStanareSO;
import poslovnalogika.so.stanar.SacuvajStanaraSO;
import poslovnalogika.so.stanar.UcitajListuStanaraSO;
import poslovnalogika.so.zaduzenje_inventara.IzmeniZaduzenjeInventaraSO;
import poslovnalogika.so.zaduzenje_inventara.PrikaziZaduzenjeInventaraSO;
import poslovnalogika.so.zaduzenje_inventara.PronadjiZaduzenjaInventaraSO;
import poslovnalogika.so.zaduzenje_inventara.SacuvajZaduzenjeInventaraSO;
import poslovnalogika.so.zaduzenje_inventara.UcitajListuZaduzenjaInventaraSO;

/**
 *
 * @author Bodin Todorovic
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }

        return instanca;
    }

    public List<OpstiDomenskiObjekat> vratiListuFakulteta() throws Exception {
        UcitajListuFakultetaSO so = new UcitajListuFakultetaSO();
        so.izvrsenjeSO(new Fakultet());
        return so.vratiListuFakulteta();
    }
    
    public OpstiDomenskiObjekat vratiReferenta(Referent referentUsernamePassword) throws Exception {
//        Referent referent = new Referent();
        //postavljam ovo u polje 'korisnickoIme' samo da bih mogao posle da ga koristim kao kriterijum
//        referent.setKorisnickoIme(usernamePassword);
        
        PronadjiReferentaSO so = new PronadjiReferentaSO();
        so.izvrsenjeSO(referentUsernamePassword);
        return so.vratiReferenta();
    }
    
    public List<OpstiDomenskiObjekat> vratiListuStanara() throws Exception {
        UcitajListuStanaraSO so = new UcitajListuStanaraSO();
        so.izvrsenjeSO(new Stanar());
        return so.vratiListuStanara();
    }
    
    public List<OpstiDomenskiObjekat> vratiListuReferenata() throws Exception {
        UcitajListuReferenata so = new UcitajListuReferenata();
        so.izvrsenjeSO(new Referent());
        return so.vratiListuReferenata();
    }
    
    public void sacuvajStanara(Stanar stanar) throws Exception {
        OpstaSO so = new SacuvajStanaraSO();
        so.izvrsenjeSO(stanar);
    }
    
//    public List<OpstiDomenskiObjekat> vratiListuPrenocista() throws Exception {
//        UcitajListuPrenocistaSO so = new UcitajListuPrenocistaSO();
//        so.izvrsenjeSO(new Prenociste());
//        return so.vratiPrenocista();
//    }

    public void sacuvajPrenociste(Prenociste prenociste) throws Exception {
        OpstaSO so = new SacuvajPrenocisteSO();
        so.izvrsenjeSO(prenociste);
    }
    
    public List<OpstiDomenskiObjekat> vratiListuZaduzenjaInventara() throws Exception {
        //System.out.println("Ulazi li u ovu metodu?");
        UcitajListuZaduzenjaInventaraSO so = new UcitajListuZaduzenjaInventaraSO();
        so.izvrsenjeSO(new ZaduzenjeInventara());
        return so.vratiZaduzenjaInventara();
    }
    
    public List<OpstiDomenskiObjekat> vratiListuInventara() throws Exception {
        UcitajListuInventaraSO so = new UcitajListuInventaraSO();
        so.izvrsenjeSO(new Inventar());
        return so.vratiListuInventara();
    }
    
    public void sacuvajZaduzenje(ZaduzenjeInventara zaduzenje) throws Exception {
        OpstaSO so = new SacuvajZaduzenjeInventaraSO();
        so.izvrsenjeSO(zaduzenje);
    }
    
    public List<OpstiDomenskiObjekat> vratiListuPrenocistaSaPretragom(List<Object> parametri) throws Exception {
        PronadjiPrenocistaSO so = new PronadjiPrenocistaSO();
        so.izvrsenjeSO(parametri);
        return so.vratiListuPrenocista();
    }
    
    public List<OpstiDomenskiObjekat> vratiListuZaduzenjaSaPretragom(List<Object> parametriZaduzenja) throws Exception {
        PronadjiZaduzenjaInventaraSO so = new PronadjiZaduzenjaInventaraSO();
        so.izvrsenjeSO(parametriZaduzenja);
        return so.vratiListuZaduzenja();
    }

    public List<OpstiDomenskiObjekat> vratiListuStanaraSaPretragom(List<Object> parametriStanara) throws Exception {
        PronadjiStanareSO so = new PronadjiStanareSO();
        so.izvrsenjeSO(parametriStanara);
        return so.vratiListuStanara();
    }

    public void izmeniPrenociste(Prenociste prenocisteIzmena) throws Exception {
        OpstaSO so = new IzmeniPrenocisteSO();
        so.izvrsenjeSO(prenocisteIzmena);
    }

    public void izmeniStanara(Stanar stanarIzmena) throws Exception {
        OpstaSO so = new IzmeniStanaraSO();
        so.izvrsenjeSO(stanarIzmena);
    }

//    public List<OpstiDomenskiObjekat> vratiListuStavkiZaduzenjaSaPretragom(List<Object> parametriStavkeZaduzenja) {
//        PronadjiStavkeZaduzenjaInventaraSO so = new PronadjiStavkeZaduzenjaInventaraSO();
//        so.izvrsenjeSO(parametriStavkeZaduzenja);
//        return so.vratiListuStavki();
//    }

    public ZaduzenjeInventara vratiOdredjenoZaduzenje(OpstiDomenskiObjekat zaduzenjeZaVracanje) throws Exception {
        PrikaziZaduzenjeInventaraSO so = new PrikaziZaduzenjeInventaraSO();
        so.izvrsenjeSO(zaduzenjeZaVracanje);
        return so.vratiZaduzenje();
    }

    public void izmeniZaduzenje(ZaduzenjeInventara zaduzenjeIzmena) throws Exception {
        OpstaSO so = new IzmeniZaduzenjeInventaraSO();
        so.izvrsenjeSO(zaduzenjeIzmena);
    }

    public Prenociste vratiOdredjenoPrenociste(Prenociste prenocisteZaVracanje) throws Exception {
        PrikaziPrenocisteSO so = new PrikaziPrenocisteSO();
        so.izvrsenjeSO(prenocisteZaVracanje);
        return so.vratiPrenociste();
    }

    public Stanar vratiOdredjenogStanara(Stanar stanarZaVracanje) throws Exception {
        PrikaziStanaraSO so = new PrikaziStanaraSO();
        so.izvrsenjeSO(stanarZaVracanje);
        return so.vratiStanara();
    }

    public void obrisiStanara(Stanar stanarBrisanje) throws Exception {
        ObrisiStanaraSO so = new ObrisiStanaraSO();
        so.izvrsenjeSO(stanarBrisanje);
    }
    

    

    
}
