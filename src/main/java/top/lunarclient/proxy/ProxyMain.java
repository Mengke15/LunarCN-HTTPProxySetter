package top.lunarclient.proxy;

import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.FullResponseIntercept;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import com.github.monkeywie.proxyee.util.HttpUtil;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * The proxy runnable
 * @author CubeWhy
 * @since 1.1
 * */
public class ProxyMain implements Runnable {
    public static final String redirectTo = System.getProperty("redirectTo", "https://api.badlion.top");
    private HttpProxyServer proxy = new HttpProxyServer();

    /**
     * Init proxy
     * */
    public ProxyMain() {
        HttpProxyServerConfig config =  new HttpProxyServerConfig();
        config.setHandleSsl(true); // enable https support
        proxy.serverConfig(config)
                .proxyInterceptInitializer(this::initProxy);
    }

    private void initProxy(HttpProxyInterceptPipeline pipeline) {
        pipeline.addLast(
                new FullResponseIntercept() {
                    @Override
                    public boolean match(HttpRequest httpRequest, HttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
                        return HttpUtil.checkUrl(pipeline.getHttpRequest(), "^api.lunarclientprod.com$"); // match Lunar API
                    }

                    @Override
                    public void handleResponse(HttpRequest httpRequest, FullHttpResponse httpResponse, HttpProxyInterceptPipeline pipeline) {
                        // do redirect
                        httpRequest.setUri(redirectTo);
                    }
                }
        );
    }

    /**
     * When an object-implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        proxy.start(8080); // start the proxy
    }
}
