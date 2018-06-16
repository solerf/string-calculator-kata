import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Felipe Soler
 * @date 03/06/2018
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = "[,\\n]";

    public int add(String numbers) {
        if(numbers == null || numbers.isEmpty()){
            return 0;
        }

        String delimiter = getDelimiter(numbers);
        return Arrays.stream(removeDelimiter(numbers).split(delimiter))
                .mapToInt(Integer::valueOf).sum();
    }

    private String removeDelimiter(String numbers) {
        Pattern pattern = Pattern.compile("//\\[(.+)]\n(.+)");
        Matcher matcher = pattern.matcher(numbers);

        if(matcher.matches()){
            return matcher.group(2);
        }
        return numbers;
    }

    private String getDelimiter(String numbers) {
        Pattern pattern = Pattern.compile("//\\[(.+)]\n(.+)");
        Matcher matcher = pattern.matcher(numbers);

        if(matcher.matches()){
            return matcher.group(1);
        }
        return DEFAULT_DELIMITER;
    }

}
