package info.gianlucacosta.rayon.morphology;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class InflectablePartOfSpeechParser<T extends PartOfSpeech>
        extends PartOfSpeechParser<T> {
    private static final Pattern inflectionPattern =
            Pattern.compile("<inflection\\s+form\\s*=\\s*\"([^\"]+)\"\\s+gracePOS\\s*=\\s*\"([^\"]+)\"[^>]*>");

    public InflectablePartOfSpeechParser(Consumer<Exception> exceptionConsumer) {
        super(exceptionConsumer);
    }

    @Override
    protected Optional<T> parse(String posXml) {
        parseInflections(posXml);

        return parseAfterInflections(posXml);
    }


    private void parseInflections(String posXml) {
        Matcher matcher =
                inflectionPattern.matcher(posXml);

        while (matcher.find()) {
            String inflectionXml =
                    matcher.group(0);

            String form =
                    matcher.group(1);

            String grace =
                    matcher.group(2);


            try {
                consumeInflection(
                        inflectionXml,
                        grace,
                        form
                );
            } catch (Exception ex) {
                exceptionConsumer.accept(ex);
            }
        }
    }

    protected abstract void consumeInflection(
            String inflectionXml,
            String grace,
            String form
    );

    protected abstract Optional<T> parseAfterInflections(String posXml);
}
