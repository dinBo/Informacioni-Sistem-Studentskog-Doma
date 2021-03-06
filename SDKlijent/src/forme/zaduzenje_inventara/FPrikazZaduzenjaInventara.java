/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.zaduzenje_inventara;

import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import domen.StavkaZaduzenjaInventara;
import domen.ZaduzenjeInventara;
import forme.prenociste.FPrikazPrenocista;
import forme.prenociste.FUnosPrenocista;
import forme.prenociste.model.PrenocisteTableModel;
import forme.zaduzenje_inventara.model.ZaduzenjeInventaraTableModel;
import help.Helper;
import java.awt.BorderLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import kontroler.Kontroler;

/**
 *
 * @author Bodin Todorovic
 */
public class FPrikazZaduzenjaInventara extends javax.swing.JPanel {

    /**
     * Creates new form FPrikazZaduzenjaInventara
     */
    public FPrikazZaduzenjaInventara() {
        initComponents();
        vratiSve();
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
        jtxtKriterijumPretrage = new javax.swing.JTextField();
        jbtnPretrazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblZaduzenjaInventara = new javax.swing.JTable();
        jbtnDetalji = new javax.swing.JButton();

        jLabel1.setText("Kriterijum pretrage:");

        jbtnPretrazi.setText("Pretrazi");
        jbtnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPretraziActionPerformed(evt);
            }
        });

        jtblZaduzenjaInventara.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblZaduzenjaInventara);

        jbtnDetalji.setText("Detalji");
        jbtnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDetaljiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnDetalji)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jtxtKriterijumPretrage, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jbtnPretrazi))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtKriterijumPretrage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPretrazi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnDetalji)
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPretraziActionPerformed
        // TODO add your handling code here:
        try {
            String kriterijum = jtxtKriterijumPretrage.getText();
            List<OpstiDomenskiObjekat> listaZaduzenja = Kontroler.getInstanca().vratiZaduzenjaInventaraSaPretragom(kriterijum);

            if (!listaZaduzenja.isEmpty()) {
                srediTabelu(listaZaduzenja);
                if (evt.getSource().equals(jbtnPretrazi)) {
                    JOptionPane.showMessageDialog(this, "Sistem je pronasao zaduzenja inventara");
                }

            } else {
                srediTabelu(new ArrayList<>());
                if (evt.getSource().equals(jbtnPretrazi)) {
                    JOptionPane.showMessageDialog(this, "Ne postoje takva zaduzenja inventara u sistemu");
                }
            }

        } catch (Exception ex) {
            if (evt.getSource().equals(jbtnPretrazi)) {
                System.out.println("forme.zaduzenje_inventara.FUnosZaduzenjaInventara - linija 140 - " + ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbtnPretraziActionPerformed

    private void jbtnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDetaljiActionPerformed
        // TODO add your handling code here:
        int red = jtblZaduzenjaInventara.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Odaberite red!");
        } else {
            try {
                ZaduzenjeInventaraTableModel model = (ZaduzenjeInventaraTableModel) jtblZaduzenjaInventara.getModel();
                ZaduzenjeInventara zi = (ZaduzenjeInventara) model.vratiZaduzenje(red);

                zi = Kontroler.getInstanca().vratiOdredjenoZaduzenje(zi);

                Helper.getInstance().put("zi", zi);
                FUnosZaduzenjaInventara f = new FUnosZaduzenjaInventara();
                Window w = SwingUtilities.getWindowAncestor(FPrikazZaduzenjaInventara.this);
                JDialog jd = new JDialog((JDialog) w, "Prikaz Zaduzenja Inventara", true);
                jd.setLayout(new BorderLayout());
                jd.add(f, BorderLayout.CENTER);
                jd.pack();
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);
                while (jd.isVisible()) {

                }
                jbtnPretraziActionPerformed(evt);
            } catch (Exception ex) {
//                Logger.getLogger(FPrikazZaduzenjaInventara.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("forme.zaduzenje_inventara.FPrikazZaduzenjaInventara - linija 174 - " + ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

        }
    }//GEN-LAST:event_jbtnDetaljiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDetalji;
    private javax.swing.JButton jbtnPretrazi;
    private javax.swing.JTable jtblZaduzenjaInventara;
    private javax.swing.JTextField jtxtKriterijumPretrage;
    // End of variables declaration//GEN-END:variables

    private void srediTabelu(List<OpstiDomenskiObjekat> lista) {
//        List<ZaduzenjeInventara> listaZaduzenja;
//        try {
//            //System.out.println("Pre");
//            listaZaduzenja = Kontroler.getInstanca().vratiZaduzenjaInventara();
//            //System.out.println("Posle");
//        } catch (Exception ex) {
//            Logger.getLogger(FPrikazPrenocista.class.getName()).log(Level.SEVERE, null, ex);
//            listaZaduzenja = new ArrayList<>();
//        }

        TableModel ptm = new ZaduzenjeInventaraTableModel(lista);
        jtblZaduzenjaInventara.setModel(ptm);
    }

    private void vratiSve() {
        List<OpstiDomenskiObjekat> listaZaduzenja;
        try {
            //System.out.println("Pre");
            listaZaduzenja = Kontroler.getInstanca().vratiZaduzenjaInventara();
            //System.out.println("Posle");
        } catch (Exception ex) {
//            Logger.getLogger(FPrikazPrenocista.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("forme.zaduzenje_inventara.FPrikazZaduzenjaInventara - linija 214 - " + ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage());
            listaZaduzenja = new ArrayList<>();
        }

        srediTabelu(listaZaduzenja);
    }
}
