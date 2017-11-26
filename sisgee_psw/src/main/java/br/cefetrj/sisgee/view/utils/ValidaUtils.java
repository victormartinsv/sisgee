package br.cefetrj.sisgee.view.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe criada para métodos de validação, para melhor reuso de código.
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ValidaUtils {
	
	/**
	 * Método para validar campo por tamanho, para valores de texto(String)
	 * @param campo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() > tamanho) {
			msg = "O campo " + nomeCampo + " deve ter tamanho máximo de " + tamanho + ".";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campo por tamanho, para valores inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor inteiro a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, Integer param) {
		String msg = "";
		if(param > tamanho) {
			msg = "O campo " + nomeCampo + " deve ter valor máximo de " + tamanho + ".";
		}		
		return msg;
	}	
	
	/**
	 * M�todo para validar campo com tamanho restito, para valores de texto(String)
	  * @param campo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanhoExato(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() < tamanho || param.length() > tamanho) {
			msg = "O campo " + nomeCampo + " deve ter " + tamanho + " caracteres.";
		}		
		return msg;
	}
	
	
	/**
	 * Método para validar campo obrigatório
	 * @param nomeCampo texto com o nome do campo.
	 * @param param valor a ser testado como obrigatório.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaObrigatorio(String nomeCampo, String param) {
		String msg = "";
		if(param == null || param.isEmpty()) {
			msg = "O campo " + nomeCampo + " é obrigatório.";
			System.out.println(msg);
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param param com o valor a ser convertido para integer.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaInteger(String nomeCampo, String param) {
		String msg = "";
		if(!param.matches("\\d*")) {
			msg = "O campo " + nomeCampo + " deve ser numérico.";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos numéricos de ponto flutuante
	 * @param nomeCampo texto com o nome do campo.
	 * @param param String com o valor a ser convertido para float.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaFloat(String nomeCampo, String param) {
		String msg = "";		
		try {
			@SuppressWarnings("unused")
			Float valorFloat = Float.parseFloat(param);				
		}catch(Exception e) {
			msg = "O campo " + nomeCampo + " deve ser num�rico.";
		}		
		return msg;
	}
	
	/**
	 * Método para validar campos booleanos
	 * @param nomeCampo texto com o nome do campo.
	 * @param param String com o valor a ser convertido para boolean.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaBoolean(String nomeCampo, String param) {
		String msg = "";		
		try {
			@SuppressWarnings("unused")
			Boolean valorBoolean = Boolean.parseBoolean(param);				
		}catch(Exception e) {
			msg = "O campo " + nomeCampo + " deve ser booleano(Verdadeiro ou Falso, Sim ou Não, etc).";
		}		
		return msg;
	}
	
	
	/**
	 * Método para validar campos de data (java.util.Date)
	 * @param nomeCampo texto com o nome do campo.
	 * @param param data a ser convertida para Date
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDate(String nomeCampo, String param) {
		String msg = "";
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			@SuppressWarnings("unused")
			Date dataFormatada = format.parse(param);
		}catch(Exception e) {
			msg = "O campo "+ nomeCampo + " é uma data inválida";
		}		
		return msg;
	}
	
	/**
	 * Método para validar duas datas (início e fim de um período)
	 * @param dataInicio data que marca o início do período.
	 * @param dataFim data que marca o final do período.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDatas(Date dataInicio, Date dataFim) {
		String msg = "";
		if(dataInicio.compareTo(dataFim) > 0) {
			msg = "Data final não pode ser anterior que a data inicial";
		}		
		return msg;
	}	
	
	public static String validaUf(String nomeCampo, String param) {
		String msg = "";
		boolean valid = false;
		UF [] ufs = UF.asList();
		for (UF uf : ufs) {
			if(param.equals(uf.getUf())) {
				valid = true;
				return "";
			}
		}
		if(valid == false) {
			msg = "O campo " + nomeCampo + " é uma UF inválida";
		}
		
		return msg;
	}
	
}
