package co.idesoft.architetture;

public class NumeroPalindromo {

    public static boolean isPalindromo(int number) {
        // Converte il numero intero in una stringa
        String numStr = Integer.toString(number);
        // Inverte la stringa originale
        StringBuilder reversed = new StringBuilder(numStr).reverse();
        // Confronta la stringa originale con quella invertita.

        return numStr.equals(reversed.toString());
    }

    public static void main(String[] args) {

        int num = 12321;
        if (isPalindromo(num)) {
            System.out.println(num + " è un palindromo");
        } else {
            System.out.println(num + "non è palindromo");
        }
    }
}
