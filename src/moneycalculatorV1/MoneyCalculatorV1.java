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
        double amount = 0;
        double converted = 0;
        String currency = "";
        try {
            b = false;
            amount = scanner.nextDouble();
        } catch (Exception e) {
            b = true;

        }
        System.out.println("Introduce una divisa");
        currency = scanner.nextLine().toUpperCase();
        
        
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
                while(divisa.equals(currency)){
                    System.out.println("Procesando");
                    i=j;
                    j=div.indexOf(",", i+1);
                    t=div.substring(i+1,j);
                    divisa=t.substring(1,4);
                }
                String exchangeRate = t.substring(6);
                converted = Double.parseDouble(exchangeRate)*amount;
                b=false;
            } catch (Exception ex) {
                b = true;
                //System.out.println("No");
            }

        }
        System.out.println(String.format("Son %.2f " + currency, converted));
    }
}