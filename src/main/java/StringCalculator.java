import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Felipe Soler
 * @date 03/06/2018
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = "[,\\n]";
    public static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//\\[(.+)]\n(.+)");
    public static final int DELIMITER_GROUP = 1;
    public static final int NO_DELIMITER_GROUP = 2;

    public int add(String rawNumbers) {
        if(rawNumbers == null || rawNumbers.isEmpty()){
            return 0;
        }

        String delimiter = getDelimiter(rawNumbers);
        String[] numbers = removeDelimiter(rawNumbers).split(delimiter);

        checkNegative(numbers);

        return Arrays.stream(numbers)
                .mapToInt(Integer::valueOf).sum();
    }

    private void checkNegative(String[] numbers) {
        String negatives = Arrays.stream(numbers)
                .map(Integer::valueOf)
                .filter(n -> n < 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        if(!negatives.isEmpty()){
            throw new IllegalArgumentException(String.format("negatives not allowed: %s", negatives));
        }
    }

    private String removeDelimiter(String numbers) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(numbers);

        if(matcher.matches()){
            return matcher.group(NO_DELIMITER_GROUP);
        }
        return numbers;
    }

    private String getDelimiter(String numbers) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(numbers);

        if(matcher.matches()){
            return matcher.group(DELIMITER_GROUP);
        }
        return DEFAULT_DELIMITER;
    }

}
