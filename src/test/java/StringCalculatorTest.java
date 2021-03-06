import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Felipe Soler
 * @date 03/06/2018
 */
public class StringCalculatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private StringCalculator stringCalculator;

    @Before
    public void before(){
        stringCalculator = new StringCalculator();
    }

    @Test
    public void test_step1_empty_string(){
        int actual = stringCalculator.add("");
        assertThat(actual, is(0));
    }

    @Test
    public void test_step1_one_number(){
        int actual = stringCalculator.add("1");
        assertThat(actual, is(1));
    }

    @Test
    public void test_step1_two_numbers_1(){
        int actual = stringCalculator.add("1,2");
        assertThat(actual, is(3));
    }

    @Test
    public void test_step1_two_numbers_2(){
        int actual = stringCalculator.add("3,2");
        assertThat(actual, is(5));
    }

    @Test
    public void test_step1_null_string(){
        int actual = stringCalculator.add(null);
        assertThat(actual, is(0));
    }

    @Test
    public void test_step2_multiple_numbers(){
        int actual = stringCalculator.add("3,2,1,1,5");
        assertThat(actual, is(12));
    }

    @Test
    public void test_step3_new_line_delimiter_1(){
        int actual = stringCalculator.add("3,2,1\n1,5");
        assertThat(actual, is(12));
    }

    @Test
    public void test_step3_new_line_delimiter_2(){
        int actual = stringCalculator.add("3\n2,1\n1,5,3");
        assertThat(actual, is(15));
    }

    @Test(expected = Exception.class)
    public void test_step3_new_line_delimiter_invalid(){
        stringCalculator.add("3,2,\n,1,5");
    }

    @Test
    public void test_step4_custom_delimiter_1(){
        int actual = stringCalculator.add("//[-]\n3-2-1-6");
        assertThat(actual, is(12));
    }

    @Test
    public void test_step4_custom_delimiter_2(){
        int actual = stringCalculator.add("//[!]\n3!2!1!6");
        assertThat(actual, is(12));
    }

    @Test
    public void test_step5_negative_not_allowed_1(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("negatives not allowed: -5");
        stringCalculator.add("3,2,1,-5");
    }

    @Test
    public void test_step5_negative_not_allowed_2(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("negatives not allowed: -1, -5");
        stringCalculator.add("-1,3,2,1,-5");
    }

    @Test
    public void test_step6_ignore_bigger_than_1000_1(){
        int actual = stringCalculator.add("1001,3,1000,6800");
        assertThat(actual, is(1003));
    }

    @Test
    public void test_step6_ignore_bigger_than_1000_2(){
        int actual = stringCalculator.add("//[x]\n1001x1090x1100x5000");
        assertThat(actual, is(0));
    }

    @Test
    public void test_step6_ignore_bigger_than_1000_3(){
        int actual = stringCalculator.add("11\n1090\n1100\n99\n500\n30001");
        assertThat(actual, is(610));
    }
}
