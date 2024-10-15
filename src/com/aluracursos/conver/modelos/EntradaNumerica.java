package com.aluracursos.conver.modelos;

import com.aluracursos.conver.util.Apariencia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaNumerica {

    public static int Numero(String inputMessage, String errorMessage,int option) {
        Scanner teclado = new Scanner(System.in);

        int valueInput;

        while (true) {
            System.out.println(Apariencia.BLUE + inputMessage + Apariencia.RESET);
            try {
                valueInput = teclado.nextInt();
                if (valueInput > option && option != 0) {
                    throw new InputMismatchException();
                }
                teclado.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println(Apariencia.RED + errorMessage + Apariencia.RESET);
                teclado.nextLine();
            }
        }
        return valueInput;
    }
}
