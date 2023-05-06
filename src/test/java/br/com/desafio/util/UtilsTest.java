package br.com.desafio.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UtilsTest {
	
	@Test
	public void formatarDataOK() {
		String data = Utils.formatarData(LocalDate.now());
		assertNotNull(data);
	}
	
	@Test
	public void formatarDataNull() {
		String data = Utils.formatarData(null);
		assertEquals("", data);
	}

}
