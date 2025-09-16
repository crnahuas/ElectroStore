package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Métodos utilitarios para leer y validar entradas desde consola.
 */
public final class InputUtils {

    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    private InputUtils() {
    }

    public static String readId(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Validaciones.id(BR.readLine(), "código");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String readText(String prompt, int max, String campo) {
        while (true) {
            try {
                System.out.print(prompt);
                return Validaciones.texto(BR.readLine(), campo, max);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double readPrecio(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = BR.readLine().replace(',', '.');
                return Validaciones.precio(Double.parseDouble(s));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int readCantidad(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Validaciones.cantidad(Integer.parseInt(BR.readLine()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int readMenuOption(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(BR.readLine());
                if (val < min || val > max) {
                    throw new IllegalArgumentException("Fuera de rango.");
                }
                return val;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
