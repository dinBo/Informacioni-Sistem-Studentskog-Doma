/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.prenociste;

import domen.Fakultet;
import domen.Prenociste;
import domen.Stanar;
import help.Helper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import klijent.Klijent;
import kontroler.Kontroler;

/**
 *
 * @author Bodin Todorovic
 */
public class FUnosPrenocista extends javax.swing.JPanel {

    /**
     * Creates new form FUnosPrenocista
     */
    public FUnosPrenocista() {
        initComponents();
        napuniCBStanari();
        jtxtPrenocisteID.setText("0");//ovo izmeni, namesti da vraca max prenocisteID
        //border = jtxtPrenocisteID.getBorder();
        Prenociste p = (Prenociste) Helper.getInstance().get("p");
        if (p != null) {
            prikazPrenocista(p);
            jbtnSacuvaj.setText("Izmeni");
            Helper.getInstance().remove("p");
            JOptionPane.showMessageDialog(this, "Sistem je pronasao izabrano prenociste");

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtxtPrenocisteID = new javax.swing.JTextField();
        jtxtImeGosta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtPrezimeGosta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtDatumOd = new javax.swing.JTextField();
        jtxtDatumDo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcomboStanar = new javax.swing.JComboBox();
        jbtnSacuvaj = new javax.swing.JButton();

        jLabel1.setText("Prenociste id:");

        jtxtPrenocisteID.setEnabled(false);

        jLabel2.setText("Ime gosta:");

        jLabel3.setText("Prezime gosta:");

        jLabel4.setText("Datum od:");

        jLabel5.setText("Datum do:");

        jLabel6.setText("Stanar:");

        jcomboStanar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbtnSacuvaj.setText("Sacuvaj prenociste");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtDatumDo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jtxtDatumOd, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jtxtPrezimeGosta, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jcomboStanar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addComponent(jbtnSacuvaj))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtPrenocisteID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtImeGosta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtPrenocisteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtImeGosta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtPrezimeGosta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtDatumDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcomboStanar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSacuvaj))
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        // TODO add your handling code here:
        try {
            izvrsiValidaciju();
            int prenocisteID = Integer.parseInt(jtxtPrenocisteID.getText());
            String imeGosta = jtxtImeGosta.getText().trim();
            String prezimeGosta = jtxtPrezimeGosta.getText().trim();
            Stanar stanar = (Stanar) jcomboStanar.getSelectedItem();

            DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            Date datumOd;
            Date datumDo;
            try {
                datumOd = sdf.parse(jtxtDatumOd.getText().trim());
                datumDo = sdf.parse(jtxtDatumDo.getText().trim());
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Unesite pravilan format datuma");
                return;
            }

            Prenociste prenociste = new Prenociste(prenocisteID, imeGosta, prezimeGosta, datumOd, datumDo, stanar);
            izvrsiValidacijuPrenocista(prenociste);

            String poruka;
            if (jbtnSacuvaj.getText().equals("Sacuvaj prenociste")) {
                System.out.println("Usao u 'Sacuvaj'");
                poruka = Kontroler.getInstanca().sacuvajPrenociste(prenociste);
            } else {
                System.out.println("Usao u 'Izmeni'");
                poruka = Kontroler.getInstanca().izmeniPrenociste(prenociste);
            }
            jbtnSacuvaj.setEnabled(false);
            JOptionPane.showMessageDialog(this, poruka);
        } catch (Exception ex) {
            //Logger.getLogger(FUnosStanara.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("forme.prenociste.FUnosPrenocista - linija 193 - " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JComboBox jcomboStanar;
    private javax.swing.JTextField jtxtDatumDo;
    private javax.swing.JTextField jtxtDatumOd;
    private javax.swing.JTextField jtxtImeGosta;
    private javax.swing.JTextField jtxtPrenocisteID;
    private javax.swing.JTextField jtxtPrezimeGosta;
    // End of variables declaration//GEN-END:variables

    private void napuniCBStanari() {
        jcomboStanar.removeAllItems();
        ArrayList<Stanar> stanari;
        try {
            stanari = Kontroler.getInstanca().vratiStanare();
        } catch (Exception ex) {
//            Logger.getLogger(FStanar.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("forme.prenociste.FUnosPrenocista - linija 222 - " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage());
            stanari = new ArrayList<>();
        }
        for (Stanar stanar : stanari) {
            jcomboStanar.addItem(stanar);
        }
    }

    private void izvrsiValidaciju() throws Exception {
        if (Klijent.getInstanca().getSocket().isClosed()) {
            throw new Exception("Konkcija sa serverom je izgubljena. Restartujte program i pokusajte ponovo.");
        }
        if (jtxtImeGosta.getText().isEmpty() || jtxtDatumDo.getText().isEmpty() || jtxtDatumOd.getText().isEmpty() || jtxtPrezimeGosta.getText().isEmpty()) {
            throw new Exception("Sva polja su obavezna");
        }
    }

    private void prikazPrenocista(Prenociste p) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        //----------------------------------------
        try {
            Prenociste prenociste = Kontroler.getInstanca().vratiOdredjenoPrenociste(p);

            jtxtPrenocisteID.setText(prenociste.getPrenocisteID() + "");
            jtxtDatumDo.setText(sdf.format(prenociste.getDatumDo()));
            jtxtDatumOd.setText(sdf.format(prenociste.getDatumOd()));
            jtxtImeGosta.setText(prenociste.getImeGosta());
            jtxtPrezimeGosta.setText(prenociste.getPrezimeGosta());
            System.out.println(prenociste.getStanar());
            jcomboStanar.setSelectedItem(prenociste.getStanar());
        } catch (Exception ex) {
            jtxtPrenocisteID.setText(p.getPrenocisteID() + "");
            jtxtDatumDo.setText(sdf.format(p.getDatumDo()));
            jtxtDatumOd.setText(sdf.format(p.getDatumOd()));
            jtxtImeGosta.setText(p.getImeGosta());
            jtxtPrezimeGosta.setText(p.getPrezimeGosta());
            System.out.println(p.getStanar());
            jcomboStanar.setSelectedItem(p.getStanar());
        }
        //----------------------------------------
//        jtxtPrenocisteID.setText(p.getPrenocisteID() + "");
//        jtxtDatumDo.setText(sdf.format(p.getDatumDo()));
//        jtxtDatumOd.setText(sdf.format(p.getDatumOd()));
//        jtxtImeGosta.setText(p.getImeGosta());
//        jtxtPrezimeGosta.setText(p.getPrezimeGosta());
//        System.out.println(p.getStanar());
//        jcomboStanar.setSelectedItem(p.getStanar());
    }

    private void izvrsiValidacijuPrenocista(Prenociste prenociste) throws Exception {
        if (prenociste.getDatumOd().before(new Date())) {
            throw new Exception("'Datum od' ne sme biti pre danasnjeg datuma");
        }
        if (prenociste.getDatumDo().before(prenociste.getDatumOd())) {
            throw new Exception("'Datum do' mora biti veci od 'datum od'");
        }
    }
}
