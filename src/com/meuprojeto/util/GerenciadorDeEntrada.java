package com.meuprojeto.util;

import java.util.Scanner;

public class GerenciadorDeEntrada {
    private static final Scanner scanner = new Scanner(System.in);

    private GerenciadorDeEntrada() {
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void fecharScanner(){
        scanner.close();
    }
}
