package mangues.socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketClient {

	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		client.startClient();
	}
	
	private void startClient() {
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		BufferedWriter bw = null;
		Scanner br = null;
		BufferedReader br2 = null;
			try {
				System.out.println("向服务器请求连接");
				//创建客户端socket，指定ip和端口号
				socket=new Socket("localhost",9898);
				//获取输出流，想服务器端发送信息
				os=socket.getOutputStream();
				is=socket.getInputStream();
				//包装
				bw = new BufferedWriter(new OutputStreamWriter(os));
				br = new Scanner(System.in);
				br2 = new BufferedReader(new InputStreamReader(is));
				startServerReplyListener(br2);   //时刻监听着服务器端发来的请求
				String  i;
				while(!(i=br.nextLine()).equals("exit")){
					bw.write(i + "\n" + i);
					bw.flush();				
				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					socket.close();
					os.close();
					bw.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	//时刻监听着服务器的线程
	private void startServerReplyListener(final BufferedReader reader) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String str;
					while((str=reader.readLine())!=null){
						System.out.println(str);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
