public class CodingBat {
    public static boolean sleepIn(boolean weekday, boolean vacation) {
        if ((weekday == false || weekday == true) && vacation == true ) {
            return true;
        }
        if (weekday == false && vacation == false) {
            return true;
        }
        return false;
    }

    public static boolean monkeyTrouble(boolean aSmile, boolean bSmile) {
        if ((aSmile == true && bSmile == true) || (aSmile == false && bSmile == false)) {
            return true;
        }
        return false;
    }
    public static int countEvens(int[] nums) {
        int evens = 0;
        for ( int i = 0; i < nums.length; i ++) {
            if ( nums[i] % 2 == 0) {
                evens++;
            }
        }
        return evens;
    }

    public static String helloName(String name) {
        return "Hello " + name + "!";
    }


    public static void main(String[] args) {

        System.out.println("sleepIn(false, false): " + sleepIn(false, false));
        System.out.println("sleepIn(true, true): " + sleepIn(true, true));

        System.out.println("monkeyTrouble(true, true): " + monkeyTrouble(true, true));

        System.out.println("monkeyTrouble(false, false): " + monkeyTrouble(false, false));

        int[] numbers = {1, 2, 4, 6, 5, 7, 9};
        System.out.println("countEvens([1,2,4,6,5,7,9]): " + countEvens(numbers));

        System.out.println("helloName(\"Bob\"): " + helloName("Bobbie"));
    }
}





