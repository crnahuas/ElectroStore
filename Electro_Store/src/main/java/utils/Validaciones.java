package utils;

/**
 * Utilidades de validación para entradas de usuario y atributos de dominio.
 */
public final class Validaciones {

    private Validaciones() {
    }

    public static String id(String value, String campo) {
        if (value == null || (value = value.trim()).isEmpty()) {
            throw new IllegalArgumentException("El " + campo + " es obligatorio.");
        }
        if (!value.matches("[A-Za-z0-9_-]{2,20}")) {
            throw new IllegalArgumentException("El " + campo + " debe ser alfanumérico (2–20).");
        }
        return value;
    }

    public static String texto(String value, String campo, int max) {
        if (value == null || (value = value.trim()).isEmpty()) {
            throw new IllegalArgumentException("El " + campo + " es obligatorio.");
        }
        if (value.length() > max) {
            throw new IllegalArgumentException("El " + campo + " supera " + max + " caracteres.");
        }
        return value;
    }

    public static double precio(double v) {
        if (v <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        if (v > 9_999_999_999d) {
            throw new IllegalArgumentException("Precio fuera de rango.");
        }
        return v;
    }

    public static int cantidad(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (v > 1_000_000) {
            throw new IllegalArgumentException("Cantidad fuera de rango.");
        }
        return v;
    }
}
