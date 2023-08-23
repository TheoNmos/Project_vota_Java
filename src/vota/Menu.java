package vota;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu extends JFrame {

	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConsultar = new JButton("CONSULTAR VOTAÇÕES");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Votacoes_Anteriores_1 v1 = new Votacoes_Anteriores_1();
				v1.setVisible(true);
				dispose();
			}
		});
		btnConsultar.setOpaque(true);
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		btnConsultar.setBackground(new Color(225, 81, 96));
		btnConsultar.setBounds(57, 284, 380, 300);
		contentPane.add(btnConsultar);
		
		JButton btnVotar = new JButton("VOTAR");
		btnVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Votar votar1 = new Votar();
				votar1.setVisible(true);
				dispose();
			}
		});
		btnVotar.setOpaque(true);
		btnVotar.setForeground(Color.WHITE);
		btnVotar.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		btnVotar.setBackground(new Color(0, 128, 64));
		btnVotar.setBounds(506, 284, 400, 300);
		contentPane.add(btnVotar);
		
		JLabel lblNewLabel = new JLabel("Selecione uma opção:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 52));
		lblNewLabel.setBounds(203, 110, 536, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnTrocaUser = new JButton("Trocar usuário");
		btnTrocaUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnTrocaUser.setBounds(10, 11, 128, 29);
		contentPane.add(btnTrocaUser);
	}
}
