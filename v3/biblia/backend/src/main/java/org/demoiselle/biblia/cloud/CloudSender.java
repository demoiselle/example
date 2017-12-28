package org.demoiselle.biblia.cloud;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

/**
 *
 * @author SERPRO
 */
@Stateless
public class CloudSender {

    private static final Logger LOG = Logger.getLogger(CloudSender.class.getName());

    public void send(String fingerprint, String textMessage) {

        try {

            CloudNotification webn = new CloudNotification();
            webn.setBody(textMessage);
            webn.setClick_action("https://seuapp.com.br/");
            webn.setIcon("https://seuapp.com.br/images/logo-large.png");
            webn.setTitle("seuapp.com.br");

            CloudMessage men = new CloudMessage();
            men.setPriority("normal");
            men.setNotification(webn);
            men.setTo(fingerprint);
            men.setContent_available(Boolean.TRUE);

            String url = "https://fcm.googleapis.com/fcm/send";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/json");
            // https://firebase.google.com/docs/cloud-messaging/js/client?authuser=0
            con.setRequestProperty("Authorization", "key=SuaChave");
            // https://console.firebase.google.com/project/{SuaApp}/settings/cloudmessaging/
            con.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
            outputStreamWriter.write(new Gson().toJson(men));
            outputStreamWriter.flush();
            int responseCode = con.getResponseCode();

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(CloudSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CloudSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
