package commands;

import java.util.List;
import menu.Command;
import model.Inventario;
import model.Producto;

/**
 * Lista todos los productos del inventario ordenados por nombre.
 */
public class ListarProductosCommand implements Command {

    private final Inventario inventario;

    public ListarProductosCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Listar productos";
    }

    @Override
    public void execute() {
        List<Producto> list = inventario.listarTodos();
        if (list.isEmpty()) {
            System.out.println("Inventario vacío.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
