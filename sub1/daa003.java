import java.util.*;

public class daa003 {

    public static int charConvert (char c) {
        int n = 0;
        if (c < 'K') n = c - 'A';
        else if (c < 'W') n = c - 'A' - 1;
        else if (c < 'Y') n = c - 'A' - 2;
        else n = c - 'A' - 3;
        return n;
    }

    public static int convertPlate(String reg_plate) {
        int sum = 0;
        int offset = 5290000;

        if (Character.isAlphabetic(reg_plate.charAt(0)) && (Character.isAlphabetic(reg_plate.charAt(6)))) sum += offset * 3;
        else if (Character.isAlphabetic(reg_plate.charAt(3))) sum += offset * 2;
        else if (Character.isAlphabetic(reg_plate.charAt(6))) sum += offset;

        int base = 1;
        for (int i = 7; i >= 0; --i)
        {
            if (Character.isDigit(reg_plate.charAt(i)))
            {
                sum += base * (reg_plate.charAt(i) - '0');
                base *= 10;
            }
        }
        for (int i = 7; i >= 0; --i)
        {
            if (Character.isAlphabetic(reg_plate.charAt(i)))
            {
                sum += base * (charConvert(reg_plate.charAt(i)));
                base *= 23;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        for (int i = 0; i < t; ++i)
        {
            int value1 = convertPlate(stdin.next());
            int value2 = convertPlate(stdin.next());

            System.out.println(Math.abs(value1 - value2));
       }
        stdin.close();
    }
}
