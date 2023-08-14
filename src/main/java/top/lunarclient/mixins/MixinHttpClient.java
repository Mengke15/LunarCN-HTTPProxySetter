package top.lunarclient.mixins;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;

@Mixin(HttpClient.class)
public class MixinHttpClient {
    @Shadow
    public static HttpClient.Builder newBuilder() {
        return HttpClient.newBuilder();
    }

    /**
     * @author CubeWhy
     * @reason proxy
     */
    @Overwrite
    public static HttpClient newHttpClient() {
        // add proxy
        HttpClient.Builder builder = newBuilder();
        String host = System.getProperty("httpProxyHost", "127.0.0.1"); // proxy host
        String port = System.getProperty("httpProxyPort", "8080"); // proxy port
        if (host != null && port != null) {
            // throws NumberFormatEvent
            builder.proxy(ProxySelector.of(new InetSocketAddress(host, Integer.parseInt(port))));
        }
        return builder.build();
    }
}
