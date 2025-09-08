package commands;

import menu.Command;
import model.Inventario;

/** Imprime un informe textual del inventario. */
public class GenerarInformeCommand implements Command {
    private final Inventario inventario;

    public GenerarInformeCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override public String label() { return "Generar informe"; }

    @Override public void execute() {
        System.out.println(inventario.generarInforme());
    }
}
