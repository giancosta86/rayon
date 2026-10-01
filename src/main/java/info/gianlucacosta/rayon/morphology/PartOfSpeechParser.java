package info.gianlucacosta.rayon.morphology;

import java.util.Optional;
import java.util.function.Consumer;

abstract class PartOfSpeechParser<T extends PartOfSpeech> {
    protected final Consumer<Exception> exceptionConsumer;

    public PartOfSpeechParser(Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
    }

    protected abstract Optional<T> parse(String posXml);
}
