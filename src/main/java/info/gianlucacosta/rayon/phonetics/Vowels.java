package info.gianlucacosta.rayon.phonetics;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public interface Vowels {
    Set<Character> PLAIN_VOWELS =
            Stream.of('A', 'E', 'I', 'O', 'U', 'Y', 'a', 'e', 'i', 'o', 'u', 'y')
                    .collect(Collectors.toSet());


    Set<Character> DIERESIS_VOWELS =
            Stream.of('Ë', 'ë', 'Ï', 'ï')
                    .collect(Collectors.toSet());


    Set<Character> VOWELS =
            Stream.concat(
                    PLAIN_VOWELS.stream(),
                    DIERESIS_VOWELS.stream()
            )
                    .collect(Collectors.toSet());


    static boolean isPlainVowel(char c) {
        return PLAIN_VOWELS.contains(c);
    }


    static boolean isDieresisVowel(char c) {
        return DIERESIS_VOWELS.contains(c);
    }


    static boolean isVowel(char c) {
        return VOWELS.contains(c);
    }


    static boolean canReceiveDieresis(char c) {
        switch (c) {
            case 'E':
            case 'e':
            case 'I':
            case 'i':
                return true;

            default:
                return false;
        }
    }
}