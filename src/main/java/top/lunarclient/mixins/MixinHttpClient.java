package top.lunarclient.mixins;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;

@Mixin(HttpClient.class)
public abstract class MixinHttpClient {
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
        String host = System.getProperty("httpProxyHost", null);
        String port = System.getProperty("httpProxyPort", null);
        if (host != null && port != null) {
            // throws NumberFormatEvent
            builder.proxy(ProxySelector.of(new InetSocketAddress(host, Integer.parseInt(port))));
        }
        return builder.build();
    }
}
