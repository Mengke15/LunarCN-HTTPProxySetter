# LunarHPS

LunarClient HttpProxy setter

## The built-in proxy

> We used the proxy library from [monkeyWie/proxyee](https://github.com/monkeyWie/proxyee)

Redirect api.lunarclientprod.com to your own API

VM-arg: -DredirectTo=your-own-api-url

Default value: https://api.badlion.top

### Why built-in proxy is not working

The built-in proxy uses port 8080, make sure no program uses this port

## Replace with your own proxy

Edit VM args

-DhttpProxyHost=proxyHost -DhttpProxyPort=proxyPort

