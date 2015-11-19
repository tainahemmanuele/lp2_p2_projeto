/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;


import Pop.Exceptions.DataException;

/**
 * Classe criada com para formatar a data de nascimento do usuario e a data do
 * post.
 * 
 * @author Tainah Emmmanuele
 *
 */
public class FormataData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2471976678046711251L;

	/**
	 * Construtor de FormataData.
	 */
	public FormataData() {

	}

	/**
	 * Metodo utilizado para converter uma string data em um objeto LocalDate.
	 * 
	 * @param dataNascimento
	 *            : data a ser convertida.
	 * @return: retorna um objeto do tipo LocalDate.
	 * @throws DataException
	 *             : Excecao lancada caso a data seja invalida, tanto formato
	 *             quanto a data. Ex: 29/02/2001.
	 */
	public LocalDate converteData(String dataNascimento) throws DataException {
		String[] parteDaData = dataNascimento.split("/");
		if ((parteDaData[0].length() == 2) && (parteDaData[1].length() == 2)
				&& (parteDaData[2].length() == 4)) {
			try {
				int dia = Integer.valueOf(parteDaData[0]);
				int mes = Integer.valueOf(parteDaData[1]);
				int ano = Integer.valueOf(parteDaData[2]);
				LocalDate dataTemporaria = LocalDate.of(ano, mes, dia);
				if (dia == 29 && mes ==2) {
					if (!(dataTemporaria.isLeapYear() && dia == 29 && mes == 2)) {
						throw new DataException("Data nao existe.");
					}
				}
				return dataTemporaria;

			} catch (NumberFormatException exception) {
				throw new DataException("Formato de data esta invalida.");

			} catch (DateTimeException exception) {
				throw new DataException("Data nao existe.");

			}
		} else {
			throw new DataException("Formato de data esta invalida.");
		}

	}

	/**
	 * Metodo utilizado para converter uma string data em um objeto
	 * LocalDateTime.
	 * 
	 * @param dataNascimento
	 *            : data a ser convertida.
	 * @return: retorna um objeto do tipo LocalDateTime.
	 * @throws DataException
	 *             : Excecao lancada caso a data seja invalida, tanto formato
	 *             quanto a data. Ex: 29/02/2001.
	 */
	public LocalDateTime converteHoraData(String data) throws DataException {
		String[] quebraDataHora = data.split(" ");
		String[] parteDaData = quebraDataHora[0].split("/");
		String[] parteDaHora = quebraDataHora[1].split(":");
		if ((parteDaData[0].length() == 2) && (parteDaData[1].length() == 2)
				&& (parteDaData[2].length() == 4)) {
			try {
				int dia = Integer.valueOf(parteDaData[0]);
				int mes = Integer.valueOf(parteDaData[1]);
				int ano = Integer.valueOf(parteDaData[2]);
				int hora = Integer.valueOf(parteDaHora[0]);
				int minuto = Integer.valueOf(parteDaHora[1]);
				int segundo = Integer.valueOf(parteDaHora[2]);
				LocalDateTime dataTemporaria = LocalDateTime.of(ano, mes, dia,
						hora, minuto, segundo);
				if (dia == 29) {
					if (!(dataTemporaria.toLocalDate().isLeapYear()
							&& dia == 29 && mes == 2)) {
						throw new DataException(
								"Erro na atualizacao de perfil. Data nao existe.");
					}
				}
				return dataTemporaria;

			} catch (NumberFormatException exception) {
				throw new DataException(
						"Erro na atualizacao de perfil. Formato de data esta invalida.");

			} catch (DateTimeException exception) {
				throw new DataException(
						"Erro na atualizacao de perfil. Data nao existe.");

			}
		} else {
			throw new DataException(
					"Erro na atualizacao de perfil. Formato de data esta invalida.");
		}
	}

}
