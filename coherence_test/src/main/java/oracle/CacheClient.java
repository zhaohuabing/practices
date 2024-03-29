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
		
		System.out.println("------Wait for 2 minutes------");
		try {
			Thread.sleep(2*60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------retrieve Object from cache------");
		value = (String) cache.get("testKey");
		System.out.println(value);
	}
}
