package frm;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.joda.time.DateTime;

import entidades.entMaeCli;
import negocio.negCliente;
import util.utilNumero;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class frmServidor extends JFrame implements Runnable{
	
	static ServerSocket servidor = null;
	static Socket cli = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787352196010705642L;
	boolean banderaV = true;
	boolean banderaM = false;
	private JPanel contentPane;
	private JTextArea txtContenedor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmServidor frame = new frmServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		contentPane.add(scrollPane);
		
		txtContenedor = new JTextArea();
		scrollPane.setViewportView(txtContenedor);
		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		try {
			servidor = new ServerSocket(9090);
			while (banderaV) {

			cli = servidor.accept();
	
			//--------------------------------objeto verificar Cliente
			ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
			if(flujo != null){
			entMaeCli mc = (entMaeCli) flujo.readObject();
			txtContenedor.append("\n"+"=========================");	
			txtContenedor.append("\n"+cli.getInetAddress());
			txtContenedor.append("\n"+mc.getNumeroTarjeta());
			txtContenedor.append("\n"+mc.getClave());
			txtContenedor.append("\n"+"=========================");
			//----------------------------------------------------------			
			
			entMaeCli mlv = negCliente.Instancia().verificarAccesoCliente(mc.getNumeroTarjeta(), mc.getClave());
			System.out.println("MaeCli: "+mlv);
			if(mlv != null){
			ObjectOutputStream flujodatosVirificacion = new ObjectOutputStream(cli.getOutputStream());
			flujodatosVirificacion.writeObject(mlv);
			banderaV = false;
			banderaM = true;
			}else{

				DataOutputStream msg = new DataOutputStream(cli.getOutputStream());
				msg.writeUTF("Usuario o Password no Valido.");
			}
			//---------------------------------------------------------Fin verificar
			}
			flujo.close();
			cli.close();
		}
		while (banderaM) {
			
			//Se acepta el SocketServer
			//----
			
			cli = servidor.accept();

			//------------------------Datos frmMenu otroMonto
			ObjectInputStream flujoMaeCli = new ObjectInputStream(cli.getInputStream());
			entMaeCli datosMenu = (entMaeCli) flujoMaeCli.readObject();
			entMaeCli MenuDevolverMaeClienteId = negCliente.Instancia().DevolverMaeClienteId(datosMenu.getId());
			txtContenedor.append("\n"+"Devolver: "+MenuDevolverMaeClienteId);
			txtContenedor.append("\n"+"========FRMMENU=================");	
			entMaeCli editar = new entMaeCli();
			editar.setId(MenuDevolverMaeClienteId.getId());
			editar.setNumeroTarjeta(MenuDevolverMaeClienteId.getNumeroTarjeta());
			editar.setNombre(MenuDevolverMaeClienteId.getNombre());
			MenuDevolverMaeClienteId.setUltMovMonto(datosMenu.getUltMovMonto());
			editar.setSaldo(MenuDevolverMaeClienteId.getSaldo());
			editar.setUltMovMonto(datosMenu.getUltMovMonto());
			editar.setFechaUlt(datosMenu.getFechaUlt());
			editar.setHoraUlt(datosMenu.getHoraUlt());
			if(editar.saldoMayorUltMovMont()&&(editar.verificarTipoCheque(20)||editar.verificarTipoCheque(50))){
				editar.actualizarSaldo();
				txtContenedor.append("\n"+"Saldo: "+editar.getSaldo()+"  Ultimo Monto: "+editar.getUltMovMonto());
				txtContenedor.append("\n"+editar);
				boolean x = negCliente.Instancia().editarMaeCliente(editar);
				if(x){
				File archivo = new File("c:\\log\\data_atm.log");
				BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo,true),"utf-8"));
				Fescribe.write(editar+"\r\n");
				Fescribe.close();
				}			
			}
		}	
		} catch (Exception e) {
			   System.out.println("Error en la segunda bandera: "+e.getMessage());
			   
		}
	}
}
