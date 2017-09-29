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
public class Inventar implements OpstiDomenskiObjekat{
    private int sifraInventara;
    private String nazivInventara;

    public Inventar(int sifraInventara, String nazivInventara) {
        this.sifraInventara = sifraInventara;
        this.nazivInventara = nazivInventara;
    }

    public Inventar() {
    }

    @Override
    public String toString() {
        return nazivInventara;
    }

    
    
    public int getSifraInventara() {
        return sifraInventara;
    }

    public void setSifraInventara(int sifraInventara) {
        this.sifraInventara = sifraInventara;
    }

    public String getNazivInventara() {
        return nazivInventara;
    }

    public void setNazivInventara(String nazivInventara) {
        this.nazivInventara = nazivInventara;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this.sifraInventara == ((Inventar) obj).getSifraInventara()) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public String vratiNazivTabele() {
        return "Inventar";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Inventar(rs.getInt("sifraInventara"), rs.getString("nazivInventara")));
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje inventara", ex);
        }
    }

    @Override
    public String vratiInsertVrednosti() {
        return "'" + sifraInventara + "','" + nazivInventara + "'";
    }

    @Override
    public OpstiDomenskiObjekat konvertuj(ResultSet rs) throws Exception {
        OpstiDomenskiObjekat odo = null;
        try {
            if (rs.next()) {
                sifraInventara = rs.getInt("sifraInventara");
                nazivInventara = rs.getString("nazivInventara");
                
                odo = new Inventar(sifraInventara, nazivInventara);
            }
            return odo;
        } catch (SQLException ex) {
            throw new Exception("Inventar ne postoji", ex);
        }
    }

    @Override
    public String vratiUslovSaIdentifikatorom() {
        return "WHERE inventar.sifraInventara = " + sifraInventara;
    }

    @Override
    public String vratiIdentifikator() {
        return "inventar.sifraInventara";
    }

    @Override
    public Object get(String nazivAtributa) {
        switch(nazivAtributa){
            case "sifraInventara": return getSifraInventara();
            case "nazivInventara": return getNazivInventara();
            default: return "N/A";
        }
    }

    @Override
    public void set(String nazivAtributa, Object vrednostAtributa) {
        switch(nazivAtributa){
            case "sifraInventara": setSifraInventara((int)vrednostAtributa);
                break;
            case "nazivInventara": setNazivInventara((String) vrednostAtributa);
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
        return "WHERE sifraInventara = '" + kriterijum + "' OR nazivInventara = '" + kriterijum + "'";
    }

    @Override
    public String vratiUpdateVrednosti() {
        return "sifraInventara = '" + sifraInventara + "', nazivInventara = '" + nazivInventara;
    }
}
