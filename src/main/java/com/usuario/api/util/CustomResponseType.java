package com.usuario.api.util;

public abstract class CustomResponseType {

	private int respnseCode;
	
	private String message;	

	public CustomResponseType() {		
	}

	public CustomResponseType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getRespnseCode() {
		return respnseCode;
	}

	public void setRespnseCode(int respnseCode) {
		this.respnseCode = respnseCode;
	}

}
