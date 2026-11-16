package com.algoritmos.taller;

import java.util.Arrays;

/**
 * Driver ejecutable para demostrar y verificar todos los algoritmos base y
 * los 10 ejercicios guiados del Taller de Búsqueda.
 * Compatible con Java 17+.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public class MainTallerDemo {

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("   UNIVERSIDAD DE CARTAGENA - TALLER DE ALGORITMOS DE BÚSQUEDA");
        System.out.println("   Estudiante: Rosary Carmona (rcarmonan@unicartagena.edu.co)");
        System.out.println("===============================================================\n");

        demostrarAlgoritmosBase1D();
        demostrarAlgoritmosBase2D();
        demostrarEjerciciosGuiados();

        System.out.println("\n--- Ejecutando Benchmark de Rendimiento (Ejercicio 10) ---");
        EjerciciosGuiados.benchmarkLinearVsBinary(1_000_000, 10);
    }

    private static void demostrarAlgoritmosBase1D() {
        System.out.println(">>> 1. DEMOSTRACIÓN ALGORITMOS BASE 1D (ArraySearches)");
        int[] a = {7, 3, 3, 9, 1, 3};
        System.out.println("Arreglo original: " + Arrays.toString(a));
        System.out.println("Linear first 3 -> índice " + ArraySearches.linearSearch(a, 3));
        System.out.println("Linear last 3  -> índice " + ArraySearches.linearSearchLast(a, 3));
        System.out.println("All 3          -> índices " + Arrays.toString(ArraySearches.linearSearchAll(a, 3)));
        System.out.println("Sentinel 9     -> índice " + ArraySearches.linearSearchSentinel(a, 9));

        int[] sorted = {1, 3, 3, 3, 7, 9, 10};
        System.out.println("\nArreglo ordenado: " + Arrays.toString(sorted));
        System.out.println("Binary Iter 7  -> índice " + ArraySearches.binarySearchIter(sorted, 7));
        System.out.println("Binary Rec 7   -> índice " + ArraySearches.binarySearchRec(sorted, 7));
        System.out.println("lowerBound(3)  -> índice " + ArraySearches.lowerBound(sorted, 3));
        System.out.println("lastIndexOf(3) -> índice " + ArraySearches.lastIndexOfSorted(sorted, 3));
        System.out.println();
    }

    private static void demostrarAlgoritmosBase2D() {
        System.out.println(">>> 2. DEMOSTRACIÓN ALGORITMOS BASE 2D (MatrixSearches)");
        int[][] m1 = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
        };
        System.out.println("Matriz m1 (filas y columnas ordenadas):");
        imprimirMatriz(m1);
        int[] posStaircase = MatrixSearches.staircaseSearch(m1, 5);
        System.out.println("Staircase search de 5 -> [fila, col]: " + Arrays.toString(posStaircase));

        int[][] m2 = {
            {1, 3, 5},
            {7, 9, 11},
            {13, 15, 17}
        };
        System.out.println("\nMatriz m2 (ordenada globalmente):");
        imprimirMatriz(m2);
        int[] posFlattened = MatrixSearches.binarySearchFlattened(m2, 15);
        System.out.println("Binary Search Flattened de 15 -> [fila, col]: " + Arrays.toString(posFlattened));
        System.out.println();
    }

    private static void demostrarEjerciciosGuiados() {
        System.out.println(">>> 3. DEMOSTRACIÓN DE EJERCICIOS GUIADOS (EjerciciosGuiados)");
        int[] arr = {10, 25, 4, 25, 80, 25, 3};
        System.out.println("Arreglo de prueba: " + Arrays.toString(arr));

        // Ejercicio 1: contains
        System.out.println("Ej 1: contains(arr, 25) -> " + EjerciciosGuiados.contains(arr, 25));
        System.out.println("Ej 1: contains(arr, 99) -> " + EjerciciosGuiados.contains(arr, 99));

        // Ejercicio 2: count
        System.out.println("Ej 2: count(arr, 25)    -> " + EjerciciosGuiados.count(arr, 25) + " apariciones");

        // Ejercicio 3: removeFirstOccurrence
        int[] sinPrimer25 = EjerciciosGuiados.removeFirstOccurrence(arr, 25);
        System.out.println("Ej 3: removeFirst(25)   -> " + Arrays.toString(sinPrimer25));

        // Ejercicio 4: argMin
        int minIdx = EjerciciosGuiados.argMin(arr);
        System.out.println("Ej 4: argMin(arr)       -> índice " + minIdx + " (valor: " + arr[minIdx] + ")");

        // Ejercicio 5: binarySearchOrInsertionPoint
        int[] ordenado = {10, 20, 30, 40, 50};
        System.out.println("\nArreglo ordenado: " + Arrays.toString(ordenado));
        System.out.println("Ej 5: binarySearchOrInsertion(30) -> " + EjerciciosGuiados.binarySearchOrInsertionPoint(ordenado, 30));
        System.out.println("Ej 5: binarySearchOrInsertion(25) -> " + EjerciciosGuiados.binarySearchOrInsertionPoint(ordenado, 25) + " (no existe, punto inserción: 2)");

        // Ejercicio 6: firstIndexOfSorted
        int[] duplicados = {5, 10, 10, 10, 20, 30};
        System.out.println("\nArreglo con duplicados: " + Arrays.toString(duplicados));
        System.out.println("Ej 6: firstIndexOfSorted(10) -> índice " + EjerciciosGuiados.firstIndexOfSorted(duplicados, 10));

        // Ejercicio 7: countInMatrix2D
        int[][] matDesordenada = {
            {5, 2, 5},
            {1, 5, 8},
            {5, 0, 9}
        };
        System.out.println("\nMatriz con duplicados:");
        imprimirMatriz(matDesordenada);
        System.out.println("Ej 7: countInMatrix2D(5) -> " + EjerciciosGuiados.countInMatrix2D(matDesordenada, 5) + " apariciones");

        // Ejercicio 8: staircaseSearchAll
        int[][] matEscalera = {
            {1, 4, 4, 7},
            {2, 4, 5, 8},
            {3, 6, 7, 9}
        };
        System.out.println("\nMatriz escalera:");
        imprimirMatriz(matEscalera);
        int[][] ocurrencias4 = EjerciciosGuiados.staircaseSearchAll(matEscalera, 4);
        System.out.print("Ej 8: staircaseSearchAll(4) -> ");
        for (int[] pos : ocurrencias4) {
            System.out.print(Arrays.toString(pos) + " ");
        }
        System.out.println();

        // Ejercicio 9: binarySearchFlattenedRange
        int[][] matGlobal = {
            {1,  3,  5,  5},
            {5,  5,  9,  11},
            {13, 15, 17, 19}
        };
        System.out.println("\nMatriz global con rango de 5s:");
        imprimirMatriz(matGlobal);
        int[][] rango5 = EjerciciosGuiados.binarySearchFlattenedRange(matGlobal, 5);
        System.out.println("Ej 9: Rango de 5s -> Primero: " + Arrays.toString(rango5[0]) + ", Último: " + Arrays.toString(rango5[1]));
    }

    private static void imprimirMatriz(int[][] m) {
        for (int[] row : m) {
            System.out.println("   " + Arrays.toString(row));
        }
    }
}
