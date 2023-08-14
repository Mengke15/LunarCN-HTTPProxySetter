package top.lunarclient;

import org.cubewhy.lunarcn.loader.api.ModInitializer;
import top.lunarclient.proxy.ProxyMain;

@SuppressWarnings("unused")
public class ModEntry implements ModInitializer {
    public static final Thread proxyThread = new Thread(new ProxyMain());

    @Override
    public void preInit() {
        System.out.println("[LunarHPS] Init!");
        System.out.println("Start proxy");
        proxyThread.start();
    }
}
