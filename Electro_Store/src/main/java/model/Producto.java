package model;

import utils.Format;
import utils.Validaciones;

/**
 * Entidad de dominio que representa un producto en el inventario. Invariantes:
 * código y nombre obligatorios, precio > 0, cantidad >= 0.
 */
public class Producto {

    private final String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidad) {
        this.codigo = Validaciones.id(codigo, "código");
        this.nombre = Validaciones.texto(nombre, "nombre", 100);
        this.descripcion = (descripcion == null) ? "" : descripcion.trim();
        this.precio = Validaciones.precio(precio);
        this.cantidad = Validaciones.cantidad(cantidad);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void actualizarPrecio(double nuevo) {
        this.precio = Validaciones.precio(nuevo);
    }

    public void setCantidad(int nueva) {
        this.cantidad = Validaciones.cantidad(nueva);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s | Stock: %d",
                codigo, nombre, descripcion, Format.precio(precio), cantidad);
    }
}
