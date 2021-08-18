package es;

/**
 * Representa uma conta bancária.
 * 
 * Funciona como um conta á ordem, sem juros nem comissões.
 * Com simples operações de Levantar, Depositar e Transferir.
 * 
 * Foi implementada a Boa Prática de Verificar a Validade dos Parâmetros,
 * @see <a href="https://kea.nu/files/textbooks/new/Effective%20Java%20%282017%2C%20Addison-Wesley%29.pdf" target="_blank">Item 49 Check Parameters for Validity</a>  
 * @see <a href="https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997" target="_blank">Effective Java</a>
 * 
 * 
 * @author antrob
 */
public class Conta {
	
	/**
	 * Montante mínimo de abertura de conta
	 * 
	 */
	public static final double MONTANTE_MINIMO = 100.0;
	
	
	// Por simplicidade escolhemos o tipo double, 
	// apesar de não ser o indicado para cálculo monetário/financeiro
	private double saldo;
	
	
	// Acessores 
	
	/**
	 * Para consulta do saldo da conta.
	 * 
	 * @return a quantia existente na conta
	 */
	public double getSaldo() {
		return saldo;
	}


	// Construtores 
	
	/**
	 * Constrói uma conta igualando o saldo ao {@link #MONTANTE_MINIMO MONTANTE_MINIMO} de abertura da conta.
	 * 
	 */
	public Conta() {
		saldo = MONTANTE_MINIMO;
		
	}
	
	/**
	 * Constrói uma conta com saldo inicial igual ao valor passado como parâmetro. 
	 * 
	 * O saldo inicial da conta não pode ser inferior ao {@link #MONTANTE_MINIMO MONTANTE_MINIMO}.  
	 * 
	 * @param saldoInicial saldo inicial da conta
	 * @throws IllegalArgumentException se o valor de do saldoInicial for inferior ao {@link #MONTANTE_MINIMO MONTANTE_MINIMO}
	 */
	public Conta(double saldoInicial) {
		if(saldoInicial >= MONTANTE_MINIMO) {
			saldo = saldoInicial;
		} else
			throw new IllegalArgumentException("Saldo inicial introduzido insuficiente.");
	}
	
	// Métodos 
	
	/**
	 * Depositar uma determinada quantia na conta.
	 *  
	 * @param quantia valor a depositar 
	 * @throws IllegalArgumentException se a quantia a depositar for inferior ou igual a zero
	 */
	public void depositar( double quantia ) {
		if(quantia <= 0) {
			throw new IllegalArgumentException("Quantia a depositar inválida.");
		} else 
			saldo = saldo + quantia;
	}
	
	
	/**
	 * Levantar uma determinada quantia na conta.
	 * 
	 * @param quantia valor a levantar
	 * @throws IllegalArgumentException se a quantia a depositar for inferior ou igual a zero,
	 *         ou se a conta não tiver saldo suficiente para efectuar o levantamento
	 * 
	 */
	public void levantar( double quantia ) { 
		if(quantia <= 0) {
			throw new IllegalArgumentException("Quantia a depositar inválida.");
		} else if(quantia > saldo) {
			throw new IllegalArgumentException("Não tem saldo suficiente para levantamento da quantia indicada");
		} else
			saldo = saldo - quantia;
		
	}
	
	
	/**
	 * Transferir da conta corrente para a conta destino
	 * 
	 * @param contaDestino a conta que irá receber o montante da transferência 
	 * @param montante o montante da transferência
	 * @throws IllegalArgumentException se o montante a transferir for inferior ou igual a zero,
	 *         ou se a conta origem da transferência não tiver saldo suficiente para efectuar a transferência
	 * @throws NullPointerException caso o parâmetro contaDestino seja null        
	 */
	public void transferir( Conta contaDestino, double montante ) { 
		if(montante <= 0 ) {
			throw new IllegalArgumentException("Montante da transferência inválido.");
		} else if(montante > saldo) {
			throw new IllegalArgumentException("Não tem saldo suficiente para efeturar a transferência.");
		} else if(contaDestino == null) {
			throw new NullPointerException("Conta destino inexistente.");
		} else {
			saldo = saldo - montante;
			contaDestino.depositar(montante);
		}
		
	}

}