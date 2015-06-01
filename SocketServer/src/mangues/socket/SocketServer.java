package mangues.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	/**
	 * @Title: main
	 * @param args
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.startServer();
	}

	// 启动SocketServer
	private void startServer() {
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		BufferedWriter pw = null;
		Socket socket = null;
		try {
			// 创建一个服务器端的Socket，ServerSocket,指定绑定的端口，并监听次端口
			ServerSocket serverSocket = new ServerSocket(8889);
			System.out.println("*******服务器即将启动，等待客户端连接****");
			// 调用accept()方法开始监听，等待客户端进行连接
			socket = serverSocket.accept();
			System.out.println(socket.getLocalAddress()+"连接");
			// 获取输入流，并读取客户端信息
			 in = socket.getInputStream();
			 os = socket.getOutputStream();
			 isr = new InputStreamReader(in);
			 br = new BufferedReader(isr);
			 pw = new BufferedWriter(new OutputStreamWriter(os,"utf-8"));
			 pw.write("连接成功");
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println("这里是服务器端，客户端说：" + str);
				pw.write(str + "\n");
				pw.flush();
			}	

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				os.close();
				br.close();
				isr.close();
				in.close();
				socket.close();// 关闭socket的连接
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
