/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Bodin Todorovic
 */
public class ServerNit extends Thread {

    ServerSocket serverSocket;
    boolean serverAktivan = true;

    public ServerNit(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        serverAktivan = true;
        osluskujKlijente();
    }

    public void iskljuciServerskuNit() throws IOException {
        for (int i = 0; i < Server.getKlijenti().size(); i++) {
            KlijentNit klijent = Server.getKlijenti().get(i);
            System.out.println(klijent);
            klijent.iskljuciKlijentskuNit();
            klijent.stop();
        }
        Server.getKlijenti().clear();
        serverAktivan = false;
    }

    private void osluskujKlijente() {
        try {
            while (serverAktivan) {
                System.out.println("Server osluskuje klijente");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent je povezan");
                KlijentNit klijentNit = new KlijentNit(socket);
                Server.getKlijenti().add(klijentNit);
                klijentNit.start();
            }
        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server iskljucen");
    }
}
