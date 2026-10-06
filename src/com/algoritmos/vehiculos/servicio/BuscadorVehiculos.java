package com.algoritmos.vehiculos.servicio;

import com.algoritmos.vehiculos.modelo.Vehiculo;

/**
 * Servicio de algoritmos de búsqueda manual sobre colecciones de Vehículos.
 * Diseñado bajo las especificaciones de la rúbrica académica (sin métodos nativos de búsqueda).
 * Compatible con Java 17+.
 *
 * Funcionalidades:
 * 1. Búsqueda lineal exacta de placa: O(n).
 * 2. Búsqueda de todas las placas que inicien con determinado prefijo: O(n * k).
 * 3. Búsqueda binaria exacta de placa post-ordenamiento: O(log n).
 * 4. Validación estricta de precondiciones para búsqueda binaria.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public final class BuscadorVehiculos {

    private BuscadorVehiculos() {
        // Clase utilitaria con métodos estáticos
    }

    /**
     * Comprueba manualmente si una cadena 'texto' inicia con el prefijo 'prefijo',
     * ignorando mayúsculas y minúsculas carácter a carácter (sin depender de métodos mágicos).
     */
    public static boolean iniciaCon(String texto, String prefijo) {
        if (texto == null || prefijo == null) return false;
        if (prefijo.length() > texto.length()) return false;

        for (int i = 0; i < prefijo.length(); i++) {
            char c1 = Character.toUpperCase(texto.charAt(i));
            char c2 = Character.toUpperCase(prefijo.charAt(i));
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Búsqueda exacta de una placa utilizando recorrido secuencial lineal manual.
     * No requiere que el arreglo esté ordenado.
     *
     * Complejidad:
     * - Mejor caso: O(1) (si el vehículo está en la posición 0).
     * - Peor caso: O(n) (si no existe o está al final).
     * - Promedio: O(n/2).
     *
     * @param vehiculos   Arreglo de vehículos.
     * @param placaTarget Placa exacta a localizar.
     * @return Índice de la coincidencia en el arreglo, o -1 si no se encuentra.
     */
    public static int buscarIndicePorPlacaLineal(Vehiculo[] vehiculos, String placaTarget) {
        if (vehiculos == null || vehiculos.length == 0 || placaTarget == null) {
            return -1;
        }

        String targetNormalizado = Vehiculo.normalizarPlaca(placaTarget);
        for (int i = 0; i < vehiculos.length; i++) {
            if (vehiculos[i] != null && vehiculos[i].getPlaca().equalsIgnoreCase(targetNormalizado)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna la referencia al objeto Vehiculo encontrado mediante búsqueda lineal exacta.
     */
    public static Vehiculo buscarVehiculoPorPlacaLineal(Vehiculo[] vehiculos, String placaTarget) {
        int indice = buscarIndicePorPlacaLineal(vehiculos, placaTarget);
        return (indice != -1) ? vehiculos[indice] : null;
    }

    /**
     * Búsqueda parcial: Localiza todos los vehículos cuya placa comience por el prefijo dado.
     * Trabaja directamente sobre arreglos primitivos de objetos Vehiculo[], sin colecciones dinámicas.
     *
     * Algoritmo en dos pasadas:
     * 1. Primera pasada: Cuenta las coincidencias exactas para dimensionar el arreglo.
     * 2. Segunda pasada: Llena el nuevo arreglo con las referencias a los vehículos encontrados.
     *
     * Complejidad: O(n * k), donde n es el número de vehículos y k la longitud del prefijo.
     *
     * @param vehiculos Arreglo de vehículos.
     * @param prefijo   Cadena de caracteres iniciales a filtrar.
     * @return Nuevo arreglo Vehiculo[] con el tamaño exacto de coincidencias encontradas.
     */
    public static Vehiculo[] buscarPorPrefijo(Vehiculo[] vehiculos, String prefijo) {
        if (vehiculos == null || vehiculos.length == 0 || prefijo == null) {
            return new Vehiculo[0];
        }

        String prefijoLimpio = prefijo.trim();
        if (prefijoLimpio.isEmpty()) {
            return new Vehiculo[0];
        }

        // Paso 1: Conteo de coincidencias
        int totalCoincidencias = 0;
        for (Vehiculo v : vehiculos) {
            if (v != null && iniciaCon(v.getPlaca(), prefijoLimpio)) {
                totalCoincidencias++;
            }
        }

        if (totalCoincidencias == 0) {
            return new Vehiculo[0];
        }

        // Paso 2: Creación y llenado del arreglo resultado con tamaño exacto
        Vehiculo[] resultado = new Vehiculo[totalCoincidencias];
        int posicion = 0;
        for (Vehiculo v : vehiculos) {
            if (v != null && iniciaCon(v.getPlaca(), prefijoLimpio)) {
                resultado[posicion++] = v;
            }
        }

        return resultado;
    }

    /**
     * Búsqueda binaria manual de una placa sobre un arreglo de objetos Vehiculo.
     *
     * PRECONDICIÓN VITAL: El arreglo DEBE estar previamente ordenado de forma alfabética
     * creciente por placa. Si no cumple esta precondición, se produce una advertencia o
     * comportamiento indeterminado.
     *
     * Estrategia de Divide y Vencerás:
     * En cada paso descarta la mitad del espacio de búsqueda comparando lexicográficamente.
     *
     * Complejidad:
     * - Mejor caso: O(1) (si el objetivo coincide exactamente con el punto medio inicial).
     * - Peor caso: O(log n).
     * - Promedio: O(log n).
     *
     * @param vehiculos   Arreglo de vehículos previamente ordenado.
     * @param placaTarget Placa exacta a buscar.
     * @return Índice donde se localiza el vehículo, o -1 si no existe.
     */
    public static int buscarIndicePorPlacaBinaria(Vehiculo[] vehiculos, String placaTarget) {
        if (vehiculos == null || vehiculos.length == 0 || placaTarget == null) {
            return -1;
        }

        // Validación defensiva de la precondición
        if (!OrdenadorVehiculos.estaOrdenadoPorPlaca(vehiculos)) {
            System.err.println("[ADVERTENCIA - PRECONDICIÓN NO CUMPLIDA]: " +
                    "El arreglo de vehículos no está ordenado por placa. La búsqueda binaria requiere orden previo.");
        }

        String targetNormalizado = Vehiculo.normalizarPlaca(placaTarget);
        int lo = 0;
        int hi = vehiculos.length - 1;

        while (lo <= hi) {
            // Previene desbordamiento aritmético con respecto a (lo + hi) / 2
            int mid = lo + ((hi - lo) >>> 1);
            Vehiculo actual = vehiculos[mid];
            String placaActual = (actual != null) ? actual.getPlaca() : "";

            int cmp = placaActual.compareToIgnoreCase(targetNormalizado);

            if (cmp == 0) {
                return mid; // Elemento localizado
            }
            if (cmp < 0) {
                lo = mid + 1; // Descartar mitad izquierda
            } else {
                hi = mid - 1; // Descartar mitad derecha
            }
        }

        return -1; // No encontrado
    }

    /**
     * Retorna el objeto Vehiculo localizado mediante búsqueda binaria manual.
     */
    public static Vehiculo buscarVehiculoPorPlacaBinaria(Vehiculo[] vehiculos, String placaTarget) {
        int indice = buscarIndicePorPlacaBinaria(vehiculos, placaTarget);
        return (indice != -1) ? vehiculos[indice] : null;
    }
}
