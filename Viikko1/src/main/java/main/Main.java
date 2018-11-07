package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {
    static final double asd = 100.0;
    static final double asd1 = 20.2;
    public static void main(String[] args) {
        

        
        Varasto mehua = new Varasto(asd);
        Varasto olutta = new Varasto(asd, asd1);

        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);

      
    }


}
