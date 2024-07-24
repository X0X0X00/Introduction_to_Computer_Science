// Zhenhao Zhang  zzh133@u.rochester.edu 

import java.util.Scanner;

public class SavingsProject {

    public static void main(String[] args) {


        // 变量定义 define the variables
        double initial_deposit = 0.00;
        double monthly_deposit = 0.00;
        String compounding_frequency;
        int number_of_years = 0;
        double total = 0.00; // total money
        double interest = 0.00;


        // let user enter the values
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the following data:  Principal monthly apy compounding years: ");
        Double initial_deposit_user = s.nextDouble();
        Double monthly_deposit_user = s.nextDouble();
        Double interest_rate_user = s.nextDouble();
        String compounding_frequency_user = s.next();
        Integer number_of_years_user = s.nextInt();


        //  print the values
        System.out.print("Simulating"+" "+"$");
        System.out.printf("%.2f",initial_deposit_user);
        System.out.print(" "+"plus"+" "+"$");
        System.out.printf("%.2f",monthly_deposit_user);
        System.out.print(" per month"+" at"+" ");
        System.out.printf("%.2f",interest_rate_user);
        System.out.println("%"+" "+"APY compounded yearly for "+number_of_years_user+" "+"years");


        if(compounding_frequency_user.equals("Yearly")||compounding_frequency_user.equals("yearly"))
        {
            //rotating yearly
            total=initial_deposit_user;
            for (int i =1; i <=  number_of_years_user; i++)
            {
                total+=monthly_deposit_user*12;
                interest+=(total)*(interest_rate_user/100);
                total*=1+(interest_rate_user/100);
                System.out.printf("Year %d ending balance is $%.2f with %.2f from interest.",i,total,interest);
                System.out.println();
            }
        }


        if(compounding_frequency_user.equals("Monthly")||compounding_frequency_user.equals("monthly"))
        {
            // rotating monthly
            total=initial_deposit_user;
            for (int i =1; i <=  number_of_years_user; i++)
            {
                for (int j=1 ;j<=12; j++) {
                    total += monthly_deposit_user;
                    interest += total * (interest_rate_user /100/12);
                    total *= 1 + (interest_rate_user / (12*100));

                }
                System.out.printf("Year %d ending balance is $%.2f with %.2f from interest.", i, total, interest);
                System.out.println();
                }
            }


        if(compounding_frequency_user.equals("Daily")||compounding_frequency_user.equals("daily"))
        {
            // rotating daily
            total=initial_deposit_user;
            for (int i =1; i <=  number_of_years_user; i++){
                for(int j =1; j <= 12; j++){
                    total+=monthly_deposit_user;
                    for (int k =1; k <= 30; k++){
                        double dailyInterest = (( total*(interest_rate_user/100)) / 360);
                        total+=dailyInterest;
                        interest+=dailyInterest;
                    }
                }
                System.out.printf("Year %d ending balance is $%.2f with %.2f from interest.\n",i,total,interest);
            }
        }
    }
}
