package mangues.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyTextLineCodecFactory implements ProtocolCodecFactory {

	private MyTextLineDecoder mDecoder;
	private MyTextLineEncoder mEncoder;
	private MyTextLineCumulativeDecoder myTextLineCumulativeDecoder;

	public MyTextLineCodecFactory() {
		mDecoder = new MyTextLineDecoder();
		mEncoder = new MyTextLineEncoder();
		myTextLineCumulativeDecoder = new MyTextLineCumulativeDecoder();
	}

	//解密
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return myTextLineCumulativeDecoder;
	}

	//加密
	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return mEncoder;
	}

}
