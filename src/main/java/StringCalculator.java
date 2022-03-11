package main.java;

public class StringCalculator {

    public int Add(String numbers) {
        int result = 0;
        int compare;

        if(numbers.length() > 0) {
            String numbersOnly = numbers.replace(",","").replace("\n","").replace("/","").replace(";","");
            for (int i = 0; i < numbersOnly.length(); i++) {
                compare = Character.compare(numbersOnly.charAt(i), '-');
                if(compare == 0) {
                    throw new IllegalArgumentException("Negatives not allowed -" + numbersOnly.charAt(i+1));
                }
                result += Character.getNumericValue(numbersOnly.charAt(i));
            }
        }
        return result;
    }
}
