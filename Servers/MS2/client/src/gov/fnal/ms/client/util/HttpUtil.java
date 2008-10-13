package gov.fnal.ms.client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
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
    private String cert;
    private String pass;
    public HttpUtil(String cert, String pass) {
        this.cert = cert;
        this.pass = pass;
    }
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

    public void readSecureUrl(String url, String fileName) throws Exception {
            initSecureConnection();
            HttpsURLConnection con = null;
            BufferedReader in = null;
            try {
                    con =  ( HttpsURLConnection ) (new URL(url)).openConnection() ;
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    readUrl(in, fileName);
            } finally {
                    if(in != null) in.close();
                    if(con != null)  con.disconnect();
            }
    }

    public void postSecureUrlFile(String url, String fileName) throws Exception {
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
                    postUrl(out, fileName);
                    out.flush();

                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    readUrlCheckExcep(in);
            } finally {
                    if(in != null) in.close();
                    if(out != null) out.close();
                    if(con != null)  con.disconnect();
            }
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

    public void readUrl(String url, String fileName) throws Exception {
            //System.out.println("url in readUrl is " + url);
            if (( new URL(url)).getProtocol().toLowerCase().equals("https")) {
		    readSecureUrl(url, fileName);
		    return;
	    }

            HttpURLConnection con = null;
            BufferedReader in = null;
            try {
                    con =  ( HttpURLConnection ) (new URL(url)).openConnection() ;
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    readUrl(in, fileName);
            } finally {
                    if(in != null) in.close();
                    if(con != null)  con.disconnect();
            }
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

    public void postUrlFile(String url, String data, String fileName) throws Exception {
            //System.out.println("url in postUrl is " + url);
            if (( new URL(url)).getProtocol().toLowerCase().equals("https")) {
		    postSecureUrlFile(url, fileName);
		    return;
	    }

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
		    postUrl(out, fileName);
                    out.flush();

                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    readUrlCheckExcep(in);
            } finally {
                    if(in != null) in.close();
                    if(out != null) out.close();
                    if(con != null)  con.disconnect();
            }
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
 
    private void readUrlCheckExcep(BufferedReader in) throws Exception {
            String inputLine;
            while ((inputLine = in.readLine()) != null){
		    if(inputLine.indexOf(Util.EXCEPTION) != -1) {
			    for (int i = 0 ; i != 5; ++i) {
				    String tmp = in.readLine();
				    if (tmp == null) {
			     			if(inputLine.indexOf("code ='1024'") == -1) throw new Exception(inputLine);
				    }
				    inputLine += tmp;
			    }
			     if(inputLine.indexOf("code ='1024'") == -1) throw new Exception(inputLine);
		    }
            }
    }
   
    private void readUrl(BufferedReader in, String fileName) throws Exception {
	    (new Util()).deleteFile("/tmp/" + fileName);
	    RandomAccessFile raf = null;
	    try {
		    raf = new RandomAccessFile("/tmp/" + fileName, "rw");
		    String inputLine;
	            while ((inputLine = in.readLine()) != null){
			    if(inputLine.indexOf(Util.EXCEPTION) != -1) {
				    for (int i = 0 ; i != 5; ++i) {
					    String tmp = in.readLine();
					    if (tmp == null) throw new Exception(inputLine);
					    inputLine += tmp;
				    }
	
				    throw new Exception(inputLine);
			    }
        	            raf.writeBytes(inputLine + "\n");
	            }
	    } finally {
		    if (raf != null) raf.close();
	    }

    }
    private void postUrl(DataOutputStream out, String fileName) throws Exception {
	    RandomAccessFile raf = null;
	    try {
		    raf = new RandomAccessFile("/tmp/" + fileName, "r");
		    String inputLine;
	            while ((inputLine = raf.readLine()) != null){
        	            out.writeBytes(inputLine + "\n");
	            }
	    } finally {
		    if (raf != null) raf.close();
		    (new Util()).deleteFile("/tmp/" + fileName);
	    }

    }

    private void initSecureConnection () throws Exception {
            String certToUse = "/home/sekhri/mycert.p12";
            //String certToUse = "C:\\Documents and Settings\\Vijay Sekhri\\Desktop\\mycert.p12";
            String passToUse = "vijayneha";
            if(this.cert != null) certToUse = this.cert;
            if(this.pass != null) passToUse = this.pass;
            
            
            //final String cert = "/home/sekhri/mycert.p12";
            //final String cert = "C:\\Documents and Settings\\Vijay Sekhri\\Desktop\\mycert.p12";
            //String cert = System.getProperty("certificate");
            
            //String pass = System.getProperty("password");;
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load((new FileInputStream(certToUse)), passToUse.toCharArray());
            KeyManagerFactory kmfactory = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm());
            kmfactory.init(ks, passToUse.toCharArray());
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



    public static void main(String args[]) throws Exception{
        //HttpUtil hu = new HttpUtil("C:\\Documents and Settings\\Vijay Sekhri\\Desktop\\hostcert.p12", "vijayneha");
        HttpUtil hu = new HttpUtil();
        String xml = hu.readUrl("https://cmsweb.cern.ch/sitedb/json/index/CMSNametoSE?name=T1_UK_RAL");
        System.out.println("xml is " + xml);
    }


}
class RelaxedTrustManager implements X509TrustManager{
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
        public X509Certificate[] getAcceptedIssuers() { return null; }
}
