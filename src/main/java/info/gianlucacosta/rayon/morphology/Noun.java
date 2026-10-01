package info.gianlucacosta.rayon.morphology;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Noun extends PartOfSpeech {
    private final Optional<Gender> genderOption;
    private final Map<Number, String> inflections;

    Noun(String posXml, Optional<Gender> genderOption, Map<Number, String> inflections) {
        super(posXml);
        this.genderOption = genderOption;

        this.inflections =
                Collections.unmodifiableMap(inflections);
    }


    public Optional<Gender> getGender() {
        return genderOption;
    }


    public Optional<String> getInflection(Number number) {
        return Optional.ofNullable(
                inflections.get(number)
        );
    }


    public Optional<String> getSingular() {
        return getInflection(Number.SINGULAR);
    }


    public Optional<String> getPlural() {
        return getInflection(Number.PLURAL);
    }
}
