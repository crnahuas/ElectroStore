package commands;

import java.util.List;
import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/**
 * Busca productos por descripción (contiene; case-insensitive).
 */
public class BuscarPorDescripcionCommand implements Command {

    private final Inventario inventario;

    public BuscarPorDescripcionCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Buscar por descripción";
    }

    @Override
    public void execute() {
        String term = InputUtils.readNonEmpty("Descripción (o parte): ");
        List<Producto> list = inventario.buscarPorDescripcion(term);
        if (list.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            list.forEach(System.out::println);
        }
    }
}
