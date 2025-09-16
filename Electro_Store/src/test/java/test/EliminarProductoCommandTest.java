package test;

import commands.EliminarProductoCommand;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import model.Inventario;
import model.Producto;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class EliminarProductoCommandTest {

    private InputStream originalIn;

    @AfterEach
    void tearDown() {
        if (originalIn != null) {
            System.setIn(originalIn);
        }
    }

    @Test
    void elimina_producto_existente() {
        Inventario inv = new Inventario();
        inv.agregarProducto(new Producto("X1", "Mouse", "", 9990, 5));
        EliminarProductoCommand cmd = new EliminarProductoCommand(inv);
        String input = "X1\n";
        originalIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        cmd.execute();
        assertNull(inv.obtenerPorCodigo("X1"));
    }
}
