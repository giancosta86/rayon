package info.gianlucacosta.rayon.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * General-purpose utilities for converting to/from strings
 */
public interface StringConversions {
    static Stream<Character> stringToStream(String source) {
        return source
                .chars()
                .mapToObj(charCode -> (char) charCode);
    }


    static String streamToString(Stream<Character> source) {
        StringBuilder result = new StringBuilder();

        source.forEach(result::append);

        return result.toString();
    }


    static Deque<Character> stringToDeque(String source) {
        return
                stringToStream(source)
                        .collect(Collectors.toCollection(LinkedList::new));
    }


    static List<Character> stringToList(String source) {
        return
                stringToStream(source)
                        .collect(Collectors.toList());
    }


    static String throwableToString(Throwable throwable) {
        StringWriter stringWriter =
                new StringWriter();

        throwable.printStackTrace(
                new PrintWriter(
                        stringWriter
                )
        );

        return stringWriter.toString();
    }
}