import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Felipe Soler
 * @date 03/06/2018
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = "[,\\n]";
    public static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//\\[(.+)]\n(.+)");
    public static final int DELIMITER_GROUP = 1;
    public static final int NO_DELIMITER_GROUP = 2;

    public int add(String numbers) {
        if(numbers == null || numbers.isEmpty()){
            return 0;
        }

        String delimiter = getDelimiter(numbers);
        return Arrays.stream(removeDelimiter(numbers).split(delimiter))
                .mapToInt(Integer::valueOf).sum();
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
