package testExec;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class Main {
	public static void main(String args[]) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		//cb.addServer().port(11222).host("127.0.0.1").protocolVersion("2.6").security().authentication().enable().saslMechanism("PLAIN").username("user").password("user");
		cb.addServer().port(11222).host("127.0.0.1").protocolVersion("2.6");
		try (RemoteCacheManager rcm = new RemoteCacheManager(cb.build())) {
			RemoteCache<String, String> scache = rcm.getCache("___script_cache");
			String sc = "// mode=local,language=javascript\n " + "var a = \"ciao\";" + " a;";
			scache.put("script.js", sc);
			System.out.println("OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
