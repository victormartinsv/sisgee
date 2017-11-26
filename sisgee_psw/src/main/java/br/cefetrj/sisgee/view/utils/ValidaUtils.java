package br.cefetrj.sisgee.view.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe criada para mÃ©todos de validaÃ§Ã£o, para melhor reuso de cÃ³digo.
 * @author Paulo CantuÃ¡ria
 * @since 1.0
 */
public class ValidaUtils {
	
	/**
	 * MÃ©todo para validar campo por tamanho, para valores de texto(String)
	 * @param campo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() > tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_txt";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar campo por tamanho, para valores inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor inteiro a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanho(String nomeCampo, int tamanho, Integer param) {
		String msg = "";
		if(param > tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_num";
		}		
		return msg;
	}	
	
	/**
	 * MÃ©todo para validar campo com tamanho restito, para valores de texto(String)
	  * @param campo texto com o nome do campo.
	 * @param tamanho tamanho do campo.
	 * @param param valor do texto a ser testado.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaTamanhoExato(String nomeCampo, int tamanho, String param) {
		String msg = "";
		if(param.length() != tamanho) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_tamanho_exato";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar campo obrigatÃ³rio
	 * @param nomeCampo texto com o nome do campo.
	 * @param param valor a ser testado como obrigatÃ³rio.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaObrigatorio(String nomeCampo, String param) {
		String msg = "";
		if(param == null || param.isEmpty()) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_obrigatorio";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar campos numÃ©ricos inteiros
	 * @param nomeCampo texto com o nome do campo.
	 * @param param com o valor a ser convertido para integer.
	 * @return String com mensagem de erro ou vazia
	 */
	public static String validaInteger(String nomeCampo, String param) {
		String msg = "";
		if(!param.matches("\\d*")) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_numerico";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar campos numÃ©ricos de ponto flutuante
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
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_numerico";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar campos booleanos
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
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_booleano";
		}		
		return msg;
	}
	
	
	/**
	 * MÃ©todo para validar campos de data (java.util.Date)
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
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_date";
		}		
		return msg;
	}
	
	/**
	 * MÃ©todo para validar duas datas (inÃ­cio e fim de um perÃ­odo)
	 * @param dataInicio data que marca o inÃ­cio do perÃ­odo.
	 * @param dataFim data que marca o final do perÃ­odo.
	 * @return String com mensagem de erro ou vazia.
	 */
	public static String validaDatas(Date dataInicio, Date dataFim) {
		String msg = "";
		if(dataInicio.compareTo(dataFim) > 0) {
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_datas";
		}		
		return msg;
	}	
	
	
	/**
	 * MÃ©todo para validar a seleÃ§Ã£o de Estados (UFs)
	 * 
	 * @param nomeCampo
	 * @param param
	 * @return
	 */
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
			msg = "br.cefetrj.sisgee.valida_utils.msg_valida_UF";
		}
		
		return msg;
	}
		
}

