package top.resty.spboot.utils;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2024年11月28 - 16:04
 */
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class SSLUtils {
  private static void trustAllHttpsCertificates() throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[1];
    TrustManager tm = new miTM();
    trustAllCerts[0] = tm;
    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, trustAllCerts, null);
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  }
  static class miTM implements TrustManager,X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }
    public boolean isServerTrusted(X509Certificate[] certs) {
      return true;
    }
    public boolean isClientTrusted(X509Certificate[] certs) {
      return true;
    }
    public void checkServerTrusted(X509Certificate[] certs, String authType)
        throws CertificateException {
      return;
    }
    public void checkClientTrusted(X509Certificate[] certs, String authType)
        throws CertificateException {
      return;
    }
  }
  /**
   * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
   * @throws Exception
   */
  public static void ignoreSsl() throws Exception{
    HostnameVerifier hv = new HostnameVerifier() {
      public boolean verify(String urlHostName, SSLSession session) {
        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
        return true;
      }
    };
    trustAllHttpsCertificates();
    HttpsURLConnection.setDefaultHostnameVerifier(hv);
  }
}

