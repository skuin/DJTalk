package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GUI extends Frame implements ActionListener, ItemListener, Runnable {
	TextField in_id; // ���̵�
	JPasswordField in_pw; // ��й�ȣ
	
	Button b1; // ���۹�ư

	public GUI(String str) {
		super(str);
		init();
		start();

		this.setLocation(100, 100); // ������ ������ġ
		super.setVisible(true); // ������ �������� ȭ�鿡 ���
		super.setSize(270, 200); // �������� ó�� ũ��
		super.setResizable(true); // ������ ������ ����

	}

	public void init() {

		Panel p = new Panel();
		setLayout(new BorderLayout());
		Label id = new Label("���̵�");
		Label password = new Label("��й�ȣ");

		b1 = new Button("����");
		b1.addActionListener(this); // ��ư�̺�Ʈ

		in_id = new TextField(20);
		in_pw = new JPasswordField(20);


		GridLayout g = new GridLayout(3, 2);
		p.setLayout(g);
		p.add(id);
		p.add(in_id);
		p.add(password);
		p.add(in_pw);

		add("Center", p);
		add("South", b1);

	}

	public void start() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}

		});

	}

	public static void main(String[] args) {
		GUI exam = new GUI("ȸ������");
	}

	public void run() { // ������ ���Ǻκ�

	}

	public void itemStateChanged(ItemEvent e) { // üũ���� Ȯ��

	}

	public void actionPerformed(ActionEvent e) { // �׼��̺�Ʈ
		Object obj = e.getSource();
		
		if ((Button) obj == b1) {
			try {
				Start.addGuest(in_id.getText(),in_pw.getText()); 
				super.setVisible(false);
			} catch (Exception e2) {
				System.out.println(e2);
			}

		}

	}

}