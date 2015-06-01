import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;


public class TestComet implements ServletContextListener {
	private static final String CHANNEL = "test";
	private static final String CHANNEL2 = "test2";

	public void contextInitialized(ServletContextEvent arg0) {
		CometContext cc = CometContext.getInstance();
		cc.registChannel(CHANNEL);// 注册应用的channel
		cc.registChannel(CHANNEL2);

		Thread helloAppModule = new Thread(new HelloAppModule(),
				"Sender App Module");
		// 是否启动
		helloAppModule.setDaemon(true);
		// 启动线程
		helloAppModule.start();

		Thread helloAppModule2 = new Thread(new HelloAppModule2(),
				"Sender App Module");
		// 是否启动
		helloAppModule2.setDaemon(true);
		// 启动线程
		helloAppModule2.start();
	}

	class HelloAppModule2 implements Runnable {
		public void run() {
			while (true) {
				try {
					// 睡眠时间
					Thread.sleep(5000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				CometEngine engine = CometContext.getInstance().getEngine();
				// 获取消息内容
				long l = getFreeMemory();
				// 开始发送
				engine.sendToAll(CHANNEL2, new Date());
			}
		}
	}

	class HelloAppModule implements Runnable {
		public void run() {
			while (true) {
				try {
					// 睡眠时间
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				CometEngine engine = CometContext.getInstance().getEngine();
				// 获取消息内容
				long l = getFreeMemory();
				// 开始发送
				engine.sendToAll(CHANNEL, l);
			}
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory() / 1024;
	}
}
