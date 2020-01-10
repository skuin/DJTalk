package ChatServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ChatClient.MainView;
import GUI.DAO;
import GUI.Start;

public class Server{
	
	MainView m = new MainView();
	//public static String name;
	
	private ServerSocket socket; //��������
	private Socket soc; // ������� 
	private int Port; // ��Ʈ��ȣ

	private ArrayList vc = new ArrayList(); // ����� ����ڸ� ������ ����

	public static void main(String[] args)
	{
	
		Server server = new Server();
	}

	public Server() {
		try {
			socket = new ServerSocket(5000); // ������ ��Ʈ ���ºκ�
			System.out.println("���� ������");
			
			if(socket!=null) // socket �� ���������� ��������
			{
			Connection();
			}
			
		} catch (IOException e) {
			System.out.println("�������.");
		}
		}
	
	private void Connection() {

		Thread th = new Thread(new Runnable() { // ����� ������ ���� ������
			@Override
			public void run() {
				while (true) { // ����� ������ ����ؼ� �ޱ� ���� while��
					try {
						// accept�� �Ͼ�� �������� ���� �����
						soc = socket.accept(); 
						// ����� ���� ������ �ݹ� ������Ƿ�, user Ŭ���� ���·� ��ü ����
                        // �Ű������� ���� ����� ���ϰ�, ���͸� ��Ƶд�
						UserInfo user = new UserInfo(soc, vc); 
						vc.add(user); // �ش� ���Ϳ� ����� ��ü�� �߰�

						user.start(); // ���� ��ü�� ������ ����

					} catch (IOException e) {
						System.out.println("���� : " + e);
					} 

				}

			}
		});

		th.start();
	}

	class UserInfo extends Thread {

		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		private Socket user_socket;
		private ArrayList user_vc;

		private String Nickname = "";

		public UserInfo(Socket soc, ArrayList vc) // �����ڸ޼ҵ�
		{
			// �Ű������� �Ѿ�� �ڷ� ����
			this.user_socket = soc;
			this.user_vc = vc;

			User_network();

		}

		public void User_network() {
			try {
				is = user_socket.getInputStream();
				dis = new DataInputStream(is);
				os = user_socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				Nickname = dis.readUTF(); // ������� �г��� �޴ºκ�
				System.out.println("������ : "+ Nickname +"\n");
				send_Message(Nickname+"���� ���� ���� �Ǿ����ϴ�"); // ����� ����ڿ��� ���������� �˸�

			} catch (Exception e) {
				System.out.println("���ӽ���!");
			}

		}

		public void InMessage(String str) {
			System.out.println("����� �޽��� :"+str);
			// ����� �޼��� ó��
			broad_cast(str);

		}

		public void broad_cast(String str) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserInfo imsi = (UserInfo) user_vc.get(i);
				imsi.send_Message(Nickname+" : "+str);

			}

		}

		public void send_Message(String str) {
			try {
				dos.writeUTF(str);
			} 
			catch (IOException e) {
				System.out.println("�����߻�");
			}
		}

		public void run() // ������ ����
		{

			while (true) {
				try {
					
					// ����ڿ��� �޴� �޼���
					String msg = dis.readUTF();
					InMessage(msg);
					
				} 
				catch (IOException e) 
				{
					
					try {
						dos.close();
						dis.close();
						user_socket.close();
						vc.remove( this ); // �������� ���� ��ü�� ���Ϳ��� �����
						break;
					} catch (Exception ee) {
					
					}// catch�� ��
				}// �ٱ� catch����

			}
			
			
			
		}// run�޼ҵ� ��

	} // ���� userinfoŬ������
	
	

}
