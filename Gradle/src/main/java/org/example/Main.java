package org.example;

public class Main {

    public static int add(int a, int b) {
        return Math.addExact(a, b);
    }

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int result = Main.add(a, b);

        System.out.println("The result is "+result);
    }
}