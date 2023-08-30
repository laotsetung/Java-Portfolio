package lao.hotel.views;

import lao.hotel.db.Hospede;
import lao.hotel.db.HospedesDAO;
import lao.hotel.db.Reservas;
import lao.hotel.db.ReservasDAO;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservasDAO rDAO;// = new ReservasDAO();
	private HospedesDAO hDAO;// = new HospedesDAO();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		//inicializa variaveis DAO´S
		try {
			rDAO = new ReservasDAO();
			hDAO = new HospedesDAO();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		setIconImage(new ImageIcon("src/main/java/lao/hotel/imagenes/lOGO-50PX.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon("src/main/java/lao/hotel/imagenes/reservado.png"), scroll_table, null);
		scroll_table.setVisible(true);


		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hospedes", new ImageIcon("src/main/java/lao/hotel/imagenes/pessoas.png"), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);


		//coloca os dados nas tabelas
		atualizaTabelas();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("src/main/java/lao/hotel/imagenes/Ha-100px.png"));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarRegistro(txtBuscar.getText());
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tabSelect = panel.getSelectedIndex();
				Object idReg;

				if(tabSelect == 0) {
					idReg = tbReservas.getModel().getValueAt(tbReservas.getSelectedRow(), 0);
				}else{
					idReg = tbHospedes.getModel().getValueAt(tbHospedes.getSelectedRow(),0);
				}

				Editar edit = null;
				try {
					edit = new Editar((Integer) idReg, tabSelect);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}
				edit.setVisible(true);
				dispose();
			}

		});
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tabSelect = panel.getSelectedIndex();

				int res = 99;
				if(tabSelect == 0){
					res = JOptionPane.showConfirmDialog(null, "Deseja mesmo EXCLUIR a RESERVA de número : "
									+ tbReservas.getModel().getValueAt(tbReservas.getSelectedRow(),0) + "? (E o hospede desta reserva)",
							"Excluir Registro", JOptionPane.OK_CANCEL_OPTION);

				}else if(tabSelect == 1){
					res = JOptionPane.showConfirmDialog(null, "Deseja mesmo EXCLUIR o Hospede : "
									+ tbHospedes.getModel().getValueAt(tbHospedes.getSelectedRow(),1) + "? (E sua reserva)",
							"Excluir Registro", JOptionPane.OK_CANCEL_OPTION);

				}

				//ok
				if(res == 0){
					//excluir registros
					if(tabSelect == 0) {
						Object idRes = tbReservas.getModel().getValueAt(tbReservas.getSelectedRow(),0);
						try {
							rDAO.deletar((Integer) idRes);
							atualizaTabelas();

						} catch (SQLException ex) {
							throw new RuntimeException(ex);
						}
						System.out.println(idRes);
					}else{
						Object idHos = tbHospedes.getModel().getValueAt(tbHospedes.getSelectedRow(),0);

						try {
								hDAO.deletar((Integer) idHos);
								atualizaTabelas();

						} catch (SQLException ex) {
							throw new RuntimeException(ex);
						}

						System.out.println(idHos);
					}
				}



			}
		});
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	}

	public void atualizaTabelas(){
		try {
			List<Reservas> reservas1; // = new ArrayList<Reservas>();
			reservas1 = rDAO.listar();

			modelo.setNumRows(0);
			for (Reservas r : reservas1) {
				modelo.addRow(new Object[]{r.getID_RES(), r.getDtEntrada(),
						r.getDtSaida(), r.getValor(), r.getForma_pag()});
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try {

			List<Hospede> hospedes1; // = new ArrayList<Reservas>();
			hospedes1 = hDAO.listar();

			modeloHospedes.setNumRows(0);
			for (Hospede h : hospedes1) {
				modeloHospedes.addRow(new Object[]{h.getIdHos(),h.getNome(), h.getsNome(),
						h.getDataNasc(), h.getNacionalidade(), h.getTelefone(), h.getId_res()});
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

	public void buscarRegistro(String busca){
		try {
			List<Hospede> hospedes1; // = new ArrayList<Reservas>();
			hospedes1 = hDAO.listarBusca(busca);

			modeloHospedes.setNumRows(0);
			modelo.setNumRows(0);

			for (Hospede h : hospedes1) {
				modeloHospedes.addRow(new Object[]{h.getIdHos(),h.getNome(), h.getsNome(),
						h.getDataNasc(), h.getNacionalidade(), h.getTelefone(), h.getId_res()});

				Reservas r = rDAO.selectReserva(h.getId_res());
				modelo.addRow(new Object[]{r.getID_RES(), r.getDtEntrada(),
						r.getDtSaida(), r.getValor(), r.getForma_pag()});
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}

}


