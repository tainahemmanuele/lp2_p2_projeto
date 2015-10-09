package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Pop.Exceptions.DataException;

public class Util {
	private LocalDate data = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Util(){


	}
	

	public LocalDate converteData(String dataNascimento) throws DataException{
		data.format(formatter);
		String[] s = dataNascimento.split("/");
		if ((s[0].length() == 2) && (s[1].length() == 2)
				&& (s[2].length() == 4)) {
		    if ( Integer.parseInt(s[0])<=31 && Integer.parseInt(s[1])<=12 && Integer.parseInt(s[2])>= 1){
		    	return data.parse(dataNascimento, formatter);
		    }else {
				throw new DataException(
						"Erro no cadastro de Usuarios. Data nao existe.",
						2);
		    }
		} else {
			throw new DataException(
					"Erro no cadastro de Usuarios. Formato de data esta invalida.",
					2);
		}
		
	}
	
	public LocalDate converteDataAtualizacao(String dataNascimento) throws DataException{
		data.format(formatter);
		String[] s = dataNascimento.split("/");
		if ((s[0].length() == 2) && (s[1].length() == 2)
				&& (s[2].length() == 4)) {
			if ((s[0].matches("\\d+")) == true
					&& (s[1].matches("\\d+")) == true
					&& (s[2].matches("\\d+")) == true) {
		    if ( Integer.parseInt(s[0])<=31 && Integer.parseInt(s[1])<=12 && Integer.parseInt(s[2])>= 1){
		    	if (data.parse(dataNascimento, formatter).isLeapYear()== true){
		    	return data.parse(dataNascimento, formatter);
		    	}  else {
					throw new DataException(
							"Erro na atualizacao de perfil. Data nao existe.",
							2);
			    }
		    	
		    } else {
		    	System.out.println(2);
				throw new DataException(
						"Erro na atualizacao de perfil. Data nao existe.",
						2);
		    }
			} else {
				throw new DataException(
						"Erro na atualizacao de perfil. Formato de data esta invalida.",
						2);
		}} else {
			throw new DataException(
					"Erro na atualizacao de perfil. Formato de data esta invalida.",
					2);
		}
		
	}

}