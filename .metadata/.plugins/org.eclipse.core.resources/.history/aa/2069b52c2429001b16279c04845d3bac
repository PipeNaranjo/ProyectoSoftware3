package software.cafeteria.logica;

import java.io.Serializable;

import software.cafeteria.entidades.RegistroDeVentas;

public class Tienda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Inventario inventario;

	private RegistroDeVentas registroVentas;

	public Tienda() {
		inventario = new Inventario();
		registroVentas = new RegistroDeVentas(0, 0);
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public RegistroDeVentas getRegistroVentas() {
		return registroVentas;
	}

	public void setRegistroVentas(RegistroDeVentas registroVentas) {
		this.registroVentas = registroVentas;
	}

}
