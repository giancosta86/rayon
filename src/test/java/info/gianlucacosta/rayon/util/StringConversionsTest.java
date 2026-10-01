package info.gianlucacosta.rayon.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class StringConversionsTest {
    private final String testString = "This is a test";

    private final List<Character> testList = Arrays.asList('T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't');


    @Test
    public void stringToStreamShouldWork() {
        List<Character> retrievedList =
                StringConversions
                        .stringToStream(testString)
                        .collect(Collectors.toList());

        assertThat(
                retrievedList,
                equalTo(testList)
        );
    }


    @Test
    public void streamToStringShouldWork() {
        String retrievedString =
                StringConversions
                        .streamToString(testList.stream());

        assertThat(
                retrievedString,
                equalTo(testString)
        );
    }


    @Test
    public void stringToDequeShouldWork() {
        Deque<Character> retrievedDeque =
                StringConversions.stringToDeque(testString);

        Deque<Character> expectedDeque =
                new LinkedList<>(testList);

        assertThat(
                retrievedDeque,
                equalTo(expectedDeque)
        );
    }


    @Test
    public void stringToListShouldWork() {
        List<Character> retrievedList =
                StringConversions.stringToList(testString);

        assertThat(
                retrievedList,
                equalTo(testList)
        );
    }
}