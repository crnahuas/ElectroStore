package commands;

import java.util.List;
import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/**
 * Busca productos por nombre (contiene; case-insensitive).
 */
public class BuscarPorNombreCommand implements Command {

    private final Inventario inventario;

    public BuscarPorNombreCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Buscar por nombre";
    }

    @Override
    public void execute() {
        String term = InputUtils.readNonEmpty("Nombre (o parte): ");
        List<Producto> list = inventario.buscarPorNombre(term);
        if (list.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
