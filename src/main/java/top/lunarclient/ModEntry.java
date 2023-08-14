package top.lunarclient;

import org.cubewhy.lunarcn.loader.api.ModInitializer;

@SuppressWarnings("unused")
public class ModEntry implements ModInitializer {
    @Override
    public void preInit() {
        System.out.println("[LunarHPS] Init!");
    }
}
