package vota;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdm frame = new MenuAdm();
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
	public MenuAdm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecione uma opção:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 52));
		lblNewLabel.setBounds(193, 84, 536, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("CONSULTAR VOTAÇÕES");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta_adm consulta = new consulta_adm();
				consulta.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setOpaque(true);
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		btnNewButton_1_1.setBackground(new Color(225, 81, 96));
		btnNewButton_1_1.setBounds(47, 258, 380, 300);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnCriarVotao = new JButton("CRIAR VOTAÇÃO");
		btnCriarVotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criar1 c = new Criar1();
				c.setVisible(true);
				dispose();
			}
		});
		btnCriarVotao.setOpaque(true);
		btnCriarVotao.setForeground(Color.WHITE);
		btnCriarVotao.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 22));
		btnCriarVotao.setBackground(new Color(0, 0, 255));
		btnCriarVotao.setBounds(496, 258, 400, 300);
		contentPane.add(btnCriarVotao);
		
		JButton btnNewButton_1 = new JButton("Trocar usuário");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 11, 112, 29);
		contentPane.add(btnNewButton_1);
	}

}
