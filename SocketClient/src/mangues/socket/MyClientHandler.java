package mangues.socket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyClientHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		//System.out.println("clinet:"+(String)message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}

	/**
	 * 空闲时运行
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}

}
