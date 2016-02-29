package oracle;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CacheClient {

	public static void main(String[] args) {
		NamedCache cache = CacheFactory.getCache("testcache");
		System.out.println("------put Object into cache------");
		cache.put("testKey", "testValue");
		System.out.println("------retrieve Object from cache------");
		String value = (String) cache.get("testKey");
		System.out.println(value);
	}
}
