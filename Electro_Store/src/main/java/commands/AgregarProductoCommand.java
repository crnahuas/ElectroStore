package commands;

import menu.Command;
import model.Inventario;
import model.Producto;
import utils.InputUtils;

/**
 * Agrega un producto validando unicidad de código.
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
        String codigo = InputUtils.readId("Código único: ");
        if (inventario.obtenerPorCodigo(codigo) != null) {
            System.out.println("Ya existe un producto con ese código.");
            return;
        }
        String nombre = InputUtils.readText("Nombre: ", 100, "nombre");
        String desc = InputUtils.readText("Descripción (máx 255, vacío permitido): ", 255, "descripción");
        double precio = InputUtils.readPrecio("Precio (>0): ");
        int cantidad = InputUtils.readCantidad("Cantidad (>=0): ");
        try {
            Producto p = new Producto(codigo, nombre, desc, precio, cantidad);
            boolean ok = inventario.agregarProducto(p);
            System.out.println(ok ? "Producto agregado." : "No se pudo agregar (duplicado).");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
