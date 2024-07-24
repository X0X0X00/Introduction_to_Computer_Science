// Zhenhao Zhang zzh133 LAB2 HW2

import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {

        System.out.println("Multiplication Table");
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter an integer: ");
        int number = s.nextInt();
        for (int i = 1; i<= number ; i ++){
            System.out.print("\t"+i);
        }
        System.out.println();

        for (int i = 1; i<= number ; i ++){
            System.out.print(i+"\t");
            for (int j =1; j <= number;j++){
                System.out.print(i*j+"\t");
            }
            System.out.println();
        }
        System.out.print("\n"+"\n"+"\n");



        System.out.print("Prime Testing: ");
        int n = number*number;
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= i; j++) {
                if (i % j == 0 && i != j) {
                    break;
                }
                if (i % j == 0 && i == j) {
                    System.out.print(i+" ");
                }
            }
        }
        System.out.print("\n"+"\n"+"\n"+"\n");


        
        System.out.println("Infinite Series Test");
        System.out.print("Enter a second integer K: ");
        int k = s.nextInt();
        double sum = 0.0;
        double Third_number = 1;
        for (int i = 1 ; i<=k ; i++){
            Third_number = Third_number * 0.5;
            sum = sum + Third_number;
        }
        System.out.print(sum);
        // The trend is for the results to converge to 1;
    }
}
