package software.cafeteria.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Recibo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<ProductosVentas> productosV;
	private GregorianCalendar fecha;
	private boolean tarjeta;
	private int EfectivoRegistrado;

	public Recibo(boolean tarjeta) {
		this.id = "";
		this.productosV = new ArrayList<ProductosVentas>();
		this.fecha = new GregorianCalendar();
		this.tarjeta = tarjeta;
	}

	public boolean agregarProductos(ProductosInventario producto, int cantidad) {
		ProductosVentas a = new ProductosVentas(producto, cantidad);
		productosV.add(a);
		EfectivoRegistrado = 0;
		return true;
	}

	public int getPrecioTotal() {
		int suma = 0;
		for (ProductosVentas a : productosV) {
			suma += a.getProducto().getPrecio() * a.getCantidad();
		}
		return suma;
	}

	public int getCambio() {
		if (tarjeta) {
			return 0;
		} else {
			return (EfectivoRegistrado - getPrecioTotal());
		}
	}

	// --------------------------------------------------------------------------//
	public String getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id + "";
	}

	public ArrayList<ProductosVentas> getProductosV() {
		return productosV;
	}

	public void setProductosV(ArrayList<ProductosVentas> productosV) {
		this.productosV = productosV;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public boolean getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(boolean tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getEfectivoRegistrado() {
		return EfectivoRegistrado;
	}

	public void setEfectivoRegistrado(int efectivoRegistrado) {
		EfectivoRegistrado = efectivoRegistrado;
	}

}
