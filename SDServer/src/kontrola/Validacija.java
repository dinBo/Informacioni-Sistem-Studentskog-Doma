/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrola;

import domen.Prenociste;
import domen.Stanar;
import domen.StavkaZaduzenjaInventara;
import domen.ZaduzenjeInventara;
import java.util.Date;

/**
 *
 * @author Bodin Todorovic
 */
public class Validacija {

    public static Validacija instance;

    private Validacija() {

    }

    public static Validacija getInstance() {
        if (instance == null) {
            instance = new Validacija();
        }
        return instance;
    }

    public void proveriStanara(Stanar stanar) throws Exception {
        if (stanar.getBrojSobe() > 321 || stanar.getBrojSobe() < 1) {
            throw new Exception("Brojevi sobe smeju biti u rasponu od 1 do 321");
        }
        
        
    }

    public void proveriPrenociste(Prenociste prenociste) throws Exception {
        if (prenociste.getDatumOd().before(new Date())) {
            throw new Exception("'Datum od' ne sme biti pre danasnjeg datuma");
        }
        if (prenociste.getDatumDo().before(prenociste.getDatumOd())) {
            throw new Exception("'Datum do' mora biti veci od 'datum od'");
        }
    }

    public void proveriZaduzenje(ZaduzenjeInventara zaduzenje) throws Exception {
        int brojac = 0;
        for (StavkaZaduzenjaInventara stavka : zaduzenje.getListaStavki()) {
            if (!stavka.getStatus().equals("delete")) {
                brojac++;
            }
        }
        if (brojac == 0) {
            throw new Exception("Zaduzenje mora imati bar jednu stavku");
        }

        for (StavkaZaduzenjaInventara stavka : zaduzenje.getListaStavki()) {
            if (stavka.getKolicina() <= 0 && !stavka.getStatus().equals("delete")) {
                throw new Exception("Kolicina mora biti veca od 0");
            }
        }

        /*
         **  Pravi problem jer se ne brise dugmetom izbaci, mora da se proverava status stavke i redni brojevi umesto
         sifre jer kad ubacis sifra je 0 **
         */
        for (StavkaZaduzenjaInventara stavka : zaduzenje.getListaStavki()) {
            if (!stavka.getStatus().equals("delete")) {
                if (stavka.getInventar().getSifraInventara() == 0) {
                    throw new Exception("Morate izabrati inventar stavke");
                }
                for (StavkaZaduzenjaInventara s : zaduzenje.getListaStavki()) {
                    if (!s.getStatus().equals("delete")) {
                        if (stavka.getInventar().getSifraInventara() == s.getInventar().getSifraInventara()
                                && stavka.getRedniBroj() != s.getRedniBroj()) {
                            throw new Exception("Isti inventar ne moze biti zaduzen u razlicitim stavkama");
                        }
                    }
                }
            }
        }
    }
}
