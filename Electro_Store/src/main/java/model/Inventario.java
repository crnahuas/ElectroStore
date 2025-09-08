package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Administración de productos en memoria.
 * <p>
 * Garantiza unicidad por código y provee operaciones de consulta.</p>
 */
public class Inventario {

    /**
     * Estructura de almacenamiento por código único.
     */
    private final Map<String, Producto> productos = new HashMap<>();

    /**
     * Agrega un producto si su código no existe en el inventario.
     *
     * @param producto
     * @return
     */
    public boolean agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto nulo.");
        }
        String codigo = producto.getCodigo();
        if (productos.containsKey(codigo)) {
            return false;
        }
        productos.put(codigo, producto);
        return true;
    }

    /**
     * Elimina un producto por código.
     *
     * @param codigo
     * @return
     */
    public boolean eliminarProducto(String codigo) {
        if (codigo == null) {
            return false;
        }
        return productos.remove(codigo) != null;
    }

    /**
     * Busca productos cuyo nombre contenga el término dado (case-insensitive).
     *
     * @param nombreParcial
     * @return
     */
    public List<Producto> buscarPorNombre(String nombreParcial) {
        if (nombreParcial == null) {
            return List.of();
        }
        String needle = nombreParcial.toLowerCase(Locale.ROOT);
        List<Producto> result = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getNombre().toLowerCase(Locale.ROOT).contains(needle)) {
                result.add(p);
            }
        }
        result.sort(Comparator.comparing(Producto::getNombre));
        return result;
    }

    /**
     * Busca productos cuya descripción contenga el término dado
     * (case-insensitive).
     *
     * @param descParcial
     * @return
     */
    public List<Producto> buscarPorDescripcion(String descParcial) {
        if (descParcial == null) {
            return List.of();
        }
        String needle = descParcial.toLowerCase(Locale.ROOT);
        List<Producto> result = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getDescripcion().toLowerCase(Locale.ROOT).contains(needle)) {
                result.add(p);
            }
        }
        result.sort(Comparator.comparing(Producto::getNombre));
        return result;
    }

    /**
     * Obtiene un producto por su código o null si no existe.
     *
     * @param codigo
     * @return
     */
    public Producto obtenerPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        return productos.get(codigo);
    }

    /**
     * Devuelve todos los productos ordenados por nombre.
     *
     * @return
     */
    public List<Producto> listarTodos() {
        List<Producto> list = new ArrayList<>(productos.values());
        list.sort(Comparator.comparing(Producto::getNombre));
        return list;
    }

    /**
     * Genera un informe textual del inventario.
     *
     * @return
     */
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("=== Informe Inventario ElectroStore ===\n");
        if (productos.isEmpty()) {
            sb.append("(Sin productos)\n");
        } else {
            for (Producto p : listarTodos()) {
                sb.append(p).append("\n");
            }
        }
        return sb.toString();
    }
}
