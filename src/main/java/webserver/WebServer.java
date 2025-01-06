package webserver;

import okhttp3.*;

import java.io.IOException;

import Utenti.*;

public class WebServer {
    // URL dei file PHP
    private static final String USER_URL = "https://tommasomazzoni.altervista.org/WS.php";
    private static final String STUDENTI_URL = "https://tommasomazzoni.altervista.org/studente.php";
    private static final String DOCENTI_URL = "https://tommasomazzoni.altervista.org/docente.php";
    private static final String GENITORI_URL = "https://tommasomazzoni.altervista.org/genitore.php";
    private static final String CLASSI_URL = "https://tommasomazzoni.altervista.org/classe.php";

    /**
     * Funzione che registra un utente inviando una richiesta HTTP POST al server.
     *
     * @param action Azione da eseguire.
     * @param username Nome utente.
     * @param password Password utente.
     */
    public static void registerUser(String action, String username, String password) {
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
                .url(USER_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                } else {
                    String responseBody = response.body().string();
                    System.out.println(responseBody);
                }
            } catch (IOException ex) {
                // Mostra un errore in caso di problemi con la connessione
                System.out.println("Eccezione: " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Funzione che crea/elimina uno studente dal sistema inviando una richiesta HTTP POST al server.
     *
     * @param action Azione da eseguire.
     * @param studente Studente da creare/eliminare.
     */
    public static void creaEliminaStudente(String action, Studente studente) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("username",studente.getCredenziali().getUser())
                .add("password",studente.getCredenziali().getPassword())
                .add("nome",studente.getNome())
                .add("cognome",studente.getCognome())
                .add("data",studente.getStringData())
                .add("codiceFiscale",studente.getCF())
                .add("classe",studente.getClasse().toString())
                .add("voti",studente.getStringVoti())
                .add("note",studente.getStringNote())
                .add("assenze",studente.getStringAssenze())
                .build();

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
                } else {
                    System.out.println(response.body().string());
                }
            } catch (IOException ex) {
                System.out.println("Eccezione: " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Funzione che crea/elimina un docente dal sistema inviando una richiesta HTTP POST al server.
     *
     * @param action Azione da eseguire.
     * @param docente Docente da creare/eliminare.
     */
    public static void creaEliminaDocente(String action, Docente docente) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("username",docente.getCredenziali().getUser())
                .add("password",docente.getCredenziali().getPassword())
                .add("nome",docente.getNome())
                .add("cognome",docente.getCognome())
                .add("data",docente.getStringData())
                .add("codiceFiscale",docente.getCF())
                .add("classi",docente.getStringClassi())
                .add("materie", docente.getStringMaterie())
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(DOCENTI_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                } else {
                    System.out.println(response.body().string());
                }
            } catch (IOException ex) {
                System.out.println("Eccezione: " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Funzione che crea/elimina un genitore dal sistema inviando una richiesta HTTP POST al server.
     *
     * @param action Azione da eseguire.
     * @param genitore Genitore da creare/eliminare.
     */
    public static void creaEliminaGenitore(String action, Genitore genitore) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("username",genitore.getCredenziali().getUser())
                .add("password",genitore.getCredenziali().getPassword())
                .add("nome",genitore.getNome())
                .add("cognome",genitore.getCognome())
                .add("data",genitore.getStringData())
                .add("codiceFiscale",genitore.getCF())
                .add("cfFiglio",genitore.getFiglio().getCF())
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(GENITORI_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                } else {
                    System.out.println(response.body().string());
                }
            } catch (IOException ex) {
                System.out.println("Eccezione: " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Funzione che crea/elimina una classe dal sistema inviando una richiesta HTTP POST al server.
     *
     * @param action Azione da eseguire.
     * @param classe Classe da creare/eliminare.
     */
    public static void creaEliminaClasse(String action, Classe classe) {
        // Configura il client HTTP
        OkHttpClient client = new OkHttpClient();

        // Costruisce il corpo della richiesta
        RequestBody formBody = new FormBody.Builder()
                .add("azione", action)
                .add("anno", String.valueOf(classe.getAnno()))
                .add("indirizzo",classe.getIndirizzo())
                .add("sezione", String.valueOf(classe.getSezione()))
                .build();

        // Costruisce la richiesta HTTP
        Request request = new Request.Builder()
                .url(CLASSI_URL)
                .post(formBody)
                .build();

        // Esegue la richiesta in un thread separato
        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                // Mostra la risposta nella text area
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                } else {
                    System.out.println(response.body().string());
                }
            } catch (IOException ex) {
                System.out.println("Eccezione: " + ex.getMessage());
            }
        }).start();
    }

    /**
     * Funzione che serve per ottenere la lista delle classi dal server tramite una richiesta HTTP GET.
     *
     * @return Risposta server con informazioni sulle classi.
     */
    public static String getClassi() {
        OkHttpClient client = new OkHttpClient();

        Request r = new Request.Builder().url(CLASSI_URL).get().build();

        try {
            Response response = client.newCall(r).execute();

            // Mostra la risposta nella console
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            } else {
                String responseBody = response.body().string();
            }
        } catch (IOException ex) {
            // Gestisci gli errori di connessione
            System.out.println("Eccezione: " + ex.getMessage());
        }
        return "";
    }

    /**
     * Funzione che serve per ottenere la lista dei genitori dal server tramite una richiesta HTTP GET.
     *
     * @return Risposta server con informazioni sui genitori.
     */
    public static String getGenitori(){
        OkHttpClient client = new OkHttpClient();

        Request r = new Request.Builder().url(GENITORI_URL).get().build();

        try {
            Response response = client.newCall(r).execute();

            // Mostra la risposta nella console
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            } else {
                String responseBody = response.body().string();
            }
        } catch (IOException ex) {
            // Gestisci gli errori di connessione
            System.out.println("Eccezione: " + ex.getMessage());
        }
        return "";
    }

    /**
     * Funzione che serve per ottenere la lista dei docenti dal server tramite una richiesta HTTP GET.
     *
     * @return Risposta server con informazioni sui docenti.
     */
    public static String getDocenti(){
        OkHttpClient client = new OkHttpClient();

        Request r = new Request.Builder().url(DOCENTI_URL).get().build();

        try {
            Response response = client.newCall(r).execute();

            // Mostra la risposta nella console
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            } else {
                String responseBody = response.body().string();
            }
        } catch (IOException ex) {
            // Gestisci gli errori di connessione
            System.out.println("Eccezione: " + ex.getMessage());
        }
        return "";
    }

    /**
     * Funzione che serve per ottenere la lista degli studenti dal server tramite una richiesta HTTP GET.
     *
     * @return Risposta server con informazioni sugli studenti.
     */
    public static String getStudenti(){
        OkHttpClient client = new OkHttpClient();

        Request r = new Request.Builder().url(STUDENTI_URL).get().build();

        try {
            Response response = client.newCall(r).execute();

            // Mostra la risposta nella console
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                return responseBody;
            } else {
                String responseBody = response.body().string();
                System.out.println("errore: " + responseBody);
            }
        } catch (IOException ex) {
            System.out.println("Eccezione: " + ex.getMessage());
        }
        return "";
    }
}