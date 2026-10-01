package info.gianlucacosta.rayon.morphology;

public enum Tense implements GraceAnnotated {
    PRESENT("p"),
    IMPERFECT("i"),
    PAST("s"),
    FUTURE("f");

    private final String graceCode;

    Tense(String graceCode) {
        this.graceCode = graceCode;
    }


    @Override
    public String getGraceCode() {
        return graceCode;
    }
}
