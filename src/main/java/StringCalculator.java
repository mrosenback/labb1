import java.util.Arrays;

public class StringCalculator {

    public static int Add(String numbers) {
        int result = 0;

        if(numbers.length() == 0) {
            result = 0;
        } else {
            result = Arrays.stream(numbers.split(",")).map(Integer::parseInt).reduce(0, Integer::sum);
        }
        return result;
    }
}
