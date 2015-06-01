package mangues.socket;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	public static void main(String[] args) {
		MinaServer minaServer = new MinaServer();
		minaServer.startMinaServer();
	}
	
	
	
	
	private void startMinaServer() {
		try {
			NioSocketAcceptor acceptor = new NioSocketAcceptor();
			acceptor.setHandler(new MyServerHandler());
			//拦截器，增加一个拦截器
			acceptor.getFilterChain().addLast("mangues", new ProtocolCodecFilter(new MyTextLineCodecFactory()));
			//acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5); //单位秒，空闲状态设置时间
			//4.绑定端口
			acceptor.bind(new InetSocketAddress(9898));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


