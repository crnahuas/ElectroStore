package test;

import commands.AgregarProductoCommand;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import model.Inventario;
import model.Producto;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class AgregarProductoCommandTest {

    private InputStream originalIn;

    @AfterEach
    void tearDown() {
        if (originalIn != null) {
            System.setIn(originalIn);
        }
    }

    @Test
    void agrega_producto_via_consola_simulada() {
        Inventario inv = new Inventario();
        AgregarProductoCommand cmd = new AgregarProductoCommand(inv);
        String input = String.join("\n", "P100", "Parlante JBL", "Bluetooth", "45990", "12") + "\n";
        originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        cmd.execute();
        Producto p = inv.obtenerPorCodigo("P100");
        assertNotNull(p);
        assertEquals("Parlante JBL", p.getNombre());
        assertEquals(45990, p.getPrecio());
        assertEquals(12, p.getCantidad());
    }
}
