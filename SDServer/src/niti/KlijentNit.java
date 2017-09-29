/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Bodin Todorovic
 */
public class KlijentNit extends Thread {

    private Socket socket;
    boolean klijentAktivan = true;

    public KlijentNit(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        opsluziKlijenta();
    }

    public void iskljuciKlijentskuNit() throws IOException {
        klijentAktivan = false;
        socket.close();
        System.out.println("Klijentski soket iskljucen: " + socket.isClosed());
    }

    private void opsluziKlijenta() {
        while (klijentAktivan) {
            try {
                try {
                    Server.getInstanca().obradiKlijenta(socket);
                } catch (SocketException | EOFException ex) {
                    System.out.println("Klijent se odjavio\nIskljucujem klijenta");
                    socket.close();
                    klijentAktivan = false;
                    Server.getKlijenti().remove(this);
                    System.out.println("Klijent iskljucen");
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Dogodila se greska prilikom zatvaranja soketa");
                klijentAktivan = false;
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
