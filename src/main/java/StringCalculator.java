import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    public int add(final String rawNumbers) {
        if (isEmpty(rawNumbers)) return 0;

        final List<Integer> numbers = getNumbers(rawNumbers).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        final String negatives = numbers.stream()
                .filter(n -> n < 0)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        if(!negatives.isEmpty()){
            throw new IllegalArgumentException(String.format("negatives not allowed: %s", negatives));
        }

        return numbers.stream()
                .mapToInt(n -> n)
                .filter(n -> n <= 1000)
                .sum();
    }

    private boolean isEmpty(final String rawNumbers) {
        return rawNumbers == null || rawNumbers.isEmpty();
    }

    private Collection<String> getNumbers(final String rawNumbers) {
        final String delimiter = getDelimiter(rawNumbers);
        final int noDelimiterGroup = 2;
        final Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(rawNumbers);
        String[] numbers = matcher.matches() ? matcher.group(noDelimiterGroup).split(delimiter) : rawNumbers.split(delimiter);
        return Arrays.asList(numbers);
    }

    private String getDelimiter(final String rawNumbers) {
        final int delimiterGroup = 1;
        final Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(rawNumbers);
        return matcher.matches() ? matcher.group(delimiterGroup) : DEFAULT_DELIMITER;
    }

}
