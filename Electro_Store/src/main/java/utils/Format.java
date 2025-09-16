package utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utilidad para formatear valores (ej. precios).
 */
public final class Format {

    private static final NumberFormat CLP = NumberFormat.getCurrencyInstance(new Locale("es", "CL"));

    private Format() {
    }

    public static String precio(double v) {
        return CLP.format(v);
    }
}
