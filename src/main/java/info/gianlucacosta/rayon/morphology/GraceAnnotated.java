package info.gianlucacosta.rayon.morphology;

import java.util.stream.Stream;

/**
 * Type annotated via GRACE grammatical codes - see GRACE format (Rajman et al., 1997)
 */
public interface GraceAnnotated {
    /**
     * Given a GRACE code and a GRACE-annotated enum type, returns the enum value
     * associated with the given code
     *
     * @param graceCode   The GRACE code
     * @param resultClass The enum class
     * @param <T>         The enum type
     * @return The enum value annotated with the given GRACE code
     * @throws IllegalArgumentException If no value was bound to the given GRACE code
     */
    static <T extends Enum<T> & GraceAnnotated> T getFromGrace(char graceCode, Class<T> resultClass) {
        return getFromGrace(
                String.valueOf(graceCode),
                resultClass
        );
    }


    /**
     * Given a GRACE code and a GRACE-annotated enum type, returns the enum value
     * associated with the given code
     *
     * @param graceCode   The GRACE code
     * @param resultClass The enum class
     * @param <T>         The enum type
     * @return The enum value annotated with the given GRACE code
     * @throws IllegalArgumentException If no value was bound to the given GRACE code
     */
    static <T extends Enum<T> & GraceAnnotated> T getFromGrace(String graceCode, Class<T> resultClass) {
        return
                Stream.of(
                        resultClass.getEnumConstants()
                )
                        .filter(value ->
                                value.getGraceCode().equals(graceCode)
                        )
                        .findAny()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        String.format(
                                                "Invalid GRACE code for class '%s': %s",
                                                resultClass.getSimpleName(),
                                                graceCode
                                        )
                                )
                        );


    }

    /**
     * Provides the GRACE morphological code for the object
     *
     * @return The GRACE code
     */
    String getGraceCode();
}
