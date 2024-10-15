package com.aluracursos.conver.config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * La clase Conexion proporciona métodos para realizar solicitudes HTTP a recursos remotos.
 */
public class Conexion {

    /**
     * Realiza una solicitud HTTP GET al URI especificado y devuelve el cuerpo de la respuesta.
     */
    public static String FetchHTTP(String uriResource) {

        try {
            // Se crea un cliente HTTP para realizar la solicitud
            HttpClient client = HttpClient.newHttpClient();

            // Se construye la solicitud HTTP GET con el URI especificado
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uriResource))
                    .build();

            // Se envía la solicitud y se obtiene la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (IOException e) {
             System.err.println("Se produjo una IOException al realizar la solicitud HTTP: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Se produjo una InterruptedException al realizar la solicitud HTTP: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Se produjo una excepción al realizar la solicitud HTTP: " + e.getMessage());
        }

        return "http_response";
    }
}
