package com.algoritmos.vehiculos.servicio;

import com.algoritmos.vehiculos.modelo.Vehiculo;

/**
 * Servicio de ordenamiento manual para arreglos de objetos Vehiculo.
 * Implementa ordenamiento alfabético manual por placa sin depender de Arrays.sort().
 * Compatible con Java 17+.
 *
 * Algoritmo implementado: Quicksort clásico manual adaptado a objetos Vehiculo.
 * Complejidad: O(n log n) caso promedio / mejor caso, O(n^2) peor caso.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public final class OrdenadorVehiculos {

    private OrdenadorVehiculos() {
        // Constructor privado para clase utilitaria
    }

    /**
     * Verifica formalmente si un arreglo de vehículos se encuentra ordenado
     * alfabéticamente en orden no decreciente por su placa.
     * Esta es la PRECONDICIÓN indispensable para poder ejecutar Búsqueda Binaria.
     */
    public static boolean estaOrdenadoPorPlaca(Vehiculo[] vehiculos) {
        if (vehiculos == null || vehiculos.length <= 1) {
            return true;
        }
        for (int i = 0; i < vehiculos.length - 1; i++) {
            if (vehiculos[i] == null || vehiculos[i + 1] == null) {
                continue;
            }
            if (vehiculos[i].getPlaca().compareToIgnoreCase(vehiculos[i + 1].getPlaca()) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ordena un arreglo de vehículos alfabéticamente por placa usando el algoritmo
     * manual Quicksort (Divide y Vencerás).
     *
     * @param vehiculos Arreglo de vehículos a ordenar in-place.
     */
    public static void ordenarPorPlaca(Vehiculo[] vehiculos) {
        if (vehiculos == null || vehiculos.length <= 1) {
            return;
        }
        quicksort(vehiculos, 0, vehiculos.length - 1);
    }

    /**
     * Procedimiento recursivo de Quicksort.
     */
    private static void quicksort(Vehiculo[] arr, int lo, int hi) {
        if (lo < hi) {
            int p = particion(arr, lo, hi);
            quicksort(arr, lo, p - 1);
            quicksort(arr, p + 1, hi);
        }
    }

    /**
     * Esquema de partición de Lomuto para comparar alfabéticamente por placa.
     */
    private static int particion(Vehiculo[] arr, int lo, int hi) {
        Vehiculo pivote = arr[hi];
        String placaPivote = (pivote != null) ? pivote.getPlaca() : "";
        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            String placaActual = (arr[j] != null) ? arr[j].getPlaca() : "";
            // Comparación lexicográfica: si arr[j] <= pivote
            if (placaActual.compareToIgnoreCase(placaPivote) <= 0) {
                i++;
                intercambiar(arr, i, j);
            }
        }
        intercambiar(arr, i + 1, hi);
        return i + 1;
    }

    /**
     * Intercambia dos elementos en el arreglo.
     */
    private static void intercambiar(Vehiculo[] arr, int i, int j) {
        Vehiculo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
