package ChatClient;

import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.DAO;
import GUI.Start;
import ChatServer.Server;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField; // ���� �޼��� ���°�

	private String id;
	private String ip;
	private int port;
	
	JButton imoticon1;		// �̸�Ƽ�� ��ư
	JButton imoticon2;		// �̸�Ƽ�� ��ư
	JButton imoticon3;		// �̸�Ƽ�� ��ư
	JButton imoticon4;

	JButton filebtn; //��������
	
	JButton sendBtn; // ���۹�ư
	JTextArea textArea; // ���ŵ� �޼����� ��Ÿ�� ����

	private Socket socket; // �������
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	
	
	public MainView()// ������
	{
	}
	
	
	public String getName()
	{
		return id;
	}	
	
	public void startNetwork(DAO data, String ip, int port) {
		this.id = data.getName();
		this.ip = ip;
		this.port = port;
		
		init();
		start();
		
		
		textArea.append(id + "���� �����ϼ̽��ϴ�.\n");
		//textArea.append("ó�� ������ �޽����� �г����� ���� �� �� �ֽ��ϴ�\n");
		network();
	}

	public void network() {
		// ������ ����
		try {
			socket = new Socket(ip, port);
			if (socket != null) // socket�� null���� �ƴҶ� ��! ����Ǿ�����
			{
				Connection(); // ���� �޼ҵ带 ȣ��
			}
		} catch (UnknownHostException e) {
			textArea.append("ȣ��Ʈ IP �ƴ�");
		} catch (IOException e) {
			textArea.append("���� ���� ����!!\n");
		}
	}

	public void Connection() { // ���� ���� �޼ҵ� ����κ�

		try { // ��Ʈ�� ����
			is = socket.getInputStream();
			dis = new DataInputStream(is);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			
			send_Message(Start.data.getName());
		} catch (IOException e) {
			textArea.append("��Ʈ�� ���� ����!!\n");
		}


		Thread th = new Thread(new Runnable() { // �����带 ������ �����κ��� �޼����� ����
					@Override
					public void run() {
						while (true) {

							try {
								String msg = dis.readUTF(); // �޼����� �����Ѵ�
								textArea.append(msg + "\n");
							} catch (IOException e) {
								textArea.append("�޼��� ���� ����!!\n");
								// ������ ���� ��ſ� ������ ������ ��� ������ �ݴ´�
								try {
									os.close();
									is.close();
									dos.close();
									dis.close();
									socket.close();
									break; // ���� �߻��ϸ� while�� ����
								} catch (IOException e1) {

								}

							}
						} // while�� ��

					}// run�޼ҵ� ��
				});

		th.start();

	}

	public void send_Message(String str) { // ������ �޼����� ������ �޼ҵ�
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			textArea.append("�޼��� �۽� ����!!\n");
		}
	}
	 
	public void init() { // ȭ�鱸�� �޼ҵ�

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 340, 302);
		contentPane.add(scrollPane);

		textArea = new JTextArea() {
			 {
		            setOpaque(false);
		     }
			public void paintComponent(Graphics g) {
				 Image img = new ImageIcon("D:\\KPU\\4-2\\JAVA\\Source\\MariaDB Test2\\src\\ChatClient\\main.png").getImage();
		         g.drawImage(img, 0, 0, null);
		         super.paintComponent(g);
		      }
		};
		scrollPane.setViewportView(textArea);

		textField = new JTextField();
		
		textField.setBounds(0, 312, 200, 42);
		contentPane.add(textField);
		textField.setColumns(10);

		sendBtn = new JButton("��   ��");
		sendBtn.setBounds(230, 312, 100, 42);
		contentPane.add(sendBtn);
		
		imoticon1 = new JButton("�ູ");
		imoticon1.setBounds(0, 360, 60, 42);
		contentPane.add(imoticon1);
		
		imoticon2 = new JButton("����");
		imoticon2.setBounds(60, 360, 60, 42);
		contentPane.add(imoticon2);

		imoticon3 = new JButton("����");
		imoticon3.setBounds(120, 360, 60, 42);
		contentPane.add(imoticon3);
		
		imoticon4 = new JButton("�ֱ�");
		imoticon4.setBounds(180,360,60,42);
		contentPane.add(imoticon4);


		textArea.setEnabled(false); // ����ڰ� �������ϰ� ���´�

		setVisible(true);

	}

	public void start() { // �׼��̺�Ʈ ���� �޼ҵ�
		sendBtn.addActionListener(new Myaction()); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
		// ����
		imoticon1.addActionListener(new ImoticonAction1());
		imoticon2.addActionListener(new ImoticonAction2());
		imoticon3.addActionListener(new ImoticonAction3());
		imoticon4.addActionListener(new ImoticonAction4());
		
		//filebtn.addActionListener(new FileButton());
	}

	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == sendBtn)	 // �׼� �̺�Ʈ�� sendBtn�϶�
			{

				send_Message(textField.getText());
				textField.setText(""); 		// �޼����� ������ ���� �޼��� ����â�� ����.
				textField.requestFocus();   // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
			}

		}

	}
	class ImoticonAction1 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imoticon1)
			{
				send_Message("^_^");
			}
		}
	}
	class ImoticonAction2 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imoticon2)
			{
				send_Message("(��_��)");
			}
		}
	}
	class ImoticonAction3 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imoticon3)
			{
				send_Message("(OTL)");
			}
		}
	}

	class ImoticonAction4 implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==imoticon4)
			{
				send_Message("(>_<)");
			}
		}
	}
	
}


