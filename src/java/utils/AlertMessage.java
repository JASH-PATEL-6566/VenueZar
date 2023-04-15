package utils;

import java.security.NoSuchAlgorithmException;

public class AlertMessage {
    public static String alertMessage(String msg,String page) throws NoSuchAlgorithmException {
        return "<script type=\"text/javascript\">\nvar result = confirm("+"\""+msg+"\""+");\nif (result) {\nwindow.location.href = "+"\""+page+"\""+";\n}\n else {\nwindow.location.href = "+"\""+page+"\""+";\n}\n</script>";
    }
}
