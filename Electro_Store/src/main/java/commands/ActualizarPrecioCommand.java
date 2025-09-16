package commands;

import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/**
 * Actualiza el precio de un producto existente.
 */
public class ActualizarPrecioCommand implements Command {

    private final Inventario inventario;

    public ActualizarPrecioCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Actualizar precio";
    }

    @Override
    public void execute() {
        String codigo = InputUtils.readId("Código del producto a actualizar: ");
        Producto p = inventario.obtenerPorCodigo(codigo);
        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        double nuevoPrecio = InputUtils.readPrecio("Nuevo precio (>0): ");
        try {
            p.actualizarPrecio(nuevoPrecio);
            System.out.println("Precio actualizado: " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
