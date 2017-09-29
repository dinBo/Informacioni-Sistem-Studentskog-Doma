/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.stanar.model;

import domen.Stanar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bodin Todorovic
 */
public class StanarTableModel extends AbstractTableModel{

    private List<Stanar> stanari;
    private String[] kolone = {"ID", "Ime:", "Prezime:", "Ime Roditelja:", "Broj Sobe:", "Blok:", "Datum Rodjenja:",
        "Godina Upisa:", "Fakultet:", "Referent:"};

    public StanarTableModel(List<Stanar> stanari) {
        this.stanari = stanari;
    }
    
    @Override
    public int getRowCount() {
        if (stanari == null) {
            return 0;
        }
        return stanari.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Stanar stanar = stanari.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return stanar.getStanarID();
            case 1:
                return stanar.getIme();
            case 2:
                return stanar.getPrezime();
            case 3:
                return stanar.getImeRoditelja();
            case 4:
                return stanar.getBrojSobe();
            case 5:
                return stanar.getBlok();
            case 6:
                return sdf.format(stanar.getDatumRodjenja());
            case 7:
                return stanar.getGodinaUpisa();
            case 8:
                return stanar.getFakultet().getNaziv();
            case 9:
                return stanar.getReferent().getIme() + " " + stanar.getReferent().getPrezime();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }
    
    public Stanar vratiStanara(int rowIndex) {
        return stanari.get(rowIndex);
    }
    
}
