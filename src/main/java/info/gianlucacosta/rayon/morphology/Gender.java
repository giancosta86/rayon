package info.gianlucacosta.rayon.morphology;

public enum Gender implements GraceAnnotated {
    MASCULINE("m"),
    FEMININE("f"),
    EPICENE("e");

    private final String graceCode;

    Gender(String graceCode) {
        this.graceCode = graceCode;
    }

    @Override
    public String getGraceCode() {
        return graceCode;
    }
}
