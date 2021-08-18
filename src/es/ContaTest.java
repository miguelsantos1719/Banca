package es;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConta() {
		Conta conta = new Conta();
		assertEquals(Conta.MONTANTE_MINIMO, conta.getSaldo(), "O saldo náo é igual ao montante mínimo");
	}
	
	@Test
	void testContaDouble() {
		//TESTE 1
		Conta conta = new Conta(120.0);
		assertEquals(120.0, conta.getSaldo(), "O saldo não é igual ao montante introduzido inicialmente");
		
		//TESTE 2
		assertThrows(IllegalArgumentException.class, () -> new Conta(80.0));
	}

	@Test
	void testDepositar() {
		//TESTE 1
		Conta conta = new Conta();
		double saldoAntesDeposito = conta.getSaldo();
		double montante = 150;
		conta.depositar(montante);
		assertEquals(saldoAntesDeposito + montante, conta.getSaldo(), "O saldo deve ser igual ao valor antes do deposito mais o valor depositado");
		
		//TESTE2
		Conta conta2 = new Conta();
		assertThrows(IllegalArgumentException.class, () -> conta2.depositar(-2));
		
	}

	@Test
	void testLevantar() {
		//TESTE 1
		Conta conta = new Conta();
		//double saldoAntesLevantamento = conta.getSaldo();
		conta.levantar(80);
		double saldoAposLevantamento = conta.getSaldo();
		
		assertEquals(saldoAposLevantamento, conta.getSaldo());
		
		//TESTE 2
		Conta conta2 = new Conta();
		assertThrows(IllegalArgumentException.class, () -> conta2.levantar(0));
		
		//TESTE 3
		//Conta conta3 = new Conta();
		assertThrows(IllegalArgumentException.class, () -> conta2.levantar(150));
		
	}

	@Test
	void testTransferir() {
		Conta conta1 = new Conta();
		Conta conta2 = new Conta();
		double conta1AntesTransferir = conta1.getSaldo();
		conta1.levantar(80);
		conta1.transferir(conta2, 80);
		double conta2AposTransferir = conta2.getSaldo();
		
		//TESTE 1 - TRANSFERENCIA
		assertEquals(conta1AntesTransferir - 80, conta1.getSaldo());
		assertEquals(conta2AposTransferir, conta2.getSaldo());
		
		//TESTE 2 - ILLEGAL ARGUMENT
		assertThrows(IllegalArgumentException.class, () -> conta1.transferir(conta2, -25));
		assertThrows(IllegalArgumentException.class, () -> conta1.transferir(conta2, 190));
		
		//TESTE 3 - NULL
		Conta conta3 = null;
		assertThrows(NullPointerException.class, () -> conta1.transferir(conta3, 70));
		
	}

}