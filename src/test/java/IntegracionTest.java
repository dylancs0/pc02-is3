import org.example.modelo.Producto;
import org.example.modelo.Pedido;
import org.example.service.ServicioX;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    @Test
    @DisplayName("Flujo exitoso")
    public void testFlujoExitoso() {
        Pedido pedido = new Pedido();
        Producto p1 = new Producto("Sku-I1", "Smartphone", 500.0, 5, "Electronica", true, true);
        assertTrue(pedido.agregarProducto(p1, 2));
        assertTrue(pedido.validarStock());
        assertTrue(ServicioX.validarDescuentoAplicable(p1, 10.0));
    }

    @Test
    @DisplayName("Error por duplicado")
    public void testErrorPorDuplicado() {
        Pedido pedido = new Pedido();
        Producto p1 = new Producto("Sku-I2", "Tablet", 300.0, 3, "Electronica", true, true);
        assertTrue(pedido.agregarProducto(p1, 1));
        assertFalse(pedido.agregarProducto(p1, 1));
        assertEquals(1, pedido.getDetallesPedido().size());
        assertTrue(pedido.validarStock());
    }

    @Test
    @DisplayName("Stock invalido")
    public void testStockInvalidoMasServicio() {
        Pedido pedido = new Pedido();
        Producto valido = new Producto("Sku-I3", "Audifonos", 50.0, 2, "Audio", true, true);
        Producto invalido = new Producto("Sku-I4", "Microfono", 30.0, 0, "Audio", true, false);
        assertTrue(pedido.agregarProducto(valido, 2));
        assertFalse(pedido.agregarProducto(invalido, 0));
        assertTrue(pedido.validarStock());
        assertTrue(ServicioX.validarDescuentoAplicable(valido, 15.0));
    }
}