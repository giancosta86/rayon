package info.gianlucacosta.rayon.morphology;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EndingStaticTest {
    private static final String testString = "Test";


    @Test
    public void getEndingsShouldWorkIfMinimumLengthIsZero() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 0),
                        new Ending(testString, 1),
                        new Ending(testString, 2),
                        new Ending(testString, 3)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        0,
                        3
                ),

                equalTo(expectedEndings)
        );
    }


    @Test
    public void getEndingsShouldFailIfMinimumLengthIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Ending.getEndings(testString, -1, 3);
        });
    }


    @Test
    public void getEndingsShouldFailIfMaximumLengthIsLessThanMinimumLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            Ending.getEndings(testString, 3, 2);
        });
    }

    @Test
    public void getEndingsShouldWorkForValidRanges() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 1),
                        new Ending(testString, 2),
                        new Ending(testString, 3)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        1,
                        3
                ),

                equalTo(expectedEndings)
        );
    }


    @Test
    public void getEndingsShouldWorkForRangesExceedingTheSourceLength() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 1),
                        new Ending(testString, 2),
                        new Ending(testString, 3),
                        new Ending(testString, 4)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        1,
                        50
                ),

                equalTo(expectedEndings)
        );
    }


    @Test
    public void getEndingsShouldAssumeTheMinimumLengthIsOneIfUnspecified() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 1),
                        new Ending(testString, 2),
                        new Ending(testString, 3)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        3
                ),

                equalTo(expectedEndings)
        );
    }

    @Test
    public void getEndingsShouldSupportAnyValidRange() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 2),
                        new Ending(testString, 3)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        2,
                        3
                ),

                equalTo(expectedEndings)
        );
    }


    @Test
    public void getEndingsShouldSupportRangesHavingJustOneItem() {
        List<Ending> expectedEndings =
                Arrays.asList(
                        new Ending(testString, 3)
                );

        assertThat(
                Ending.getEndings(
                        testString,
                        3,
                        3
                ),

                equalTo(expectedEndings)
        );
    }
}
