/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Util;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.InfoUsuarioException;

public class Verificacao {
	public Verificacao() {

	}

	public String verificaNome(String nome) throws InfoUsuarioException {
		if (nome.equals("")) {
			throw new InfoUsuarioException(
					"Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new InfoUsuarioException(
					"Nome dx usuarix nao pode ser vazio.");
		} else {
			return nome;
		}
	}

	public String verificaEmail(String email) throws InfoUsuarioException {
		if (((email.endsWith(".com") || (email.endsWith(".com.br")))
				&& (email.matches("(.*)@(.*)")) == true)) {
			return email;
		} else {
			throw new InfoUsuarioException("Formato de e-mail esta invalido.");
		}
	}

	public String verificaSenha(String senha) throws InfoUsuarioException {
		if (senha.equals("")) {
			throw new InfoUsuarioException(
					"Senha dx usuarix nao pode ser vazia.");
		} else if (senha.startsWith(" ")) {
			throw new InfoUsuarioException(
					"Senha dx usuarix nao pode ser vazia.");
		} else {
			return senha;

		}
	}



}
