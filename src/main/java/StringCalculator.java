package main.java;

public class StringCalculator implements Logger {

    public int Add(String numbers) {
        int result = 0;
        StringBuilder n = new StringBuilder();

        if(numbers.length() > 0) {
            for (int i = 0; i < numbers.length(); i++) {
                if(numbers.charAt(i) == '-') {
                    throw new IllegalArgumentException("Negatives not allowed -" + numbers.charAt(i+1));
                } if(numbers.charAt(i) == ',' || numbers.charAt(i) == '\n' || numbers.charAt(i) == '/' || numbers.charAt(i) == ';') {
                    if(!n.isEmpty()) {
                        result += Integer.parseInt(n.toString());
                        n = new StringBuilder();
                    }
                } else {
                    n.append(numbers.charAt(i));
                    if(i == numbers.length() - 1) {
                        result += Integer.parseInt(n.toString());
                    } if(Integer.parseInt(String.valueOf(n)) > 1000) {
                        log(Integer.parseInt(String.valueOf(n)));
                    }
                }
            }
        }
        return result;
    }

    public void log(int number) {
        System.out.println("A number bigger than 1000 is detected: " + number);
    }
}
