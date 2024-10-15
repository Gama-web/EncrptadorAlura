package com.aluracursos.conver.config;

public class MonedaConvertida {

    private final double conversion;

    public MonedaConvertida(ResultadoConversionMoneda ResultadoConversionMoneda) {
        this.conversion = ResultadoConversionMoneda.conversion_result();
    }

    public double getConversion() {
        return conversion;
    }
}
