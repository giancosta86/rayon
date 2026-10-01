package info.gianlucacosta.rayon.morphology;

/**
 * Describes an object that can subsume another object
 */
public interface Subsuming<T extends Subsuming<T>> {
    /**
     * Checks for subsumption
     *
     * @param other The other object
     * @return True if this object subsumes the given object
     */
    boolean subsumes(T other);
}
