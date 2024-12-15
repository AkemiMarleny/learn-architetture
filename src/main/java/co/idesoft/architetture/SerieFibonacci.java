package co.idesoft.architetture;

public class SerieFibonacci {

    public static void generareSerie(int quantitaElementi) {
        int a = 0;
        int b = 1;

        for (int i = 0; i < quantitaElementi; i++) {
            System.out.print(a + " ");

            int prossimo = a + b; // prossimo = 1
            a = b; // a = 1
            b = prossimo; // b = 1
        }
    }

    public static void main(String[] args) {
        SerieFibonacci.generareSerie(10);
    }
}
