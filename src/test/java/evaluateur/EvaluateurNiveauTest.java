package evaluateur;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


@DisplayName("Tests fonctionnels de la classe EvaluateurNiveau")
class EvaluateurNiveauTest {

	@BeforeAll
	public static void setEvaluateurNiveau() {
	}

	@DisplayName("Tests aux limites EvaluateurNiveau")
	@ParameterizedTest(name = "{index} => cours={0}, examen={1}, attendu={2}")
	@CsvFileSource(resources = "/test-data-examen.csv")
	void testsLimitesEvaluateurNiveau(String cours, String examen, String attendu) throws Exception {
		// junit 5 syntax
		//assertEquals(EvaluateurNiveau.evaluerNiveau(cours, examen), attendu);
		
		// hamcrest
		assertThat(EvaluateurNiveau.evaluerNiveau(cours, examen), is(equalTo(attendu)));
	}

	@Test
	public void testMauvaisFormatCours() {
		Assertions.assertThrows(Exception.class, () -> {
			EvaluateurNiveau.evaluerNiveau("1C", "70");
		});
	}

	@Test
	public void testValeurHorsBornesExamen() {
		Assertions.assertThrows(Exception.class, () -> {
			EvaluateurNiveau.evaluerNiveau("20", "76");
		});
	}
}
