package reactiva.reactivamovil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by daniel on 11/12/17.
 */

public class Utils {

    public static String URL = "http://107.170.105.224:6522";
    public static boolean DEMO_MODE = true;

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString((messageDigest[i] & 0xFF) | 0x100).substring(1, 3));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
