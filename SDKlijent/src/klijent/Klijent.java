/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.RequestObject;
import transfer.ResponseObject;

/**
 *
 * @author Bodin Todorovic
 */
public class Klijent {

    private static Klijent instanca;
    private Socket socket;
    
    private Klijent() {
    }

    public static Klijent getInstanca() {
        
        if(instanca == null)
            instanca = new Klijent();
        return instanca;
    }
    
    public void poveziSe(String IPAdresa, int port) throws Exception{
        try {
            socket = new Socket(IPAdresa, port);
            System.out.println("Klijent se povezao sa serverom");
        } catch (IOException ex) {
//            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("klijent.Klijent - linija 43 - pogresna kombinacija IP/Port");
            throw new Exception("Ne postoji server na takvoj kombinaciji IPAdresa/Port");
        }
    }
    
    
    public void posaljiZahtev(RequestObject request) throws Exception{
    
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
        } catch (IOException ex) {
//            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("klijent.Klijent - linija 56 - Izbacen sa servera");
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex1);
                System.out.println("Greska prilikom zatvaranja soketa");
            }
            throw new Exception("Konkcija sa serverom je izgubljena. Restartujte program i pokusajte ponovo.");
        }
    }
    
    public ResponseObject primiOdgovor(){
        ResponseObject response = new ResponseObject();
        
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            response = (ResponseObject) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }

    public Socket getSocket() {
        return socket;
    }
}
