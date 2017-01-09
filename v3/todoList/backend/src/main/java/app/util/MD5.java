
package app.util;

import java.security.MessageDigest;
import static java.security.MessageDigest.getInstance;
import java.security.NoSuchAlgorithmException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;


public class MD5 {

    private static final Logger LOG = getLogger(MD5.class.getName());

    public static String parser(String texto) {
        try {
            if (texto != null && !texto.isEmpty()) {
                MessageDigest md = getInstance("MD5");
                md.update(texto.getBytes());

                byte byteData[] = md.digest();

                //convert the byte to hex format method 1
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }
                return sb.toString();
            }
        } catch (NoSuchAlgorithmException ex) {
            getLogger(MD5.class.getName()).log(SEVERE, null, ex);
        }
        return "";
    }

    private MD5() {
    }
}
