package vota;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class Votacoes_Anteriores_2 extends JFrame {
	
	String url = "jdbc:mysql://localhost/escola";
	String username = "root";
	String password = "root";

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Votacoes_Anteriores_2 frame = new Votacoes_Anteriores_2();
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
	public Votacoes_Anteriores_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnVotos = new JTextPane();
		txtpnVotos.setText("11 Votos*");
		txtpnVotos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnVotos.setBackground(Color.WHITE);
		txtpnVotos.setBounds(790, 270, 153, 31);
		contentPane.add(txtpnVotos);
		
		JTextPane txtpnVotos_3 = new JTextPane();
		txtpnVotos_3.setText("6 Votos*");
		txtpnVotos_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnVotos_3.setBackground(Color.WHITE);
		txtpnVotos_3.setBounds(790, 358, 153, 31);
		contentPane.add(txtpnVotos_3);
		
		JButton btnNewButton_1 = new JButton("1. Opção 1*requisição BD*");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1.setBounds(10, 253, 950, 57);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("2. Opção 2*requisição BD*");
		btnNewButton_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_1_1.setBounds(10, 346, 950, 57);
		contentPane.add(btnNewButton_1_1);
		
		JButton voltar = new JButton("<- Voltar");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Votacoes_Anteriores_1 v1 = new Votacoes_Anteriores_1();
				v1.setVisible(true);
				dispose();
			}
		});
		voltar.setHorizontalAlignment(SwingConstants.LEFT);
		voltar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		voltar.setBounds(10, 561, 162, 39);
		contentPane.add(voltar);
		
		JLabel lblVotaoX = new JLabel("Votação X*");
		lblVotaoX.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblVotaoX.setBounds(10, 131, 242, 84);
		contentPane.add(lblVotaoX);
		
		JLabel lblNewLabel = new JLabel("Consultar votações");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(10, 11, 262, 31);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("____________________________________________________________________________________________________________________________________________________________");
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(10, 34, 964, 20);
		contentPane.add(textPane);
	}
}
