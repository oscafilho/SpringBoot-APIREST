package com.icaetano.login.validacao;

public class ErrorFormularioDTO {

	private String code;
	private String campo;
	private String erro;
	
	
	public ErrorFormularioDTO(String campo, String erro, String code) {
		this.campo = campo;
		this.erro = erro;
		this.code = code;
	}


	public String getCampo() {
		return campo;
	}


	public String getErro() {
		return erro;
	}


	public String getCode() {
		return code;
	}
	
	
	
}
