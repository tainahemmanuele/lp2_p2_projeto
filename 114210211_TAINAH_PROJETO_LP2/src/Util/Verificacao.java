package Util;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;

public class Verificacao {
	private String nome;
	private String email;

	public Verificacao(){
		
	}
	
	public String verificaNome(String nome) throws CadastroUsuarioException{
		if (nome.equals("")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		} else {
			this.nome = nome;
		}
		return nome;
	}
	
	public String verificaEmail (String email) throws CadastroUsuarioException{
		if ((email.endsWith(".com") == true)
				&& (email.endsWith(".com.br") == false)
				&& (email.matches("(.*)@(.*)")) == true) {
			this.email = email;
			return this.email;
		} else if ((email.endsWith(".com") == false)
				&& (email.endsWith(".com.br") == true)
				&& (email.matches("(.*)@(.*)")) == true) {
			this.email = email;
			return this.email;
			// }else if((email.matches("(.*)@(.*)")) == true){
			// this.email = email;
		} else {
			throw new CadastroUsuarioException(
					"Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}
	}
	
	public String verificaNomeAtualizacao(String nome) throws AtualizaUsuarioException{
		if (nome.equals("")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new AtualizaUsuarioException(
					"Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		} else {
			this.nome = nome;
		}
		return nome;
	}
	
	public String verificaEmailAtualizacao (String email) throws AtualizaUsuarioException{
		if ((email.endsWith(".com") == true)
				&& (email.endsWith(".com.br") == false)
				&& (email.matches("(.*)@(.*)")) == true) {
			this.email = email;
			return this.email;
		} else if ((email.endsWith(".com") == false)
				&& (email.endsWith(".com.br") == true)
				&& (email.matches("(.*)@(.*)")) == true) {
			this.email = email;
			return this.email;
			// }else if((email.matches("(.*)@(.*)")) == true){
			// this.email = email;
		} else {
			throw new  AtualizaUsuarioException(
							"Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
		}
	}
	
	
	
}
