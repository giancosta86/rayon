package info.gianlucacosta.rayon.morphology;

/**
 * Generic part of speech (POS) - obtained from a <b>&lt;pos&gt;</b> tag in GLAWI.
 */
public abstract class PartOfSpeech {
    private final String posXml;

    PartOfSpeech(String posXml) {
        this.posXml = posXml;
    }

    /**
     * The original XML tag
     *
     * @return The original <b>&lt;pos...&gt;...&lt;/pos&gt;</b> tag
     */
    public String getPosXml() {
        return posXml;
    }
}
