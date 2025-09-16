package electro_store;

import commands.ActualizarPrecioCommand;
import commands.AgregarProductoCommand;
import commands.BuscarPorDescripcionCommand;
import commands.BuscarPorNombreCommand;
import commands.EliminarProductoCommand;
import commands.GenerarInformeCommand;
import commands.ListarProductosCommand;
import java.util.ArrayList;
import java.util.List;
import menu.Command;
import model.Inventario;
import utils.InputUtils;

/**
 * Interfaz de consola: muestra menú y gestiona entrada.
 */
public class MenuPrincipal {

    private final Inventario inventario;
    private final List<Command> commands;

    public MenuPrincipal() {
        this.inventario = new Inventario();
        this.commands = new ArrayList<>();
        precargarEjemplos();
        cargarComandos();
    }

    public void iniciar() {
        System.out.println("===== ElectroStore (Inventario en Consola) =====");
        while (true) {
            mostrarMenu();
            int opt = InputUtils.readMenuOption("Opción: ", 0, commands.size());
            if (opt == 0) {
                System.out.println("¡Hasta pronto!");
                break;
            }
            try {
                commands.get(opt - 1).execute();
            } catch (Exception ex) {
                System.out.println("Se produjo un error: " + ex.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\nMenú:");
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d) %s%n", (i + 1), commands.get(i).label());
        }
        System.out.println("0) Salir");
    }

    private void cargarComandos() {
        commands.add(new AgregarProductoCommand(inventario));
        commands.add(new EliminarProductoCommand(inventario));
        commands.add(new BuscarPorNombreCommand(inventario));
        commands.add(new BuscarPorDescripcionCommand(inventario));
        commands.add(new ActualizarPrecioCommand(inventario));
        commands.add(new ListarProductosCommand(inventario));
        commands.add(new GenerarInformeCommand(inventario));
    }

    private void precargarEjemplos() {
        inventario.agregarProducto(new model.Producto("P001", "Laptop Lenovo", "Notebook 15.6\" i5", 550000, 10));
        inventario.agregarProducto(new model.Producto("P002", "Audífonos Sony WH-CH520", "Bluetooth on-ear", 39990, 25));
        inventario.agregarProducto(new model.Producto("P003", "Mouse Logitech M185", "Inalámbrico", 12990, 40));
    }
}
