package moneycalculatorV1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculatorV1 {
    
    public static void main(String[] args) {
        System.out.println("Introduce an amount");
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        double a = 0;
        double x = 0;
        String c = "";
        try {
            b = false;
            a = scanner.nextDouble();
        } catch (Exception e) {
            b = true;

        }
        System.out.println("Introduce una divisa");
        c = scanner.nextLine().toUpperCase();
        
        
        b = true;

        while (b) {
            try {
                URL url = new URL("https://api.exchangeratesapi.io/latest");
                Scanner scanner1 = new Scanner(url.openStream());
                String div = scanner1.nextLine();
                //System.out.println(div);
                int i = div.indexOf(":");
                int j = div.indexOf(",", i);
                String t = div.substring(i + 2, j);
                String divisa=t.substring(1,4);
                while(divisa.equals(c)){
                    System.out.println("Procesando");
                    i=j;
                    j=div.indexOf(",", i+1);
                    t=div.substring(i+1,j);
                    divisa=t.substring(1,4);
                }
                String num = t.substring(6);
                x = Double.parseDouble(num)*a;
                b=false;
            } catch (Exception ex) {
                b = true;
                //System.out.println("No");
            }

        }
        System.out.println(String.format("Son %.2f " + c, x));
    }
}