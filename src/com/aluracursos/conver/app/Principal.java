package com.aluracursos.conver.app;

import com.aluracursos.conver.config.Conexion;
import com.aluracursos.conver.config.MonedaConvertida;
import com.aluracursos.conver.config.ResultadoConversionMoneda;
import com.aluracursos.conver.modelos.EntradaNumerica;
import com.aluracursos.conver.util.Apariencia;
import com.aluracursos.conver.util.Header;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // Clave API para acceder al servicio de conversión de moneda
        String claveAPI = "1c5970a572b8aabddfadef49";

        // URL para obtener los nombres de las monedas y sus códigos
        String uriMonedas = "https://openexchangerates.org/api/currencies.json";
        String nombresMonedas = Conexion.FetchHTTP(uriMonedas);
        JsonObject nombresMonedasJson = gson.fromJson(nombresMonedas, JsonObject.class);

        // Lista de códigos de monedas disponibles
        String[] codigosMonedas = {"COP", "BWP", "BYN", "BZD", "CAD", "CDF", "PEN", "ARS", "USD", "EUR", "BRL", "MXN", "CNY"};
        int cantidadMonedas = codigosMonedas.length;

        // Variable para controlar si el usuario desea continuar o no
        String continuar = "s";
        while (continuar.equals("s")) {

            // Imprimir el encabezado del menú
            Header.menu();

            // Mostrar las opciones de monedas disponibles para convertir
            for (int i = 0; i < cantidadMonedas; i++) {
                try {
                    String nombreMoneda = nombresMonedasJson.get(codigosMonedas[i]).getAsString();
                    System.out.println((i + 1) + ". " + nombreMoneda);
                } catch (NullPointerException e) {
                    System.out.println("Moneda no encontrada");
                }
            }

            // Obtener la entrada del usuario para seleccionar las monedas y el monto a convertir
            String mensajeMonedaOrigen = "Ingrese el número de la moneda que desea CONVERTIR: ";
            String mensajeMonedaDestino = "Ingrese el número de la moneda a la que desea CONVERTIR: ";
            String mensajeMonto = "Ingrese el valor que desea CONVERTIR: ";
            String mensajeError = "¡Opción inválida!. Por favor, ingrese una opción válida.";

            int monedaOrigen = EntradaNumerica.Numero(mensajeMonedaOrigen, mensajeError, cantidadMonedas);
            int monedaDestino = EntradaNumerica.Numero(mensajeMonedaDestino, mensajeError, cantidadMonedas);
            int monto = EntradaNumerica.Numero(mensajeMonto, mensajeError, 0);

            // Obtener los códigos de las monedas seleccionadas
            String codigoMonedaOrigen = codigosMonedas[monedaOrigen - 1];
            String codigoMonedaDestino = codigosMonedas[monedaDestino - 1];

            // Construir la URL para la conversión de moneda
            String uriConversion = "https://v6.exchangerate-api.com/v6/" + claveAPI + "/pair/"
                    + codigoMonedaOrigen + "/" + codigoMonedaDestino + "/" + monto;

            // Realizar la solicitud HTTP para obtener el resultado de la conversión
            String resultadoConversion = Conexion.FetchHTTP(uriConversion);
            ResultadoConversionMoneda resultadoConversionMoneda = gson.fromJson(resultadoConversion, ResultadoConversionMoneda.class);
            MonedaConvertida monedaConvertida = new MonedaConvertida(resultadoConversionMoneda);
            String nombreMonedaDestino = nombresMonedasJson.get(codigoMonedaDestino).getAsString();

            // Mostrar el resultado de la conversión
            System.out.println(Apariencia.GREEN + "La conversión es: " + monedaConvertida.getConversion() + " "
                    + nombreMonedaDestino + Apariencia.RESET);

            // Preguntar al usuario si desea continuar
            System.out.println("¿Desea continuar? [S/N]");
            continuar = teclado.nextLine();
        }

        // Cerrar el Scanner después de su uso
        teclado.close();

        System.out.println(Apariencia.CYAN + "¡Gracias por preferirnos, Hasta La Proxima!");
    }
}
