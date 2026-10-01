package info.gianlucacosta.rayon.phonetics;


import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class VowelsTest {
    @Test
    public void isVowelShouldDetectPlainVowels() {
        Vowels
                .PLAIN_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isVowel(vowel),
                                is(true)
                        )
                );
    }


    @Test
    public void isVowelShouldDetectDieresisVowels() {
        Vowels
                .DIERESIS_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isVowel(vowel),
                                is(true)
                        )
                );
    }


    @Test
    public void isVowelShouldNotDetectConsonants() {
        assertThat(
                Vowels.isVowel('b'),
                is(false)
        );
    }


    @Test
    public void isPlainVowelShouldDetectPlainVowels() {
        Vowels
                .PLAIN_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isPlainVowel(vowel),
                                is(true)
                        )
                );
    }


    @Test
    public void isPlainVowelShouldNotDetectDieresisVowels() {
        Vowels
                .DIERESIS_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isPlainVowel(vowel),
                                is(false)
                        )
                );
    }


    @Test
    public void isPlainVowelShouldNotDetectConsonants() {
        assertThat(
                Vowels.isPlainVowel('b'),
                is(false)
        );
    }


    @Test
    public void isDieresisVowelShouldNotDetectPlainVowels() {
        Vowels
                .PLAIN_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isDieresisVowel(vowel),
                                is(false)
                        )
                );
    }


    @Test
    public void isDieresisVowelShouldDetectDieresisVowels() {
        Vowels
                .DIERESIS_VOWELS
                .forEach(vowel ->
                        assertThat(
                                Vowels.isDieresisVowel(vowel),
                                is(true)
                        )
                );
    }


    @Test
    public void isDieresisVowelShouldNotDetectConsonants() {
        assertThat(
                Vowels.isDieresisVowel('b'),
                is(false)
        );
    }


    @Test
    public void canReceiveDieresisShouldBeTrueForPlainVowelsSupportingIt() {
        assertThat(
                Vowels.canReceiveDieresis('E'),
                is(true)
        );

        assertThat(
                Vowels.canReceiveDieresis('e'),
                is(true)
        );

        assertThat(
                Vowels.canReceiveDieresis('I'),
                is(true)
        );

        assertThat(
                Vowels.canReceiveDieresis('i'),
                is(true)
        );
    }


    @Test
    public void canReceiveDieresisShouldBeFalseForPlainVowelsNotSupportingIt() {
        Stream.of('A', 'a', 'O', 'o', 'U', 'u', 'Y', 'y')
                .forEach(vowel -> {
                    assertThat(
                            Vowels.canReceiveDieresis(vowel),
                            is(false)
                    );
                });
    }


    @Test
    public void canReceiveDieresisShouldBeFalseForDieresisVowels() {
        Vowels.DIERESIS_VOWELS.forEach(vowel ->
                assertThat(
                        Vowels.canReceiveDieresis(vowel),
                        is(false)
                )
        );
    }


    @Test
    public void canReceiveDieresisShouldBeFalseForConsonants() {
        assertThat(
                Vowels.canReceiveDieresis('x'),
                is(false)
        );
    }
}