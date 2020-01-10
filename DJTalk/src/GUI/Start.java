package GUI;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import ChatClient.Client;

import java.awt.*;

public class Start extends Frame implements ActionListener {
	
	Login login = new Login("�α���");
	GUI create = new GUI("ȸ������");
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
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
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
			
			if( i == 1) System.out.println("���ڵ� �߰� ����");
			else System.out.println("���ڵ� �߰� ����");
			
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
					System.out.println("�α��� ����");
					data.setName(id);
					client.StartMes(data);
					client.setVisible(true);
					login.setVisible(false);
				}
				else {
					System.out.println("�α��� ����");
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