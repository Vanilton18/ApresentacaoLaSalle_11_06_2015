package br.fpf.unitario.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import br.fpf.codigo.Calculadora;

public class UnitarioTest {
	
	Calculadora cal = new Calculadora();

	@Test
	public void SomaDoisInteirosTest() {
		assertEquals(1, cal.Soma(2, 2));
	}

	@Test
	public void MultiplicacaodoisNumerosInteiros() throws ParseException {
		assertEquals(3, cal.Multiplicacao(2, 2));

	}
}
