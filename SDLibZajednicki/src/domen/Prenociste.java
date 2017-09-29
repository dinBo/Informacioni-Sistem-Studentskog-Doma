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
public class Prenociste implements OpstiDomenskiObjekat {

    private int prenocisteID;
    private String imeGosta;
    private String prezimeGosta;
    private Date datumOd;
    private Date datumDo;
    private Stanar stanar;

    public Prenociste(int prenocisteID, String imeGosta, String prezimeGosta, Date datumOd, Date datumDo, Stanar stanar) {
        this.prenocisteID = prenocisteID;
        this.imeGosta = imeGosta;
        this.prezimeGosta = prezimeGosta;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.stanar = stanar;
    }

    public Prenociste() {
    }

    public int getPrenocisteID() {
        return prenocisteID;
    }

    public void setPrenocisteID(int prenocisteID) {
        this.prenocisteID = prenocisteID;
    }

    public String getImeGosta() {
        return imeGosta;
    }

    public void setImeGosta(String imeGosta) {
        this.imeGosta = imeGosta;
    }

    public String getPrezimeGosta() {
        return prezimeGosta;
    }

    public void setPrezimeGosta(String prezimeGosta) {
        this.prezimeGosta = prezimeGosta;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Stanar getStanar() {
        return stanar;
    }

    public void setStanar(Stanar stanar) {
        this.stanar = stanar;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.prenocisteID == ((Prenociste) obj).getPrenocisteID()) {
            return true;
        }
        return false;
    }

    @Override
    public String vratiNazivTabele() {
        return "Prenociste";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                prenocisteID = rs.getInt("prenocisteID");
                imeGosta = rs.getString("imeGosta");
                prezimeGosta = rs.getString("prezimeGosta");
                datumOd = new Date(rs.getDate("datumOd").getTime());
                datumDo = new Date(rs.getDate("datumDo").getTime());

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

                int referentID = rs.getInt("referentID");
                String imeRef = rs.getString("r.ime");
                String prezimeRef = rs.getString("r.prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                Referent referent = new Referent(referentID, imeRef, prezimeRef, korisnickoIme, lozinka);

                stanar = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent);

                lista.add(new Prenociste(prenocisteID, imeGosta, prezimeGosta, datumOd, datumDo, stanar));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Stanar.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje prenocista", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        //mozda ce da baca exception zbog apostrofa
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + prenocisteID + "','" + imeGosta + "','" + prezimeGosta + "','" + sdf.format(datumOd) + "','" + sdf.format(datumDo) + "','" + stanar.getStanarID() + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                prenocisteID = rs.getInt("prenocisteID");
                imeGosta = rs.getString("imeGosta");
                prezimeGosta = rs.getString("prezimeGosta");
                datumOd = new Date(rs.getDate("datumOd").getTime());
                datumDo = new Date(rs.getDate("datumDo").getTime());

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

                int referentID = rs.getInt("referentID");
                String imeRef = rs.getString("r.ime");
                String prezimeRef = rs.getString("r.prezime");
                String korisnickoIme = rs.getString("korisnickoIme");
                String lozinka = rs.getString("lozinka");
                Referent referent = new Referent(referentID, imeRef, prezimeRef, korisnickoIme, lozinka);

                stanar = new Stanar(stanarID, ime, prezime, imeRoditelja, brojSobe, blok, datumRodjenja, godinaUpisa, fakultet, referent);

                odo = new Prenociste(prenocisteID, imeGosta, prezimeGosta, datumOd, datumDo, stanar);
            }
            return odo;
        } catch (SQLException ex) {
            throw new Exception("Prenociste ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return " prenociste.prenocisteID = " + prenocisteID;
    }

    @Override
    public String vratiIdentifikator() {
        return "prenociste.prenocisteID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch (nazivAtributa) {
            case "prenocisteID":
                return getPrenocisteID();
            case "imeGosta":
                return getImeGosta();
            case "prezimeGosta":
                return getPrezimeGosta();
            case "datumOd":
                return getDatumOd();
            case "datumDo":
                return getDatumDo();
            case "stanar":
                return getStanar();
            default:
                return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch (nazivAtributa) {
            case "prenocisteID":
                setPrenocisteID((int) vrednostAtributa);
                break;
            case "imeGosta":
                setImeGosta((String) vrednostAtributa);
                break;
            case "prezimeGosta":
                setPrezimeGosta((String) vrednostAtributa);
                break;
            case "datumOd":
                setDatumOd((Date) vrednostAtributa);
                break;
            case "datumDo":
                setDatumDo((Date) vrednostAtributa);
                break;
            case "stanar":
                setStanar((Stanar) vrednostAtributa);
                break;
        }
    }

    //ovde spajam sa onima koje imam medju poljima
    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return " JOIN stanar s ON(prenociste.stanarID = s.stanarID) JOIN fakultet f ON(s.fakultetID = f.fakultetID) JOIN referent r ON(s.referentID = r.referentID)";
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
            return "WHERE prenocisteID = '" + kriterijum + "' OR imeGosta = '" + kriterijum + "' OR prezimeGosta = '" + kriterijum + "' OR datumOd = '" + kriterijumZaDatum + "' OR datumDo = '" + kriterijumZaDatum + "' OR s.stanarID = '" + kriterijum + "' OR s.ime = '" + kriterijum + "' OR s.prezime = '" + kriterijum + "' OR s.brojSobe = '" + kriterijum + "' OR s.blok = '" + kriterijum + "' OR datumOd = '" + kriterijumZaDatum + "' OR datumDo = '" + kriterijumZaDatum + "'";
        }
    }

    @Override
    public String vratiUpdateVrednosti() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "prenocisteID = " + prenocisteID + ", imeGosta = '" + imeGosta + "', prezimeGosta = '" + prezimeGosta + "', datumOd = '" + sdf.format(datumOd) + "', datumDo = '" + sdf.format(datumDo) + "', stanarID = " + stanar.getStanarID();
    }
}
