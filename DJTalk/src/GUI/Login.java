package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Login extends JFrame{
	TextField tId; // ���̵�
	JPasswordField tPw; // ��й�ȣ

	JButton bLogin; // ���۹�ư
	JButton bCreate;
	
	public Login(String str) {
		super(str);
		init();
		start();

		this.setLocation(100, 100); // ������ ������ġ
		super.setVisible(true); // ������ �������� ȭ�鿡 ���
		super.setSize(270, 200); // �������� ó�� ũ��
		super.setResizable(true); // ������ ������ ����

	}
	
	public void init()
	{
		JPanel p = new JPanel();
		setLayout(new BorderLayout());
		JLabel b2 = new JLabel ("���̵� : ");
		JLabel b3 = new JLabel ("��й�ȣ : ");
		bLogin = new JButton("�α���");
		bCreate = new JButton("ȸ������");

		tId = new TextField(20);
		tPw = new JPasswordField(20);
		
		GridLayout g = new GridLayout(3, 2);
		p.setLayout(g);
		p.add(b2);
		p.add(tId);
		p.add(b3);
		p.add(tPw);
		p.add(bLogin);
		p.add(bCreate);
		
		add("Center", p);
	}
	
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}

		});

	}

	public static void main(String[] args) {
		Login exam = new Login("�α���");
	}

}
