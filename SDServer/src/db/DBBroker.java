/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Fakultet;
import domen.OpstiDomenskiObjekat;
import domen.Referent;
import domen.Stanar;
import domen.StavkaZaduzenjaInventara;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBUtil;

/**
 *
 * @author Bodin Todorovic
 */
public class DBBroker {

    Connection konekcija;
    private static DBBroker instance;

    private DBBroker() {}

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void ucitajDriver() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            DBUtil dbUtil = new DBUtil();

            String url = dbUtil.vratiUrl();
            String username = dbUtil.vratiUsername();
            String password = dbUtil.vratiPassword();

            konekcija = DriverManager.getConnection(url, username, password);
            konekcija.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Neuspesno uspostavanje konekcije!", ex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", ex);
        }
    }

    public void potvrdiTransakciju() throws Exception {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspresno potvrdjivanje transakcije!", ex);
        }
    }

    public void ponistiTransakciju() throws Exception {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            throw new Exception("Neuspresno ponistavanje transakcije!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() + "" + odo.vratiTabeluSaUslovomSpajanja();
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            
            list = odo.napuni(rs);
            
            rs.close();
            sqlStatement.close();
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }
    
    public void sacuvaj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiInsertVrednosti() + ")";
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
            
            sqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno cuvanje objekta!", ex);
        }
    }
    
    public  OpstiDomenskiObjekat pronadji(OpstiDomenskiObjekat odo)throws Exception {
        try {
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() +""+odo.vratiTabeluSaUslovomSpajanja()+ " WHERE " + odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            
            OpstiDomenskiObjekat odo1 = odo.konvertuj(rs);
            
            sqlStatement.close();
            
            return odo1;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje objekta!", ex);
        }
    }
    
    public void izmeni(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "UPDATE "+ odo.vratiNazivTabele()+ " SET "+ odo.vratiUpdateVrednosti()+" WHERE "+odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
            
            sqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno cuvanje objekta!",ex);
        }
    }
    
    public void obrisi(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "DELETE FROM "+odo.vratiNazivTabele()+" WHERE " + odo.vratiUslovSaIdentifikatorom();
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            sqlStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno brisanje objekta!",ex);
        }
    }
    
    public List<OpstiDomenskiObjekat> vratiListuSaPretragom(String kriterijum, OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele()+""+odo.vratiTabeluSaUslovomSpajanja()+" "+odo.vratiKriterijumPretrage(kriterijum);
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            
            list = odo.napuni(rs);
            
            rs.close();
            sqlStatement.close();
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }
    
    public int vratiMaxVrednostIdentifikatora(OpstiDomenskiObjekat odo) throws Exception{
        try {
            int max=0;
            String sql = "SELECT MAX("+odo.vratiIdentifikator()+") FROM " + odo.vratiNazivTabele();
            System.out.println(sql);
            
            Statement sqlStatement = konekcija.createStatement();
            
            ResultSet rs = sqlStatement.executeQuery(sql);
            rs.next();
            max = rs.getInt(1);
            
            rs.close();
            sqlStatement.close();
            
            return max;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje maksimalne vrednosti!", ex);
        }
    }
    
}
