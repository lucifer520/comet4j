package mangues.socket;

import java.io.BufferedReader;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class MyTextLineDecoder implements ProtocolDecoder {

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int startPosition = in.position();//获取读取的位置
		while(in.hasRemaining()){
			byte b = in.get();
			if(b == '\n'){   //遇到换行符，表示一行
				int currentPosition = in.position();  //获取换行符的位置
				int limit = in.limit();   // 记录总长度
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
			}
		}
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {

	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {

	}

}
