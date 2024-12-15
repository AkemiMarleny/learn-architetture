package co.idesoft.architetture;

import java.util.Scanner;

public class NumeroFibonacci {

    public static boolean isFibonacci(int numero) {
        if (numero < 0) {
            return false;
        }

        int a = 0, b = 1;

        while (a <= numero) {
            if (a == numero) {
                // se la condizione è sodisfatta,
                // il ciclo while si interrompe.
                return true;
            }

            int prossimo = a + b;
            a = b;
            b = prossimo;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci un numero: ");
        int numero = scanner.nextInt();

        if (isFibonacci(numero)) {
            System.out.println("Il numero " + numero + " appartiene alla sequenza di Fibonacci.");
        } else {
            System.out.println("Il numero " + numero + " non appartiene alla sequenza di Fibonacci.");
        }

        scanner.close();
    }

}
