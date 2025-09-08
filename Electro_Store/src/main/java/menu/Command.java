package menu;

/**
 * Contrato del patrón Command para acciones de menú. Cada implementación
 * representa una opción ejecutable.
 */
public interface Command {

    /**
     * @return etiqueta que se muestra en el menú.
     */
    String label();

    /**
     * Ejecuta la acción del comando.
     */
    void execute();
}
