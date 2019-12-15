package Net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class demo_URLcoder {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s= URLEncoder.encode("·è¿ñjava","UTF-8");
        System.out.println(s);
        String r= URLDecoder.decode("%E7%96%AF%E7%8B%82java","UTF-8");
        System.out.println(r);
    }
}
