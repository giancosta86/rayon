package info.gianlucacosta.rayon.morphology;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NounParser extends InflectablePartOfSpeechParser<Noun> {
    private static final Pattern genderPattern =
            Pattern.compile("<pos\\s+.*?gender=\"([mfe])\"[^>]*>");

    private Optional<Gender> gender =
            Optional.empty();

    private final Map<Number, String> inflections =
            new HashMap<>();


    public NounParser(Consumer<Exception> exceptionConsumer) {
        super(exceptionConsumer);
    }

    @Override
    protected void consumeInflection(String inflectionXml, String grace, String form) {
        if (grace.endsWith("s")) {
            inflections.put(Number.SINGULAR, form);
        } else if (grace.endsWith("p")) {
            inflections.put(Number.PLURAL, form);
        }
    }

    @Override
    protected Optional<Noun> parseAfterInflections(String posXml) {
        if (inflections.isEmpty()) {
            return Optional.empty();
        } else {
            Matcher genderMatcher =
                    genderPattern.matcher(posXml);

            if (genderMatcher.find()) {
                String genderCode =
                        genderMatcher.group(1);

                gender =
                        Optional.of(
                                GraceAnnotated.getFromGrace(genderCode, Gender.class)
                        );
            }

            return Optional.of(
                    new Noun(posXml, gender, inflections)
            );
        }
    }
}
