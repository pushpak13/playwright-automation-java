package org.playwright.Day7;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.playwright.PlaywrightPOMSeries.WebSocketServerImpl;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertFalse;

public class WebSocketTest {
    Page page;
    Browser browser;
    BrowserContext context;
    WebSocketServerImpl webSocketServer;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch();
        context = browser.newContext();
        page = context.newPage();
        //webSocketServer = new WebSocketServerImpl(webSocketServer);

    }

    @Test
    public void getWebsocket() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("http://google.com");

            page.waitForWebSocket(() -> { // I only want Websocket x
                page.onWebSocket(webSocket -> {
                    System.out.println("WebSocket opened: " + webSocket.url());
                });
            });
        }
    }
@Test
    public void webSocket() throws TimeoutException {
    //java.net.http.WebSocket ws = new WebSocket("ws://localhost:8080");
    WebSocketServerImpl server = new WebSocketServerImpl("ws://localhost:8080");
    System.out.println(server);

        /*com.microsoft.playwright.WebSocket ws = page.waitForWebSocket(() -> {

            Socket webSocketServer = new Socket();
            page.evaluate("port => {\n" +
                    "  window.ws = new WebSocket('ws://localhost:' + port + '/ws');\n" +
                    "}", webSocketServer.getPort());
        });
        boolean[] error = {false};
        ws.onSocketError(e -> error[0] = true);
        ws.waitForFrameReceived(() -> {});
        page.evaluate("window.ws.close()");
        assertFalse(error[0]);

         */
    }
}
