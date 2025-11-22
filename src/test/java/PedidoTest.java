import org.example.modelo.Producto;
import org.example.modelo.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    private Pedido pedido;
    private Producto prodActivo;
    private Producto prodInactivo;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido();
        prodActivo = new Producto("Sku-001", "Laptop", 1200.50, 10, "Electronica", true, true);
        prodInactivo = new Producto("Sku-002", "Teclado", 25.0, 5, "Electronica", false, false);
    }

    @Test
    @DisplayName("agregarProducto - cantidad no valida")
    public void testAgregarProductoCantidadNoValida() {
        assertFalse(pedido.agregarProducto(prodActivo, 0));
        assertFalse(pedido.agregarProducto(prodActivo, -3));
    }

    @Test
    @DisplayName("agregarProducto - duplicado por sku")
    public void testAgregarProductoDuplicadoPorSku() {
        assertTrue(pedido.agregarProducto(prodActivo, 2));
        assertFalse(pedido.agregarProducto(prodActivo, 1));
    }

    @Test
    @DisplayName("agregarProducto - agregado correcto en detallesPedido")
    public void testAgregarProductoCorrecto() {
        boolean r = pedido.agregarProducto(prodActivo, 3);
        assertTrue(r);
        assertEquals(1, pedido.getDetallesPedido().size());
        Producto linea = pedido.getDetallesPedido().get(0);
        assertEquals(prodActivo.getSku(), linea.getSku());
        assertEquals(3, linea.getCantidad());
    }

    @Test
    @DisplayName("agregarProducto - preserva los atributos del producto en la linea agregada")
    public void testAgregarProductoPreservaAtributos() {
        boolean r = pedido.agregarProducto(prodActivo, 4);
        assertTrue(r);
        Producto linea = pedido.getDetallesPedido().get(0);
        assertEquals(prodActivo.getNombre(), linea.getNombre());
        assertEquals(prodActivo.getPrecio(), linea.getPrecio());
        assertEquals(prodActivo.getSku(), linea.getSku());
        assertEquals(prodActivo.getCategoria(), linea.getCategoria());
        assertEquals(prodActivo.isEsActivo(), linea.isEsActivo());
        assertEquals(prodActivo.isDescuentoAplicable(), linea.isDescuentoAplicable());
        assertEquals(4, linea.getCantidad());
    }

    @Test
    @DisplayName("agregarProducto - no se permite roducto inactivo")
    public void testAgregarProductoInactivo() {
        assertFalse(pedido.agregarProducto(prodInactivo, 2));
    }

    @Test
    @DisplayName("validarStock - lista vacía -> true")
    public void testValidarStockListaVacia() {
        assertTrue(pedido.validarStock());
    }

    @Test
    @DisplayName("validarStock: todas las cantidades mayor a 0 -> true")
    public void testValidarStockTodasValidas() {
        pedido.agregarProducto(prodActivo, 1);
        Producto otro = new Producto("Sku-003", "Mouse", 15.0, 999, "Electronica", true, false);
        pedido.agregarProducto(otro, 999);
        assertTrue(pedido.validarStock());
    }

    @Test
    @DisplayName("validarStock - existe cantidad 0 -> false")
    public void testValidarStockCantidadCero() {
        Producto conCero = new Producto("Sku-004", "Cables", 5.0, 0, "Accesorios", true, false);
        assertFalse(pedido.agregarProducto(conCero, 0));
        assertTrue(pedido.validarStock());
    }

    @Test
    @DisplayName("validarStock - cantidad negativa no permitida por constructor -> lanza excepcion")
    public void testValidarStockCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("Sku-000", "e", 1.0, -5));
    }

    @Test
    @DisplayName("validarStock - valores limite -> true")
    public void testValidarStockValoresLimite() {
        Producto a = new Producto("Sku-010", "ItemA", 1.0, 1);
        Producto b = new Producto("Sku-011", "ItemB", 2.0, 999);
        assertTrue(pedido.agregarProducto(a, 1));
        assertTrue(pedido.agregarProducto(b, 999));
        assertTrue(pedido.validarStock());
    }
}
