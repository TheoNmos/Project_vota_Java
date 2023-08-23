package vota;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import java.awt.ComponentOrientation;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class Votar extends JFrame{


	JRadioButton opt2;
	JRadioButton opt1;
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/vota";
	String USER = "root";
	String PASS = "root";
	int i = 0;
	List <String> votacoes = new ArrayList();
	int y  = 150;
	String votacao_selecionada = "";
	String QUERY = "";
	int id_votacao = 0;
	String opcao1 = "";
	String opcao2 = "";
	boolean isPressed;
	boolean votou = false;
	MaskFormatter mascaraCpf;

	private JPanel contentPane;
	private JPasswordField senha_votante;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Votar frame = new Votar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Votar() {

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		QUERY = "SELECT titulo FROM votacao";
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);
				) {		      
			while(rs.next()){
				votacoes.add(rs.getString("titulo"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450,250, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Votar");
		lblNewLabel.setForeground(new Color(0, 128, 64));
		lblNewLabel.setBounds(0, 11, 92, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		contentPane.add(lblNewLabel);

		JLabel lblVotaesDisponveis = new JLabel("Votações disponíveis:");
		lblVotaesDisponveis.setBounds(52, 103, 241, 36);
		lblVotaesDisponveis.setHorizontalAlignment(SwingConstants.CENTER);
		lblVotaesDisponveis.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblVotaesDisponveis);

		JButton btnNewButton_1_1 = new JButton("< Voltar");
		btnNewButton_1_1.setBounds(29, 562, 108, 38);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton_1_1);

		JButton[] botoes = new JButton[votacoes.size()];

		JPanel p = new JPanel();
		p.setDoubleBuffered(false);
		p.setEnabled(false);
		p.setLayout(new GridLayout(0, 1));

		JLabel labelTitulo = new JLabel();
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTitulo.setBounds(535, 150, 439, 96);
		contentPane.add(labelTitulo);

		for(String votacao:votacoes) {
			botoes[i] = new JButton(votacao);
			botoes[i].setBounds(240, y, 501, 58);
			botoes[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {



					votacao_selecionada = votacao;
					labelTitulo.setText(votacao_selecionada);

					QUERY = "SELECT opcao1,opcao2 FROM votacao WHERE titulo = '"+votacao_selecionada+"'";

					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(QUERY);
							) {		      
						while(rs.next()){

							opcao1 = (rs.getString("opcao1"));
							opcao2 = (rs.getString("opcao2"));
							opt1.setText(opcao1);
							opt2.setText(opcao2);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					isPressed = true;
				}
			});
			botoes[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			botoes[i].setFont(new Font("Verdana", Font.PLAIN, 21));
			contentPane.add(botoes[i]);
			p.add(botoes[i]);
			p.add(new JLabel(""));
			y+=75;
			i++;
		};

		JScrollPane scrollPane_1 = new JScrollPane(p);
		scrollPane_1.setBounds(75, 150, 450, 400);
		contentPane.add(scrollPane_1);

		opt1 = new JRadioButton("        ");
		opt1.setHorizontalAlignment(SwingConstants.CENTER);
		opt1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		opt1.setBounds(570, 314, 173, 23);
		contentPane.add(opt1);

		opt2 = new JRadioButton("        ");
		opt2.setHorizontalAlignment(SwingConstants.CENTER);
		opt2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		opt2.setBounds(758, 314, 173, 23);
		contentPane.add(opt2);

		ButtonGroup group = new ButtonGroup();
		group.add(opt1);
		group.add(opt2);


		JLabel lblVotaesDisponveis_1 = new JLabel("Selecione a opção na qual deseja votar:");
		lblVotaesDisponveis_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVotaesDisponveis_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVotaesDisponveis_1.setBounds(535, 271, 439, 36);
		contentPane.add(lblVotaesDisponveis_1);

		JFormattedTextField cpf = new JFormattedTextField(mascaraCpf);
		cpf.setBounds(670, 77, 212, 20);
		contentPane.add(cpf);

		senha_votante = new JPasswordField();
		senha_votante.setBounds(670, 123, 212, 20);
		contentPane.add(senha_votante);

		JButton confirmar = new JButton("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cpf_votante = cpf.getText();

				if (isPressed == true) {

					QUERY = "SELECT id_votacao FROM votacao where titulo = '" + votacao_selecionada + "'";

					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(QUERY);
							) {		      
						while(rs.next()){

							System.out.println(votacao_selecionada);
							System.out.printf(rs.getInt("id_votacao")+ "\n");
							id_votacao = (rs.getInt("id_votacao"));

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					QUERY = "SELECT cpf_votante FROM voto WHERE id_votacao = '"+id_votacao+"'";

					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(QUERY);
							) {		      
						while(rs.next()){
							String cpf_atual = (rs.getString("cpf_votante"));
							if ((cpf_votante.equals(cpf_atual)) || (cpf_votante.equals("000.000.000-00")) || (cpf_votante.equals("   .   .   -  ")) ) {
								votou = true;
							}
							System.out.println(cpf_atual);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					System.out.println(votou);

					if (votou == false) {
						if(opt1.isSelected()==true) {
							try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
									Statement stmt = conn.createStatement();
									) {	

								QUERY= "INSERT INTO voto (id_votacao,cpf_votante,opcoes) VALUES ('"+id_votacao+"','"+cpf_votante+"','"+opcao1+"')";

								stmt.executeUpdate(QUERY);  	  
							} catch (SQLException e1) {
								e1.printStackTrace();
							} 
							JOptionPane.showMessageDialog(null, "Voto confirmado!");
							Menu menu = new Menu();
							menu.setVisible(true);
							dispose();

						}
						else if (opt2.isSelected()==true) {
							try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
									Statement stmt = conn.createStatement();
									) {		      
								// Execute a query
								System.out.println("Inserindo dados em tabela");

								QUERY = "INSERT INTO voto (id_votacao,opcoes) VALUES ("+id_votacao+",'"+opcao2+"')";

								stmt.executeUpdate(QUERY);  	  
							} catch (SQLException e1) {
								e1.printStackTrace();
							} 
							JOptionPane.showMessageDialog(null, "Voto confirmado!");
							Menu menu = new Menu();
							menu.setVisible(true);
							dispose();

						}
						else {
							JOptionPane.showMessageDialog(null, "Selecione uma opção para votar!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Informe um CPF válido ou que ainda não tenha votado nessa votação!");
					}
				}else {JOptionPane.showMessageDialog(null, "Selecione uma votação antes de votar!");}
			}

		});
		confirmar.setOpaque(true);
		confirmar.setForeground(new Color(51, 51, 51));
		confirmar.setFont(new Font("Tahoma", Font.BOLD, 17));
		confirmar.setBorderPainted(false);
		confirmar.setBackground(new Color(102, 204, 102));
		confirmar.setBounds(758, 556, 184, 44);
		contentPane.add(confirmar);

		JTextPane textPane = new JTextPane();
		textPane.setText("____________________________________________________________________________________________________________________________________________________________");
		textPane.setForeground(Color.BLACK);
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(10, 42, 964, 20);
		contentPane.add(textPane);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(610, 73, 50, 36);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(610, 116, 62, 36);
		contentPane.add(lblNewLabel_1_1);

	}
}
