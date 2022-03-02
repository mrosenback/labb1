import java.util.Arrays;

public class StringCalculator {

    public int Add(String numbers) {
        int result = 0;

        if(numbers.length() > 0) {
            result = Arrays.stream(numbers.split("[,\n/;]")).map(Integer::parseInt).reduce(0, Integer::sum);
        }

        try {
            if(result < 0) {
                throw new IllegalArgumentException("Negatives not allowed " + result);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
