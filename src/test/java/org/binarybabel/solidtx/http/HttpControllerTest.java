package org.binarybabel.solidtx.http;

import org.binarybabel.solidtx.embed.json.JSONValue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.SSLHandshakeException;
import java.io.FileInputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpControllerTest {
    private final String baseUrl_http = "http://localhost:38080";
    private final String baseUrl_https = "https://localhost:38443";
    private final String baseUrl_invalid = "http://localhost:38081";
    private final String sslCertificate = "tools/test_server/tmp/cert.pem";

    private HttpController hc;

    public HttpControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
        hc = new HttpController();
    }

    @After
    public void tearDown() throws Exception {
        hc = null;
    }

    private HttpAuth getGoodAuth() {
        return new HttpBasicAuth("TimBL", "Nov89");
    }

    private HttpAuth getBadAuth() {
        return new HttpBasicAuth("Orwell", "1984");
    }

    //
    // GENERAL
    //

    @Test(expected = MalformedURLException.class)
    public void testMalformedURL() throws Exception {
        try {
            hc.http_get("");
        } catch(HttpException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(expected = ConnectException.class)
    public void testConnectionRefused() throws Exception {
        try {
            hc.http_get(baseUrl_invalid);
        } catch(HttpException e) {
            assertTrue(e.isTemporary());
            throw (Exception) e.getCause();
        }
    }

    //
    // HTTP
    //

    @Test
    public void testHttp() throws Exception {
        String out = hc.http_get(baseUrl_http + "/core/hello");
        Assert.assertEquals(JSONValue.toJSONString("Hello World"), out);

        out = hc.http_put(baseUrl_http + "/core/method");
        assertEquals(JSONValue.toJSONString("PUT"), out);

        out = hc.http_post(baseUrl_http + "/core/method", null);
        assertEquals(JSONValue.toJSONString("POST"), out);

        out = hc.http_delete(baseUrl_http + "/core/method");
        assertEquals(JSONValue.toJSONString("DELETE"), out);
    }

    @Test(expected = HttpException.class)
    public void testHttpInsecureAuth() throws Exception {
        hc.setAuth(getGoodAuth());
        try {
            hc.http_get(baseUrl_http + "/core/auth/secret");
        } catch (HttpException e) {
            assertEquals(HttpException.INSECURE, e.getErrorCode());
            throw e;
        }
    }

    @Test
    public void testHttpAuthGood() throws Exception {
        hc.authRequiresSecure = false;
        hc.setAuth(getGoodAuth());

        String out = hc.http_get(baseUrl_http + "/core/auth/secret");
        assertEquals(JSONValue.toJSONString("Morocco Mole"), out);
    }

    @Test(expected = HttpException.class)
    public void testHttpAuthBad() throws Exception {
        hc.authRequiresSecure = false;
        hc.setAuth(getBadAuth());

        String out;

        try {
            out = hc.http_get(baseUrl_http + "/core/auth/secret");
        } catch (HttpException e) {
            assertEquals(HttpException.UNAUTHORIZED, e.getErrorCode());
            throw e;
        }

        assertEquals("", out);
    }

    //
    // HTTPS
    //

    @Test
    public void testHttps() throws Exception {
        applyTestCertificate(hc);

        String out = hc.http_get(baseUrl_https + "/core/secure/hello");
        assertEquals(JSONValue.toJSONString("Walt sent me"), out);

        out = hc.http_put(baseUrl_https + "/core/method");
        assertEquals(JSONValue.toJSONString("PUT"), out);

        out = hc.http_post(baseUrl_https + "/core/method", null);
        assertEquals(JSONValue.toJSONString("POST"), out);

        out = hc.http_delete(baseUrl_https + "/core/method");
        assertEquals(JSONValue.toJSONString("DELETE"), out);
    }

    @Test
    public void testHttpsAuthGood() throws Exception {
        applyTestCertificate(hc);
        hc.setAuth(getGoodAuth());

        String out = hc.http_get(baseUrl_https + "/core/secure/auth/secret");
        assertEquals(JSONValue.toJSONString("Secret Squirrel"), out);
    }

    @Test(expected = HttpException.class)
    public void testHttpsAuthBad() throws Exception {
        applyTestCertificate(hc);
        hc.setAuth(getBadAuth());
        String out;

        try {
            out = hc.http_get(baseUrl_https + "/core/secure/auth/secret");
        } catch (HttpException e) {
            assertEquals(HttpException.UNAUTHORIZED, e.getErrorCode());
            throw e;
        }

        assertEquals("", out);
    }

    @Test(expected = SSLHandshakeException.class)
    public void testInvalidCertificateChain() throws Exception {
        hc.setAuth(getGoodAuth());
        String out;

        try {
            out = hc.http_get(baseUrl_https + "/core/secure/hello");
        } catch (HttpException e) {
            assertEquals(HttpException.INVALID_SSL, e.getErrorCode());
            throw (Exception) e.getCause();
        }

        assertEquals("", out);
    }

    private void applyTestCertificate(HttpController http) throws Exception {
        FileInputStream io = new FileInputStream(sslCertificate);
        http.setCustomCertificateAuthority(io);
    }

    //
    // POST
    //

    @Test
    public void testPostValue() throws Exception {
        String testValue = "ABC123";
        String out = hc.http_post(baseUrl_http + "/core/data", "value=" + testValue);
        assertEquals(JSONValue.toJSONString(testValue), out);
    }


}
