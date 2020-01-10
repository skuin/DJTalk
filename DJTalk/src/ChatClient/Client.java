package ChatClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.DAO;

public class Client extends JFrame {

	//static DAO data = new DAO();

	private JPanel panel;
	private JTextField tf_ID; // ID�� �Է¹�����
	private JTextField tf_IP; // IP�� �Է¹�����
	ImageIcon ic = new ImageIcon("D:\\KPU\\4-2\\JAVA\\Source\\MariaDB Test2\\src\\ChatClient\\pink.png");

	public Client() // ������
	{
		super("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 200);
		setBackground(Color.WHITE);

	}

	public void StartMes(DAO data) {
		panel = new JPanel() {
			{
				setOpaque(false);
			}

			public void paintComponent(Graphics g) {
				Image img = new ImageIcon("D:\\KPU\\4-2\\JAVA\\Source\\MariaDB Test2\\src\\ChatClient\\pink.png")
						.getImage();
				g.drawImage(img, 0, 0, null);
				super.paintComponent(g);
			}
		};
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		// IP �Է�ĭ

		JLabel lblServerIp = new JLabel("IP");
		lblServerIp.setFont(new Font("HY����L", Font.PLAIN, (int) 15));
		lblServerIp.setBounds(43, 33, 90, 14);
		panel.add(lblServerIp);

		tf_IP = new JTextField();
		tf_IP.setColumns(10);
		tf_IP.setBounds(92, 30, 150, 21);
		panel.add(tf_IP);

		JButton btnNewButton = new JButton("��    ��");
		btnNewButton.setBounds(36, 90, 206, 52);
		btnNewButton.setFont(new Font("HY����L", Font.PLAIN, (int) 20));
		btnNewButton.setBackground(Color.lightGray);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// String _id = tf_ID.getText().trim();
				String _ip = tf_IP.getText().trim();

				MainView view = new MainView();
				view.startNetwork(data, _ip, 5000);
				setVisible(false);
			}
		});
		// IP�� ID�� �Է�������� ä��â���� �Ѿ��.
	}

}
