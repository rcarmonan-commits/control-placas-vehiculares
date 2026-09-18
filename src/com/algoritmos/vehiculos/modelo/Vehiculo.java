package com.algoritmos.vehiculos.modelo;

import java.util.Objects;

/**
 * Entidad de dominio que representa un Vehículo dentro del sistema de control vehicular.
 * Compatible con Java 17+.
 *
 * Contiene los tres atributos requeridos por la rúbrica:
 * - Placa: Identificador único vehicular (normalizado a mayúsculas y sin espacios superfluos).
 * - Propietario: Nombre completo del titular.
 * - Tipo: Clasificación del automotor (ej. Automóvil, Motocicleta, Camión, SUV).
 *
 * Implementa Comparable para permitir comparaciones directas por placa.
 *
 * @author Rosary Carmona - Universidad de Cartagena
 */
public class Vehiculo implements Comparable<Vehiculo> {

    private String placa;
    private String propietario;
    private String tipo;

    /**
     * Constructor por defecto.
     */
    public Vehiculo() {
        this.placa = "";
        this.propietario = "";
        this.tipo = "";
    }

    /**
     * Constructor parametrizado con normalización defensiva de datos.
     *
     * @param placa       Placa única del vehículo (se almacena en mayúsculas).
     * @param propietario Nombre del dueño.
     * @param tipo        Tipo de vehículo (Automóvil, Camión, etc.).
     */
    public Vehiculo(String placa, String propietario, String tipo) {
        this.placa = normalizarPlaca(placa);
        this.propietario = (propietario != null) ? propietario.trim() : "";
        this.tipo = (tipo != null) ? tipo.trim() : "";
    }

    /**
     * Normaliza la placa eliminando espacios en blanco y convirtiendo a mayúsculas.
     */
    public static String normalizarPlaca(String input) {
        if (input == null) return "";
        return input.trim().toUpperCase();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = normalizarPlaca(placa);
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = (propietario != null) ? propietario.trim() : "";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = (tipo != null) ? tipo.trim() : "";
    }

    /**
     * Comparación natural por placa (orden alfabético ascendente).
     */
    @Override
    public int compareTo(Vehiculo otro) {
        if (otro == null) return 1;
        return this.placa.compareToIgnoreCase(otro.placa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(placa, vehiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    @Override
    public String toString() {
        return String.format("[Placa: %-8s | Propietario: %-22s | Tipo: %-12s]",
                placa, propietario, tipo);
    }
}
