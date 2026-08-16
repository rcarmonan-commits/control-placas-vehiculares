package com.algoritmos.taller;

/**
 * Biblioteca de algoritmos de búsqueda sobre matrices bidimensionales (2D).
 * Compatible con Java 17+.
 *
 * Implementaciones base del Taller de Algoritmos de Búsqueda:
 * - Búsqueda lineal 2D (ingenua).
 * - Búsqueda en escalera (Staircase search) en matrices ordenadas por filas y columnas: O(n + m).
 * - Búsqueda binaria en matriz aplanada (ordenada globalmente): O(log(n * m)).
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public final class MatrixSearches {

    private MatrixSearches() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Búsqueda lineal en matrices 2D (ingenua).
     * Recorre fila por fila y columna por columna.
     * Complejidad: O(n * m).
     *
     * @return Arreglo con {fila, columna} si se encuentra, o {-1, -1} si no existe.
     */
    public static int[] linearSearch2D(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new int[]{-1, -1};
        }
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) continue;
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Búsqueda en escalera ("Staircase search").
     * Precondición: La matriz está ordenada de manera que cada fila es creciente de izquierda
     * a derecha y cada columna es creciente de arriba hacia abajo:
     * m[i][j] <= m[i][j+1] y m[i][j] <= m[i+1][j].
     *
     * Estrategia: Inicia en la esquina superior derecha (fila 0, columna p - 1).
     * Si el elemento actual es mayor que target, se descarta la columna (retrocede j--).
     * Si es menor, se descarta la fila (desciende i++).
     *
     * Complejidad: O(n + m), donde n es el número de filas y m de columnas.
     */
    public static int[] staircaseSearch(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new int[]{-1, -1};
        }
        int n = m.length;
        int p = m[0].length;
        int i = 0;
        int j = p - 1;

        while (i < n && j >= 0) {
            if (m[i][j] == target) {
                return new int[]{i, j};
            }
            if (m[i][j] > target) {
                j--; // Descarta columna actual
            } else {
                i++; // Descarta fila actual
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Búsqueda binaria en matriz ordenada globalmente ("aplanada").
     * Precondición: Cada fila está ordenada y el primer elemento de una fila
     * es mayor que el último elemento de la fila anterior.
     *
     * Se mapea el espacio 2D a un índice virtual 1D de 0 a (n * p - 1):
     * - fila = mid / p
     * - columna = mid % p
     *
     * Complejidad: O(log(n * m)).
     */
    public static int[] binarySearchFlattened(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new int[]{-1, -1};
        }
        int n = m.length;
        int p = m[0].length;
        int lo = 0;
        int hi = n * p - 1;

        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int r = mid / p;
            int c = mid % p;
            int val = m[r][c];

            if (val == target) {
                return new int[]{r, c};
            }
            if (val < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }
}
