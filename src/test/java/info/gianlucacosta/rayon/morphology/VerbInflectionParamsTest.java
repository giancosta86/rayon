package info.gianlucacosta.rayon.morphology;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class VerbInflectionParamsTest {
    @Test
    public void getGraceCodeShouldWorkForInfinitive() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.INFINITIVE),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmn----"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForPresentParticiple() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.PARTICIPLE),
                        Optional.of(Tense.PRESENT),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmpp---"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForPastParticiple() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.PARTICIPLE),
                        Optional.of(Tense.PAST),
                        Optional.empty(),
                        Optional.of(Number.PLURAL),
                        Optional.of(Gender.FEMININE)
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmps-pf"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForIndicativeFuture() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.INDICATIVE),
                        Optional.of(Tense.FUTURE),
                        Optional.of(Person.SECOND),
                        Optional.of(Number.PLURAL),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmif2p-"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForSubjunctiveImperfect() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.SUBJUNCTIVE),
                        Optional.of(Tense.IMPERFECT),
                        Optional.of(Person.THIRD),
                        Optional.of(Number.SINGULAR),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmsi3s-"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForConditionalPresent() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.CONDITIONAL),
                        Optional.of(Tense.PRESENT),
                        Optional.of(Person.FIRST),
                        Optional.of(Number.SINGULAR),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmcp1s-"))
        );
    }


    @Test
    public void getGraceCodeShouldWorkForImperativePresent() {
        VerbInflectionParams inflectionParams =
                new VerbInflectionParams(
                        Optional.of(Mood.IMPERATIVE),
                        Optional.of(Tense.PRESENT),
                        Optional.of(Person.SECOND),
                        Optional.of(Number.PLURAL),
                        Optional.empty()
                );

        assertThat(
                inflectionParams.getGraceCode(),
                is(equalTo("Vmmp2p-"))
        );
    }
}
