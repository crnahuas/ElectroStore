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
        String termino = InputUtils.readText("Nombre a buscar: ", 100, "nombre");
        List<Producto> resultados = inventario.buscarPorNombre(termino);
        if (resultados.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            resultados.forEach(System.out::println);
        }
    }
}
