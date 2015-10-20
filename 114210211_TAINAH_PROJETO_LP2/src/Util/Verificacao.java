/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Util;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;

public class Verificacao {
	public Verificacao() {

	}

	public String verificaNome(String nome) throws CadastroUsuarioException {
		if (nome.equals("")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else {
			return nome;
		}
	}

	public String verificaEmail(String email) throws CadastroUsuarioException {
		if ((email.endsWith(".com") == true)
				&& (email.endsWith(".com.br") == false)
				&& (email.matches("(.*)@(.*)")) == true) {
			return email;
		} else if ((email.endsWith(".com") == false)
				&& (email.endsWith(".com.br") == true)
				&& (email.matches("(.*)@(.*)")) == true) {
			return email;
		} else {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}
	}

	public String verificaSenha(String senha) throws CadastroUsuarioException {
		if (senha.equals("")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazia.");
		} else if (senha.startsWith(" ")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazia.");
		} else {
			return senha;

		}
	}

	public String verificaNomeAtualizacao(String nome)
			throws AtualizaUsuarioException {
		if (nome.equals("")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		} else {
			return nome;
		}
	}

	public String verificaEmailAtualizacao(String email)
			throws AtualizaUsuarioException {
		if ((email.endsWith(".com") == true)
				&& (email.endsWith(".com.br") == false)
				&& (email.matches("(.*)@(.*)")) == true) {
			return email;
		} else if ((email.endsWith(".com") == false)
				&& (email.endsWith(".com.br") == true)
				&& (email.matches("(.*)@(.*)")) == true) {
			return email;
		} else {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
		}
	}

	public String verificaSenhaAtualizacao(String senha)
			throws AtualizaUsuarioException {
		if (senha.equals("")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Senha dx usuarix nao pode ser vazia.");
		} else if (senha.startsWith(" ")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Senha dx usuarix nao pode ser vazia.");
		} else {
			return senha;

		}
	}

}
