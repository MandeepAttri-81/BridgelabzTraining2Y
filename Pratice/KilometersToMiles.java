package Pratice;

import java.util.Scanner;

public class KilometersToMiles {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter kilometers: ");
        double kilometers = sc.nextDouble();

        double miles = kilometers * 0.621371;

        System.out.println("Miles = " + miles);
    }
}