package stringcalculator;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",";
    private static final int MAX_NUMBER = 1000;

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = getDelimiter(numbers);
        List<Integer> numbersList = getNumbersList(numbers, delimiter);
        validateNumbers(numbersList);

        return numbersList.stream().mapToInt(Integer::intValue).sum();
    }

    private static String getDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.*)\n").matcher(numbers);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return DEFAULT_DELIMITER;
    }

    private static List<Integer> getNumbersList(String numbers, String delimiter) {
        String[] numbersArray = numbers.split(delimiter);
        return Arrays.stream(numbersArray)
                .map(String::trim)
                .map(s -> Integer.parseInt(s.substring(0, Math.min(s.length(), MAX_NUMBER_STRING_LENGTH))))
                .collect(Collectors.toList());
    }

    private static void validateNumbers(List<Integer> numbersList) {
        List<Integer> negativeNumbers = numbersList.stream()
                .filter(number -> number < 0)
                .collect(Collectors.toList());

        if (!negativeNumbers.isEmpty()) {
            throw new RuntimeException("negative numbers not allowed: " + negativeNumbers);
        }
    }

    private static final int MAX_NUMBER_STRING_LENGTH = String.valueOf(MAX_NUMBER).length();
}
