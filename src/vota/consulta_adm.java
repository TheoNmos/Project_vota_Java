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
import java.awt.ComponentOrientation;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JTabbedPane;

public class consulta_adm extends JFrame {



	String DB_URL = "jdbc:mysql://127.0.0.1:3306/vota";
	String USER = "root";
	String PASS = "root";
	int i = 0;
	List <String> votacoes = new ArrayList();
	int y  = 150;
	String votacao_selecionada = "";
	String QUERY = "";
	int id_votacao = 0;
	String opcao_atual = "";
	int counterOpt1 = 0;
	int counterOpt2 = 0;
	String opt1 = "";
	String opt2 = "";


	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Votacoes_Anteriores_1 frame = new Votacoes_Anteriores_1();
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
	public consulta_adm() {

		JList list = new JList();

		String QUERY = "SELECT titulo FROM votacao";
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY);
				) {		      
			while(rs.next()){

				//MOSTRAR INFOS DA VOTAÇÃO
				System.out.printf(rs.getString("titulo")+ "\n");
				votacoes.add(rs.getString("titulo"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CONSULTAR");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 22, 185, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblNewLabel);

		JLabel lblVotaesDisponveis = new JLabel("VOTAÇÕES ANTERIORES");
		lblVotaesDisponveis.setBounds(10, 103, 326, 36);
		lblVotaesDisponveis.setHorizontalAlignment(SwingConstants.CENTER);
		lblVotaesDisponveis.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblVotaesDisponveis);

		

		JLabel lblNewLabel_2 = new JLabel("_______________________________________________________________________________________________________________________");
		lblNewLabel_2.setBounds(29, 49, 839, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel qtdVotos1 = new JLabel("");
		qtdVotos1.setHorizontalAlignment(SwingConstants.CENTER);
		qtdVotos1.setFont(new Font("Tahoma", Font.BOLD, 25));
		qtdVotos1.setBounds(551, 350, 67, 46);
		contentPane.add(qtdVotos1);

		JLabel qtdVotos2 = new JLabel("");
		qtdVotos2.setHorizontalAlignment(SwingConstants.CENTER);
		qtdVotos2.setFont(new Font("Tahoma", Font.BOLD, 25));
		qtdVotos2.setBounds(835, 350, 67, 46);
		contentPane.add(qtdVotos2);
		
		JLabel opcao1 = new JLabel("");
		opcao1.setHorizontalAlignment(SwingConstants.CENTER);
		opcao1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		opcao1.setBounds(461, 306, 246, 46);
		contentPane.add(opcao1);

		JLabel opcao2 = new JLabel("");
		opcao2.setHorizontalAlignment(SwingConstants.CENTER);
		opcao2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		opcao2.setBounds(749, 306, 235, 46);
		contentPane.add(opcao2);


		JLabel votacao_atual = new JLabel("");
		votacao_atual.setFont(new Font("Tahoma", Font.BOLD, 20));
		votacao_atual.setHorizontalAlignment(SwingConstants.CENTER);
		votacao_atual.setBounds(472, 150, 502, 86);
		contentPane.add(votacao_atual);

		JButton[] botoes = new JButton[votacoes.size()];

		JPanel p = new JPanel();
		p.setDoubleBuffered(false);
		p.setEnabled(false);
		p.setLayout(new GridLayout(0, 1));// tentano mudar o layout para ver se adiciona outras linhas

		for(String votacao:votacoes) {
			botoes[i] = new JButton(votacao);
			botoes[i].setBounds(240, y, 501, 58);
			botoes[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			botoes[i].setFont(new Font("Verdana", Font.PLAIN, 21));
			botoes[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					counterOpt1 = 0;
					counterOpt2= 0;
					votacao_selecionada = votacao;
					votacao_atual.setText(votacao_selecionada);
					
					//nao esta adaptado para o casod e adicionar mais opcoes.
					String QUERY = "SELECT id_votacao, opcao1,opcao2 FROM votacao where titulo = '" + votacao_selecionada + "'";

					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(QUERY);
							) {		      
						while(rs.next()){

							id_votacao = (rs.getInt("id_votacao"));
							opt1 = (rs.getString("opcao1"));
							opt2 = (rs.getString("opcao2"));
							System.out.println(opt1);
							System.out.println(opt2);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					QUERY = "SELECT opcoes FROM voto where id_votacao = '" + id_votacao + "'";

					try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(QUERY);
							) {		      
						while(rs.next()){

							opcao_atual = (rs.getString("opcoes"));
							System.out.println(opcao_atual);
							if(opcao_atual.equals(opt1)){counterOpt1++;}else{counterOpt2++;}

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					qtdVotos1.setText(""+counterOpt1); 
					qtdVotos2.setText(""+counterOpt2);
					opcao1.setText(opt1+": ");
					opcao2.setText(opt2+": ");
					
				}
			});
			contentPane.add(botoes[i]);
			p.add(botoes[i]);
			p.add(new JLabel(""));
			y+=75;
			i++;
		};

		JScrollPane scrollPane_1 = new JScrollPane(p);
		scrollPane_1.setBounds(62, 150, 400, 400);
		contentPane.add(scrollPane_1);
		
		JButton btnVoltar = new JButton("<<");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdm adm = new MenuAdm();
				adm.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(10, 577, 49, 23);
		contentPane.add(btnVoltar);
	}
}
