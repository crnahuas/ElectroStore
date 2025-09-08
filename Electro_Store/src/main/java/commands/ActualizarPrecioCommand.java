package commands;

import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/** Actualiza el precio de un producto existente. */
public class ActualizarPrecioCommand implements Command {
    private final Inventario inventario;

    public ActualizarPrecioCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override public String label() { return "Actualizar precio"; }

    @Override public void execute() {
        String codigo = InputUtils.readNonEmpty("Código del producto: ");
        Producto p = inventario.obtenerPorCodigo(codigo);
        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        double nuevo = InputUtils.readPositiveDouble("Nuevo precio (>0): ");
        try {
            p.actualizarPrecio(nuevo);
            System.out.println("Precio actualizado: " + p);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
