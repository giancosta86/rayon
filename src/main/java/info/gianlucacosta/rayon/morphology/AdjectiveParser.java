package info.gianlucacosta.rayon.morphology;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

class AdjectiveParser extends InflectablePartOfSpeechParser<Adjective> {
    private final Map<Pair<Gender, Number>, String> inflections =
            new HashMap<>();


    public AdjectiveParser(Consumer<Exception> exceptionConsumer) {
        super(exceptionConsumer);
    }

    @Override
    protected void consumeInflection(String inflectionXml, String grace, String form) {
        String inflectionGraceCode =
                new Ending(grace, 2).getExpression();

        char genderGraceCode =
                inflectionGraceCode.charAt(0);

        Gender gender =
                GraceAnnotated.getFromGrace(genderGraceCode, Gender.class);


        char numberGraceCode =
                inflectionGraceCode.charAt(1);

        Number number =
                GraceAnnotated.getFromGrace(numberGraceCode, Number.class);


        Pair<Gender, Number> inflectionKey =
                new Pair<>(gender, number);


        inflections.put(
                inflectionKey,
                form
        );
    }

    @Override
    protected Optional<Adjective> parseAfterInflections(String posXml) {
        if (inflections.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(
                    new Adjective(posXml, inflections)
            );
        }
    }
}
