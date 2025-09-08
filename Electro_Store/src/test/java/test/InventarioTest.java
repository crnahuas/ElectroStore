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
    void agregarProductoYEvitarDuplicados() {
        Producto p1 = new Producto("P001","Laptop","i5",500000,5);
        assertTrue(inv.agregarProducto(p1));

        Producto p2 = new Producto("P001","Laptop Pro","i7",800000,2);
        assertFalse(inv.agregarProducto(p2));
    }

    @Test
    void agregarProductoNull_lanzaExcepcionYValidaMensaje() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> inv.agregarProducto(null)
        );
        assertEquals("Producto nulo.", ex.getMessage());
    }

    @Test
    void eliminarProductoPorCodigo() {
        inv.agregarProducto(new Producto("P002","Mouse","",9990,10));
        assertTrue(inv.eliminarProducto("P002"));
        assertFalse(inv.eliminarProducto("P002"));
        assertFalse(inv.eliminarProducto(null));
    }

    @Test
    void buscarPorNombre() {
        inv.agregarProducto(new Producto("P003","Audifonos Sony","BT",39990,5));
        inv.agregarProducto(new Producto("P004","Teclado Mecanico","RGB",24990,3));

        List<Producto> res = inv.buscarPorNombre("sony");
        assertEquals(1, res.size());
        assertEquals("P003", res.get(0).getCodigo());

        assertTrue(inv.buscarPorNombre("inexistente").isEmpty());
        assertTrue(inv.buscarPorNombre(null).isEmpty());
    }

    @Test
    void buscarPorDescripcion() {
        inv.agregarProducto(new Producto("P005","USB-C Hub","6 puertos",19990,7));
        inv.agregarProducto(new Producto("P006","Cargador GaN","65W",32990,4));

        List<Producto> res = inv.buscarPorDescripcion("puertos");
        assertEquals(1, res.size());
        assertEquals("P005", res.get(0).getCodigo());

        assertTrue(inv.buscarPorDescripcion(null).isEmpty());
    }

    @Test
    void listarTodosDevuelveTodosOrdenados() {
        inv.agregarProducto(new Producto("P008","Zeta","",1,1));
        inv.agregarProducto(new Producto("P009","Alfa","",1,1));

        List<Producto> lista = inv.listarTodos();
        assertEquals(2, lista.size());
        assertEquals("Alfa", lista.get(0).getNombre());
        assertEquals("Zeta", lista.get(1).getNombre());
    }

    @Test
    void generarInforme_noFallaConInventarioVacio() {
        String informe = inv.generarInforme();
        assertTrue(informe.contains("Informe"));
        assertTrue(informe.contains("Sin productos") || informe.contains("(Sin productos)"));
    }
}
