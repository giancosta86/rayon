package info.gianlucacosta.rayon.morphology;

public enum Person implements GraceAnnotated {
    FIRST("1"),
    SECOND("2"),
    THIRD("3");

    private final String graceCode;

    Person(String graceCode) {
        this.graceCode = graceCode;
    }

    @Override
    public String getGraceCode() {
        return graceCode;
    }
}
