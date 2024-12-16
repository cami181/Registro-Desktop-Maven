//import okhttp3.*;

public class WebServer {
   /* private static void sendRequest(String action, String username, String password, JTextArea responseArea) {
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
    }*/
}