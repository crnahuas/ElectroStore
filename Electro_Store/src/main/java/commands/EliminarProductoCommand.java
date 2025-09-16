package commands;

import menu.Command;
import model.Inventario;
import utils.InputUtils;

/**
 * Elimina un producto por código si existe.
 */
public class EliminarProductoCommand implements Command {

    private final Inventario inventario;

    public EliminarProductoCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Eliminar producto";
    }

    @Override
    public void execute() {
        String codigo = InputUtils.readId("Código a eliminar: ");
        boolean removed = inventario.eliminarProducto(codigo);
        System.out.println(removed ? "Producto eliminado." : "No existe un producto con ese código.");
    }
}
