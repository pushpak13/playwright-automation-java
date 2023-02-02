package org.playwright.PlaywrightPOMSeries;

import com.microsoft.playwright.WebSocket;
import com.microsoft.playwright.WebSocketFrame;
import java.net.InetSocketAddress;
import java.util.function.Consumer;


public class WebSocketServerImpl implements WebSocket  {
    WebSocket webSocket;

    public WebSocketServerImpl(String url) {

    }

       public void send() throws InterruptedException {
        int port = 1234;


    }

    @Override
    public void onClose(Consumer<WebSocket> consumer) {

    }

    @Override
    public void offClose(Consumer<WebSocket> consumer) {

    }

    @Override
    public void onFrameReceived(Consumer<WebSocketFrame> consumer) {

    }

    @Override
    public void offFrameReceived(Consumer<WebSocketFrame> consumer) {

    }

    @Override
    public void onFrameSent(Consumer<WebSocketFrame> consumer) {

    }

    @Override
    public void offFrameSent(Consumer<WebSocketFrame> consumer) {

    }

    @Override
    public void onSocketError(Consumer<String> consumer) {

    }

    @Override
    public void offSocketError(Consumer<String> consumer) {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String url() {
        return null;
    }

    @Override
    public WebSocketFrame waitForFrameReceived(WaitForFrameReceivedOptions waitForFrameReceivedOptions, Runnable runnable) {
        return null;
    }

    @Override
    public WebSocketFrame waitForFrameSent(WaitForFrameSentOptions waitForFrameSentOptions, Runnable runnable) {
        return null;
    }
}
