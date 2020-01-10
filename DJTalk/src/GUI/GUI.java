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
	TextField in_id; // 아이디
	JPasswordField in_pw; // 비밀번호
	
	Button b1; // 전송버튼

	public GUI(String str) {
		super(str);
		init();
		start();

		this.setLocation(100, 100); // 프레임 시작위치
		super.setVisible(true); // 실제로 프레임을 화면에 출력
		super.setSize(270, 200); // 프레임의 처음 크기
		super.setResizable(true); // 프레임 사이즈 조절

	}

	public void init() {

		Panel p = new Panel();
		setLayout(new BorderLayout());
		Label id = new Label("아이디");
		Label password = new Label("비밀번호");

		b1 = new Button("전송");
		b1.addActionListener(this); // 버튼이벤트

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
		GUI exam = new GUI("회원가입");
	}

	public void run() { // 스레드 정의부분

	}

	public void itemStateChanged(ItemEvent e) { // 체크상태 확인

	}

	public void actionPerformed(ActionEvent e) { // 액션이벤트
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