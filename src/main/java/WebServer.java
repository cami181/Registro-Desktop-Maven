import Controllore.Controllore;
import gui.CreaStudentiFrame;
import okhttp3.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Utenti.*;

public class WebServer {

    // URL del server PHP
    private static final String SERVER_URL = "http://your-server-address/your-script.php"; // Sostituisci con l'URL del tuo server

    private static void sendRequestStudente(String action, Studente studente, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("studente", String.valueOf(studente))
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    SwingUtilities.invokeLater(() -> responseArea.setText("Risposta del server: \n" + responseBody));
                } else {
                    SwingUtilities.invokeLater(() -> responseArea.setText("Errore dal server: \n" + response.message()));
                }
            } catch (IOException ex) {
                // Mostra un errore in caso di problemi con la connessione
                SwingUtilities.invokeLater(() -> responseArea.setText("Errore di connessione: \n" + ex.getMessage()));
            }
        }).start();
    }

    private static void sendRequestGenitore(String action, Genitore genitore, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("genitore", String.valueOf(genitore))
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    SwingUtilities.invokeLater(() -> responseArea.setText("Risposta del server: \n" + responseBody));
                } else {
                    SwingUtilities.invokeLater(() -> responseArea.setText("Errore dal server: \n" + response.message()));
                }
            } catch (IOException ex) {
                // Mostra un errore in caso di problemi con la connessione
                SwingUtilities.invokeLater(() -> responseArea.setText("Errore di connessione: \n" + ex.getMessage()));
            }
        }).start();
    }

    private static void sendRequestDocente(String action, Docente docente, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("docente", String.valueOf(docente))
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    SwingUtilities.invokeLater(() -> responseArea.setText("Risposta del server: \n" + responseBody));
                } else {
                    SwingUtilities.invokeLater(() -> responseArea.setText("Errore dal server: \n" + response.message()));
                }
            } catch (IOException ex) {
                // Mostra un errore in caso di problemi con la connessione
                SwingUtilities.invokeLater(() -> responseArea.setText("Errore di connessione: \n" + ex.getMessage()));
            }
        }).start();
    }

    private static void sendRequestClasse(String action, Classe classe, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("classe", String.valueOf(classe))
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    SwingUtilities.invokeLater(() -> responseArea.setText("Risposta del server: \n" + responseBody));
                } else {
                    SwingUtilities.invokeLater(() -> responseArea.setText("Errore dal server: \n" + response.message()));
                }
            } catch (IOException ex) {
                // Mostra un errore in caso di problemi con la connessione
                SwingUtilities.invokeLater(() -> responseArea.setText("Errore di connessione: \n" + ex.getMessage()));
            }
        }).start();
    }
}
