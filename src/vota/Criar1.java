package vota;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Criar1 extends JFrame {
	
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/vota";
	String USER = "root";
	String PASS = "root";

	private JPanel contentPane;
	private JTextField tema;
	private JTextField opcao1;
	private JTextField opcao2;
	private JTextField data_fim_votacao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Criar1 frame = new Criar1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Criar1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Criar Votação");
		lblNewLabel.setBounds(21, 11, 155, 32);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("_________________________________________________________________________________________");
		lblNewLabel_1.setBounds(10, 47, 534, 14);
		lblNewLabel_1.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Insira o tema da sua votação:");
		lblNewLabel_2.setBounds(68, 109, 424, 39);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblNewLabel_2);
		
		tema = new JTextField();
		tema.setBounds(68, 171, 473, 38);
		tema.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tema.setColumns(10);
		contentPane.add(tema);
		
		JButton Voltar = new JButton("< Voltar");
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		Voltar.setBounds(10, 547, 125, 33);
		Voltar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(Voltar);
		
		JButton Criar = new JButton("Criar >>");
		Criar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tema_votacao = tema.getText();
				String opcao_1 = opcao1.getText();
				String opcao_2 = opcao2.getText();
				String data_final = data_fim_votacao.getText();
				
				if (tema.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira um tema!");
				}
				else {
					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					         Statement stmt = conn.createStatement();
					      ) {		      
					         // Execute a query
					         System.out.println("Inserindo dados em tabela");
					         
					         String sql = "INSERT INTO votacao (id_votacao,titulo,opcao1,opcao2,data_final) VALUES (default,'"+tema_votacao+"','"+opcao_1+"','"+opcao_2+"', '"+data_final+"')";
					         
					         stmt.executeUpdate(sql);  	  
					      } catch (SQLException e1) {
					         e1.printStackTrace();
					      } 
					JOptionPane.showMessageDialog(null, "Votação criada com sucesso!");
					MenuAdm menu = new MenuAdm();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		Criar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Criar.setBounds(819, 547, 131, 32);
		contentPane.add(Criar);
		
		JLabel insiraOpcoes = new JLabel("Insira as opções da sua votação:");
		insiraOpcoes.setFont(new Font("Tahoma", Font.PLAIN, 32));
		insiraOpcoes.setBounds(69, 236, 489, 39);
		contentPane.add(insiraOpcoes);
		
		opcao1 = new JTextField();
		opcao1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		opcao1.setColumns(10);
		opcao1.setBounds(70, 296, 473, 38);
		contentPane.add(opcao1);
		
		opcao2 = new JTextField();
		opcao2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		opcao2.setColumns(10);
		opcao2.setBounds(70, 362, 473, 38);
		contentPane.add(opcao2);
		
		JLabel numeroOpcao1 = new JLabel("1.");
		numeroOpcao1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		numeroOpcao1.setBounds(30, 302, 30, 26);
		contentPane.add(numeroOpcao1);
		
		JLabel numeroOpcao2 = new JLabel("2.");
		numeroOpcao2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		numeroOpcao2.setBounds(30, 368, 30, 26);
		contentPane.add(numeroOpcao2);
		
		data_fim_votacao = new JTextField();
		data_fim_votacao.setFont(new Font("Tahoma", Font.PLAIN, 26));
		data_fim_votacao.setColumns(10);
		data_fim_votacao.setBounds(68, 486, 473, 38);
		contentPane.add(data_fim_votacao);
		
		JLabel lblNewLabel_2_1 = new JLabel("Votação disponivel até: (ano-mes-dia)");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2_1.setBounds(68, 436, 528, 39);
		contentPane.add(lblNewLabel_2_1);
	}
}
