/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.zaduzenje_inventara.model;

import domen.StavkaZaduzenjaInventara;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bodin Todorovic
 */
public class StavkeTableModel extends AbstractTableModel {

    List<StavkaZaduzenjaInventara> listaStavki;
    String[] kolone = new String[]{"Redni broj:", "Kolicina", "Naziv inventara:"};

    public StavkeTableModel(List<StavkaZaduzenjaInventara> lista) {
        this.listaStavki = lista;
        for (StavkaZaduzenjaInventara stavka : lista) {
            stavka.setStatus("update");
        }
    }

    @Override
    public int getRowCount() {
        if (listaStavki == null) {
            return 0;
        }
        return vratiBrojRedova();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaZaduzenjaInventara stavka = vratiStavku(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRedniBroj();
            case 1:
                return stavka.getKolicina();
            case 2:
                return stavka.getInventar();
            default:
                return "N/A";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

    public void dodajRed() {
        StavkaZaduzenjaInventara sn = new StavkaZaduzenjaInventara();
        int redniBroj = vratiBrojRedova();
        redniBroj++;
        sn.setRedniBroj(redniBroj);
        System.out.println("RB ubacene: " + redniBroj);
        listaStavki.add(sn);
        fireTableDataChanged();
    }

    public List<StavkaZaduzenjaInventara> vratiStavke() {
        return listaStavki;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaZaduzenjaInventara stavka = vratiStavku(rowIndex);
        switch (columnIndex) {
            case 1:
                try {
                    stavka.set("kolicina", Integer.parseInt((String) aValue));
                } catch (Exception ex) {
                    stavka.set("kolicina", 0);
                }
                break;
            case 2:
                stavka.set("inventar", aValue);
                break;

        }
    }

    public void obrisiRed(int i) {
        StavkaZaduzenjaInventara stavka = vratiStavku(i);
        System.out.println("RB obrisane: " + stavka.getRedniBroj());
        stavka.setStatus("delete");
        srediRedneBrojeve();
        fireTableDataChanged();
    }

    public StavkaZaduzenjaInventara vratiStavku(int red) {
        for (StavkaZaduzenjaInventara s : listaStavki) {
            if (s.getRedniBroj() == red + 1 && !s.getStatus().equals("delete")) {
                return s;
            }
        }
        return null;
    }

    public int vratiBrojRedova() {
        int brRedova = 0;
        for (StavkaZaduzenjaInventara s : listaStavki) {
            if (!s.getStatus().equals("delete")) {
                brRedova++;
            }
        }
        return brRedova;
    }
    private void srediRedneBrojeve() {
        int red = 1;
        for (StavkaZaduzenjaInventara stavka : listaStavki) {
            System.out.println("**Iteracija*************************");
            if (!stavka.getStatus().equals("delete")) {
                System.out.println("Trenutni RB: " + stavka.getRedniBroj());
                stavka.setRedniBroj(red);
                System.out.println(" Novi RB: " + stavka.getRedniBroj() + " Inventar: " + stavka.getInventar().getNazivInventara());
                red++;
            } else {
                System.out.println("U else-u! Postavljen delete! RB: " + stavka.getRedniBroj() + " Inventar: " + stavka.getInventar().getNazivInventara());
            }
        }
        System.out.println("-----------------------------");
    }
}
