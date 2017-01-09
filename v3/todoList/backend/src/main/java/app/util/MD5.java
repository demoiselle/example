/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 70744416353
 */
public class MD5 {

    public static String parser(String texto) {
        try {
            if (texto != null && !texto.isEmpty()) {
                MessageDigest md = MessageDigest.getInstance("MD5");
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
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
