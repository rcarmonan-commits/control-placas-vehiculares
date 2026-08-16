package com.algoritmos.taller;

import java.util.Comparator;

/**
 * Biblioteca de algoritmos de búsqueda sobre arreglos unidimensionales (1D).
 * Compatible con Java 17+.
 *
 * Implementaciones base del Taller de Algoritmos de Búsqueda:
 * - Búsqueda lineal (primera, última y todas las ocurrencias).
 * - Búsqueda lineal con centinela.
 * - Búsqueda binaria iterativa y recursiva.
 * - Variantes lowerBound y lastIndexOfSorted.
 * - Búsqueda binaria genérica con Comparator.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public final class ArraySearches {

    private ArraySearches() {
        // Constructor privado para evitar instanciación (clase utilitaria)
    }

    /**
     * Búsqueda lineal clásica: retorna el índice de la primera aparición o -1 si no existe.
     * Complejidad: O(n) peor caso, O(1) mejor caso.
     */
    public static int linearSearch(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) return i;
        }
        return -1;
    }

    /**
     * Retorna todas las ocurrencias del objetivo en un nuevo arreglo de índices.
     * Realiza un conteo previo para dimensionar exactamente el arreglo resultante.
     */
    public static int[] linearSearchAll(int[] a, int target) {
        if (a == null || a.length == 0) return new int[0];
        int count = 0;
        for (int v : a) {
            if (v == target) count++;
        }
        int[] idx = new int[count];
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                idx[k++] = i;
            }
        }
        return idx;
    }

    /**
     * Búsqueda lineal desde el final: retorna el índice de la última aparición o -1.
     */
    public static int linearSearchLast(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == target) return i;
        }
        return -1;
    }

    /**
     * Búsqueda lineal con centinela.
     * Coloca temporalmente el objetivo en la última posición para eliminar
     * la comprobación de límites del bucle en cada iteración.
     */
    public static int linearSearchSentinel(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        int last = a[a.length - 1];
        a[a.length - 1] = target; // Centinela colocado
        int i = 0;
        while (a[i] != target) {
            i++;
        }
        a[a.length - 1] = last; // Restaurar valor original
        if (i < a.length - 1) return i;
        return (last == target) ? a.length - 1 : -1;
    }

    /**
     * Búsqueda binaria iterativa.
     * Precondición: el arreglo debe estar ordenado en orden no decreciente.
     * Complejidad: O(log n) peor caso, O(1) mejor caso.
     */
    public static int binarySearchIter(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            // Evita desbordamiento aritmético de enteros (lo + hi)
            int mid = lo + ((hi - lo) >>> 1);
            if (a[mid] == target) return mid;
            if (a[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Búsqueda binaria recursiva.
     * Precondición: el arreglo debe estar ordenado en orden no decreciente.
     */
    public static int binarySearchRec(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        return bs(a, target, 0, a.length - 1);
    }

    private static int bs(int[] a, int t, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + ((hi - lo) >>> 1);
        if (a[mid] == t) return mid;
        if (a[mid] < t) return bs(a, t, mid + 1, hi);
        return bs(a, t, lo, mid - 1);
    }

    /**
     * Retorna el primer índice con elemento >= target (lower bound) o a.length si no existe.
     */
    public static int lowerBound(int[] a, int target) {
        if (a == null || a.length == 0) return 0;
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (a[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * Retorna el último índice donde aparece target en un arreglo ordenado con duplicados.
     */
    public static int lastIndexOfSorted(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (a[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int idx = lo - 1;
        return (idx >= 0 && a[idx] == target) ? idx : -1;
    }

    /**
     * Búsqueda binaria sobre arreglos de objetos genéricos usando Comparator.
     * Precondición: arreglo ordenado según el comparador dado.
     */
    public static <T> int binarySearch(T[] a, T target, Comparator<? super T> cmp) {
        if (a == null || a.length == 0 || cmp == null) return -1;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int c = cmp.compare(a[mid], target);
            if (c == 0) return mid;
            if (c < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
