package info.gianlucacosta.rayon.morphology;

import java.util.Collections;
import java.util.Set;

/**
 * Corresponds to a simplified view of the <b>&lt;article&gt;</b> tag in GLAWI.
 * <p>
 * Contains methods listing its main parts of speech - but analysis of the
 * original XML text structure is still possible.
 * <p>
 * <strong>Please, note:</strong> by design, only parts of speech that represent lemmas
 * are included in the article fields; if you want to inspect non-lemma <b>&lt;pos&gt;</b>
 * tags, you'll need to access the underlying XML for the whole article.
 */
public class LemmaArticle {
    private final String title;

    private final String xml;

    private final Set<Noun> nouns;
    private final Set<Adjective> adjectives;
    private final Set<Verb> verbs;
    private final Set<OtherPartOfSpeech> otherPos;


    LemmaArticle(
            String title,
            String xml,
            Set<Noun> nouns,
            Set<Adjective> adjectives,
            Set<Verb> verbs,
            Set<OtherPartOfSpeech> otherPos
    ) {
        this.title = title;
        this.xml = xml;

        this.nouns =
                Collections.unmodifiableSet(nouns);

        this.adjectives =
                Collections.unmodifiableSet(adjectives);

        this.verbs =
                Collections.unmodifiableSet(verbs);

        this.otherPos =
                Collections.unmodifiableSet(otherPos);
    }


    public String getTitle() {
        return title;
    }


    /**
     * The original XML tag
     *
     * @return The original <b>&lt;article&gt;...&lt;article&gt;</b> GLAWI tag
     */
    public String getXml() {
        return xml;
    }

    public Set<Noun> getNouns() {
        return nouns;
    }


    public Set<Verb> getVerbs() {
        return verbs;
    }


    public Set<Adjective> getAdjectives() {
        return adjectives;
    }


    public Set<OtherPartOfSpeech> getOtherPos() {
        return otherPos;
    }
}
