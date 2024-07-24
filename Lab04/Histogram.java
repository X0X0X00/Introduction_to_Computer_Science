/*
zhenhao zhang zzh133@u.rochester.edu lab4
*/

import java.util.Scanner;


public class Histogram {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Enter width / height
        System.out.println("Enter width / height: ");
        Scanner width_height = new Scanner(System.in);
        String array_range = s.nextLine();
        String[] array1 = array_range.split(" ", 2);


        // number of values
        System.out.println("How many values?");
        Integer numbers = Integer.parseInt(s.nextLine());


        //values 具体数字
        System.out.println("What are they?");
        String array_value = s.nextLine();
        String[] Input_numbers_String = array_value.split(" ", numbers);



        // String change to int  and find the max value
        int[] Input_numbers_Int = new int[Input_numbers_String.length];
        int max_height = 0;
        for (int i = 0; i < Input_numbers_String.length; i++) {
            Input_numbers_Int[i] = Integer.parseInt(Input_numbers_String[i]);
            if (max_height < Input_numbers_Int[i]) {
                max_height = Input_numbers_Int[i];
            }
        }


        // find height
        int height = Integer.parseInt(array1[1]);
        double ratio = (double) height / max_height;
        int[] height_times_ratio = new int[Input_numbers_Int.length];
        for (int i = 0; i < numbers; i++) {
            height_times_ratio[i] = (int) (Input_numbers_Int[i] * ratio);
        }
        int width = Integer.parseInt(array1[0]);
        int width_for_each_value = (int) width / numbers;


        char[][] image_array = new char[height][width]; // 2D array
        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                if ((height - 1) - height_times_ratio[i / width_for_each_value] >= j) {
                    image_array[j][i] = ' ';
                } else {
                    if (i % (width_for_each_value) == width_for_each_value-1 || i % (width_for_each_value) == 0) {
                        image_array[j][i] = '|';
                    } else {
                        image_array[j][i] = '*';
                    }
                }
            }

        }



        // run the code
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(image_array[j][i]);
            }
            System.out.println();
        }
    }
}


