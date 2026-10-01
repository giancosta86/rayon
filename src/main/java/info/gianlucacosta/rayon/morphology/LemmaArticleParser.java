package info.gianlucacosta.rayon.morphology;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class LemmaArticleParser {
    private static final Pattern titlePattern =
            Pattern.compile("<title>([^<]+)<\\/title>");

    private static final Pattern posPattern =
            Pattern.compile("(?s)<pos type\\s*=\\s*\"([^\"]+)\"\\s+lemma\\s*=\\s*\"([^\"])\".*?<\\/pos>");

    private final Consumer<Exception> exceptionConsumer;

    public LemmaArticleParser(Consumer<Exception> exceptionConsumer) {
        this.exceptionConsumer = exceptionConsumer;
    }


    private final Set<Noun> nouns =
            new HashSet<>();

    private final Set<Verb> verbs =
            new HashSet<>();

    private final Set<Adjective> adjectives =
            new HashSet<>();

    private final Set<OtherPartOfSpeech> otherPos =
            new HashSet<>();


    public Optional<LemmaArticle> parse(String articleXml) {
        try {
            Matcher titleMatcher =
                    titlePattern.matcher(articleXml);

            if (!titleMatcher.find()) {
                return Optional.empty();
            }


            String articleTitle =
                    titleMatcher.group(1);

            Matcher posMatcher =
                    posPattern.matcher(articleXml);


            while (posMatcher.find()) {
                try {
                    String lemmaFlagText =
                            posMatcher.group(2);

                    if (lemmaFlagText.equals("1")) {
                        String posXml =
                                posMatcher.group(0);

                        String posType =
                                posMatcher.group(1);


                        switch (posType) {
                            case "nom":
                                NounParser nounParser =
                                        new NounParser(exceptionConsumer);

                                nounParser.parse(posXml)
                                        .ifPresent(nouns::add);

                                break;


                            case "adjectif":
                                AdjectiveParser adjectiveParser =
                                        new AdjectiveParser(exceptionConsumer);

                                adjectiveParser.parse(posXml)
                                        .ifPresent(adjectives::add);

                                break;


                            case "verbe":
                                VerbParser verbParser =
                                        new VerbParser(exceptionConsumer);

                                verbParser.parse(posXml)
                                        .ifPresent(verbs::add);

                                break;


                            default:
                                OtherPartOfSpeechParser otherPartOfSpeechParser =
                                        new OtherPartOfSpeechParser(exceptionConsumer, posType);

                                otherPartOfSpeechParser.parse(posXml)
                                        .ifPresent(otherPos::add);

                                break;
                        }
                    }
                } catch (Exception ex) {
                    exceptionConsumer.accept(ex);
                }
            }


            return Optional.of(
                    new LemmaArticle(
                            articleTitle,
                            articleXml,
                            nouns,
                            adjectives,
                            verbs,
                            otherPos
                    )
            );
        } catch (Exception ex) {
            exceptionConsumer.accept(ex);
            return Optional.empty();
        }
    }
}
