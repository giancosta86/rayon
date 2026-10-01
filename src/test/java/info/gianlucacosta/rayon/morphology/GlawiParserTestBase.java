package info.gianlucacosta.rayon.morphology;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public abstract class GlawiParserTestBase {
    protected void testParser(String lemma, Consumer<GlawiParser> parserTester) {
        try (GlawiParser parser = new GlawiParser(
                getClass().getResourceAsStream(
                        String.format(
                                "testLemmas/%s.xml",
                                lemma
                        )
                )
        )) {
            parserTester.accept(parser);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    protected void testArticles(String lemma, Consumer<Queue<LemmaArticle>> articlesTester) {
        testParser(lemma, parser -> {
            Queue<LemmaArticle> articles =
                    new ConcurrentLinkedQueue<>();

            parser.parse(articles::add);

            articlesTester.accept(articles);
        });
    }


    protected void testArticle(String lemma, Consumer<LemmaArticle> articleTester) {
        testArticles(lemma, articles -> {
            assertThat(
                    articles.size(),
                    is(1)
            );

            LemmaArticle article =
                    articles.poll();

            articleTester.accept(article);
        });
    }


    protected void assertNoProcessedArticles(String lemma) {
        testArticles(lemma, articles -> {
            assertThat(
                    articles,
                    is(empty())
            );
        });
    }


    protected void assertNounCount(
            LemmaArticle article,
            Optional<Gender> genderOption,
            Optional<String> singularOption,
            Optional<String> pluralOption,
            long expectedCount
    ) {
        assertThat(
                article.getNouns().stream()
                        .filter(noun ->
                                noun.getGender().equals(
                                        genderOption
                                ) &&
                                        noun.getSingular().equals(
                                                singularOption
                                        ) &&
                                        noun.getPlural().equals(
                                                pluralOption
                                        )

                        )
                        .count(),

                is(expectedCount)
        );
    }


    protected void assertAdjectiveCount(
            LemmaArticle article,
            Optional<String> masculineSingularOption,
            Optional<String> feminineSingularOption,
            Optional<String> masculinePluralOption,
            Optional<String> femininePluralOption,
            long expectedCount
    ) {
        assertThat(
                article.getAdjectives().stream()
                        .filter(adjective ->
                                adjective.getMasculine().equals(
                                        masculineSingularOption
                                ) &&
                                        adjective.getFeminine().equals(
                                                feminineSingularOption
                                        ) &&
                                        adjective.getInflection(Gender.MASCULINE, Number.PLURAL).equals(masculinePluralOption) &&
                                        adjective.getInflection(Gender.FEMININE, Number.PLURAL).equals(femininePluralOption)
                        )
                        .count(),

                is(expectedCount)
        );
    }


    protected void assertVerbCount(
            LemmaArticle article,
            Map<VerbInflectionParams, String> expectedInflections,
            long expectedCount
    ) {
        assertThat(
                article.getVerbs().stream()
                        .filter(verb ->
                                verb.getInflections().equals(expectedInflections)
                        )
                        .count(),

                is(expectedCount)
        );


        assertThat(
                article.getVerbs().stream()
                        .filter(verb ->
                                expectedInflections.entrySet().stream()
                                        .allMatch(entry -> {
                                            VerbInflectionParams inflectionParams =
                                                    entry.getKey();

                                            String expectedInflectionForm =
                                                    entry.getValue();

                                            return verb.getInflection(
                                                    inflectionParams.getMood(),
                                                    inflectionParams.getTense(),
                                                    inflectionParams.getPerson(),
                                                    inflectionParams.getNumber(),
                                                    inflectionParams.getGender()
                                            ).equals(
                                                    Optional.of(
                                                            expectedInflectionForm
                                                    )
                                            );
                                        })
                        )
                        .count(),

                is(expectedCount)
        );
    }


    protected void assertOtherPartOfSpeechCount(
            LemmaArticle article,
            String posType,
            long expectedCount
    ) {
        assertThat(
                article.getOtherPos().stream()
                        .filter(otherPos ->
                                otherPos.getType().equals(posType)
                        )
                        .count(),

                is(expectedCount)
        );
    }
}
