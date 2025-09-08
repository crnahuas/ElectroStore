package model;

/**
 * Representa un artículo electrónico de la tienda.
 * <p>
 * Reglas de negocio:
 * <ul>
 * <li><b>codigo</b> no puede ser nulo ni vacío.</li>
 * <li><b>nombre</b> no puede ser nulo ni vacío.</li>
 * <li><b>precio</b> debe ser &gt; 0.</li>
 * <li><b>cantidad</b> debe ser &ge; 0.</li>
 * </ul>
 * Ejemplo de uso:
 * <pre>{@code
 * Producto p = new Producto("P001","Laptop","15.6\" i5", 550000, 10);
 * p.actualizarPrecio(579990);
 * p.setCantidad(12);
 * }</pre>
 */
public class Producto {

    /**
     * Clave única inmutable.
     */
    private final String codigo;
    /**
     * Nombre comercial del producto.
     */
    private String nombre;
    /**
     * Descripción corta (opcional).
     */
    private String descripcion;
    /**
     * Precio de venta; debe ser &gt; 0.
     */
    private double precio;
    /**
     * Stock disponible; debe ser &ge; 0.
     */
    private int cantidad;

    /**
     * Crea un nuevo producto validando los atributos obligatorios y sus rangos.
     */
    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidad) {
        if (isBlank(codigo)) {
            throw new IllegalArgumentException("El código es obligatorio.");
        }
        if (isBlank(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.descripcion = descripcion == null ? "" : descripcion.trim();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters
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

    /**
     * Actualiza el nombre validando que no sea vacío.
     */
    public void setNombre(String nombre) {
        if (isBlank(nombre)) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Actualiza la descripción (si es null se convierte a "").
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? "" : descripcion.trim();
    }

    /**
     * Cambia el precio validando que sea &gt; 0.
     */
    public void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.precio = nuevoPrecio;
    }

    /**
     * Cambia la cantidad en stock validando que sea &ge; 0.
     */
    public void setCantidad(int nuevaCantidad) {
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        this.cantidad = nuevaCantidad;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | $%,.0f | Stock: %d",
                codigo, nombre, descripcion, precio, cantidad);
    }
}
