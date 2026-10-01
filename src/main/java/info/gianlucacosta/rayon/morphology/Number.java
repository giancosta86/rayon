package info.gianlucacosta.rayon.morphology;

public enum Number implements GraceAnnotated {
    SINGULAR("s"),
    PLURAL("p");

    private final String graceCode;

    Number(String graceCode) {
        this.graceCode = graceCode;
    }

    @Override
    public String getGraceCode() {
        return graceCode;
    }
}
