package test;

import model.Producto;

// JUnit para pruebas
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void crear_producto_valido() {
        Producto p = new Producto("P001", "Laptop", "i5", 550000, 10);
        assertEquals("P001", p.getCodigo());
        assertEquals("Laptop", p.getNombre());
        assertEquals(550000, p.getPrecio());
        assertEquals(10, p.getCantidad());
    }

    @Test
    void validaciones_constructor() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> new Producto("", "X", "", 1000, 1));
        assertEquals("El código es obligatorio.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> new Producto("!", "X", "", 1000, 1));
        assertEquals("El código debe ser alfanumérico (2–20).", ex2.getMessage());
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P", "", "", 1000, 1));
        assertEquals("El nombre es obligatorio.", ex3.getMessage());
        IllegalArgumentException ex4 = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P1", "X", "", 0, 1));
        assertEquals("El precio debe ser mayor a 0.", ex4.getMessage());
        IllegalArgumentException ex5 = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P1", "X", "", -1, -3));
        assertEquals("El precio debe ser mayor a 0.", ex5.getMessage());
    }

    @Test
    void actualizar_precio_y_cantidad() {
        Producto p = new Producto("P002", "Mouse", "", 12990, 5);
        p.actualizarPrecio(14990);
        assertEquals(14990, p.getPrecio());
        p.setCantidad(7);
        assertEquals(7, p.getCantidad());
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> p.actualizarPrecio(0));
        assertEquals("El precio debe ser mayor a 0.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> p.setCantidad(-1));
        assertEquals("La cantidad no puede ser negativa.", ex2.getMessage());
    }
}
