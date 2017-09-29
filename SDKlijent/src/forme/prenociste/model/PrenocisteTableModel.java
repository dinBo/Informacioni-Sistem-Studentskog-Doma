/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.prenociste.model;

import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import forme.stanar.model.*;
import domen.Stanar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bodin Todorovic
 */
public class PrenocisteTableModel extends AbstractTableModel{

    private List<OpstiDomenskiObjekat> listaPrenocista;
    private String[] kolone = {"ID", "Ime Gosta:", "Prezime Gosta:", "Datum od:", "Datum do:", "Stanar:", "Broj sobe:",
        "Blok:"};

    public PrenocisteTableModel(List<OpstiDomenskiObjekat> prenocista) {
        this.listaPrenocista = prenocista;
    }
    
    @Override
    public int getRowCount() {
        if (listaPrenocista == null) {
            return 0;
        }
        return listaPrenocista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        OpstiDomenskiObjekat prenociste = listaPrenocista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return prenociste.get("prenocisteID");
            case 1:
                return prenociste.get("imeGosta");
            case 2:
                return prenociste.get("prezimeGosta");
            case 3:
                String datumOd = sdf.format(prenociste.get("datumOd"));
                return datumOd;
            case 4:
                String datumDo = sdf.format(prenociste.get("datumDo"));
                return datumDo;
            case 5:
                Stanar stanar = (Stanar) prenociste.get("stanar");
                return stanar.toString();
            case 6:
                Stanar stanar1 = (Stanar) prenociste.get("stanar");
                return stanar1.getBrojSobe();
            case 7:
                Stanar stanar2 = (Stanar) prenociste.get("stanar");
                return stanar2.getBlok();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }
    
    public Prenociste vratiPrenociste(int rowIndex) {
        return (Prenociste) listaPrenocista.get(rowIndex);
    }
    
}
