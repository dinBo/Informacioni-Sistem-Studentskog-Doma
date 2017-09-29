/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komponente.model.KlijentiTableModel;
import niti.KlijentNit;
import poslovnalogika.Kontroler;
import niti.NitOsvezavanjeTabele;
import server.Server;

/**
 *
 * @author Bodin Todorovic
 */
public class GlavnaServerskaForma extends javax.swing.JFrame {

    /**
     * Creates new form GlavnaServerskaForma
     */
    public GlavnaServerskaForma() {
        initComponents();
        jbtnPokreni.setEnabled(true);
        jbtnZaustavi.setEnabled(false);
        NitOsvezavanjeTabele nt = new NitOsvezavanjeTabele(this);
        nt.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtxtPort = new javax.swing.JTextField();
        jbtnPokreni = new javax.swing.JButton();
        jbtnZaustavi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblKlijenti = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jbtnIzbaciKlijenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        jLabel2.setText("Port:");

        jbtnPokreni.setText("Pokreni server");
        jbtnPokreni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPokreniActionPerformed(evt);
            }
        });

        jbtnZaustavi.setText("Zaustavi server");
        jbtnZaustavi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnZaustaviActionPerformed(evt);
            }
        });

        jtblKlijenti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtblKlijenti);

        jLabel1.setText("Aktivni Klijenti:");

        jbtnIzbaciKlijenta.setText("Izbaci klijenta");
        jbtnIzbaciKlijenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIzbaciKlijentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnPokreni, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jbtnZaustavi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jbtnIzbaciKlijenta))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPokreni)
                    .addComponent(jbtnZaustavi))
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnIzbaciKlijenta)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPokreniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPokreniActionPerformed
        try {
            int port = Integer.parseInt(jtxtPort.getText().trim());
            Server.getInstanca().pokreniServera(port);
            jbtnPokreni.setEnabled(false);
            jbtnZaustavi.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Server je pokrenut na portu: " + port);
        } catch (Exception ex) {
//            Logger.getLogger(GlavnaServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            jbtnPokreni.setEnabled(true);
            jbtnZaustavi.setEnabled(false);
            if (jtxtPort.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Morate uneti broj porta");
            } else {
                JOptionPane.showMessageDialog(this, "Server nije pokrenut");
            }
        }
    }//GEN-LAST:event_jbtnPokreniActionPerformed

    private void jbtnZaustaviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnZaustaviActionPerformed
        // TODO add your handling code here:
        try {
            Server.getInstanca().zaustaviServera();
            jbtnPokreni.setEnabled(true);
            jbtnZaustavi.setEnabled(false);
            srediTabelu();
            JOptionPane.showMessageDialog(this, "Server je zaustavljen");
        } catch (Exception ex) {
            Logger.getLogger(GlavnaServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
            jbtnPokreni.setEnabled(false);
            jbtnZaustavi.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Server nije zaustavljen");
        }
    }//GEN-LAST:event_jbtnZaustaviActionPerformed

    private void jbtnIzbaciKlijentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIzbaciKlijentaActionPerformed
        int red = jtblKlijenti.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Odaberite red!");
        } else {
            try {
                KlijentiTableModel tm = (KlijentiTableModel) jtblKlijenti.getModel();
                KlijentNit klijent = tm.vratiKlijenta(red);
                klijent.getSocket().close();
                klijent.stop();
                Server.getKlijenti().remove(klijent);
                srediTabelu();
                JOptionPane.showMessageDialog(this, "Klijent je uspesno izbacen");
            } catch (IOException ex) {
                Logger.getLogger(GlavnaServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Klijent nije izbacen");
            }
        }
    }//GEN-LAST:event_jbtnIzbaciKlijentaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GlavnaServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlavnaServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlavnaServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlavnaServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlavnaServerskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnIzbaciKlijenta;
    private javax.swing.JButton jbtnPokreni;
    private javax.swing.JButton jbtnZaustavi;
    private javax.swing.JTable jtblKlijenti;
    private javax.swing.JTextField jtxtPort;
    // End of variables declaration//GEN-END:variables

    public void srediTabelu() {
        KlijentiTableModel tm = new KlijentiTableModel(Server.getKlijenti());
        jtblKlijenti.setModel(tm);
    }
}