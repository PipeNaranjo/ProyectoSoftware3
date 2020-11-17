package software.cafeteria.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class InformeFiscal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroInforme;
	private GregorianCalendar fecha;
	private int efectivo;
	private int tarjeta;
	private Integer[][] iva;
	private Integer[][] ganancia;
	private ArrayList<Recibo> lista;
	
	public InformeFiscal(int numeroInforme, ArrayList<Recibo> lista) {
		setNumeroInforme(numeroInforme);
		this.fecha=lista.get(0).getFecha();
		this.lista=lista;
		calcularValores(lista);
	}

	private void calcularValores(ArrayList<Recibo> lista) {
		Integer[] i=new Integer[100];
		Integer[] g=new Integer[100];
		for(Recibo r:lista) {
			int suma=0;
			for(ProductosVentas p:r.getProductosV()) {
				int ivaP = p.getProducto().getIva();
				int $p=p.getProducto().getPrecio();
				int $piva=($p*ivaP)/100;
				if(i[ivaP-1]==null) {
					i[ivaP-1]=0;
					g[ivaP-1]=0;
				}
				i[ivaP-1]+=$piva*p.getCantidad();
				g[ivaP-1]+=($p-$piva)*p.getCantidad();
				suma+=$p*p.getCantidad();
			}
			if(r.getTarjeta()) {
				this.tarjeta+=suma;
			}else {
				this.efectivo+=suma;
			}
		}
		ArrayList<Integer> vi=new ArrayList<Integer>();
		for(int j=0;j<100;j++) {
			if(i[j]!=null) {
				vi.add(j);
			}
		}
		this.iva = new Integer[vi.size()][2];
		this.ganancia = new Integer[vi.size()][2];
		int j=0;
		for(int n:vi) {
			this.iva[j][0]=n+1;
			this.iva[j][1]=i[n];
			this.ganancia[j][0]=n+1;
			this.ganancia[j][1]=g[n];
			j++;
		}
		System.out.println("sisa");
	}
	
	public int getTotalEnCaja() {
		return this.efectivo+this.tarjeta;
	}
	
	public String numeroPrimerRecibo() {
		return lista.get(0).getId();
	}
	
	public String numeroUltimoRecibo() {
		return lista.get(lista.size()).getId();
	}
	
	public String getNumeroInforme() {
		return numeroInforme;
	}
	
	public void setNumeroInforme(int numeroInforme) {
		this.numeroInforme=numeroInforme+"";
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public int getEfectivo() {
		return efectivo;
	}

	public int getTarjeta() {
		return tarjeta;
	}

	public Integer[][] getIva() {
		return iva;
	}

	public Integer[][] getGanancia() {
		return ganancia;
	}

	public ArrayList<Recibo> getLista() {
		return lista;
	}
	
}
