package fnal.gov.client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.KeyManagerFactory;
import java.security.cert.X509Certificate;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class HttpUtil {
    public HttpUtil() {
    }
    
    public String readSecureUrl(String url) throws Exception {
            initSecureConnection();
            HttpsURLConnection con = null;
            BufferedReader in = null;
            String toReturn = "";
            try {
                    con =  ( HttpsURLConnection ) (new URL(url)).openConnection() ;
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    toReturn  = readUrl(in);
            } finally {
                    if(in != null) in.close();
                    if(con != null)  con.disconnect();
            }
            return toReturn;
    }


    public String postSecureUrl(String url, String data) throws Exception {
            initSecureConnection();
            HttpsURLConnection con = null;
            BufferedReader in = null;
            DataOutputStream out = null;
            String toReturn = "";
            //System.out.println("\n\n\n*********************************************\n\ndata is " + data);
            try {
                    con =  ( HttpsURLConnection ) (new URL(url)).openConnection() ;
                    con.setDoOutput(true);

                    out = new DataOutputStream(con.getOutputStream());
                    out.writeBytes(data);
                    out.flush();

                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    toReturn  = readUrl(in);
            } finally {
                    if(in != null) in.close();
                    if(out != null) out.close();
                    if(con != null)  con.disconnect();
            }
            return toReturn;
    }

    public String readUrl(String url) throws Exception {
            //System.out.println("url in readUrl is " + url);
            if (( new URL(url)).getProtocol().toLowerCase().equals("https")) return readSecureUrl(url);

            HttpURLConnection con = null;
            BufferedReader in = null;
            String toReturn = "";
            try {
                    con =  ( HttpURLConnection ) (new URL(url)).openConnection() ;
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    toReturn  = readUrl(in);
            } finally {
                    if(in != null) in.close();
                    if(con != null)  con.disconnect();
            }
            return toReturn;
    }

    public String postUrl(String url, String data) throws Exception {
            //System.out.println("url in postUrl is " + url);
            if (( new URL(url)).getProtocol().toLowerCase().equals("https")) return postSecureUrl(url, data);

            HttpURLConnection con = null;
            BufferedReader in = null;
            DataOutputStream out = null;
            String toReturn = "";
            //System.out.println("\n\n\n*********************************************\n\ndata is " + data);
            try {
                    con =  (HttpURLConnection) (new URL(url)).openConnection() ;
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    out = new DataOutputStream(con.getOutputStream());
                    out.writeBytes(data);
                    out.flush();

                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    toReturn  = readUrl(in);
            } finally {
                    if(in != null) in.close();
                    if(out != null) out.close();
                    if(con != null)  con.disconnect();
            }
            return toReturn;
    }

    private String readUrl(BufferedReader in) throws Exception {
            String toReturn = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                    toReturn += inputLine;
                    //System.out.println(inputLine);
            }
            return toReturn;
    }

    private void initSecureConnection () throws Exception {
            //final String cert = "/home/sekhri/mycert.p12";
            //final String cert = "C:\\Documents and Settings\\Vijay Sekhri\\Desktop\\mycert.p12";
            String cert = System.getProperty("certificate");
            //final String pass = "vijayneha";
            String pass = System.getProperty("password");;
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load((new FileInputStream(cert)), pass.toCharArray());
            KeyManagerFactory kmfactory = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm());
            kmfactory.init(ks, pass.toCharArray());
            SSLContext sc = SSLContext.getInstance("ssl");
            TrustManager[] tma = { new RelaxedTrustManager() };
            sc.init(kmfactory.getKeyManagers(), tma, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                            //System.out.println("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());
                            return true;
                    }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

    }

}
class RelaxedTrustManager implements X509TrustManager{
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
        public X509Certificate[] getAcceptedIssuers() { return null; }
}
