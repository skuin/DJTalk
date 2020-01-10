package GUI;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import ChatClient.Client;

import java.awt.*;

public class Start extends Frame implements ActionListener {
	
	Login login = new Login("로그인");
	GUI create = new GUI("회원가입");
	Client client = new Client();
	
	public static DAO data = new DAO();
	
	public Start(){
		login.setVisible(true);
		login.bLogin.addActionListener(this);
		login.bCreate.addActionListener(this);
		create.b1.addActionListener(this);
		create.setVisible(false);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(login.bCreate)) {
			create.setVisible(true);
		}
		else if(obj.equals(login.bLogin)) {
			loginCheck();
		}
	}
	
	public static Connection makeConnection() {
		String url = "jdbc:mariadb://localhost:3308/user_db";
		String id = "root";
		String password = "1234";
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		
		return con;
	}
	
	static void addGuest(String id, String password) {
		Connection con = makeConnection();
		try {
			Statement stmt = con.createStatement();
			String s = "INSERT INTO user_table (uid, pwd) VALUES ";
			s += "('" + id + "','" + password + "')";
			System.out.println(s);
			int i = stmt.executeUpdate(s);
			
			if( i == 1) System.out.println("레코드 추가 성공");
			else System.out.println("레코드 추가 실패");
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			System.exit(0);
		}
	}
	
	void loginCheck() {
		Connection con = makeConnection();
		
		String id;
		String pw;

		id = login.tId.getText().trim();
		pw = login.tPw.getText().trim();
		
		String query = "SELECT uid, pwd FROM user_table WHERE uid='" + id + "'";
		System.out.println(query);
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		
			if(rs.next()) {
				id = rs.getString("uid");
				if (pw.equals(rs.getString("pwd"))) {
					System.out.println("로그인 성공");
					data.setName(id);
					client.StartMes(data);
					client.setVisible(true);
					login.setVisible(false);
				}
				else {
					System.out.println("로그인 실패");
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		
	}
	
	public static void main(String arg[]) throws SQLException {
		Connection con = makeConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user_table");
		
		new Start();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name =rs.getString("uid");
			System.out.println(id + " " + name);
		}
	}
}