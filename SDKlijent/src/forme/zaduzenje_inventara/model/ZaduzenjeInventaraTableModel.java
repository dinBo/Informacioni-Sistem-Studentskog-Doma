/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.zaduzenje_inventara.model;

import domen.OpstiDomenskiObjekat;
import domen.Referent;
import domen.Stanar;
import domen.ZaduzenjeInventara;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bodin Todorovic
 */
public class ZaduzenjeInventaraTableModel extends AbstractTableModel{

    private List<OpstiDomenskiObjekat> listaZaduzenja;
    private String[] kolone = {"Sifra zaduzenja", "Datum zaduzenja:", "Stanar:", "Referent:"};//, "Broj zaduzenih stavki:"};

    public ZaduzenjeInventaraTableModel(List<OpstiDomenskiObjekat> zaduzenja) {
        this.listaZaduzenja = zaduzenja;
    }
    
    @Override
    public int getRowCount() {
        if (listaZaduzenja == null) {
            return 0;
        }
        return listaZaduzenja.size();
    }

    @Override
    public int getColumnCount() {
        return 4;//5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        OpstiDomenskiObjekat zaduzenje = listaZaduzenja.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return zaduzenje.get("sifraZaduzenja");
            case 1:
                String datumZaduzenja = sdf.format(zaduzenje.get("datumZaduzenja"));
                return datumZaduzenja;
            case 2:
                Stanar stanar = (Stanar) zaduzenje.get("stanar");
                return stanar.toString();
            case 3:
                Referent referent = (Referent) zaduzenje.get("referent");
                return referent.toString();
//            case 4:
//                return zaduzenje.getListaStavki().size();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }
    
    public ZaduzenjeInventara vratiZaduzenje(int rowIndex) {
        return (ZaduzenjeInventara) listaZaduzenja.get(rowIndex);
    }
    
}
