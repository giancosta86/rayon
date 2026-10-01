package info.gianlucacosta.rayon.morphology;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Verb extends PartOfSpeech {
    private final Map<VerbInflectionParams, String> inflections;

    Verb(String posXml, Map<VerbInflectionParams, String> inflections) {
        super(posXml);

        this.inflections =
                Collections.unmodifiableMap(inflections);
    }


    public Map<VerbInflectionParams, String> getInflections() {
        return inflections;
    }


    public Optional<String> getInflection(
            Optional<Mood> mood,
            Optional<Tense> tense,
            Optional<Person> person,
            Optional<Number> number,
            Optional<Gender> gender
    ) {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        mood,
                        tense,
                        person,
                        number,
                        gender
                );

        return getInflection(inflectionParams);
    }


    public Optional<String> getInflection(VerbInflectionParams inflectionParams) {
        return Optional.ofNullable(
                inflections.get(inflectionParams)
        );
    }
}
