package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utilidades para leer datos desde consola usando {@link BufferedReader}.
 * <p>
 * Centraliza validaciones y mensajes de error para entradas comunes.</p>
 */
public class InputUtils {

    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Lee una cadena no vacía.
     *
     * @param prompt
     * @return
     */
    public static String readNonEmpty(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = BR.readLine();
                if (s != null && !s.trim().isEmpty()) {
                    return s.trim();
                }
                System.out.println("Valor obligatorio. Intenta de nuevo.");
            } catch (IOException e) {
                System.out.println("Error de lectura. Intenta nuevamente.");
            }
        }
    }

    /**
     * Lee un double positivo.
     *
     * @param prompt
     * @return
     */
    public static double readPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = BR.readLine();
                double val = Double.parseDouble(s.replace(",", "."));
                if (val > 0) {
                    return val;
                }
                System.out.println("Debe ser un número > 0.");
            } catch (Exception e) {
                System.out.println("Ingresa un número válido (usa punto o coma).");
            }
        }
    }

    /**
     * Lee un entero no negativo.
     *
     * @param prompt
     * @return
     */
    public static int readNonNegativeInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = BR.readLine();
                int val = Integer.parseInt(s.trim());
                if (val >= 0) {
                    return val;
                }
                System.out.println("Debe ser un entero >= 0.");
            } catch (Exception e) {
                System.out.println("Ingresa un entero válido.");
            }
        }
    }

    /**
     * Lee una opción de menú dentro de un rango.
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public static int readMenuOption(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v >= min && v <= max) {
                return v;
            }
            System.out.printf("Elige una opción entre %d y %d.%n", min, max);
        }
    }

    /**
     * Lee un entero cualquiera.
     *
     * @param prompt
     * @return
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = BR.readLine();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.println("Ingresa un entero válido.");
            }
        }
    }

    /**
     * Lee una cadena opcional (si es null, retorna "").
     *
     * @param prompt
     * @return
     */
    public static String readOptional(String prompt) {
        try {
            System.out.print(prompt);
            String s = BR.readLine();
            return s == null ? "" : s.trim();
        } catch (IOException e) {
            return "";
        }
    }
}
