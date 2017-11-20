package br.cefetrj.sisgee.view.utils;

/**
 * Classe criada para métodos de validação, para melhor reuso de código.
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ValidaUtils {
	
	/**
	 * Método para validar campo por tamanho, para valores de texto(String)
	 * @param campo
	 * @param tamanho
	 * @param valor
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String campo, int tamanho, String valor) {
		String msg = "";
		if(valor.length() > tamanho) {
			msg = "O campo " + campo + " deve ter tamanho máximo de " + tamanho + ".";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campo por tamanho, para valores inteiros
	 * @param campo
	 * @param tamanho
	 * @param valor
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String campo, int tamanho, Integer valor) {
		String msg = "";
		if(valor > tamanho) {
			msg = "O campo " + campo + " deve ter tamanho máximo de " + tamanho + ".";
		}		
		return msg;
	}	
	
	/**
	 * Método para validar campo obrigatório
	 * @param campo
	 * @param valor
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaObrigatorio(String campo, String valor) {
		String msg = "";
		if(valor == null || valor.isEmpty()) {
			msg += "O campo " + campo + " é obrigatório.";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos inteiros
	 * @param campo
	 * @param valor
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaInteger(String campo, String valor) {
		String msg = "";
		if(!valor.matches("\\d*")) {
			msg += "O campo " + campo + " deve ser numérico.";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos de ponto flutuante
	 * @param campo
	 * @param valor
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaFloat(String campo, String valor) {
		String msg = "";		
		try {
			Float valorFloat = Float.parseFloat(valor);				
		}catch(Exception e) {
			msg += "O campo " + campo + " deve ser numérico.";
		}		
		return msg;
	}
}
