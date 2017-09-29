/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bodin Todorovic
 */
public class Referent implements OpstiDomenskiObjekat{
    private int referentID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    @Override
    public String toString() {
        return ime + " " + prezime + ", " + korisnickoIme;
    }

    public Referent() {
    }

    public Referent(int referentID, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.referentID = referentID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getReferentID() {
        return referentID;
    }

    public void setReferentID(int referentID) {
        this.referentID = referentID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this.referentID == ((Referent) obj).getReferentID()) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public String vratiNazivTabele() {
        return "Referent";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                referentID = rs.getInt("referentID");
                ime = rs.getString("ime");
                prezime = rs.getString("prezime");
                korisnickoIme = rs.getString("korisnickoIme");
                lozinka = rs.getString("lozinka");
                
                lista.add(new Referent(referentID, ime, prezime, korisnickoIme, lozinka));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje referenta", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return "'" + referentID + "','" + ime + "','" + prezime + "','" + korisnickoIme + "','" + lozinka + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                referentID = rs.getInt("referentID");
                ime = rs.getString("ime");
                prezime = rs.getString("prezime");
                korisnickoIme = rs.getString("korisnickoIme");
                lozinka = rs.getString("lozinka");
                
                odo = new Referent(referentID, ime, prezime, korisnickoIme, lozinka);
            }
            return odo;
        } catch (SQLException ex) {
            throw new Exception("Referent ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "WHERE referent.referentID = " + referentID;
    }

    @Override
    public String vratiIdentifikator() {
        return "referent.referentID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "referentID": return getReferentID();
            case "ime": return getIme();
            case "prezime": return getPrezime();
            case "korisnickoIme": return getKorisnickoIme();
            case "lozinka": return getLozinka();
            default: return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "referentID": setReferentID((int)vrednostAtributa);
                break;
            case "ime": setIme((String) vrednostAtributa);
                break;
            case "prezime": setPrezime((String) vrednostAtributa);
                break;
            case "korisnickoIme": setKorisnickoIme((String) vrednostAtributa);
                break;
            case "lozinka": setLozinka((String) vrednostAtributa);
                break;
        }
    }

    //ovde spajam sa onima koje imam medju poljima
    @Override
    public String vratiTabeluSaUslovomSpajanja() {
        return "";
    }

    @Override
    public String vratiKriterijumPretrage(String kriterijum) {
        if(kriterijum.equals("")) return "";
        //return "WHERE referentID = '" + kriterijum + "' OR ime = '" + kriterijum + "' OR prezime = '" + kriterijum + "' OR korisnickoIme = '" + kriterijum + "' OR lozinka = '" + kriterijum + "'";
        String[] niz = kriterijum.split("/");//("[ovde_podeli]");
        String korisnickoIme1 = niz[0];
        String lozinka1 = niz[1];
        return " WHERE korisnickoIme = '" + korisnickoIme1 + "' AND " + "lozinka = '" + lozinka1 + "'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "referentID = '" + referentID + "', ime = '" + ime + "', prezime = '" + prezime + "', korisnickoIme = '" + korisnickoIme + "', lozinka = '" + lozinka;
    }
}
