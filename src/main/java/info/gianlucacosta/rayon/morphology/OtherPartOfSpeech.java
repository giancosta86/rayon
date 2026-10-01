package info.gianlucacosta.rayon.morphology;

public class OtherPartOfSpeech extends PartOfSpeech {
    private final String type;

    OtherPartOfSpeech(String posXml, String type) {
        super(posXml);
        this.type = type;
    }


    public String getType() {
        return type;
    }
}
