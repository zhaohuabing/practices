package oracle;

import com.tangosol.net.CacheFactory;

public class CacheServer {
	public void init() {
		CacheFactory.ensureCluster();
	}

	public static void main(String[] args) {
		Object lock = new Object();

		new CacheServer().init();

		synchronized (lock) {
			while (true) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
