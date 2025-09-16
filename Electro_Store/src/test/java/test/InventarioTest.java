package test;

import java.util.List;
import model.Inventario;
import model.Producto;

// JUnit para pruebas
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class InventarioTest {

    private Inventario inv;

    @BeforeEach
    void setup() {
        inv = new Inventario();
    }

    @Test
    void agregar_y_evitar_duplicados() {
        assertTrue(inv.agregarProducto(new Producto("P001", "Laptop", "", 500000, 5)));
        assertFalse(inv.agregarProducto(new Producto("P001", "Laptop Pro", "", 800000, 2)));
    }

    @Test
    void eliminar_por_codigo() {
        inv.agregarProducto(new Producto("P002", "Mouse", "", 9990, 10));
        assertTrue(inv.eliminarProducto("P002"));
        assertFalse(inv.eliminarProducto("P002"));
        assertFalse(inv.eliminarProducto(null));
    }

    @Test
    void buscar_por_nombre_y_descripcion() {
        inv.agregarProducto(new Producto("P003", "Audifonos Sony", "BT", 39990, 5));
        inv.agregarProducto(new Producto("P004", "Teclado Mecanico", "RGB", 24990, 3));
        List<Producto> n = inv.buscarPorNombre("sony");
        assertEquals(1, n.size());
        assertEquals("P003", n.get(0).getCodigo());
        List<Producto> d = inv.buscarPorDescripcion("rgb");
        assertEquals(1, d.size());
        assertEquals("P004", d.get(0).getCodigo());
        assertTrue(inv.buscarPorNombre(null).isEmpty());
        assertTrue(inv.buscarPorDescripcion(null).isEmpty());
    }

    @Test
    void listar_ordenado_y_generar_informe() {
        inv.agregarProducto(new Producto("P009", "Zeta", "", 1, 1));
        inv.agregarProducto(new Producto("P008", "Alfa", "", 1, 1));
        List<Producto> lista = inv.listarTodos();
        assertEquals(2, lista.size());
        assertEquals("Alfa", lista.get(0).getNombre());
        assertEquals("Zeta", lista.get(1).getNombre());
        String informe = inv.generarInforme();
        assertTrue(informe.contains("Informe"));
        assertTrue(new Inventario().generarInforme().toLowerCase().contains("sin productos"));
    }
}
