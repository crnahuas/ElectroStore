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
import model.Producto;
import utils.InputUtils;

/**
 * Punto de entrada de ElectroStore (consola).
 * <p>Construye el menú a partir de comandos concretos (patrón Command) y
 * orquesta el flujo de interacción con el usuario.</p>
 */
public class Electro_Store {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        precargarEjemplos(inventario);

        List<Command> commands = new ArrayList<>();
        commands.add(new AgregarProductoCommand(inventario));
        commands.add(new EliminarProductoCommand(inventario));
        commands.add(new BuscarPorNombreCommand(inventario));
        commands.add(new BuscarPorDescripcionCommand(inventario));
        commands.add(new ListarProductosCommand(inventario));
        commands.add(new GenerarInformeCommand(inventario));
        commands.add(new ActualizarPrecioCommand(inventario));

        System.out.println("===== ElectroStore (Inventario en Consola) =====");
        while (true) {
            System.out.println("\nMenú:");
            for (int i = 0; i < commands.size(); i++) {
                System.out.printf("%d) %s%n", (i + 1), commands.get(i).label());
            }
            System.out.println("0) Salir");
            int opt = InputUtils.readMenuOption("Opción: ", 0, commands.size());
            if (opt == 0) { System.out.println("¡Hasta pronto!"); break; }
            try { commands.get(opt - 1).execute(); }
            catch (Exception ex) { System.out.println("Se produjo un error: " + ex.getMessage()); }
        }
    }

    /** Precarga algunos productos de ejemplo. */
    private static void precargarEjemplos(Inventario inv) {
        inv.agregarProducto(new Producto("P001", "Laptop Lenovo", "Notebook 15.6\" i5", 550000, 10));
        inv.agregarProducto(new Producto("P002", "Audífonos Sony WH-CH520", "Bluetooth on-ear", 39990, 25));
        inv.agregarProducto(new Producto("P003", "Mouse Logitech M185", "Inalámbrico", 12990, 40));
    }
}
