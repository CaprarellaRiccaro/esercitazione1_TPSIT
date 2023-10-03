import java.io.*;
import java.net.*;

public class ClienteStr {
    String nomeServer = "localhost";
    int portaServer   = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoSever;
    BufferedReader inDalServer;
    
    public Socket connetti (){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try{
            tastiera = new BufferedReader (new InputStreamReader(System.in));
            mioSocket = new Socket (nomeServer, portaServer);
            outVersoSever = new DataOutputStream (mioSocket.getOutputStream());
            inDalServer = new BufferedReader (new InputStreamReader (mioSocket.getInputStream()));
        }
        catch (UnknownHostException e){
            System.err.println("Host Sconosciuto");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mioSocket;
    }

    public void comunica(){
        try{
            System.out.print("4 ... inserisci la stringa da trasmettere al server:" + '\n');
            stringaUtente = tastiera.readLine();
            System.out.println("5 ... invio la stringa al server e attendo ...");
            outVersoSever.writeBytes(stringaUtente + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8 ... risposta del server" + '\n');
            System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
            mioSocket.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la communicazione col server");
            System.exit(1);
        }
    }
}


