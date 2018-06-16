import java.util.Arrays;

/**
 * @author Felipe Soler
 * @date 03/06/2018
 */
public class StringCalculator {

    public int add(String numbers) {
        if(numbers == null || numbers.isEmpty()){
            return 0;
        }
        return Arrays.stream(numbers.split("[,\\n]"))
                .mapToInt(Integer::valueOf).sum();
    }

}
