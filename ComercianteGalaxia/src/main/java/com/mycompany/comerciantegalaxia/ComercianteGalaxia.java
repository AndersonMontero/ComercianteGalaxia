/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.comerciantegalaxia;

/**
 *
 * @author PC-Anderson
 */
public class ComercianteGalaxia {

    //private static final char[] SIMBOLOS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    //private static final int[] VALORES = {1, 5, 10, 50, 100, 500, 1000};
    static class SimboloRomano {

        char simbolo;
        int valor;

        SimboloRomano(char simbolo, int valor) {
            this.simbolo = simbolo;
            this.valor = valor;
        }
    }

    private static final SimboloRomano[] SIMBOLOS = {
        new SimboloRomano('I', 1),
        new SimboloRomano('V', 5),
        new SimboloRomano('X', 10),
        new SimboloRomano('L', 50),
        new SimboloRomano('C', 100),
        new SimboloRomano('D', 500),
        new SimboloRomano('M', 1000)
    };

    public static int convertirNumeroGalactico(String numeroGalactico) {
        int resultado = 0;

        for (int i = 0; i < numeroGalactico.length(); i++) {
            char simbolo = numeroGalactico.charAt(i);
            int valorActual = obtenerValor(numeroGalactico.charAt(i));
            char simboloSiguiente = 0;

            if (i > 0 && (simbolo == 'D' || simbolo == 'L' || simbolo == 'V') && simbolo == numeroGalactico.charAt(i - 1)) {
                System.out.println("Error: Símbolo '" + simbolo + "' no puede repetirse.");
                return -1;  // Manejar el error según tus necesidades
            }

            if (i + 2 < numeroGalactico.length() && simbolo == numeroGalactico.charAt(i + 2)) {
                System.out.println("Error: Símbolo '" + simbolo + "' se repite más de tres veces consecutivas.");
                return -1;  // Manejar el error según tus necesidades
            }

            if (i + 1 < numeroGalactico.length()) {
                simboloSiguiente = numeroGalactico.charAt(i + 1);
                if (simbolo == 'I' && (simboloSiguiente == 'V' || simboloSiguiente == 'X')) {
                    System.out.println("prueba");
                }
            }

            if (simbolo == 'X' && (simboloSiguiente == 'L' || simboloSiguiente == 'C')) {
                System.out.println("prueba2");
            }

            if (simbolo == 'C' && (simboloSiguiente == 'D' || simboloSiguiente == 'M')) {
                System.out.println("prueba3");
            }

            if ((simbolo == 'V' || simbolo == 'L' || simbolo == 'D')
                    && valorActual < obtenerValor(simboloSiguiente)) {
                System.out.println("Error: Símbolo '" + simbolo + "' nunca se puede restar.");
                return -1;  // Manejar el error según tus necesidades
            }

            if (i + 1 < numeroGalactico.length()) {

                if (valorActual < obtenerValor(simboloSiguiente)) {
                    if (valorMenor(simbolo)) {
                        resultado += obtenerValor(simboloSiguiente) - valorActual;
                        i++; // Saltar al siguiente símbolo
                    } else {
                        System.out.println("Error: Solo se puede restar un símbolo de valor pequeño de cualquier símbolo de valor grande.");
                        return -1;  // Manejar el error según tus necesidades
                    }
                } else {
                    resultado += valorActual;
                }
            } else {
                resultado += valorActual;
            }
        }

        return resultado;
    }

    public static String convertirNumeroRomano(int numeroArabigo) {
        if (numeroArabigo <= 0 || numeroArabigo > 3999) {
            System.out.println("Error: El número arábigo debe estar entre 1 y 3999.");
            return "";  // Manejar el error según tus necesidades
        }

        StringBuilder resultado = new StringBuilder();

        for (SimboloRomano simboloRomano : SIMBOLOS) {
            while (numeroArabigo >= simboloRomano.valor) {
                resultado.append(simboloRomano.simbolo);
                numeroArabigo -= simboloRomano.valor;
            }
        }

        return resultado.toString();
    }

    private static int obtenerValor(char simbolo) {
        for (SimboloRomano s : SIMBOLOS) {
            if (s.simbolo == simbolo) {
                return s.valor;
            }
        }
        return 0;  // Manejar símbolos no válidos, si es necesario
    }

    private static boolean valorMenor(char simbolo) {
        return simbolo == 'I' || simbolo == 'X' || simbolo == 'C';
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String numeroGalactico = "MMVI";
        int numeroArabigo = 1903;
        //MMVI
        //validaciones
        //MCMXLIV
        //XXXIX
        int resultado = convertirNumeroGalactico(numeroGalactico);
        String numeroRomano = convertirNumeroRomano(numeroArabigo);

        System.out.println("Número galáctico: " + numeroGalactico);
        System.out.println("Número arábigo: " + numeroRomano);
        System.out.println("Resultado: " + resultado);
    }
}
