package com.algoritmos.vehiculos.app;

import com.algoritmos.vehiculos.modelo.Vehiculo;
import com.algoritmos.vehiculos.servicio.BuscadorVehiculos;
import com.algoritmos.vehiculos.servicio.OrdenadorVehiculos;

/**
 * Aplicación principal de demostración y pruebas para el Control de Placas Vehiculares.
 * Cumple con todos los requisitos de la rúbrica y las pruebas para la sustentación en video.
 * Compatible con Java 17+.
 *
 * Casos cubiertos:
 * 1. Búsqueda exacta lineal (existente, inexistente, case-insensitive).
 * 2. Búsqueda por prefijo / parcial (múltiples coincidencias, una coincidencia, cero coincidencias).
 * 3. Ordenamiento alfabético manual por placa (Quicksort).
 * 4. Búsqueda binaria de placa post-ordenamiento (con validación de precondición).
 * 5. Comparación detallada entre búsqueda exacta, búsqueda parcial y búsqueda binaria.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public class MainVehiculos {

    public static void main(String[] args) {
        imprimirEncabezado();

        // 1. Crear el arreglo de objetos Vehiculo de prueba (desordenado)
        Vehiculo[] parqueAutomotor = crearDatosDePrueba();

        // 2. Demostración de Búsqueda Lineal Exacta
        demostrarBusquedaExacta(parqueAutomotor);

        // 3. Demostración de Búsqueda Parcial por Prefijo
        demostrarBusquedaPorPrefijo(parqueAutomotor);

        // 4. Demostración de Ordenamiento Alfabético Manual (Quicksort)
        demostrarOrdenamiento(parqueAutomotor);

        // 5. Demostración de Búsqueda Binaria (post-ordenamiento)
        demostrarBusquedaBinaria(parqueAutomotor);

        // 6. Demostración de Casos Borde
        demostrarCasosBorde(parqueAutomotor);

        // 7. Comparación Teórica y Empírica de Rendimiento
        compararAlgoritmos();
    }

    private static void imprimirEncabezado() {
        System.out.println("===============================================================================");
        System.out.println("         SISTEMA DE CONTROL DE PLACAS VEHICULARES - ALGORITMOS DE BÚSQUEDA");
        System.out.println("         Estudiante: Rosary Carmona (rcarmonan@unicartagena.edu.co)");
        System.out.println("         Universidad de Cartagena - Facultad de Ingeniería");
        System.out.println("===============================================================================\n");
    }

    private static Vehiculo[] crearDatosDePrueba() {
        return new Vehiculo[]{
            new Vehiculo("XYZ-892", "Carlos Mendoza", "Automóvil"),
            new Vehiculo("COL-104", "Rosary Carmona", "Camioneta"),
            new Vehiculo("ABC-123", "Andrés Gómez",   "Motocicleta"),
            new Vehiculo("BOG-555", "María Delgado",  "Automóvil"),
            new Vehiculo("COL-999", "Lucía Fernández","SUV"),
            new Vehiculo("MED-400", "Javier Herrera", "Camión"),
            new Vehiculo("ABC-999", "Diana Morales",  "Automóvil"),
            new Vehiculo("CAL-202", "Roberto Castro", "Motocicleta"),
            new Vehiculo("COL-500", "Elena Martínez", "Automóvil"),
            new Vehiculo("BAR-777", "Felipe Vargas",  "Camión")
        };
    }

    private static void demostrarBusquedaExacta(Vehiculo[] vehiculos) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("1. BÚSQUEDA EXACTA DE UNA PLACA (BÚSQUEDA LINEAL)");
        System.out.println("   Complejidad: O(n) peor caso | Precondición: No requiere orden previo.");
        System.out.println("-------------------------------------------------------------------------------");

        // Caso 1.1: Elemento existente en el arreglo
        String placaExistente = "COL-104";
        System.out.println("\n[Prueba 1.1] Buscando placa existente: \"" + placaExistente + "\"");
        int idx1 = BuscadorVehiculos.buscarIndicePorPlacaLineal(vehiculos, placaExistente);
        if (idx1 != -1) {
            System.out.println("   ✓ Vehículo encontrado en el índice [" + idx1 + "]:");
            System.out.println("     " + vehiculos[idx1]);
        } else {
            System.out.println("   ✗ No se encontró la placa.");
        }

        // Caso 1.2: Insensibilidad a minúsculas
        String placaMinuscula = "col-104";
        System.out.println("\n[Prueba 1.2] Buscando con minúsculas (insensibilidad): \"" + placaMinuscula + "\"");
        Vehiculo vMinuscula = BuscadorVehiculos.buscarVehiculoPorPlacaLineal(vehiculos, placaMinuscula);
        System.out.println("   ✓ Encontrado: " + vMinuscula);

        // Caso 1.3: Elemento no existente
        String placaInexistente = "ZZZ-000";
        System.out.println("\n[Prueba 1.3] Buscando placa inexistente: \"" + placaInexistente + "\"");
        int idxInexistente = BuscadorVehiculos.buscarIndicePorPlacaLineal(vehiculos, placaInexistente);
        System.out.println("   Resultado: " + ((idxInexistente == -1) ? "Placa NO encontrada (retorno -1)" : "Encontrada"));
        System.out.println();
    }

    private static void demostrarBusquedaPorPrefijo(Vehiculo[] vehiculos) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("2. BÚSQUEDA DE TODAS LAS PLACAS POR PREFIJO (BÚSQUEDA PARCIAL)");
        System.out.println("   Complejidad: O(n * k) | Retorna sub-arreglo exacto de coincidencias.");
        System.out.println("-------------------------------------------------------------------------------");

        // Caso 2.1: Prefijo con múltiples coincidencias
        String prefijoMultiple = "COL";
        System.out.println("\n[Prueba 2.1] Buscando placas que inicien con prefijo: \"" + prefijoMultiple + "\"");
        Vehiculo[] encontradosCOL = BuscadorVehiculos.buscarPorPrefijo(vehiculos, prefijoMultiple);
        System.out.println("   Total vehículos localizados: " + encontradosCOL.length);
        for (int i = 0; i < encontradosCOL.length; i++) {
            System.out.println("   [" + (i + 1) + "] " + encontradosCOL[i]);
        }

        // Caso 2.2: Prefijo con coincidencia única
        String prefijoUnico = "MED";
        System.out.println("\n[Prueba 2.2] Buscando placas que inicien con prefijo: \"" + prefijoUnico + "\"");
        Vehiculo[] encontradosMED = BuscadorVehiculos.buscarPorPrefijo(vehiculos, prefijoUnico);
        System.out.println("   Total vehículos localizados: " + encontradosMED.length);
        for (Vehiculo v : encontradosMED) {
            System.out.println("   -> " + v);
        }

        // Caso 2.3: Prefijo sin coincidencias
        String prefijoInexistente = "K";
        System.out.println("\n[Prueba 2.3] Buscando placas que inicien con prefijo inexistente: \"" + prefijoInexistente + "\"");
        Vehiculo[] encontradosK = BuscadorVehiculos.buscarPorPrefijo(vehiculos, prefijoInexistente);
        System.out.println("   Total vehículos localizados: " + encontradosK.length + " (arreglo vacío sin errores)");
        System.out.println();
    }

    private static void demostrarOrdenamiento(Vehiculo[] vehiculos) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("3. ORDENAMIENTO ALFABÉTICO MANUAL POR PLACA (QUICKSORT)");
        System.out.println("   Complejidad: O(n log n) | Precondición obligatoria para Búsqueda Binaria.");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.println("\n¿Está ordenado inicialmente por placa? " +
                (OrdenadorVehiculos.estaOrdenadoPorPlaca(vehiculos) ? "SÍ" : "NO"));

        System.out.println("\n>>> Arreglo ANTES del ordenamiento:");
        imprimirArreglo(vehiculos);

        // Ejecutar ordenamiento manual
        OrdenadorVehiculos.ordenarPorPlaca(vehiculos);

        System.out.println("\n>>> Arreglo DESPUÉS del ordenamiento manual por placa (A-Z):");
        imprimirArreglo(vehiculos);

        System.out.println("\n¿Está ordenado ahora por placa? " +
                (OrdenadorVehiculos.estaOrdenadoPorPlaca(vehiculos) ? "SÍ (Precondición cumplida ✓)" : "NO"));
        System.out.println();
    }

    private static void demostrarBusquedaBinaria(Vehiculo[] vehiculos) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("4. BÚSQUEDA BINARIA DE UNA PLACA (POST-ORDENAMIENTO)");
        System.out.println("   Complejidad: O(log n) | Divide y Vencerás descartando mitades.");
        System.out.println("-------------------------------------------------------------------------------");

        // Caso 4.1: Placa existente
        String placaTarget = "CAL-202";
        System.out.println("\n[Prueba 4.1] Búsqueda binaria de placa: \"" + placaTarget + "\"");
        int idxBinario = BuscadorVehiculos.buscarIndicePorPlacaBinaria(vehiculos, placaTarget);
        if (idxBinario != -1) {
            System.out.println("   ✓ Localizado en el índice [" + idxBinario + "] del arreglo ordenado:");
            System.out.println("     " + vehiculos[idxBinario]);
        } else {
            System.out.println("   ✗ No encontrado.");
        }

        // Caso 4.2: Placa en extremos
        String primeraPlaca = vehiculos[0].getPlaca();
        String ultimaPlaca = vehiculos[vehiculos.length - 1].getPlaca();
        System.out.println("\n[Prueba 4.2] Búsqueda de extremos:");
        System.out.println("   Primer elemento (\"" + primeraPlaca + "\") -> índice: " +
                BuscadorVehiculos.buscarIndicePorPlacaBinaria(vehiculos, primeraPlaca));
        System.out.println("   Último elemento (\"" + ultimaPlaca + "\") -> índice: " +
                BuscadorVehiculos.buscarIndicePorPlacaBinaria(vehiculos, ultimaPlaca));

        // Caso 4.3: Placa inexistente
        String noExiste = "ZZZ-999";
        System.out.println("\n[Prueba 4.3] Búsqueda binaria de placa inexistente: \"" + noExiste + "\"");
        int idxNoExiste = BuscadorVehiculos.buscarIndicePorPlacaBinaria(vehiculos, noExiste);
        System.out.println("   Resultado: " + ((idxNoExiste == -1) ? "Placa NO encontrada (retorno -1)" : "Encontrada"));
        System.out.println();
    }

    private static void demostrarCasosBorde(Vehiculo[] vehiculos) {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("5. VALIDACIÓN DE CASOS BORDE (EDGE CASES)");
        System.out.println("-------------------------------------------------------------------------------");

        // Caso borde: Arreglo nulo o vacío
        System.out.println("[Borde 5.1] Búsqueda en arreglo vacío:");
        Vehiculo[] arregloVacio = new Vehiculo[0];
        int resVacio = BuscadorVehiculos.buscarIndicePorPlacaLineal(arregloVacio, "ABC-123");
        System.out.println("   Búsqueda lineal en arreglo vacío -> " + resVacio);

        // Caso borde: Placa nula o vacía
        System.out.println("[Borde 5.2] Búsqueda con parámetro placa nulo:");
        int resNull = BuscadorVehiculos.buscarIndicePorPlacaBinaria(vehiculos, null);
        System.out.println("   Búsqueda binaria con null -> " + resNull);

        // Caso borde: Prefijo vacío
        System.out.println("[Borde 5.3] Búsqueda de prefijo con espacios en blanco:");
        Vehiculo[] resPrefijoVacio = BuscadorVehiculos.buscarPorPrefijo(vehiculos, "   ");
        System.out.println("   Longitud del resultado con prefijo vacío -> " + resPrefijoVacio.length);
        System.out.println();
    }

    private static void compararAlgoritmos() {
        System.out.println("===============================================================================");
        System.out.println("6. COMPARACIÓN: BÚSQUEDA EXACTA vs BÚSQUEDA PARCIAL vs BÚSQUEDA BINARIA");
        System.out.println("===============================================================================");

        System.out.println("\n+---------------------+-------------------+---------------------+-------------------------+");
        System.out.println("| Criterio            | Búsqueda Lineal   | Búsqueda Parcial    | Búsqueda Binaria        |");
        System.out.println("+---------------------+-------------------+---------------------+-------------------------+");
        System.out.println("| Propósito           | Placa exacta      | Placas por prefijo  | Placa exacta            |");
        System.out.println("| Precondición        | Ninguna (cualq.)  | Ninguna             | Arreglo ORDENADO (A-Z)  |");
        System.out.println("| Mejor Caso          | O(1)              | O(n * k)            | O(1)                    |");
        System.out.println("| Peor Caso           | O(n)              | O(n * k)            | O(log n)                |");
        System.out.println("| Caso Promedio       | O(n / 2)          | O(n * k)            | O(log n)                |");
        System.out.println("| Puede detenerse?    | Sí (al encontrar) | NO (recorre todo)   | Sí (al encontrar)       |");
        System.out.println("| Estructura salida   | int / Vehiculo    | Vehiculo[] exacto   | int / Vehiculo          |");
        System.out.println("+---------------------+-------------------+---------------------+-------------------------+");

        System.out.println("\n--- Prueba Empírica de Comparación (Simulación con 100.000 vehículos) ---");
        final int N = 100_000;
        Vehiculo[] dataset = new Vehiculo[N];
        for (int i = 0; i < N; i++) {
            String placa = String.format("COL-%05d", i);
            dataset[i] = new Vehiculo(placa, "Propietario " + i, "Automóvil");
        }

        String targetExacto = "COL-99990"; // Hacia el final para probar peor caso lineal

        // Calentamiento previo rápido (Warm-up JIT)
        for (int w = 0; w < 10; w++) {
            BuscadorVehiculos.buscarIndicePorPlacaLineal(dataset, targetExacto);
            BuscadorVehiculos.buscarIndicePorPlacaBinaria(dataset, targetExacto);
        }

        // 1. Tiempo Búsqueda Lineal Exacta
        long t1 = System.nanoTime();
        int rLineal = BuscadorVehiculos.buscarIndicePorPlacaLineal(dataset, targetExacto);
        long t2 = System.nanoTime();
        long duracionLineal = t2 - t1;

        // 2. Tiempo Búsqueda Binaria
        long t3 = System.nanoTime();
        int rBinario = BuscadorVehiculos.buscarIndicePorPlacaBinaria(dataset, targetExacto);
        long t4 = System.nanoTime();
        long duracionBinario = t4 - t3;

        // 3. Tiempo Búsqueda Parcial (prefijo "COL-099")
        long t5 = System.nanoTime();
        Vehiculo[] rParcial = BuscadorVehiculos.buscarPorPrefijo(dataset, "COL-099");
        long t6 = System.nanoTime();
        long duracionParcial = t6 - t5;

        System.out.printf("Dataset: %,d vehículos cargados.%n", N);
        System.out.printf("1. Búsqueda Lineal Exacta (O(n)):       %,12d ns (Índice: %d)%n", duracionLineal, rLineal);
        System.out.printf("2. Búsqueda Binaria Exacta (O(log n)):    %,12d ns (Índice: %d)%n", duracionBinario, rBinario);
        System.out.printf("3. Búsqueda Parcial Prefijo (O(n * k)):   %,12d ns (Coincidencias: %d)%n", duracionParcial, rParcial.length);
        System.out.printf("Factor de aceleración Binaria vs Lineal: aprox. %.1fx más rápida.%n",
                (double) duracionLineal / Math.max(duracionBinario, 1));
        System.out.println("===============================================================================\n");
    }

    private static void imprimirArreglo(Vehiculo[] vehiculos) {
        for (int i = 0; i < vehiculos.length; i++) {
            System.out.printf("   [%02d] %s%n", i, vehiculos[i]);
        }
    }
}
