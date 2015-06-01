package mangues.socket;

import java.net.InetSocketAddress;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


public class MinaClient {

	public static void main(String[] args) {
		MinaClient minaClient = new MinaClient();
		minaClient.startMinaClient();
	}
	
	
	private void startMinaClient() {

		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(new MyClientHandler());
		connector.getFilterChain().addLast("mangues",new ProtocolCodecFilter(new TextLineCodecFactory()));
		ConnectFuture facture = connector.connect(new InetSocketAddress("localhost", 9898));
		facture.awaitUninterruptibly();   //Mina是非阻塞的，不会等待连接好，会直接往下运行，这里是他阻塞等待连接完成
		IoSession session = facture.getSession();
		Scanner scanner = new Scanner(System.in);
		String  i;
		while(!(i=scanner.nextLine()).equals("exit")){
			session.write(i + "\n");			
		}
	}
}
