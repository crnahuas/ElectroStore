package commands;

import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/**
 * Agrega un producto al inventario verificando unicidad del código.
 */
public class AgregarProductoCommand implements Command {

    private final Inventario inventario;

    public AgregarProductoCommand(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String label() {
        return "Agregar producto";
    }

    @Override
    public void execute() {
        String codigo = InputUtils.readNonEmpty("Código único: ");
        if (inventario.obtenerPorCodigo(codigo) != null) {
            System.out.println("Ya existe un producto con ese código.");
            return;
        }
        String nombre = InputUtils.readNonEmpty("Nombre: ");
        String desc = InputUtils.readOptional("Descripción (opcional): ");
        double precio = InputUtils.readPositiveDouble("Precio (>0): ");
        int cantidad = InputUtils.readNonNegativeInt("Cantidad (>=0): ");

        try {
            Producto p = new Producto(codigo, nombre, desc, precio, cantidad);
            boolean ok = inventario.agregarProducto(p);
            System.out.println(ok ? "Producto agregado." : "No se pudo agregar (duplicado).");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
