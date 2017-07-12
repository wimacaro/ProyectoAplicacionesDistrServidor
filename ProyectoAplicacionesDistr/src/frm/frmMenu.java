package frm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entidades.entMaeCli;
import util.utilNumero;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmMenu extends JFrame{
	
	static Socket cli = null;
	static ObjectOutputStream flujoob = null;
	private entMaeCli objeto;
		
	public entMaeCli getObjeto() {
		return objeto;
	}

	public void setObjeto(entMaeCli objeto) {
		this.objeto = objeto;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btn20;
	private JButton btn100;
	private JButton btn160;
	private JButton btn300;
	private JButton btn500;
	private JTextField txtOtroMonto;
	private JButton btnProcesar;
	private JLabel lblidMaeClie;
	private JPanel panel;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//frmMenu frame = new frmMenu();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmMenu() {
		gui();
		eventos();
	}

	private void gui() {

		setResizable(false);
		setBackground(new Color(153, 204, 153));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 418);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//-----------------------------------------
		panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		//Border Title
		panel.setBounds(10, 11, 414, 340);
		contentPane.add(panel);
		panel.setLayout(null);
		//------------------------------------------
		btn20 = new JButton("20");
		btn20.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btn20.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btn20.setIcon(new ImageIcon(frmMenu.class.getResource("/recursos/1449222901_coins.png")));
		btn20.setBounds(10, 24, 182, 80);
		panel.add(btn20);
		
		btn300 = new JButton("300");
		btn300.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btn300.setIcon(new ImageIcon(frmMenu.class.getResource("/recursos/1449222901_coins.png")));
		btn300.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btn300.setBounds(222, 24, 182, 80);
		panel.add(btn300);
		
		btn100 = new JButton("100");
		btn100.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btn100.setIcon(new ImageIcon(frmMenu.class.getResource("/recursos/1449222901_coins.png")));
		btn100.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btn100.setBounds(10, 115, 182, 80);
		panel.add(btn100);
		
		btn500 = new JButton("500");
		btn500.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btn500.setIcon(new ImageIcon(frmMenu.class.getResource("/recursos/1449222901_coins.png")));
		btn500.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btn500.setBounds(222, 115, 182, 80);
		panel.add(btn500);
		
		btn160 = new JButton("160");
		btn160.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btn160.setIcon(new ImageIcon(frmMenu.class.getResource("/recursos/1449222901_coins.png")));
		btn160.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btn160.setBounds(10, 206, 182, 80);
		panel.add(btn160);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 204, 153));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Otro Monto", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 102, 102)));
		panel_1.setBounds(222, 206, 182, 80);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtOtroMonto = new JTextField();
		txtOtroMonto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtOtroMonto.getText().length() !=0)
				btnProcesar.setEnabled(true);
				else {
				btnProcesar.setEnabled(false);
				}
			}
		});
		txtOtroMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtOtroMonto.setFont(new Font("Tahoma", Font.PLAIN, 35));
		txtOtroMonto.setBounds(10, 21, 162, 48);
		panel_1.add(txtOtroMonto);
		txtOtroMonto.setColumns(10);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.setBorder(new LineBorder(new Color(0, 102, 102), 2));
		btnProcesar.setForeground(new Color(0, 102, 102));
		btnProcesar.setBounds(160, 306, 89, 23);
		btnProcesar.setEnabled(false);
		panel.add(btnProcesar);
		
		lblidMaeClie = new JLabel("----------");
		lblidMaeClie.setHorizontalAlignment(SwingConstants.CENTER);
		lblidMaeClie.setBounds(10, 364, 299, 14);
		lblidMaeClie.setVisible(false);
		contentPane.add(lblidMaeClie);
		//centrar formulario
		this.setLocationRelativeTo(null);
	}

	private void eventos() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				if(objeto !=null){
					System.out.println("Nombre: "+objeto.getNombre());
					lblidMaeClie.setText(String.valueOf(objeto.getId()));
					panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),"Usuario: "+objeto.getNombre()+" Saldo: "+objeto.getSaldo(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 102)));
				}
			}
		});
		

		btn20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesoDatos("20");
			}
		});
		btn100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesoDatos("100");
			}
		});
		btn160.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesoDatos("160");
			}
		});
		btn300.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesoDatos("300");
			}
		});
		btn500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				procesoDatos("500");
			}
		});
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(utilNumero.esNumerio(txtOtroMonto.getText())){
				if(Integer.parseInt(txtOtroMonto.getText())>500)
				procesoDatos(txtOtroMonto.getText());
				else{
					JOptionPane.showMessageDialog(null,"Ingrese un valor Mayor a 500.","Menu",JOptionPane.INFORMATION_MESSAGE);
				}
				}
				else{
					JOptionPane.showMessageDialog(null,"Valor Incorrecto.","Menu",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void procesoDatos(String cheque){
		try {
				entMaeCli mc = new entMaeCli();
				DateTime fecha = new DateTime();
				if(utilNumero.esNumerio(cheque)){ 					
					System.out.println(fecha.toString("dd-MM-yyyy"));
					System.out.println(fecha.toString("hh:mm:ss"));
					mc.setId(Integer.parseInt(lblidMaeClie.getText()));
				mc.setUltMovMonto(Integer.parseInt(cheque));
				mc.setFechaUlt(fecha.toString("ddMMyyyy"));
				mc.setHoraUlt(fecha.toString("hhmmss"));					
				System.out.println(lblidMaeClie.getText());
				cli = new Socket("127.0.0.1",9090);

				flujoob = new ObjectOutputStream(cli.getOutputStream());
				flujoob.writeObject(mc);
				flujoob.close();
				cli.close();					
				System.out.println("Exito");
				}else{
					JOptionPane.showMessageDialog(null, "Ingresar Solo Valores Numericos.","Menu",JOptionPane.INFORMATION_MESSAGE);
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: "+e.getMessage());
		}
	}
}
