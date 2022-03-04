import java.util.Arrays;

public class StringCalculator {

    public int Add(String numbers) {
        int result = 0;

        if(numbers.length() > 0) {
            if(numbers.contains("-")) {
                throw new IllegalArgumentException("Negatives not allowed");
            }
            result = Arrays.stream(numbers.split("[,\n/;]")).map(Integer::parseInt).reduce(0, Integer::sum);
        }

        return result;
    }
}
