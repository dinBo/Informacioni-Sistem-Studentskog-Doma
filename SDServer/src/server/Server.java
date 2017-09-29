/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.OpstiDomenskiObjekat;
import domen.Prenociste;
import domen.Referent;
import domen.Stanar;
import domen.ZaduzenjeInventara;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.KlijentNit;
import poslovnalogika.Kontroler;
import niti.ServerNit;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.ActionCode;
import util.EnumResponseStatus;

/**
 *
 * @author Bodin Todorovic
 */
public class Server {

    ServerSocket serverSocket;
    private static Server instanca;
    private ServerNit serverNit;
    private static List<KlijentNit> klijenti = new ArrayList<>();

    public static Server getInstanca() {
        if (instanca == null) {
            instanca = new Server();
        }
        return instanca;
    }

    public void pokreniServera(int port) throws Exception {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server program je pokrenut!\nCekam klijente.");

            serverNit = new ServerNit(serverSocket);
            serverNit.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Server nije pokrenut");
        }
    }

    public void zaustaviServera() throws Exception {
        try {
            serverNit.iskljuciServerskuNit();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Serverski soket nije zaustavljen");
        }
    }

    public void obradiKlijenta(Socket socket) throws IOException, ClassNotFoundException {
//        while (true) {
        System.out.println("Cekam zahtev klijenta");
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Object object = inSocket.readObject();
        RequestObject requestObject = (RequestObject) object;

        ResponseObject responseObject = obradiZahtev(requestObject);
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(responseObject);
        outSocket.flush();
//        }
    }

    public static List<KlijentNit> getKlijenti() {
        return klijenti;
    }

    private ResponseObject obradiZahtev(RequestObject requestObject) throws IOException {
        ResponseObject responseObject = new ResponseObject();

        int akcija = requestObject.getAction();
        try {
            switch (akcija) {
                case ActionCode.VRATI_SVE_FAKULTETE:
                    List<OpstiDomenskiObjekat> listaFakulteta = poslovnalogika.Kontroler.getInstanca().vratiListuFakulteta();
                    responseObject.setResponse(listaFakulteta);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.ULOGUJ_REFERENTA:
                    Referent referentUsernamePassword = (Referent) requestObject.getRequest();
                    Referent referent = (Referent) poslovnalogika.Kontroler.getInstanca().vratiReferenta(referentUsernamePassword);

                    if (referent != null) {
                        responseObject.setResponse(referent);
                        responseObject.setResponseStatus(EnumResponseStatus.OK);
                        //ovde i postavi referenta u listu za trenutno ulogovane referente
                    } else {
                        responseObject.setResponseStatus(EnumResponseStatus.ERROR);
                    }
                    break;
                case ActionCode.VRATI_SVE_STANARE:
                    List<OpstiDomenskiObjekat> listaStanara = poslovnalogika.Kontroler.getInstanca().vratiListuStanara();
                    responseObject.setResponse(listaStanara);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_SVE_REFERENTE:
                    List<OpstiDomenskiObjekat> listaReferenata = poslovnalogika.Kontroler.getInstanca().vratiListuReferenata();
                    responseObject.setResponse(listaReferenata);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.SACUVAJ_STANARA:
                    Stanar stanar = (Stanar) requestObject.getRequest();
                    Kontroler.getInstanca().sacuvajStanara(stanar);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspeso sacuvano");
                    break;
//                case ActionCode.VRATI_SVA_PRENOCISTA:
//                    List<OpstiDomenskiObjekat> listaPrenocista = poslovnalogika.Kontroler.getInstanca().vratiListuPrenocista();
//                    responseObject.setResponse(listaPrenocista);
//                    responseObject.setResponseStatus(EnumResponseStatus.OK);
//                    break;
                case ActionCode.SACUVAJ_PRENOCISTE:
                    Prenociste prenociste = (Prenociste) requestObject.getRequest();
                    Kontroler.getInstanca().sacuvajPrenociste(prenociste);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspesno sacuvano");
                    break;
                case ActionCode.VRATI_SVA_ZADUZENJA_INVENTARA:
                    //System.out.println("Pre");
                    List<OpstiDomenskiObjekat> listaZaduzenja = poslovnalogika.Kontroler.getInstanca().vratiListuZaduzenjaInventara();
                    //System.out.println("Posle");
                    responseObject.setResponse(listaZaduzenja);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_SAV_INVENTAR:
                    List<OpstiDomenskiObjekat> listaInventara = poslovnalogika.Kontroler.getInstanca().vratiListuInventara();
                    responseObject.setResponse(listaInventara);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.SACUVAJ_ZADUZENJE:
                    ZaduzenjeInventara zaduzenje = (ZaduzenjeInventara) requestObject.getRequest();
                    Kontroler.getInstanca().sacuvajZaduzenje(zaduzenje);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspesno sacuvano");
                    break;
                case ActionCode.VRATI_PRENOCISTA_SA_PRETRAGOM:
                    List<Object> parametriPrenocista = (List<Object>) requestObject.getRequest();
                    List<OpstiDomenskiObjekat> listaPrenocistaSaPretragom = Kontroler.getInstanca().vratiListuPrenocistaSaPretragom(parametriPrenocista);
                    responseObject.setResponse(listaPrenocistaSaPretragom);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_ZADUZENJA_INVENTARA_SA_PRETRAGOM:
                    List<Object> parametriZaduzenja = (List<Object>) requestObject.getRequest();
                    List<OpstiDomenskiObjekat> listaZaduzenjaSaPretragom = Kontroler.getInstanca().vratiListuZaduzenjaSaPretragom(parametriZaduzenja);
                    responseObject.setResponse(listaZaduzenjaSaPretragom);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_STANARE_SA_PRETRAGOM:
                    List<Object> parametriStanara = (List<Object>) requestObject.getRequest();
                    List<OpstiDomenskiObjekat> listaStanaraSaPretragom = Kontroler.getInstanca().vratiListuStanaraSaPretragom(parametriStanara);
                    responseObject.setResponse(listaStanaraSaPretragom);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.IZMENI_PRENOCISTE:
                    Prenociste prenocisteIzmena = (Prenociste) requestObject.getRequest();
                    Kontroler.getInstanca().izmeniPrenociste(prenocisteIzmena);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspesno sacuvano");
                    break;
                case ActionCode.IZMENI_STANARA:
                    Stanar stanarIzmena = (Stanar) requestObject.getRequest();
                    Kontroler.getInstanca().izmeniStanara(stanarIzmena);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspesno sacuvano");
                    break;
                case ActionCode.IZMENI_ZADUZENJE:
                    ZaduzenjeInventara zaduzenjeIzmena = (ZaduzenjeInventara) requestObject.getRequest();
                    Kontroler.getInstanca().izmeniZaduzenje(zaduzenjeIzmena);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Sve uspesno sacuvano");
                    break;
                case ActionCode.VRATI_ODREDJENO_PRENOCISTE:
                    Prenociste prenocisteZaVracanje = (Prenociste) requestObject.getRequest();
                    Prenociste vracenoPrenociste = Kontroler.getInstanca().vratiOdredjenoPrenociste(prenocisteZaVracanje);
                    responseObject.setResponse(vracenoPrenociste);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_ODREDJENOG_STANARA:
                    Stanar stanarZaVracanje = (Stanar) requestObject.getRequest();
                    Stanar vraceniStanar = Kontroler.getInstanca().vratiOdredjenogStanara(stanarZaVracanje);
                    responseObject.setResponse(vraceniStanar);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.VRATI_ODREDJENO_ZADUZENJE:
                    ZaduzenjeInventara zaduzenjeZaVracanje = (ZaduzenjeInventara) requestObject.getRequest();
                    ZaduzenjeInventara vracenoZaduzenje = Kontroler.getInstanca().vratiOdredjenoZaduzenje(zaduzenjeZaVracanje);
                    responseObject.setResponse(vracenoZaduzenje);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    break;
                case ActionCode.OBRISI_STANARA:
                    Stanar stanarBrisanje = (Stanar) requestObject.getRequest();
                    Kontroler.getInstanca().obrisiStanara(stanarBrisanje);
                    responseObject.setResponseStatus(EnumResponseStatus.OK);
                    responseObject.setMessage("Stanar obrisan");
                    break;
                default:
                    responseObject.setResponseStatus(EnumResponseStatus.ERROR);
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            responseObject.setResponseStatus(EnumResponseStatus.ERROR);
        }
        return responseObject;
    }
}
