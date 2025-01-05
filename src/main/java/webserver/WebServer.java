package webserver;

import okhttp3.*;

import javax.swing.*;
import java.io.IOException;

import Utenti.*;

public class WebServer {
    // URL del server PHP
    private static final String SERVER_URL = "https://tommasomazzoni.altervista.org/WS.php";
    private static final String STUDENTI_URL = "https://tommasomazzoni.altervista.org/studente.php";

    public static void registerUser(String action, String username, String password, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("username", username)
                .add("password", password)
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

    public static void sendRequestStudente(String action, Studente studente, JTextArea responseArea) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("studente", String.valueOf(studente))
                .add("azione", action)
                .add("username",studente.getCredenziali().getUser())
                .add("password",studente.getCredenziali().getPassword())
                .add("nome",studente.getNome())
                .add("cognome",studente.getCognome())
                .add("data",studente.getDataDiNascita().toString())
                .add("codiceFiscale",studente.getCF())
                .add("classe",studente.getClasse().toString())
                .add("voti",studente.getStringVoti())
                .add("note",studente.getStringNote())
                .add("assenze",studente.getStringAssenze())
                .build();
        System.out.println(studente.getStringAssenze());

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(STUDENTI_URL)
                .post(formBody)
                .build();


        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    assert response.body() != null;
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

    public static void sendRequestGenitore(String action, Genitore genitore, JTextArea responseArea) {
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

    public static void sendRequestDocente(String action, Docente docente, JTextArea responseArea) {
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

    public static void sendRequestClasse(String action, Classe classe, JTextArea responseArea) {
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