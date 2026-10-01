package info.gianlucacosta.rayon.morphology;

import javafx.util.Pair;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Adjective extends PartOfSpeech {
    private final Map<Pair<Gender, Number>, String> inflections;

    Adjective(String posXml, Map<Pair<Gender, Number>, String> inflections) {
        super(posXml);

        this.inflections =
                Collections.unmodifiableMap(inflections);
    }


    public Optional<String> getInflection(Gender gender, Number number) {
        Pair<Gender, Number> pair =
                new Pair<>(gender, number);

        return Optional.ofNullable(
                inflections.get(pair)
        );
    }


    public Optional<String> getMasculine() {
        return getInflection(Gender.MASCULINE, Number.SINGULAR);
    }


    public Optional<String> getFeminine() {
        return getInflection(Gender.FEMININE, Number.SINGULAR);
    }
}
