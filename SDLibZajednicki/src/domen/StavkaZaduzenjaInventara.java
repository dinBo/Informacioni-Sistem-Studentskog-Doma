/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bodin Todorovic
 */
public class StavkaZaduzenjaInventara implements OpstiDomenskiObjekat{
    private int sifraStavke;
    private int redniBroj;
    private int kolicina;
    private ZaduzenjeInventara zaduzenjeInventara;
    private Inventar inventar;
    private String status;  //ovo sluzi da znam da li update-ujem ili ubacujem novu

    public StavkaZaduzenjaInventara(int sifraStavke, int redniBroj, int kolicina, ZaduzenjeInventara zaduzenjeInventara, Inventar inventar) {
        this.sifraStavke = sifraStavke;
        this.redniBroj = redniBroj;
        this.kolicina = kolicina;
        this.zaduzenjeInventara = zaduzenjeInventara;
        this.inventar = inventar;
        this.status="insert";
    }

    public StavkaZaduzenjaInventara() {
        this.status="insert";
        this.inventar = new Inventar();
    }

    public int getSifraStavke() {
        return sifraStavke;
    }

    public void setSifraStavke(int sifraStavke) {
        this.sifraStavke = sifraStavke;
    }
    
    

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public ZaduzenjeInventara getZaduzenjeInventara() {
        return zaduzenjeInventara;
    }

    public void setZaduzenjeInventara(ZaduzenjeInventara zaduzenjeInventara) {
        this.zaduzenjeInventara = zaduzenjeInventara;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    @Override
    public boolean equals(Object obj) {
//        if (this.getRedniBroj() == ((StavkaZaduzenjaInventara) obj).getRedniBroj() && this.getZaduzenjeInventara()== ((StavkaZaduzenjaInventara) obj).getZaduzenjeInventara()) {
        if (this.getSifraStavke() == ((StavkaZaduzenjaInventara) obj).getSifraStavke()) {    
            return true;
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "StavkaZaduzenjaInventara";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                /*redniBroj = rs.getInt("redniBroj");
                kolicina = rs.getInt("kolicina");
                
                int stanarID = rs.getInt("stanarID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String imeRoditelja = rs.getString("imeRoditelja");
                int brojSobe = rs.getInt("brojSobe");
                String blok = rs.getString("blok");
                Date datumRodjenja = new Date(rs.getDate("datumRodjenja").getTime());
                int godinaUpisa = rs.getInt("godinaUpisa");

                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("naziv");
                Fakultet fakultet = new Fakultet(fakultetID, naziv);

                int referentID1 = rs.getInt("r1.referentID");
                String imeRef1 = rs.getString("r1.imeRef");
                String prezimeRef1 = rs.getString("r1.prezimeRef");
                String korisnickoIme1 = rs.getString("r1.korisnickoIme");
                String lozinka1 = rs.getString("r1.lozinka");
                Referent referent1 = new Referent(referentID1, imeRef1, prezimeRef1, korisnickoIme1, lozinka1);

                Stanar stanar = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent1);
                
                int sifraZaduzenja = rs.getInt("sifraZaduzenja");
                Date datumZaduzenja = new Date(rs.getDate("datumZaduzenja").getTime());
                
                //kako cu ovo kad se isto zovu polja za rs.get??????
                int referentID2 = rs.getInt("r2.referentID");
                String imeRef2 = rs.getString("r2.imeRef");
                String prezimeRef2 = rs.getString("r2.prezimeRef");
                String korisnickoIme2 = rs.getString("r2.korisnickoIme");
                String lozinka2 = rs.getString("r2.lozinka");
                Referent referent2 = new Referent(referentID2, imeRef2, prezimeRef2, korisnickoIme2, lozinka2);
                
                zaduzenjeInventara = new ZaduzenjeInventara(sifraZaduzenja, datumZaduzenja, null, stanar, referent2);
                
                inventar = new Inventar(rs.getInt("sifraInventara"), rs.getString("nazivInventara"));
                
                lista.add(new StavkaZaduzenjaInventara(redniBroj, kolicina, zaduzenjeInventara, inventar));*/
                sifraStavke=rs.getInt("sifraStavke");
                StavkaZaduzenjaInventara stavka = new StavkaZaduzenjaInventara();
                stavka.setSifraStavke(sifraStavke);
                lista.add(stavka);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(StavkaZaduzenjaInventara.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje stavke", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return "'" + sifraStavke + "','" + redniBroj + "','" + kolicina + "','" + zaduzenjeInventara.getSifraZaduzenja() + "','" + inventar.getSifraInventara() + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                sifraStavke = getSifraStavke();
                odo = new StavkaZaduzenjaInventara();
                odo.set("sifraStavke", sifraStavke);
            }
            return odo;
        } catch (SQLException ex) {
            Logger.getLogger(StavkaZaduzenjaInventara.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Stavka ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return " stavkaZaduzenjaInventara.sifraStavke = " + sifraStavke;
    }

    @Override
    public String vratiIdentifikator() {
        return "stavkaZaduzenjaInventara.sifraStavke";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "redniBroj": return getRedniBroj();
            case "kolicina": return getKolicina();
            case "zaduzenjeInventara": return getZaduzenjeInventara();
            case "inventar": return getInventar();
            default: return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "redniBroj": setRedniBroj((int)vrednostAtributa);
                break;
            case "kolicina": setKolicina((int) vrednostAtributa);
                break;
            case "zaduzenjeInventara": setZaduzenjeInventara((ZaduzenjeInventara)vrednostAtributa);
                break;
            case "inventar": setInventar((Inventar) vrednostAtributa);
                break;
        }
    }

    //ovde spajam sa onima koje imam medju poljima
    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        //return " JOIN zaduzenjeInventara zi ON(stavkaZaduzenjaInventara.sifraZaduzenja = zi.sifraZaduzenja) JOIN Inventar i ON(stavkaZaduzenjaInventara.sifraInventara = i.sifraInventara)";
        return ""; //ovde treba mnogo vise od ovog iznad
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        if(kriterijum.equals("")) return "";
        return "WHERE redniBroj = '" + kriterijum + "' OR kolicina = '" + kriterijum + "' OR sifraZaduzenja = '" + kriterijum + "' OR sifraInventara = '" + kriterijum + "'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        //mozda ne bi trebalo da dozvolis da update-uje redni broj
        return "redniBroj = " + redniBroj + ", kolicina = " + kolicina + ", sifraZaduzenja = " + zaduzenjeInventara.getSifraZaduzenja()+ ", sifraInventara = " + inventar.getSifraInventara();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
