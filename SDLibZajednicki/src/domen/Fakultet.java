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
public class Fakultet implements OpstiDomenskiObjekat {

    private int fakultetID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Fakultet() {
    }

    public Fakultet(int fakultetID, String naziv) {
        this.fakultetID = fakultetID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getFakultetID() {
        return fakultetID;
    }

    public void setFakultetID(int fakultetID) {
        this.fakultetID = fakultetID;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this.fakultetID == ((Fakultet) obj).getFakultetID()) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public String vratiNazivTabele() {
        return "Fakultet";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Fakultet(rs.getInt("fakultetID"), rs.getString("naziv")));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje fakulteta", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return "'" + fakultetID + "','" + naziv + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                fakultetID = rs.getInt("fakultetID");
                naziv = rs.getString("naziv");
                
                odo = new Fakultet(fakultetID, naziv);
            }
            return odo;
        } catch (SQLException ex) {
            throw new Exception("Fakultet ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "WHERE fakultet.fakultetID = " + fakultetID;
    }

    @Override
    public String vratiIdentifikator() {
        return "fakultet.fakultetID";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "fakultetID": return getFakultetID();
            case "naziv": return getNaziv();
            default: return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "fakultetID": setFakultetID((int)vrednostAtributa);
                break;
            case "naziv": setNaziv((String) vrednostAtributa);
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
        return "WHERE fakultetID = '" + kriterijum + "' OR naziv = '" + kriterijum + "'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "fakultetID = '" + fakultetID + "', naziv = '" + naziv;
    }

}
