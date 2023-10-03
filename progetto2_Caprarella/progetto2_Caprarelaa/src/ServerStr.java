import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server      = null;
    Socket client            = null;
    String stringaRicevuta   = null;
    String stringaModificata = null;
    BufferedReader   inDalCliente;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try{
            System.out.println(" 1 SERVER partitp in esecuzione ...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalCliente = new BufferedReader(new InputStreamReader( (client.getInputStream())));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
            stringaRicevuta = inDalCliente.readLine();
            System.out.println("6 ricevuta la stringa dal cliente ; " + stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata + '\n');

            System.out.println("9 SERVER: fine elaborazione ... buona notte!");
            client.close();
        }
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Non lo so");
            System.exit(1);
        }
    }
}
