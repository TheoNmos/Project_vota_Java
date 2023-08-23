package vota;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;



public class Login extends JFrame {
	
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/vota";
	String USER = "root";
	String PASS = "root";
	
	
	private JPanel contentPane;
	private JPasswordField cpf;
	private JPasswordField senha;
	MaskFormatter mascaraCpf;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("V.O.T.A.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblNewLabel.setBounds(368, 11, 212, 84);
		contentPane.add(lblNewLabel);
		
		JFormattedTextField cpf = new JFormattedTextField(mascaraCpf);
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cpf.setBounds(86, 225, 390, 32);
		contentPane.add(cpf);
		
		JButton btnFazerLogin = new JButton("Fazer Login");
		btnFazerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpf_adms = "000.000.000-00";
				String senha_adms = "adms";
				String cpf_testando = cpf.getText();
				String senha_testando = senha.getText();
				boolean valido = false;
				
				String QUERY = "SELECT cpf_usuario, senha FROM usuarios";
				
				try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				         Statement stmt = conn.createStatement();
				         ResultSet rs = stmt.executeQuery(QUERY);
				      ) {		      
				         while(rs.next()){
				        	 
				            if((rs.getString("cpf_usuario").equals(cpf_testando)) && (rs.getString("senha").equals(senha_testando))) {
				            	valido = true;
				            };
				            
				         }
				      } catch (SQLException e1) {
				         e1.printStackTrace();
				      }
				
				if (valido == true) {
					if ((cpf_testando.equals(cpf_adms)) && (senha_testando.equals(senha_adms))) {
						MenuAdm menuAdm = new MenuAdm();
						menuAdm.setVisible(true);
						dispose();
					}else {
					Menu menu = new Menu();
					menu.setVisible(true);
					dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Credenciais incorretas!");
				}
			}
			
		});
		btnFazerLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFazerLogin.setBounds(193, 410, 167, 32);
		contentPane.add(btnFazerLogin);
		
		JLabel lblvotaoOfflineTransparente = new JLabel("(Votação Offline Transparente e Acessível)");
		lblvotaoOfflineTransparente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblvotaoOfflineTransparente.setBounds(271, 92, 393, 25);
		contentPane.add(lblvotaoOfflineTransparente);
		
		JLabel lblCpf_1 = new JLabel("Digite seu CPF:");
		lblCpf_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblCpf_1.setBounds(167, 166, 230, 48);
		contentPane.add(lblCpf_1);
		
		JLabel lblSenha_1 = new JLabel("Digite a senha desejada:");
		lblSenha_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblSenha_1.setBounds(93, 308, 383, 48);
		contentPane.add(lblSenha_1);
		
		senha = new JPasswordField();
		senha.setFont(new Font("Tahoma", Font.PLAIN, 22));
		senha.setBounds(86, 367, 390, 32);
		contentPane.add(senha);
		
		JButton btnCadastrarse = new JButton("Cadastrar-se");
		btnCadastrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				cadastro.setVisible(true);
				dispose();
			}
			
		});
		btnCadastrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastrarse.setBounds(644, 321, 178, 32);
		contentPane.add(btnCadastrarse);
		
		JLabel lblCpf_1_1 = new JLabel("Não possui cadastro?");
		lblCpf_1_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblCpf_1_1.setBounds(599, 254, 317, 48);
		contentPane.add(lblCpf_1_1);
	}
}