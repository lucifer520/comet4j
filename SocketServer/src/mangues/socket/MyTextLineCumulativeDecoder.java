package mangues.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyTextLineCumulativeDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		int startPosition = in.position();//获取读取的位置
		while(in.hasRemaining()){
			byte b = in.get();
			if(b == '\n'){   //遇到换行符，表示一行
				int currentPosition = in.position();  //获取换行符的位置
				int limit = in.limit();   // 记录in总长度
				System.out.println(limit + "   " + currentPosition);
				
				//截取
				in.position(startPosition);//位置定向到开始位置
				in.limit(currentPosition);  //设置终止位置
				IoBuffer ioBuffer = in.slice();   //截取
				byte [] dest = new byte[ioBuffer.limit()];
				ioBuffer.get(dest);   //把iobuffer中的字符，get到byte里
				String str= new String(dest);
				out.write(str);

				//还原操作
				in.position(currentPosition);
				in.limit(limit);
				return true;   //完成读取
			}
		}
		//没读取
		in.position(startPosition);   //设置为开始位置，重新与下次读取一起在读
		return false;
	}

}
