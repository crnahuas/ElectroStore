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
        String termino = InputUtils.readText("Descripción a buscar: ", 255, "descripción");
        List<Producto> resultados = inventario.buscarPorDescripcion(termino);
        if (resultados.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            resultados.forEach(System.out::println);
        }
    }
}
