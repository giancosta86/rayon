package info.gianlucacosta.rayon.morphology.transform;

import java.util.function.Function;

/**
 * Generic morphological transform from one expression to another
 */
@FunctionalInterface
public interface Transform extends Function<String, String> {
}
