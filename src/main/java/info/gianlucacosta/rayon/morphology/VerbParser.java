package info.gianlucacosta.rayon.morphology;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

class VerbParser extends InflectablePartOfSpeechParser<Verb> {
    private final Map<VerbInflectionParams, String> inflections =
            new HashMap<>();


    public VerbParser(Consumer<Exception> exceptionConsumer) {
        super(exceptionConsumer);
    }


    @Override
    protected void consumeInflection(String inflectionXml, String grace, String form) {
        VerbInflectionParams inflectionParams =
                VerbInflectionParams.getFromGrace(grace);

        inflections.put(inflectionParams, form);
    }

    @Override
    protected Optional<Verb> parseAfterInflections(String posXml) {
        if (inflections.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(
                    new Verb(posXml, inflections)
            );
        }
    }
}
