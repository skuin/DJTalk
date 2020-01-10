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
	TextField tId; // 아이디
	JPasswordField tPw; // 비밀번호

	JButton bLogin; // 전송버튼
	JButton bCreate;
	
	public Login(String str) {
		super(str);
		init();
		start();

		this.setLocation(100, 100); // 프레임 시작위치
		super.setVisible(true); // 실제로 프레임을 화면에 출력
		super.setSize(270, 200); // 프레임의 처음 크기
		super.setResizable(true); // 프레임 사이즈 조절

	}
	
	public void init()
	{
		JPanel p = new JPanel();
		setLayout(new BorderLayout());
		JLabel b2 = new JLabel ("아이디 : ");
		JLabel b3 = new JLabel ("비밀번호 : ");
		bLogin = new JButton("로그인");
		bCreate = new JButton("회원가입");

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
		Login exam = new Login("로그인");
	}

}
