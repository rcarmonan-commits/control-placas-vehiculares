# Sistema de Control de Placas Vehiculares y Algoritmos de Búsqueda en Java

**Estudiante:** Rosary Carmona  
**Correo Institucional:** [rcarmonan@unicartagena.edu.co](mailto:rcarmonan@unicartagena.edu.co)  
**Usuario de GitHub:** [rcarmonan-commits](https://github.com/rcarmonan-commits)  
**Institución:** Universidad de Cartagena  
**Programa:** Ingeniería de Sistemas  
**Asignatura:** Estructuras de Datos / Algoritmos  

---

## 📋 Tabla de Contenido

1. [Descripción General](#-descripción-general)
2. [Estructura del Proyecto](#-estructura-del-proyecto)
3. [Componente 1: Taller Formativo de Búsqueda](#-componente-1-taller-formativo-de-búsqueda)
4. [Componente 2: Ejercicio Asignado #6 - Control de Placas](#-componente-2-ejercicio-asignado-6---control-de-placas)
5. [Análisis de Complejidad Temporal (Big-O)](#-análisis-de-complejidad-temporal-big-o)
6. [Instrucciones de Compilación y Ejecución](#-instrucciones-de-compilación-y-ejecución)
7. [Historial de Commits y Metodología Git](#-historial-de-commits-y-metodología-git)

---

## 📌 Descripción General

El presente proyecto académico aborda la implementación rigurosa y manual de **Algoritmos de Búsqueda sobre Arreglos 1D, Matrices 2D y Arreglos de Objetos**. El desarrollo se realizó esquivando el uso de métodos de búsqueda automáticos provistos por el lenguaje (`Arrays.binarySearch`, `List.contains`, `Stream`, etc.), programando cada algoritmo paso a paso con manipulación explícita de índices y memoria.

El repositorio se divide en dos fases pedagógicas:

1. **Taller de Fundamentación:** Búsquedas secuenciales, centinela, búsqueda binaria (iterativa y recursiva), variantes lower/upper bound, búsqueda en matrices (staircase y aplanada) y la solución de 10 ejercicios guiados.
2. **Caso Asignado (Ejercicio #6):** Sistema de gestión y control de vehículos por placas, implementando búsqueda exacta, búsqueda parcial por prefijo, ordenamiento manual Quicksort, búsqueda binaria y análisis de rendimiento comparativo.

---

## 📂 Estructura del Proyecto

El código está estructurado bajo principios de Clean Code, separación de responsabilidades y compatibilidad con **Java 17 (LTS)**:

```text
6._Control_de_placas_vehiculares/
├── .gitignore                          # Exclusión de binarios y archivos de IDE
├── README.md                           # Documentación técnica del proyecto
└── src/
    └── com/
        └── algoritmos/
            │
            ├── taller/                         # FASE 1: TALLER GENERAL DE BÚSQUEDA
            │   ├── ArraySearches.java          # Algoritmos 1D base (Lineal, Centinela, Binaria)
            │   ├── MatrixSearches.java         # Algoritmos 2D base (Staircase, Aplanada)
            │   ├── EjerciciosGuiados.java      # Resolución de los 10 ejercicios del taller
            │   └── MainTallerDemo.java          # Driver ejecutable y benchmarks del taller
            │
            └── vehiculos/                      # FASE 2: EJERCICIO ASIGNADO #6 (PLACAS)
                ├── modelo/
                │   └── Vehiculo.java           # POJO de dominio (placa, propietario, tipo)
                ├── servicio/
                │   ├── BuscadorVehiculos.java  # Búsqueda exacta, prefijo y binaria manual
                │   └── OrdenadorVehiculos.java # Quicksort manual alfabético por placa
                └── app/
                    └── MainVehiculos.java      # Suite principal de pruebas y comparativa
```

---

## 🧩 Componente 1: Taller Formativo de Búsqueda

### Clases Base (`ArraySearches` y `MatrixSearches`)

* **Búsqueda Lineal:** `linearSearch`, `linearSearchLast` y `linearSearchAll` (retornando un arreglo de enteros con el tamaño exacto de coincidencias).
* **Búsqueda con Centinela:** `linearSearchSentinel`, que ubica el objetivo al final para suprimir la evaluación de límites en cada iteración del ciclo.
* **Búsqueda Binaria:** Implementación iterativa y recursiva usando `mid = lo + ((hi - lo) >>> 1)` para evitar el desbordamiento de enteros positivos en Java.
* **Variantes de Rango:** `lowerBound` y `lastIndexOfSorted`.
* **Búsqueda 2D:**
  * `linearSearch2D`: Recorrido matricial completo en $O(n \cdot m)$.
  * `staircaseSearch`: Búsqueda en escalera $O(n + m)$ para matrices ordenadas por filas y columnas, iniciando en la esquina superior derecha.
  * `binarySearchFlattened`: Mapeo virtual de 2D a 1D en $O(\log(n \cdot m))$.

### Los 10 Ejercicios Guiados (`EjerciciosGuiados.java`)

1. `contains(int[] a, int t)`: Verificación booleana reutilizando `linearSearch`.
2. `count(int[] a, int t)`: Conteo de frecuencias en una sola pasada $O(n)$.
3. `removeFirstOccurrence(int[] a, int t)`: Eliminación de la primera aparición usando `System.arraycopy`.
4. `argMin(int[] a)`: Localización del índice con el valor mínimo.
5. `binarySearchOrInsertionPoint(int[] sorted, int t)`: Retorno del índice o $-(puntoInsercion) - 1$.
6. `firstIndexOfSorted(int[] a, int t)`: Primer índice apoyado en `lowerBound`.
7. `countInMatrix2D(int[][] m, int t)`: Conteo total de apariciones en matrices desordenadas.
8. `staircaseSearchAll(int[][] m, int t)`: Escalera robusta que devuelve todas las posiciones ante elementos repetidos.
9. `binarySearchFlattenedRange(int[][] m, int t)`: Rango de primer y último índice 2D.
10. `benchmarkLinearVsBinary()`: Medición empírica en nanosegundos con calentamiento de JVM (Warm-up).

---

## 🚗 Componente 2: Ejercicio Asignado #6 - Control de Placas

### 1. Modelo de Dominio (`Vehiculo.java`)

* Atributos: `placa`, `propietario`, `tipo`.
* Normalización automática de placas a mayúsculas y supresión de espacios en blanco (`trim().toUpperCase()`).
* Implementación de `Comparable<Vehiculo>` con orden alfabético natural por placa.

### 2. Algoritmos de Búsqueda Implementados (`BuscadorVehiculos.java`)

* **Búsqueda Exacta Lineal:** Recorre el arreglo secuencialmente comparando placa a placa. No requiere orden previo.
* **Búsqueda Parcial por Prefijo:** Encuentra todas las placas que inicien con determinada cadena. Funciona en **dos pasadas**: primero cuenta las ocurrencias y luego crea y retorna un arreglo `Vehiculo[]` con el tamaño exacto, sin usar listas dinámicas.
* **Búsqueda Binaria de Placas:** Ejecuta búsqueda binaria lexicográfica sobre arreglos previamente ordenados.

### 3. Ordenamiento Manual (`OrdenadorVehiculos.java`)

* Algoritmo **Quicksort manual** adaptado a arreglos de objetos `Vehiculo[]`.
* Emplea partición de Lomuto comparando alfabéticamente (`compareToIgnoreCase`).
* Validador de precondición: `estaOrdenadoPorPlaca(Vehiculo[] vehiculos)` que garantiza formalmente que el arreglo esté ordenado antes de invocar la búsqueda binaria.

---

## 📊 Análisis de Complejidad Temporal (Big-O)

| Algoritmo | Estructura | Precondición | Mejor Caso | Peor Caso | Caso Promedio | Espacio Auxiliar |
| :--- | :--- | :--- | :---: | :---: | :---: | :---: |
| **Búsqueda Lineal Exacta** | Arreglo `Vehiculo[]` | Ninguna | $O(1)$ | $O(n)$ | $O(n / 2)$ | $O(1)$ |
| **Búsqueda Parcial (Prefijo)** | Arreglo `Vehiculo[]` | Ninguna | $O(n \cdot k)$ | $O(n \cdot k)$ | $O(n \cdot k)$ | $O(m)$ donde $m \le n$ |
| **Ordenamiento Quicksort** | Arreglo `Vehiculo[]` | Ninguna | $O(n \log n)$ | $O(n^2)$ | $O(n \log n)$ | $O(\log n)$ (pila) |
| **Búsqueda Binaria Exacta** | Arreglo `Vehiculo[]` | **Ordenado A-Z** | $O(1)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ |

### 💡 Justificación Comparativa: Búsqueda Exacta vs Búsqueda Parcial

* **Búsqueda Exacta:** Permite el uso de Búsqueda Binaria ($O(\log n)$) si los datos están ordenados, o Búsqueda Lineal con interrupción temprana en cuanto se localiza la placa (promedio $n/2$ comparaciones).
* **Búsqueda Parcial (Prefijo):** Obliga a recorrer el arreglo completo ($O(n \cdot k)$), ya que múltiples vehículos pueden compartir el mismo prefijo (ej. `"COL"`), por lo que el algoritmo no puede detenerse prematuramente.

---

## 💻 Instrucciones de Compilación y Ejecución

El proyecto puede compilarse y ejecutarse desde la terminal (PowerShell o CMD) con Java 17+:

### 1. Compilar todo el proyecto

```powershell
# Crear carpeta de salida si no existe
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }

# Compilar todas las clases con codificación UTF-8
javac -encoding UTF-8 -d bin src/com/algoritmos/taller/*.java src/com/algoritmos/vehiculos/modelo/*.java src/com/algoritmos/vehiculos/servicio/*.java src/com/algoritmos/vehiculos/app/*.java
```

### 2. Ejecutar la demostración del Ejercicio #6 (Control de Placas)

```powershell
java -cp bin com.algoritmos.vehiculos.app.MainVehiculos
```

### 3. Ejecutar la demostración del Taller Formativo (10 Ejercicios y Benchmarks)

```powershell
java -cp bin com.algoritmos.taller.MainTallerDemo
```

---

## 🌿 Historial de Commits y Metodología Git

El proyecto fue desarrollado de manera progresiva en la rama `desarrollo-ejercicio` antes de fusionarse a `main`:

* Commit 1 *(15 Ago 2026, 19:56)*: `feat: crear estructura inicial del proyecto y clases base de busqueda 1D y 2D`
* Commit 2 *(28 Ago 2026, 16:30)*: `feat: implementar los 10 ejercicios guiados del taller de busqueda`
* Commit 3 *(18 Sep 2026, 17:15)*: `feat: crear modelo de dominio Vehiculo para control de placas`
* Commit 4 *(06 Oct 2026, 14:40)*: `feat: implementar busqueda lineal exacta y busqueda por prefijo de placas`
* Commit 5 *(22 Oct 2026, 18:20)*: `feat: implementar ordenamiento alfabetico manual y busqueda binaria con precondiciones`
* Commit 6 *(05 Nov 2026, 20:30)*: `test: agregar demostracion comparativa de rendimiento entre busqueda exacta y parcial`
* Commit 7 *(14 Nov 2026, 15:45)*: `docs: documentar algoritmos y complejidades Big-O en README`
* Commit 8 *(15 Nov 2026, 22:45)*: `Merge branch 'desarrollo-ejercicio' into main`
