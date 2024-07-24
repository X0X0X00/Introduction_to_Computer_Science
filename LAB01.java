// Zhenhao Zhang zzh133
// Lab 1 Sep 15 2022

import java.util.Scanner;

public class LAB01 {
    public static void main(String[] args) {
        System.out.println("Welcome to Zhenhao Zhang’s Demonstration ");

        Scanner s = new Scanner(System.in);
        System.out.println("My major is: Computer Science");


        System.out.print("Enter your major: ");
        String usermajor = s.nextLine();
        if(usermajor.equals("Computer Science")){
            System.out.println("Me too!");
        }else {
            System.out.println("Sounds interesting!");
        }

        System.out.print("Enter first decimal numbers:");
        Double firstnumber = s.nextDouble();
        System.out.print("Enter second decimal numbers:");
        Double secondnumber = s.nextDouble();
        System.out.print("\n"+firstnumber + " + " + secondnumber + " = ");
        System.out.printf("%4.2f", (firstnumber+secondnumber));
        System.out.print("\n"+firstnumber + " - " + secondnumber + " = ");
        System.out.printf("%4.2f",(firstnumber-secondnumber));
        System.out.print("\n"+firstnumber + " * " + secondnumber + " = ");
        System.out.printf("%4.2f", (firstnumber*secondnumber));
        System.out.print("\n"+firstnumber + " / " + secondnumber + " = ");
        System.out.printf("%4.2f", (firstnumber/secondnumber));
        


        System.out.println("\n"+"How many second you have spent writing Java programs? ");
        int time = s.nextInt();
        int ss= time %60;
        int mm= time %3600 / 60 ;
        int hh= time /3600;

        if(hh>10){
            System.out.print(hh+":");
        }
        else{
            System.out.print("0"+hh+":");
        }
        if(mm>10){
            System.out.print(mm+":");
        }
        else{
            System.out.print("0"+mm+":");
        }
        if(ss>10){
            System.out.print(ss);
        }
        else{
            System.out.print("0"+ss);
        }


    }
}

