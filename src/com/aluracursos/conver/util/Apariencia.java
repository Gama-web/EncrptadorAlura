package com.aluracursos.conver.util;

/**
 * La clase Apariencia proporciona constantes de cadenas de escape ANSI
 * para cambiar el color del texto en la salida de la consola. Estas constantes permiten
 * aplicar colores específicos al texto impreso en la consola.
 */
public class Apariencia {

  //Constante que restable los valores a su valor original
    public static final String RESET = "\u001B[0m";

  //Constantes que cambiaran los valores de los texto de Salida
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

}
