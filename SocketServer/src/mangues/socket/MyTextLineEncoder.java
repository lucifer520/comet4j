package mangues.socket;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
/**
 * 
 * @ClassName: MyTextLineEncoder
 * 加密 
 * @author 许涛 
 * @date 2015-4-4
 */
public class MyTextLineEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession arg0) throws Exception {

	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {

		CharsetEncoder charsetEncoder = (CharsetEncoder)session.getAttribute("encoder");
		if(charsetEncoder == null){
			charsetEncoder = Charset.defaultCharset().newEncoder();
			session.setAttribute("encoder",charsetEncoder);
		}
		//处理发送出去的message
		String s = null;
		if(message instanceof String){
			s=(String)message;
		}
		if(s!=null){
			//开辟内存
			IoBuffer ioBuffer = IoBuffer.allocate(s.length());
			ioBuffer.setAutoExpand(true);//内存可以扩展
			ioBuffer.putString(s, charsetEncoder);  //设置编码
			ioBuffer.flip();
			out.write(ioBuffer);
		}
		
		
		
	}

}
