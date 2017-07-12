package entidades;

import java.io.Serializable;

public class entMaeCli implements Serializable{

	private static final long serialVersionUID = 2401015397360725845L;
	private int id;
	private String nombre;
	private int numeroTarjeta;
	private String clave;
	private int saldo;
	private int ultMovMonto;
	private String fechaUlt;
	private String horaUlt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public int getUltMovMonto() {
		return ultMovMonto;
	}
	public void setUltMovMonto(int ultMovMonto) {
		this.ultMovMonto = ultMovMonto;
	}
	public String getFechaUlt() {
		return fechaUlt;
	}
	public void setFechaUlt(String fechaUlt) {
		this.fechaUlt = fechaUlt;
	}
	public String getHoraUlt() {
		return horaUlt;
	}
	public void setHoraUlt(String horaUlt) {
		this.horaUlt = horaUlt;
	}
	public int actualizarSaldo(){
		return (saldo = saldo - ultMovMonto);
	}
	public boolean saldoMayorUltMovMont(){
		boolean x = false;
		if(saldo>=ultMovMonto)
			x=true;
		return x;		
	}
	public boolean saldoMayorA500(){
		boolean x = false;
		if(saldo > 500)
			x = true;
		return x;
	}
	public boolean verificarTipoCheque(int cheque){
		boolean x = false;
		if(ultMovMonto%cheque==0)
			x=true;
		return x;
	}
	
	@Override
	public String toString() {
		return numeroTarjeta + nombre + saldo + ultMovMonto + fechaUlt + horaUlt;
	}
	
}
