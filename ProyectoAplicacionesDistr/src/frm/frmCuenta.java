package frm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.net.Socket;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import entidades.entMaeCli;
import util.utilNumero;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;

public class frmCuenta extends JFrame{
	
	static Socket cli=null;
	static ObjectOutputStream flujoob=null;
	ObjectInputStream flujodatosVirificacion = null;

	private static final long serialVersionUID = -2778620051956465611L;
	private JPanel contentPane;
	private JTextField txtNroTargeta;
	private JPasswordField txtClave;
	private JButton btnCancelar;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnIngresar;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCuenta frame = new frmCuenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmCuenta() {
		gui();
		eventos();
	}

	private void gui() {
		
		setResizable(false);
		setBackground(new Color(153, 204, 153));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 206);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new LineBorder(new Color(0, 102, 102)));
		btnCancelar.setForeground(new Color(0, 102, 102));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(353, 147, 89, 23);
		contentPane.add(btnCancelar);
		
		panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		panel.setBorder(new LineBorder(new Color(0, 102, 102)));
		panel.setBounds(156, 11, 286, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtNroTargeta = new JTextField();
		txtNroTargeta.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNroTargeta.setForeground(new Color(255, 255, 255));
		txtNroTargeta.setBackground(new Color(0, 102, 102));
		txtNroTargeta.setBounds(127, 32, 150, 20);
		panel.add(txtNroTargeta);
		txtNroTargeta.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtClave.setForeground(new Color(255, 255, 255));
		txtClave.setBackground(new Color(0, 102, 102));
		txtClave.setBounds(127, 70, 150, 20);
		panel.add(txtClave);
		
		JLabel lblNroTarjeta = new JLabel("Nro Tarjeta:");
		lblNroTarjeta.setForeground(new Color(0, 102, 102));
		lblNroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNroTarjeta.setBounds(10, 35, 107, 14);
		panel.add(lblNroTarjeta);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setForeground(new Color(0, 102, 102));
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClave.setBounds(10, 73, 107, 14);
		panel.add(lblClave);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmCuenta.class.getResource("/recursos/1446422649_Login.png")));
		lblNewLabel.setBounds(10, 11, 136, 125);
		contentPane.add(lblNewLabel);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBorder(new LineBorder(new Color(0, 102, 102)));
		btnIngresar.setForeground(new Color(0, 102, 102));
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIngresar.setBounds(254, 147, 89, 23);
		contentPane.add(btnIngresar);
		//centrar formulario
		this.setLocationRelativeTo(null);
		
	}

	private void eventos() {
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(utilNumero.esNumerio(txtNroTargeta.getText())){
						entMaeCli mc = new entMaeCli();
						mc.setNumeroTarjeta(Integer.parseInt(txtNroTargeta.getText()));
						mc.setClave(txtClave.getText().toString());
						//-----Envia Datos al Servidor					
						cli = new Socket("127.0.0.1",9090);
						flujoob = new ObjectOutputStream(cli.getOutputStream());
						flujoob.writeObject(mc);
						
						//-------recibe datos del Servidor		
						flujodatosVirificacion = new ObjectInputStream(cli.getInputStream());
						entMaeCli datosVerificacion = null;
						if(flujodatosVirificacion != null){
							datosVerificacion = (entMaeCli) flujodatosVirificacion.readObject();
						}else {
							DataInputStream msg = new DataInputStream(cli.getInputStream());
							JOptionPane.showMessageDialog(null,msg.readUTF());
						}
						if(datosVerificacion != null){
							
							dispose();
							frmMenu frm = new frmMenu();
							entMaeCli objeto = new entMaeCli();
							objeto.setId(datosVerificacion.getId());
							objeto.setNombre(datosVerificacion.getNombre());
							objeto.setSaldo(datosVerificacion.getSaldo());
							frm.setObjeto(objeto);
							frm.show();
							}else{
								JOptionPane.showMessageDialog(null, "Usuario o Password no Valido.","Cuenta",JOptionPane.INFORMATION_MESSAGE);
							}
						
						flujoob.close();
						cli.close();					
						System.out.println("Exito");
					}else {
						JOptionPane.showMessageDialog(null, "Ingresar Solo Valores Numericos.","Cuenta",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error: "+e.getMessage());
				}finally{
					try {
						flujoob.close();
						cli.close();
					}catch (ObjectStreamException e) {
						JOptionPane.showMessageDialog(null, "Usuario o Password no Valido.","Cuenta",JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e2) {
						System.out.println("Error: "+e2.getMessage());
						JOptionPane.showMessageDialog(null, "El Servidor no esta Conectado.","Cuenta",JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				System.exit(0);
			}
		});
		
	}
}
