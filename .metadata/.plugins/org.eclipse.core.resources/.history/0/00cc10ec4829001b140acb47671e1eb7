package software.cafeteria.pruebas;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import software.cafeteria.entidades.Empresa;
import software.cafeteria.entidades.InformeFiscal;
import software.cafeteria.entidades.Producto;
import software.cafeteria.entidades.ProductosInventario;
import software.cafeteria.entidades.ProductosVentas;
import software.cafeteria.entidades.Recibo;
import software.cafeteria.entidades.RegistroDeVentas;
import software.cafeteria.logica.Inventario;

public class PruebasUnitarias {

	Inventario inventario = new Inventario();

	@Test
	public void crearEmpresa() {
		Empresa a = new Empresa("coca-cola");
		Assert.assertEquals("coca-cola", a.getNombre());
	}

	@Test
	public void clonarProducto() {
		Producto a = new Producto("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19, 800, 1000);
		Producto b = a.clone();
		Assert.assertEquals(a.getCodigoDeBarras(), b.getCodigoDeBarras());
		Assert.assertEquals(a.getNombre(), b.getNombre());
		Assert.assertEquals(a.getEmpresa(), b.getEmpresa());
		Assert.assertEquals(a.getPresentacion(), b.getPresentacion());
		Assert.assertEquals(a.getIva(), b.getIva());
		Assert.assertEquals(a.getCosto(), b.getCosto());
		Assert.assertEquals(a.getPrecio(), b.getPrecio());
		Assert.assertNotEquals(a, b);
	}

	@Test
	public void agregarCantidadProductoV() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		ProductosVentas b = new ProductosVentas(a, 5);
		b.restarCantidad(1);
		Assert.assertEquals(4, b.getCantidad());
	}

	@Test
	public void restarCantidadProductoV() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		ProductosVentas b = new ProductosVentas(a, 5);
		b.agregarCantidad(1);
		Assert.assertEquals(6, b.getCantidad());
	}

	@Test
	public void compararProductoV1() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		ProductosVentas b = new ProductosVentas(a, 5);
		Assert.assertEquals(0, b.CompareTo("coca-cola personal"));
	}

	@Test
	public void compararProductoV2() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		ProductosVentas b = new ProductosVentas(a, 5);
		Assert.assertEquals(0, b.CompareTo(b));
	}

	@Test
	public void calcularPrecioTotalRecibo() {
		Recibo c = new Recibo(true);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 2);
		Assert.assertEquals(10000, c.getPrecioTotal());
	}

	@Test
	public void calcularCambioTotalReciboTarjeta() {
		Recibo c = new Recibo(true);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 4);
		Assert.assertEquals(0, c.getCambio());
	}

	@Test
	public void calcularCambioTotalReciboEfectivo() {
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 4);
		c.setEfectivoRegistrado(20000);
		Assert.assertEquals(5000, c.getCambio());
	}

	@Test
	public void totalIvaRecibo() {
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 4);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Bebida", 1500);
		c.agregarProductos(a, 5);
		int valor = c.getTotalIvaRecibo()[0][1];
		Assert.assertEquals(0, valor);
		valor = c.getTotalIvaRecibo()[1][1];
		Assert.assertEquals(((190 * 5) + (475 * 4)), valor);
	}

	@Test
	public void totalIvaGanancia() {
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 4);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Snacks", 1500);
		c.agregarProductos(a, 5);
		int valor = c.getTotalGananciaRecibo()[0][1];
		Assert.assertEquals((1500 * 5), valor);
		valor = c.getTotalGananciaRecibo()[1][1];
		Assert.assertEquals((810 * 5) + (2025 * 4), valor);
	}

	@Test
	public void totalDeLaCaja() {
		ArrayList<Recibo> b = new ArrayList<Recibo>();
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("346", "caracol coma rico", new Empresa("quinvalle"), 20, 5, 800, 40, "Snacks",
				1100);
		c.agregarProductos(a, 5);
		b.add(c);
		c = new Recibo(false);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 2);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Snacks", 1500);
		c.agregarProductos(a, 10);
		b.add(c);
		InformeFiscal d = new InformeFiscal(123, b);
		Assert.assertEquals((5000 + 5500 + 5000 + 15000), d.getTotalEnCaja());
	}

	@Test
	public void totalDeIvaIF() {
		ArrayList<Recibo> b = new ArrayList<Recibo>();
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("346", "caracol coma rico", new Empresa("quinvalle"), 20, 5, 800, 40, "Snacks",
				1100);
		c.agregarProductos(a, 5);
		b.add(c);
		c = new Recibo(false);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 2);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Snacks", 1500);
		c.agregarProductos(a, 5);
		b.add(c);
		InformeFiscal d = new InformeFiscal(123, b);

		Assert.assertEquals(((5000 * 19 / 100) + (5500 * 5 / 100) + (5000 * 19 / 100) + 0), d.getTotalIva());
	}

	@Test
	public void totalDeGananciaIF() {
		ArrayList<Recibo> b = new ArrayList<Recibo>();
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("346", "caracol coma rico", new Empresa("quinvalle"), 20, 5, 800, 40, "Snacks",
				1100);
		c.agregarProductos(a, 5);
		b.add(c);
		c = new Recibo(false);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		c.agregarProductos(a, 2);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Snacks", 1500);
		c.agregarProductos(a, 5);
		b.add(c);
		InformeFiscal d = new InformeFiscal(123, b);
		Assert.assertEquals((d.getTotalEnCaja() - d.getTotalIva()), d.getTotalGanancia());
	}

	@Test
	public void primerRecibo() {
		ArrayList<Recibo> b = new ArrayList<Recibo>();
		Recibo c = new Recibo(false);
		c.setId(1);
		b.add(c);
		c = new Recibo(false);
		c.setId(2);
		b.add(c);
		c = new Recibo(false);
		c.setId(3);
		b.add(c);
		c = new Recibo(false);
		c.setId(4);
		b.add(c);
		c = new Recibo(false);
		c.setId(5);
		b.add(c);
		InformeFiscal d = new InformeFiscal(123, b);
		Assert.assertEquals("1", d.numeroPrimerRecibo());
	}

	@Test
	public void ultimoRecibo() {
		ArrayList<Recibo> b = new ArrayList<Recibo>();
		Recibo c = new Recibo(false);
		c.setId(1);
		b.add(c);
		c = new Recibo(false);
		c.setId(2);
		b.add(c);
		c = new Recibo(false);
		c.setId(3);
		b.add(c);
		c = new Recibo(false);
		c.setId(4);
		b.add(c);
		c = new Recibo(false);
		c.setId(5);
		b.add(c);
		InformeFiscal d = new InformeFiscal(123, b);
		Assert.assertEquals("5", d.numeroUltimoRecibo());
	}

	@Test
	public void agregarCantidadProductoI() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		a.agregarAlInventario(10);
		Assert.assertEquals(50, a.getCantidad());
	}

	@Test
	public void restarCantidadProductoI() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		a.restarAlInventario(10);
		Assert.assertEquals(30, a.getCantidad());
	}

	@Test
	public void compararProductoI1() {
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		Assert.assertEquals(0, a.CompareTo("coca-cola personal"));
	}

	public void compararProductoI2() {
		Inventario e = new Inventario();
		RegistroDeVentas f = new RegistroDeVentas(0, 0);
		Recibo c = new Recibo(false);
		ProductosInventario a = new ProductosInventario("123", "coca-cola personal", new Empresa("coca-cola"), 20, 19,
				800, 40, "Bebida", 1000);
		e.agregarAlInventario(a, 2);
		c.agregarProductos(a, 5);
		a = new ProductosInventario("346", "caracol coma rico", new Empresa("quinvalle"), 20, 5, 800, 40, "Snacks",
				1100);
		e.agregarAlInventario(a, 2);
		c.agregarProductos(a, 5);
		f.adjuntarUnRecibo(c, e);
		c = new Recibo(false);
		a = new ProductosInventario("1234", "coca-cola 2L", new Empresa("coca-cola"), 20, 19, 2300, 40, "Bebida", 2500);
		e.agregarAlInventario(a, 2);
		c.agregarProductos(a, 2);
		a = new ProductosInventario("234", "Gusti Papa", new Empresa("fried"), 20, 0, 1000, 40, "Snacks", 1500);
		e.agregarAlInventario(a, 2);
		c.agregarProductos(a, 5);
		System.out.println(e.getProductosI().get(0).getCodigoDeBarras());
		f.adjuntarUnRecibo(c, e);

		Assert.assertEquals(2, f.getRecibos().size());
	}

	@Test
	public void agregarProductoTest() {

		ProductosInventario producto = new ProductosInventario("7702004002419", "AGUILA ZERO BOTELLA RETORNABLE",
				new Empresa("BAVARIA"), 30, 19, 48800, 20, "Licor", 2500);

		Assert.assertTrue((inventario.agregarProducto(producto)));

	}

	@Test
	public void agregarProductoTest2() {
		String codigoBarras = "7702004002419";
		String nombre = "AGUILA ZERO BOTELLA RETORNABLE";
		Empresa empresa = new Empresa("Bavaria");
		int presentacion = 30;
		int iva = 19;
		int costo = 48800;
		int cantidad = 10;
		String tipo = "Licor";
		int precio = 2500;
		ProductosInventario producto = new ProductosInventario(codigoBarras, nombre, empresa, presentacion, iva, costo,
				cantidad, tipo, precio);
		inventario.agregarProducto(producto);

		codigoBarras = "7702004002419";
		nombre = "AGUILA ZERO BOTELLA RETORNABLE";
		empresa = new Empresa("Bavaria");
		presentacion = 30;
		iva = 19;
		costo = 48800;
		cantidad = 10;
		tipo = "Licor";
		precio = 2500;

		Assert.assertFalse(inventario.agregarProducto(codigoBarras, nombre, empresa, presentacion, iva, costo, cantidad,
				tipo, precio));
	}
	
	

	@Test
	public void verificarExistenciaProductoTest() {

	}
	
	

}
