/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komponente.model;

import domen.OpstiDomenskiObjekat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import niti.KlijentNit;

/**
 *
 * @author Bodin Todorovic
 */
public class KlijentiTableModel extends AbstractTableModel{

    private List<KlijentNit> listaKlijenata;
    private String[] kolone = {"Nit:", "Socket"};

    public KlijentiTableModel(List<KlijentNit> klijenti) {
        this.listaKlijenata = klijenti;
    }
    
    @Override
    public int getRowCount() {
        if (listaKlijenata == null) {
            return 0;
        }
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KlijentNit klijent = listaKlijenata.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return klijent.toString();
            case 1:
                return klijent.getSocket().toString();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }
    
    public KlijentNit vratiKlijenta(int rowIndex) {
        return listaKlijenata.get(rowIndex);
    }
    
}
