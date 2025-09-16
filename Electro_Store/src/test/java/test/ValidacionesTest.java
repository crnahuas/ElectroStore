package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import utils.Validaciones;
import org.junit.jupiter.api.Test;

class ValidacionesTest {

    @Test
    void id_valido() {
        assertEquals("P001", Validaciones.id("P001", "código"));
        assertEquals("A-1", Validaciones.id("A-1", "código"));
        assertEquals("AB_cd", Validaciones.id(" AB_cd ", "código"));
    }

    @Test
    void id_obligatorio_y_formato() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.id("  ", "código"));
        assertEquals("El código es obligatorio.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.id("!", "código"));
        assertEquals("El código debe ser alfanumérico (2–20).", ex2.getMessage());
    }

    @Test
    void texto_obligatorio_y_largo() {
        assertEquals("Mouse", Validaciones.texto("Mouse", "nombre", 100));
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.texto(null, "nombre", 10));
        assertEquals("El nombre es obligatorio.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.texto("a".repeat(11), "nombre", 10));
        assertEquals("El nombre supera 10 caracteres.", ex2.getMessage());
    }

    @Test
    void precio_valido_y_fuera_de_rango() {
        assertEquals(19990, Validaciones.precio(19990));
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.precio(0));
        assertEquals("El precio debe ser mayor a 0.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.precio(1.0E11));
        assertEquals("Precio fuera de rango.", ex2.getMessage());
    }

    @Test
    void cantidad_valida_y_fuera_de_rango() {
        assertEquals(0, Validaciones.cantidad(0));
        assertEquals(3, Validaciones.cantidad(3));
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.cantidad(-1));
        assertEquals("La cantidad no puede ser negativa.", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class,
                () -> Validaciones.cantidad(2_000_000));
        assertEquals("Cantidad fuera de rango.", ex2.getMessage());
    }
}
