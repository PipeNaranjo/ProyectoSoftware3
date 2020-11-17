package software.cafeteria.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import software.cafeteria.logica.Inventario;

public class RegistroDeVentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigoRecibo;
	private int codigoInformeFiscal;
	private ArrayList<ArrayList<ArrayList<ArrayList<Recibo>>>> recibos;
	private ArrayList<Integer[][]> nsInformeFiscal;

	public RegistroDeVentas(int codigoRecibo, int codigoInformeFiscal) {
		this.codigoRecibo = codigoRecibo;
		this.codigoInformeFiscal = codigoInformeFiscal;
		recibos = new ArrayList<ArrayList<ArrayList<ArrayList<Recibo>>>>();
		nsInformeFiscal = new ArrayList<Integer[][]>();
		verificarAnio(new GregorianCalendar());
	}

	public boolean adjuntarUnRecibo(Recibo recibo, Inventario inventario) {
		GregorianCalendar fr = recibo.getFecha();
		verificarAnio(fr);
		recibo.setId(codigoRecibo);
		codigoRecibo++;
		recibos.get(fr.get(Calendar.YEAR) - 2020).get(fr.get(Calendar.MONTH)).get(fr.get(Calendar.DAY_OF_MONTH) - 1)
				.add(recibo);
		for (ProductosVentas a : recibo.getProductosV()) {
			inventario.obtenerproductoI(a.getProducto().getCodigoDeBarras()).restarAlInventario(a.getCantidad());
		}
		verificarInformeFiscal(fr);
		return true;
	}

	public ArrayList<Recibo> obtenerListaDeRecibos(GregorianCalendar fecha) {

		return recibos.get(fecha.get(Calendar.YEAR) - 2020).get(fecha.get(Calendar.MONTH))
				.get(fecha.get(Calendar.DAY_OF_MONTH) - 1);

	}

	public Recibo obtenerRecibo(GregorianCalendar fecha, String id) {
		ArrayList<Recibo> lista = obtenerListaDeRecibos(fecha);
		for (Recibo i : lista) {
			if (i.getId().equals(id)) {
				return i;
			}
		}
		return null;
	}

	public boolean removerRecibo(GregorianCalendar fecha, String id) {
		Recibo r = obtenerRecibo(fecha, id);
		if (r != null) {
			recibos.remove(r);
			return true;
		}
		return false;
	}

	public InformeFiscal getInformeFiscal(GregorianCalendar fecha) {
		if (existenciaDeInformeF(fecha)) {
			ArrayList<Recibo> a = obtenerListaDeRecibos(fecha);
			if (a.size() > 0) {
				return new InformeFiscal(getNoDeInformeF(fecha), a);
			}
		}
		return null;
	}

	// -------------------------------------------------------------------------------------------------------------------
	// //

	private boolean existenciaDeInformeF(GregorianCalendar fecha) {
		return nsInformeFiscal.get(fecha.get(Calendar.YEAR) - 2020)[fecha.get(Calendar.MONTH)][fecha
				.get(Calendar.DAY_OF_MONTH) - 1] != null;
	}

	private int getNoDeInformeF(GregorianCalendar fecha) {
		return nsInformeFiscal.get(fecha.get(Calendar.YEAR) - 2020)[fecha.get(Calendar.MONTH)][fecha
				.get(Calendar.DAY_OF_MONTH) - 1];
	}

	private void verificarInformeFiscal(GregorianCalendar fecha) {
		if (!existenciaDeInformeF(fecha)) {
			nsInformeFiscal.get(fecha.get(Calendar.YEAR) - 2020)[fecha.get(Calendar.MONTH)][fecha
					.get(Calendar.DAY_OF_MONTH) - 1] = codigoInformeFiscal;
			codigoInformeFiscal++;
		}
	}

	private void verificarAnio(GregorianCalendar fecha) {
		// GregorianCalendar gc=new GregorianCalendar();
		while (recibos.size() < fecha.get(Calendar.YEAR) - 2019) {
			recibos.add(new ArrayList<ArrayList<ArrayList<Recibo>>>());
			nsInformeFiscal.add(new Integer[12][]);
			for (int i = 0; i < 12; i++) {
				recibos.get(recibos.size() - 1).add(new ArrayList<ArrayList<Recibo>>());
				int diasMes = new GregorianCalendar(2019 + recibos.size(), i, 1).getActualMaximum(Calendar.DATE);
				nsInformeFiscal.get(nsInformeFiscal.size() - 1)[i] = new Integer[diasMes];
				for (int j = 0; j < diasMes; j++) {
					recibos.get(recibos.size() - 1).get(i).add(new ArrayList<Recibo>());
					nsInformeFiscal.get(nsInformeFiscal.size() - 1)[i][j] = null;
				}
			}
		}

	}
}
