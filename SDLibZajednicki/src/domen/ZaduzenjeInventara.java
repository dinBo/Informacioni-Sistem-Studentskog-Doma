/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bodin Todorovic
 */
public class ZaduzenjeInventara implements OpstiDomenskiObjekat {

    private int sifraZaduzenja;
    private Date datumZaduzenja;
    private ArrayList<StavkaZaduzenjaInventara> listaStavki;
    private Stanar stanar;
    private Referent referent;

    public ZaduzenjeInventara() {
    }

    public ZaduzenjeInventara(int sifraZaduzenja, Date datumZaduzenja, ArrayList<StavkaZaduzenjaInventara> listaStavki, Stanar stanar, Referent referent) {
        this.sifraZaduzenja = sifraZaduzenja;
        this.datumZaduzenja = datumZaduzenja;
        this.listaStavki = listaStavki;
        this.stanar = stanar;
        this.referent = referent;
    }

    public Referent getReferent() {
        return referent;
    }

    public void setReferent(Referent referent) {
        this.referent = referent;
    }

    public int getSifraZaduzenja() {
        return sifraZaduzenja;
    }

    public void setSifraZaduzenja(int sifraZaduzenja) {
        this.sifraZaduzenja = sifraZaduzenja;
    }

    public Date getDatumZaduzenja() {
        return datumZaduzenja;
    }

    public void setDatumZaduzenja(Date datumZaduzenja) {
        this.datumZaduzenja = datumZaduzenja;
    }

    public Stanar getStanar() {
        return stanar;
    }

    public void setStanar(Stanar stanar) {
        this.stanar = stanar;
    }

    public void dodajStavku(StavkaZaduzenjaInventara stavka) {
        listaStavki.add(stavka);
    }

    public ArrayList<StavkaZaduzenjaInventara> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaZaduzenjaInventara> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getSifraZaduzenja() == ((ZaduzenjeInventara) obj).getSifraZaduzenja()) {
            return true;
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaduzenjeInventara";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                int stanarID = rs.getInt("stanarID");
                String ime = rs.getString("s.ime");
                String prezime = rs.getString("s.prezime");
                String imeRoditelja = rs.getString("imeRoditelja");
                int brojSobe = rs.getInt("brojSobe");
                String blok = rs.getString("blok");
                Date datumRodjenja = new Date(rs.getDate("datumRodjenja").getTime());
                int godinaUpisa = rs.getInt("godinaUpisa");

                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("naziv");
                Fakultet fakultet = new Fakultet(fakultetID, naziv);

                int referentID1 = rs.getInt("r1.referentID");
                String imeRef1 = rs.getString("r1.ime");
                String prezimeRef1 = rs.getString("r1.prezime");
                String korisnickoIme1 = rs.getString("r1.korisnickoIme");
                String lozinka1 = rs.getString("r1.lozinka");
                Referent referent1 = new Referent(referentID1, imeRef1, prezimeRef1, korisnickoIme1, lozinka1);

                stanar = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent1);

                int referentID2 = rs.getInt("r2.referentID");
                String imeRef2 = rs.getString("r2.ime");
                String prezimeRef2 = rs.getString("r2.prezime");
                String korisnickoIme2 = rs.getString("r2.korisnickoIme");
                String lozinka2 = rs.getString("r2.lozinka");
                referent = new Referent(referentID2, imeRef2, prezimeRef2, korisnickoIme2, lozinka2);

                sifraZaduzenja = rs.getInt("sifraZaduzenja");
                datumZaduzenja = new Date(rs.getDate("datumZaduzenja").getTime());

                ZaduzenjeInventara zaduzenjeInventara = new ZaduzenjeInventara(sifraZaduzenja, datumZaduzenja, new ArrayList<StavkaZaduzenjaInventara>(), stanar, referent);

                if (!lista.contains(zaduzenjeInventara)) {
                    lista.add(zaduzenjeInventara);
                }

            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ZaduzenjeInventara.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje Zaduzenja Inventara", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return sifraZaduzenja + ",'" + df.format(datumZaduzenja) + "'," + stanar.getStanarID() + "," + referent.getReferentID();
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        ZaduzenjeInventara zaduzenjeInventara = null;
        try {
            while (rs.next()) {
                sifraZaduzenja = rs.getInt("sifraZaduzenja");
                datumZaduzenja = new Date(rs.getDate("datumZaduzenja").getTime());

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
                String imeRef1 = rs.getString("r1.ime");
                String prezimeRef1 = rs.getString("r1.prezime");
                String korisnickoIme1 = rs.getString("r1.korisnickoIme");
                String lozinka1 = rs.getString("r1.lozinka");
                Referent referent1 = new Referent(referentID1, imeRef1, prezimeRef1, korisnickoIme1, lozinka1);

                stanar = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent1);

                int referentID2 = rs.getInt("r2.referentID");
                String imeRef2 = rs.getString("r2.ime");
                String prezimeRef2 = rs.getString("r2.prezime");
                String korisnickoIme2 = rs.getString("r2.korisnickoIme");
                String lozinka2 = rs.getString("r2.lozinka");
                referent = new Referent(referentID2, imeRef2, prezimeRef2, korisnickoIme2, lozinka2);

                int sifraStavke = rs.getInt("sifraStavke");
                int redniBroj = rs.getInt("redniBroj");
                int kolicina = rs.getInt("kolicina");

                Inventar inventar = new Inventar(rs.getInt("sifraInventara"), rs.getString("nazivInventara"));

                StavkaZaduzenjaInventara stavka = new StavkaZaduzenjaInventara(sifraStavke, redniBroj, kolicina, zaduzenjeInventara, inventar);
                if (zaduzenjeInventara == null) {
                    zaduzenjeInventara = new ZaduzenjeInventara(sifraZaduzenja, datumZaduzenja, new ArrayList<>(), stanar, referent);
                    stavka.setZaduzenjeInventara(zaduzenjeInventara);
                    zaduzenjeInventara.dodajStavku(stavka);
                } else {
                    zaduzenjeInventara.dodajStavku(stavka);
                }

            }
            rs.close();
            return zaduzenjeInventara;
        } catch (SQLException ex) {
            Logger.getLogger(ZaduzenjeInventara.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("ZaduzenjeInventara ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return " ZaduzenjeInventara.sifraZaduzenja=" + sifraZaduzenja;
    }

    @Override
    public String vratiIdentifikator() {
        return "ZaduzenjeInventara.sifraZaduzenja";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch (nazivAtributa) {
            case "sifraZaduzenja":
                return getSifraZaduzenja();
            case "datumZaduzenja":
                return getDatumZaduzenja();
            case "listaStavki":
                return getListaStavki();
            case "stanar":
                return getStanar();
            case "referent":
                return getReferent();
        }
        return null;
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "sifraZaduzenja":
                setSifraZaduzenja((int) vrednostAtributa);
                break;
            case "datumZaduzenja":
                setDatumZaduzenja((Date) vrednostAtributa);
                break;
            case "listaStavki":
                setListaStavki((ArrayList<StavkaZaduzenjaInventara>) vrednostAtributa);
                break;
            case "stanar":
                setStanar((Stanar) vrednostAtributa);
                break;
            case "referent":
                setReferent((Referent) vrednostAtributa);
                break;
        }
    }

    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return " JOIN referent r2 ON(zaduzenjeInventara.referentID = r2.referentID) JOIN stavkaZaduzenjaInventara szi ON(zaduzenjeInventara.sifraZaduzenja = szi.sifraZaduzenja) JOIN inventar i ON(szi.sifraInventara = i.sifraInventara) JOIN stanar s ON(zaduzenjeInventara.stanarID = s.stanarID) JOIN fakultet f ON(s.fakultetID = f.fakultetID) JOIN referent r1 ON(s.referentID = r1.referentID)";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        if (kriterijum.equals("")) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            String kriterijumZaDatum;
            try {
                Date datum = df.parse(kriterijum);
                df = new SimpleDateFormat("yyyy-MM-dd");
                kriterijumZaDatum = df.format(datum);
            } catch (ParseException ex) {
                kriterijumZaDatum = kriterijum;
            }
            return "WHERE zaduzenjeInventara.sifraZaduzenja='" + kriterijum + "' OR datumZaduzenja='" + kriterijumZaDatum + /*"' OR s.stanarID='" + kriterijum + "' OR r2.referentID='" + kriterijum + */"' OR s.ime='" + kriterijum + "' OR r2.ime='" + kriterijum + "' OR s.prezime='" + kriterijum + "' OR r2.prezime='" + kriterijum + "'";

        }
    }

    @Override
    public String vratiUpdateVrednosti() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "sifraZaduzenja=" + sifraZaduzenja + ", datumZaduzenja='" + df.format(datumZaduzenja) + "', stanarID=" + stanar.getStanarID() + ", referentID='" + referent.getReferentID() + "'";
    }

}
