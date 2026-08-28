package com.algoritmos.taller;

import java.util.Arrays;

/**
 * Solución integral de los 10 Ejercicios Guiados del Taller de Búsqueda.
 * Compatible con Java 17+.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public final class EjerciciosGuiados {

    private EjerciciosGuiados() {
        // Clase utilitaria con métodos estáticos
    }

    /**
     * Ejercicio 1: Linear básico.
     * Retorna true si target existe en el arreglo, false en caso contrario.
     * Reutiliza ArraySearches.linearSearch(a, t).
     */
    public static boolean contains(int[] a, int target) {
        return ArraySearches.linearSearch(a, target) != -1;
    }

    /**
     * Ejercicio 2: Contar ocurrencias en O(n).
     * Realiza un único recorrido contabilizando cuántas veces aparece target.
     */
    public static int count(int[] a, int target) {
        if (a == null || a.length == 0) return 0;
        int occurrences = 0;
        for (int value : a) {
            if (value == target) {
                occurrences++;
            }
        }
        return occurrences;
    }

    /**
     * Ejercicio 3: Eliminar primera ocurrencia.
     * Retorna un nuevo arreglo sin la primera aparición del elemento target.
     * Utiliza System.arraycopy para eficiencia.
     */
    public static int[] removeFirstOccurrence(int[] a, int target) {
        if (a == null || a.length == 0) return new int[0];
        int index = ArraySearches.linearSearch(a, target);
        if (index == -1) {
            // Si el objetivo no está presente, retorna una copia exacta
            return Arrays.copyOf(a, a.length);
        }

        int[] result = new int[a.length - 1];
        // Copiar elementos antes del índice encontrado
        System.arraycopy(a, 0, result, 0, index);
        // Copiar elementos después del índice encontrado
        System.arraycopy(a, index + 1, result, index, a.length - index - 1);
        return result;
    }

    /**
     * Ejercicio 4: Índice del mínimo (argMin).
     * Retorna el índice del menor elemento en el arreglo.
     * Maneja adecuadamente el caso de arreglo vacío o nulo retornando -1.
     */
    public static int argMin(int[] a) {
        if (a == null || a.length == 0) return -1;
        int minIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Ejercicio 5: Búsqueda binaria con punto de inserción.
     * Si target existe, retorna su índice.
     * Si no existe, retorna -(puntoInsercion) - 1, equivalente a Arrays.binarySearch.
     * Precondición: el arreglo está ordenado ascendentemente.
     */
    public static int binarySearchOrInsertionPoint(int[] sorted, int target) {
        if (sorted == null || sorted.length == 0) return -1;
        int lo = 0;
        int hi = sorted.length - 1;

        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (sorted[mid] == target) {
                return mid;
            }
            if (sorted[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        // Al finalizar el bucle, 'lo' representa el punto exacto de inserción
        return -(lo) - 1;
    }

    /**
     * Ejercicio 6: Primer y último índice en arreglo ordenado.
     * Usa lowerBound para encontrar el primer índice de target.
     */
    public static int firstIndexOfSorted(int[] a, int target) {
        if (a == null || a.length == 0) return -1;
        int lb = ArraySearches.lowerBound(a, target);
        if (lb < a.length && a[lb] == target) {
            return lb;
        }
        return -1;
    }

    /**
     * Ejercicio 7: Buscar en matriz sin orden.
     * Cuenta cuántas veces aparece target en toda la matriz 2D.
     */
    public static int countInMatrix2D(int[][] m, int target) {
        if (m == null || m.length == 0) return 0;
        int total = 0;
        for (int[] row : m) {
            if (row == null) continue;
            for (int val : row) {
                if (val == target) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Ejercicio 8: Staircase robusto para todas las apariciones de target.
     * Precondición: Filas y columnas crecientes.
     * Retorna una matriz con pares de coordenadas {fila, columna}.
     */
    public static int[][] staircaseSearchAll(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new int[0][0];
        }
        int n = m.length;
        int p = m[0].length;

        // Primer conteo para dimensionar exactamente el arreglo de resultados
        int count = 0;
        int i = 0;
        int j = p - 1;
        while (i < n && j >= 0) {
            if (m[i][j] == target) {
                // Expandir en la fila hacia la izquierda por posibles duplicados contiguos
                int col = j;
                while (col >= 0 && m[i][col] == target) {
                    count++;
                    col--;
                }
                i++; // Avanzar a la siguiente fila
            } else if (m[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        int[][] results = new int[count][2];
        int idx = 0;
        i = 0;
        j = p - 1;
        while (i < n && j >= 0) {
            if (m[i][j] == target) {
                int col = j;
                while (col >= 0 && m[i][col] == target) {
                    results[idx][0] = i;
                    results[idx][1] = col;
                    idx++;
                    col--;
                }
                i++;
            } else if (m[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return results;
    }

    /**
     * Ejercicio 9: Binaria en matriz aplanada con rango (primer y último índice 2D).
     * Precondición: Matriz globalmente ordenada.
     * Retorna {{primerFila, primerCol}, {ultimaFila, ultimaCol}} o {{-1, -1}, {-1, -1}}.
     */
    public static int[][] binarySearchFlattenedRange(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return new int[][]{{-1, -1}, {-1, -1}};
        }
        int n = m.length;
        int p = m[0].length;
        int totalElements = n * p;

        // Búsqueda de primer índice 1D (lower bound)
        int lo = 0, hi = totalElements;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int val = m[mid / p][mid % p];
            if (val < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int first1D = lo;
        if (first1D >= totalElements || m[first1D / p][first1D % p] != target) {
            return new int[][]{{-1, -1}, {-1, -1}};
        }

        // Búsqueda de último índice 1D (upper bound - 1)
        lo = 0;
        hi = totalElements;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int val = m[mid / p][mid % p];
            if (val <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int last1D = lo - 1;

        return new int[][]{
            {first1D / p, first1D % p},
            {last1D / p, last1D % p}
        };
    }

    /**
     * Ejercicio 10: Medición de tiempos de ejecución empíricos.
     * Compara LinearSearch vs BinarySearch en un arreglo de tamaño grande (ej. 2 millones).
     * Incluye calentamiento de la JVM para resultados estables.
     */
    public static void benchmarkLinearVsBinary(int size, int runs) {
        System.out.println("=== BENCHMARK DE RENDIMIENTO (Taller Ejercicio 10) ===");
        System.out.println("Generando arreglo de " + size + " enteros ordenados...");
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i * 2; // Datos pares ordenados
        }

        // Objetivo ubicado hacia el final para evaluar el peor caso de la búsqueda lineal
        int target = data[size - 100];

        // 1. Fase de calentamiento de la JVM (Warm-up)
        for (int w = 0; w < 5; w++) {
            ArraySearches.linearSearch(data, target);
            ArraySearches.binarySearchIter(data, target);
        }

        // 2. Medición Búsqueda Lineal
        long totalLinearTime = 0;
        for (int r = 0; r < runs; r++) {
            long start = System.nanoTime();
            ArraySearches.linearSearch(data, target);
            long end = System.nanoTime();
            totalLinearTime += (end - start);
        }
        double avgLinearNs = (double) totalLinearTime / runs;

        // 3. Medición Búsqueda Binaria
        long totalBinaryTime = 0;
        for (int r = 0; r < runs; r++) {
            long start = System.nanoTime();
            ArraySearches.binarySearchIter(data, target);
            long end = System.nanoTime();
            totalBinaryTime += (end - start);
        }
        double avgBinaryNs = (double) totalBinaryTime / runs;

        System.out.printf("Tamaño del dataset: %,d elementos%n", size);
        System.out.printf("Número de corridas: %d%n", runs);
        System.out.printf("Tiempo promedio Búsqueda Lineal (O(n)):     %,12.2f ns (%.4f ms)%n",
                avgLinearNs, avgLinearNs / 1_000_000.0);
        System.out.printf("Tiempo promedio Búsqueda Binaria (O(log n)): %,12.2f ns (%.6f ms)%n",
                avgBinaryNs, avgBinaryNs / 1_000_000.0);
        System.out.printf("Diferencia de velocidad: La Búsqueda Binaria fue aprox. %.1fx más rápida.%n",
                avgLinearNs / Math.max(avgBinaryNs, 1.0));
        System.out.println("=====================================================");
    }
}
