/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Fakultet;
import domen.Inventar;
import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import domen.Referent;
import domen.Stanar;
import domen.StavkaZaduzenjaInventara;
import domen.ZaduzenjeInventara;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import klijent.Klijent;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ActionCode;
import util.EnumResponseStatus;

/**
 *
 * @author Bodin Todorovic
 */
public class Kontroler {

    private static Referent trenutniReferent;
    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    /*
     Ovde namesti da salje serveru trenutnog referenta
     */
    public void postaviTrenutnogReferenta(Referent referent) {
        trenutniReferent = referent;
        //boolean postavljen = (boolean) posaljiZahtev(ActionCode.POSTAVI_TRENUTNOG_REFERENTA, referent);
    }

    public Referent vratiTrenutnogReferenta() {
        return trenutniReferent;
    }

    public ArrayList<Fakultet> vratiFakultete() throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SVE_FAKULTETE, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Fakultet> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Fakultet) odo);
            }
            return lista;
        }
        throw new Exception("Fakulteti nisu vraceni");
    }

    public ArrayList<Referent> vratiReferente() throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SVE_REFERENTE, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Referent> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Referent) odo);
            }
            return lista;
        }
        throw new Exception("Referenti nisu vraceni");
    }

    public ArrayList<Stanar> vratiStanare() throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SVE_STANARE, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Stanar> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Stanar) odo);
            }
            return lista;
        }
        throw new Exception("Stanari nisu vraceni");
    }

    private ResponseObject posaljiZahtev(int action, Object request) throws Exception {
        try {
            RequestObject requestObject = new RequestObject();
            requestObject.setAction(action);
            if (request != null) {
                requestObject.setRequest(request);
            }
            
            Klijent.getInstanca().posaljiZahtev(requestObject);
            ResponseObject responseObject = Klijent.getInstanca().primiOdgovor();
            
            return responseObject;
            //return responseObject.getResponse();
        } catch (Exception ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("kontroler.Kontroler - linija 120 - " + ex.getMessage());
            throw ex;
        }
    }

    public Referent ulogujReferenta(Referent referent) throws Exception {
//        String objZaSlanje = korisnickoIme + "/" + lozinka;//"[ovde_podeli]" + lozinka;
        Referent objZaSlanje = referent;
        ResponseObject responseObject = posaljiZahtev(ActionCode.ULOGUJ_REFERENTA, objZaSlanje);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return (Referent) responseObject.getResponse();
        }
        throw new Exception("Kombinacija korisnicko ime/lozinka je netacna");
    }

    public String sacuvajStanara(Stanar stanar) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.SACUVAJ_STANARA, stanar);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Sistem je uspesno sacuvao stanara";//responseObject.getMessage();
        }
        throw new Exception("Sistem ne moze da sacuva stanara");
    }

    public List<Prenociste> vratiPrenocista() throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SVA_PRENOCISTA, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Prenociste> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Prenociste) odo);
            }
            return lista;
        }
        throw new Exception("Prenocista nisu vracena");
    }

    public String sacuvajPrenociste(Prenociste prenociste) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.SACUVAJ_PRENOCISTE, prenociste);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Novo prenociste je uspesno sacuvano";//responseObject.getMessage();
        }
        throw new Exception("Sistem ne moze da kreira novo prenociste");
    }

    public List<OpstiDomenskiObjekat> vratiZaduzenjaInventara() throws Exception {
        //System.out.println("Ulazi li u ovu metodu?");
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SVA_ZADUZENJA_INVENTARA, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
//            ArrayList<ZaduzenjeInventara> lista = new ArrayList<>();
//            for (OpstiDomenskiObjekat odo : odoLista) {
//                lista.add((ZaduzenjeInventara) odo);
//            }
            return odoLista;
        }
        throw new Exception("Neuspesno ucitavanje inventara");
    }

    public ArrayList<Inventar> vratiInventar() throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_SAV_INVENTAR, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Inventar> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Inventar) odo);
            }
            return lista;
        }
        throw new Exception("Lista Inventara nije vracen");
    }

    public String sacuvajZaduzenje(ZaduzenjeInventara zaduzenje) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.SACUVAJ_ZADUZENJE, zaduzenje);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return responseObject.getMessage();
        }
        throw new Exception("Cuvanje nije uspelo");
    }

    public List<OpstiDomenskiObjekat> vratiPrenocistaSaPretragom(String kriterijum) throws Exception {

        List<Object> parametri = new ArrayList<>();
        parametri.add(kriterijum);
        parametri.add(new Prenociste());

        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_PRENOCISTA_SA_PRETRAGOM, parametri);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
//            ArrayList<Prenociste> lista = new ArrayList<>();
//            for (OpstiDomenskiObjekat odo : odoLista) {
//                lista.add((Prenociste) odo);
//            }
//            return lista;
            return odoLista;
        }
        throw new Exception("Ne postoje takva prenocista u sistemu");
    }

    public List<OpstiDomenskiObjekat> vratiZaduzenjaInventaraSaPretragom(String kriterijum) throws Exception {

        List<Object> parametri = new ArrayList<>();
        parametri.add(kriterijum);
        parametri.add(new ZaduzenjeInventara());

        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_ZADUZENJA_INVENTARA_SA_PRETRAGOM, parametri);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();

            return odoLista;
        }
        throw new Exception("Ne postoje takva prenocista u sistemu");
    }

    public List<Stanar> vratiStanareSaPretragom(String kriterijum) throws Exception {
        
        List<Object> parametri = new ArrayList<>();
        parametri.add(kriterijum);
        parametri.add(new Stanar());

        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_STANARE_SA_PRETRAGOM, parametri);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
            ArrayList<Stanar> lista = new ArrayList<>();
            for (OpstiDomenskiObjekat odo : odoLista) {
                lista.add((Stanar) odo);
            }
            return lista;
        }
        throw new Exception("Ne postoji takav stanar u sistemu");
    }

    public String izmeniPrenociste(Prenociste prenociste) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.IZMENI_PRENOCISTE, prenociste);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Prenociste je uspesno izmenjeno";//responseObject.getMessage();
        }
        throw new Exception("Izmenjeno prenociste nije zapamceno");
    }

    public String izmeniStanara(Stanar stanar) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.IZMENI_STANARA, stanar);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Uspesno su sacuvani izmenjeni podaci o stanaru";//responseObject.getMessage();
        }
        throw new Exception("Izmenjeni podaci o stanaru nisu uspesno sacuvani");
    }

//    public List<StavkaZaduzenjaInventara> vratiStavkeProsledjenogZaduzenja(ZaduzenjeInventara zi) throws Exception {
//        
//        List<Object> parametri = new ArrayList<>();
//        parametri.add(zi.getSifraZaduzenja());
//        parametri.add(new StavkaZaduzenjaInventara());
//
//        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_STAVKE_SA_RETRAGOM, parametri);
//        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
//            List<OpstiDomenskiObjekat> odoLista = (ArrayList<OpstiDomenskiObjekat>) responseObject.getResponse();
//            List<StavkaZaduzenjaInventara> lista = new ArrayList<>();
//            for (OpstiDomenskiObjekat odo : odoLista) {
//                lista.add((StavkaZaduzenjaInventara) odo);
//            }
//            return lista;
//        }
//        throw new Exception("Ne postoje takve stavke u sistemu");
//    }

    

    public String izmeniZaduzenje(ZaduzenjeInventara zaduzenje) throws Exception {
        System.out.println("Ulazi li u metodu?");
        ResponseObject responseObject = posaljiZahtev(ActionCode.IZMENI_ZADUZENJE, zaduzenje);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return responseObject.getMessage();
        }
        throw new Exception("Izmenjeno zaduzenje inventara nije zapamceno");
    }

    public Prenociste vratiOdredjenoPrenociste(Prenociste p) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_ODREDJENO_PRENOCISTE, p);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            
            Prenociste prenociste = (Prenociste) responseObject.getResponse();
            return prenociste;
        }
        throw new Exception("Podaci izabranog prenociste nisu pronadjeni");
    }

    public Stanar vratiOdredjenogStanara(Stanar stanar) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_ODREDJENOG_STANARA, stanar);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return (Stanar) responseObject.getResponse();
        }
        throw new Exception("Stanar ne postoji");
    }
    
    public ZaduzenjeInventara vratiOdredjenoZaduzenje(ZaduzenjeInventara zi) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.VRATI_ODREDJENO_ZADUZENJE, zi);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            
            ZaduzenjeInventara zaduzenje = (ZaduzenjeInventara) responseObject.getResponse();
            return zaduzenje;
        }
        throw new Exception("Podaci izabranog zaduzenja inventara nisu pronadjeni");
    }

    public boolean obrisiStanara(Stanar s) throws Exception {
        ResponseObject responseObject = posaljiZahtev(ActionCode.OBRISI_STANARA, s);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            
//            ZaduzenjeInventara zaduzenje = (ZaduzenjeInventara) responseObject.getResponse();
            return true;
        }
        throw new Exception("Stanar nije izbrisan");
    }

}
