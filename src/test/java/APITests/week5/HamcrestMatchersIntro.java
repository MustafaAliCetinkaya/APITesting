package APITests.week5;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @DisplayName("Assertion with numbers")
    @Test
    public void simpleTest1(){

        //actual 5+5
        assertThat(5+5, is(10));//Without importing==>MatcherAssert.assertThat
        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second one accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));
        assertThat(5+5,is(lessThan(11)));
        assertThat(5+5,is(lessThanOrEqualTo(10)));
    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "EU10 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text,is("EU10 is learning Hamcrest"));
        assertThat(text,equalTo("EU10 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU10 is learning Hamcrest")));

        //check if this text starts with EU10
        assertThat(text,startsWith("EU10"));
        //now do it in case-insensitive manner
        assertThat(text,startsWithIgnoringCase("eU10"));
        //endswith
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="  ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(-34,-567,1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers,hasSize(12));
        //check if this list hasItem 77
        assertThat(listOfNumbers,hasItem(77));
        //check if this list hasItems 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));

        //check if all numbers greater than 0
        assertThat(listOfNumbers,hasItem(lessThan(0)));

    }


}