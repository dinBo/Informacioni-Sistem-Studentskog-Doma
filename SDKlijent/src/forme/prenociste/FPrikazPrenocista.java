/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.prenociste;

import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import forme.prenociste.model.PrenocisteTableModel;
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
public class FPrikazPrenocista extends javax.swing.JPanel {

    /**
     * Creates new form FPrikazPrenocista
     */
    public FPrikazPrenocista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxtKriterijum = new javax.swing.JTextField();
        jbtnPretrazi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPrenocista = new javax.swing.JTable();
        jbtnDetalji = new javax.swing.JButton();

        jbtnPretrazi.setText("Pretrazi");
        jbtnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPretraziActionPerformed(evt);
            }
        });

        jLabel1.setText("Kriterijum pretrage:");

        jtblPrenocista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblPrenocista);

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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnDetalji)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPretrazi)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtKriterijum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPretrazi)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnDetalji)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPretraziActionPerformed
        // TODO add your handling code here:
        try {
            String kriterijum = jtxtKriterijum.getText();
            List<OpstiDomenskiObjekat> listaPrenocista = Kontroler.getInstanca().vratiPrenocistaSaPretragom(kriterijum);

            if (!listaPrenocista.isEmpty()) {
                srediTabelu(listaPrenocista);
                if (evt.getSource().equals(jbtnPretrazi)) {
                    JOptionPane.showMessageDialog(this, "Sistem je pronasao prenocista");
                }

            } else {
                srediTabelu(new ArrayList<>());
                if (evt.getSource().equals(jbtnPretrazi)) {
                    JOptionPane.showMessageDialog(this, "Ne postoje takva prenocista u sistemu");
                }
            }

        } catch (Exception ex) {
            if (evt.getSource().equals(jbtnPretrazi)) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jbtnPretraziActionPerformed

    private void jbtnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDetaljiActionPerformed
        // TODO add your handling code here:
        int red = jtblPrenocista.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Odaberite red!");
        } else {
            PrenocisteTableModel model = (PrenocisteTableModel) jtblPrenocista.getModel();
            Prenociste p = (Prenociste) model.vratiPrenociste(red);
            Helper.getInstance().put("p", p);
            FUnosPrenocista f = new FUnosPrenocista();
            Window w = SwingUtilities.getWindowAncestor(FPrikazPrenocista.this);
            JDialog jd = new JDialog((JDialog) w, "Prikaz prenocista", true);
            jd.setLayout(new BorderLayout());
            jd.add(f, BorderLayout.CENTER);
            jd.pack();
            jd.setLocationRelativeTo(null);
            jd.setVisible(true);
            while(jd.isVisible()){
                
            }
            jbtnPretraziActionPerformed(evt);

        }
    }//GEN-LAST:event_jbtnDetaljiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDetalji;
    private javax.swing.JButton jbtnPretrazi;
    private javax.swing.JTable jtblPrenocista;
    private javax.swing.JTextField jtxtKriterijum;
    // End of variables declaration//GEN-END:variables

    private void srediTabelu(List<OpstiDomenskiObjekat> lista) {
        TableModel ptm = new PrenocisteTableModel(lista);
        jtblPrenocista.setModel(ptm);
    }
}
