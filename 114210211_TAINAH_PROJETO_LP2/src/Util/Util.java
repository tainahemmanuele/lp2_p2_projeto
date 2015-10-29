/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Pop.Exceptions.DataException;

public class Util {
	private LocalDate data = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");
	private LocalDateTime dataHora = LocalDateTime.now();
	private DateTimeFormatter formatterPost = DateTimeFormatter
			.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Util() {

	}

	public LocalDate converteData(String dataNascimento) throws DataException {
		String[] parteDaData = dataNascimento.split("/");		
		if ((parteDaData[0].length() == 2) && (parteDaData[1].length() == 2) && (parteDaData[2].length() == 4)) {
				try{
					int dia = Integer.valueOf(parteDaData[0]);
					int mes = Integer.valueOf(parteDaData[1]);
					int ano = Integer.valueOf(parteDaData[2]);
					LocalDate dataTemporaria = LocalDate.of(ano, mes, dia);
					if(dia == 29){
						if(!(dataTemporaria.isLeapYear() && dia == 29 && mes == 2)){
							throw new DataException("Data nao existe."); 
						}
					}
					return dataTemporaria;
					
				}catch(NumberFormatException exception){
					throw new DataException("Formato de data esta invalida.");

				}catch(DateTimeException exception){
					throw new DataException("Data nao existe.");

				}
		}else {
			throw new DataException("Formato de data esta invalida.");
		}	

	}


	public LocalDateTime converteHoraData(String data) throws DataException {
		dataHora.format(formatterPost);
		String[] quebraDataHora = data.split(" ");
		String[] parteDaData= quebraDataHora[0].split("/");
		String [] parteDaHora = quebraDataHora[1].split(":");
		if ((parteDaData[0].length() == 2) && (parteDaData[1].length() == 2) && (parteDaData[2].length() == 4)) {
			try{
				int dia = Integer.valueOf(parteDaData[0]);
				int mes = Integer.valueOf(parteDaData[1]);
				int ano = Integer.valueOf(parteDaData[2]);
				int hora = Integer.valueOf(parteDaHora[0]);
				int minuto = Integer.valueOf(parteDaHora[1]);
				int segundo = Integer.valueOf(parteDaHora[2]);
				LocalDateTime dataTemporaria = LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
				if(dia == 29){
					if(!(dataTemporaria.toLocalDate().isLeapYear() && dia == 29 && mes == 2)){
						throw new DataException("Erro na atualizacao de perfil. Data nao existe."); 
					}
				}
				return dataTemporaria;
				
			}catch(NumberFormatException exception){
				throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.");

			}catch(DateTimeException exception){
				throw new DataException("Erro na atualizacao de perfil. Data nao existe.");

			}
	}else {
		throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.");
	}	
	}

}
