package info.gianlucacosta.rayon.morphology;

import java.util.Optional;
import java.util.function.Consumer;

class OtherPartOfSpeechParser extends PartOfSpeechParser<OtherPartOfSpeech> {
    private final String posType;

    public OtherPartOfSpeechParser(
            Consumer<Exception> exceptionConsumer,
            String posType
    ) {
        super(exceptionConsumer);
        this.posType = posType;
    }

    @Override
    protected Optional<OtherPartOfSpeech> parse(String posXml) {
        return Optional.of(
                new OtherPartOfSpeech(posXml, posType)
        );
    }
}
