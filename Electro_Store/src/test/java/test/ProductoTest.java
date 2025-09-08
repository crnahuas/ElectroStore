package test;

import model.Producto;

// JUnit para pruebas
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void crearProductoValido() {
        Producto p = new Producto("P001", "Laptop", "15.6\" i5", 550000, 10);
        assertEquals("P001", p.getCodigo());
        assertEquals("Laptop", p.getNombre());
        assertEquals(550000, p.getPrecio());
        assertEquals(10, p.getCantidad());
    }

    @Test
    void debeFallarSiCodigoVacio() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto("  ", "Mouse", "", 9990, 5)
        );
        assertEquals("El código es obligatorio.", ex.getMessage());
    }

    @Test
    void debeFallarSiNombreVacio() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto("P002", "  ", "", 9990, 5)
        );
        assertEquals("El nombre es obligatorio.", ex.getMessage());
    }

    @Test
    void precioDebeSerMayorQueCero_enConstructor() {
        IllegalArgumentException ex1 = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto("P003", "Mouse", "", 0, 5)
        );
        assertEquals("El precio debe ser mayor a 0.", ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto("P004", "Mouse", "", -10, 5)
        );
        assertEquals("El precio debe ser mayor a 0.", ex2.getMessage());
    }

    @Test
    void cantidadNoPuedeSerNegativa_enConstructor() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Producto("P005", "Teclado", "", 9990, -1)
        );
        assertEquals("La cantidad no puede ser negativa.", ex.getMessage());
    }

    @Test
    void actualizarPrecioValidaRango() {
        Producto p = new Producto("P006", "Monitor", "24\"", 120000, 3);
        p.actualizarPrecio(150000);
        assertEquals(150000, p.getPrecio());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> p.actualizarPrecio(0)
        );
        assertEquals("El precio debe ser mayor a 0.", ex.getMessage());
    }

    @Test
    void setCantidadNoPermiteNegativos() {
        Producto p = new Producto("P007", "SSD", "NVMe", 45000, 1);
        p.setCantidad(0);
        assertEquals(0, p.getCantidad());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> p.setCantidad(-5)
        );
        assertEquals("La cantidad no puede ser negativa.", ex.getMessage());
    }
}
