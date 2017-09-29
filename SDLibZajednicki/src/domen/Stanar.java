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
public class Stanar implements OpstiDomenskiObjekat{
    private int stanarID;
    private String ime;
    private String prezime;
    private String imeRoditelja;
    private int brojSobe;
    private String blok;
    private Date datumRodjenja;
    private int godinaUpisa;
    private Fakultet fakultet;
    private Referent referent;
    //List<ZaduzenjeInventara> zaduzenjaInventara; //ili bi ovde trebalo da ima jedno zaduzenje sa vise stavki??

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Stanar() {
    }

    public Stanar(int stanarID, String ime, String prezime, String imeRoditelja, int brojSobe, String blok, Date datumRodjenja, int godinaUpisa, Fakultet fakultet, Referent referent) {
        this.stanarID = stanarID;
        this.ime = ime;
        this.prezime = prezime;
        this.imeRoditelja = imeRoditelja;
        this.brojSobe = brojSobe;
        this.blok = blok;
        this.datumRodjenja = datumRodjenja;
        this.godinaUpisa = godinaUpisa;
        this.fakultet = fakultet;
        this.referent = referent;
    }

    

    public int getStanarID() {
        return stanarID;
    }

    public void setStanarID(int stanarID) {
        this.stanarID = stanarID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getImeRoditelja() {
        return imeRoditelja;
    }

    public void setImeRoditelja(String imeRoditelja) {
        this.imeRoditelja = imeRoditelja;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Referent getReferent() {
        return referent;
    }

    public void setReferentID(Referent referent) {
        this.referent = referent;
    }

    public int getBrojSobe() {
        return brojSobe;
    }

    public void setBrojSobe(int brojSobe) {
        this.brojSobe = brojSobe;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.stanarID == ((Stanar) obj).getStanarID()) {
            return true;
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "Stanar";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                stanarID = rs.getInt("stanarID");
                ime = rs.getString("ime");
                prezime = rs.getString("prezime");
                imeRoditelja = rs.getString("imeRoditelja");
                brojSobe = rs.getInt("brojSobe");
                blok = rs.getString("blok");
                datumRodjenja = new Date(rs.getDate("datumRodjenja").getTime());
                godinaUpisa = rs.getInt("godinaUpisa");

                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("naziv");
                fakultet = new Fakultet(fakultetID, naziv);

                int referentID = rs.getInt("referentID");
                String imeRef = rs.getString("r.ime");
                String prezimeRef = rs.getString("r.prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                referent = new Referent(referentID, imeRef, prezimeRef, korisnickoIme, lozinka);

                lista.add(new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Stanar.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje stanara", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        //mozda ce da baca exception zbog apostrofa
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + stanarID + "','" + ime + "','" + prezime + "','" + imeRoditelja + "','" + brojSobe + "','" + blok + "','" + sdf.format(datumRodjenja) + "','" + godinaUpisa + "','" + fakultet.getFakultetID() + "','" + referent.getReferentID() + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                stanarID = rs.getInt("stanarID");
                ime = rs.getString("ime");
                prezime = rs.getString("prezime");
                imeRoditelja = rs.getString("imeRoditelja");
                brojSobe = rs.getInt("brojSobe");
                blok = rs.getString("blok");
                datumRodjenja = new Date(rs.getDate("datumRodjenja").getTime());
                godinaUpisa = rs.getInt("godinaUpisa");

                int fakultetID = rs.getInt("fakultetID");
                String naziv = rs.getString("naziv");
                fakultet = new Fakultet(fakultetID, naziv);

                int referentID = rs.getInt("referentID");
                String imeRef = rs.getString("r.ime");
                String prezimeRef = rs.getString("r.prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                referent = new Referent(referentID, imeRef, prezimeRef, korisnickoIme, lozinka);

                odo = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent);
            }
            return odo;
        } catch (SQLException ex) {
            throw new Exception("Stanar ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return " stanar.stanarID = " + stanarID;
    }

    @Override
    public String vratiIdentifikator() {
        return "stanar.stanarID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch (nazivAtributa) {
            case "stanarID":
                return getStanarID();
            case "ime":
                return getIme();
            case "prezime":
                return getPrezime();
            case "imeRoditelja":
                return getImeRoditelja();
            case "brojSobe":
                return getBrojSobe();
            case "blok":
                return getBlok();
            case "datumRodjenja":
                return getDatumRodjenja();
            case "godinaUpisa":
                return getGodinaUpisa();
            case "fakultet":
                return getFakultet();
            case "referent":
                return getReferent();
            default:
                return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "stanarID":
                setStanarID((int) vrednostAtributa);
                break;
            case "ime":
                setIme((String) vrednostAtributa);
                break;
            case "prezime":
                setPrezime((String) vrednostAtributa);
                break;
            case "imeRoditelja":
                setImeRoditelja((String) vrednostAtributa);
                break;
            case "brojSobe":
                setBrojSobe((int) vrednostAtributa);
                break;
            case "blok":
                setBlok((String) vrednostAtributa);
                break;
            case "datumRodjenja":
                setDatumRodjenja((Date) vrednostAtributa);
                break;
            case "godinaUpisa":
                setGodinaUpisa((int) vrednostAtributa);
                break;
            case "fakultet":
                setFakultet((Fakultet) vrednostAtributa);
                break;
            case "referent":
                setReferentID((Referent) vrednostAtributa);
                break;
        }
    }

    //ovde spajam sa onima koje imam medju poljima
    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return " JOIN fakultet f ON(stanar.fakultetID = f.fakultetID) JOIN referent r ON(stanar.referentID = r.referentID)";
    }

    //ovde sam ispisao svasta, mozda zabaguje ili ne radi dobro, proveri
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
            return "WHERE stanarID = '" + kriterijum + "' OR stanar.ime = '" + kriterijum + "' OR stanar.prezime = '" + kriterijum + "' OR imeRoditelja = '" + kriterijum + "' OR brojSobe = '" + kriterijum + "' OR blok = '" + kriterijum + "' OR datumRodjenja = '" + kriterijumZaDatum + "' OR godinaUpisa = '" + kriterijum + "' OR f.fakultetID = '" + kriterijum + "' OR f.naziv = '" + kriterijum + "' OR r.ime = '" + kriterijum + "' OR r.prezime = '" + kriterijum + "' OR r.korisnickoIme = '" + kriterijum + "'";
        }
    }

    @Override
    public String vratiUpdateVrednosti() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "stanarID = " + stanarID + ", ime = '" + ime + "', prezime = '" + prezime + "', imeRoditelja = '" + imeRoditelja + "', brojSobe = " + brojSobe + ", blok = '" + blok + "', datumRodjenja = '" + sdf.format(datumRodjenja) + "', godinaUpisa = " + godinaUpisa + ", fakultetID = " + fakultet.getFakultetID() + ", referentID = " + referent.getReferentID();
    }
}
