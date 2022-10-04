public class Adder {

    public static int sum(int a, int b) {
        return Math.addExact(a,b);
    }

    public static void main(String[] args) {
        System.out.println(sum(1, 2));
    }
}
