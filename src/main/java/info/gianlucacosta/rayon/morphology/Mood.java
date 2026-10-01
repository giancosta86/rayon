package info.gianlucacosta.rayon.morphology;

public enum Mood implements GraceAnnotated {
    INDICATIVE("i"),
    SUBJUNCTIVE("s"),
    CONDITIONAL("c"),
    IMPERATIVE("m"),
    INFINITIVE("n"),
    PARTICIPLE("p");

    private final String graceCode;

    Mood(String graceCode) {
        this.graceCode = graceCode;
    }

    @Override
    public String getGraceCode() {
        return graceCode;
    }
}
