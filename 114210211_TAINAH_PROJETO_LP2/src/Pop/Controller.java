/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.LoginException;
import Pop.Exceptions.LogoutException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Pop.Exceptions.PesquisaUsuarioException;
import Util.Util;
import Util.Verificacao;

public class Controller {
	private Usuario usuarioLogado;
	private ArrayList<Usuario> usuarios;
	private boolean statusSistema;
	private int contadorNotificacao;
	private Verificacao verificacao;
	private Util util;

	public Controller() {
		this.usuarios = new ArrayList<Usuario>();
		this.statusSistema = false;
		this.usuarioLogado = null;
	}

	public void iniciaSistema() {
		this.verificacao = new Verificacao();
		this.util = new Util();
		statusSistema = true;

	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String imagem) throws Exception {
		try{
			
			Usuario usuario = new Usuario(verificacao.verificaNome(nome),
					verificacao.verificaEmail(email),
					verificacao.verificaSenha(senha),
					util.converteData(dataNascimento), imagem);
			this.usuarios.add(usuario);
			return usuario.getEmail();
		}catch(Exception e){
			throw new CadastroUsuarioException(e);
		}
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws Exception {
		return cadastraUsuario(nome, email, senha, dataNascimento,
				"resources/default.jpg");

	}

	public void login(String email, String senha) throws LoginException,
			UsuarioException {
		Usuario usuario = null;
		if (this.usuarioLogado == null) {
			usuario = buscaUsuario(email);
			if (usuario == null) {
				throw new PesquisaUsuarioException(
						"Nao foi possivel realizar login. Um usuarix com email "
								+ email + 
										" nao esta cadastradx.");
			}
			if (usuario.getSenha().equals(senha)) {
				usuarioLogado = usuario;
				this.contadorNotificacao = 0;
			} else {
				throw new PesquisaUsuarioException(
						"Nao foi possivel realizar login. Senha invalida.");
			}

		} else {
			throw new LoginException(
					"Nao foi possivel realizar login. Um usuarix ja esta logadx: "
							+ this.usuarioLogado.getNome() + ".");
		}
	}

	public void logout() throws LogoutException {
		if (usuarioLogado != null) {
			usuarioLogado.limpaNotificacoes();
			usuarioLogado.limpaEmail();
			usuarioLogado = null;
		} else {
			throw new LogoutException();
		}
	}

	public String getInfoUsuario(String atributo, String email)
			throws UsuarioException {
		Usuario usuarioBusca = buscaUsuario(email);
		if (usuarioBusca == null) {
			throw new InfoUsuarioException("Um usuarix com email " + email
					+ " nao esta cadastradx.");
		}
		if (atributo.equals("Nome")) {
			return usuarioBusca.getNome();
		} else if (atributo.equals("Email")) {
			return usuarioBusca.getEmail();
		} else if (atributo.equals("Senha")) {
			throw new InfoUsuarioException();
		} else if (atributo.equals("Foto")) {
			return usuarioBusca.getImagem();
		} else if (atributo.equals("Data de Nascimento")) {
			return usuarioBusca.getDataNascimento().toString();
		}

		return email;

	}

	public String getInfoUsuario(String atributo) throws InfoUsuarioException {
		if (this.usuarioLogado != null) {

			if (atributo.equals("Nome")) {
				return usuarioLogado.getNome();
			} else if (atributo.equals("Email")) {
				return usuarioLogado.getEmail();
			} else if (atributo.equals("Senha")) {
				throw new InfoUsuarioException();
			} else if (atributo.equals("Foto")) {
				return usuarioLogado.getImagem();
			} else if (atributo.equals("Data de Nascimento")) {
				return usuarioLogado.getDataNascimento();
			}
		}

		return null;
	}

	public void fechaSistema() throws InfoUsuarioException {
		if (usuarioLogado != null) {
			throw new InfoUsuarioException(
					"Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		} else {
			statusSistema = false;

		}
	}

	public void removeUsuario(String email) throws UsuarioException {
		boolean statusUsuario = false;
		if (usuarioLogado == null) {
			Iterator itr = usuarios.iterator();
			while (itr.hasNext()) {
				Usuario usuario = (Usuario) itr.next();
				if (usuario.getEmail().equals(email)) {
					itr.remove();
					statusUsuario = true;
				}
			}
			if (statusUsuario == false) {
				throw new UsuarioException("Um usuarix com email " + email
						+ "nao esta cadastradx.");
			}
		}
	}

	public void atualizaPerfil(String atributo, String valor)
			throws ParseException, AtualizaUsuarioException {
		if (usuarioLogado == null) {
			throw new AtualizaUsuarioException(
					"Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
		try{
		if (atributo.equals("Nome")) {
			usuarioLogado.atualizaNome(valor);
		} else if (atributo.equals("E-mail")) {
			usuarioLogado.atualizaEmail(valor);
		} else if (atributo.equals("Foto")) {
			usuarioLogado.atualizaImagem(valor);
		} else if (atributo.equals("Data de Nascimento")) {
			usuarioLogado.atualizaDataNascimento(valor);
		}

		else if (atributo.equals("Telefone")) {
			usuarioLogado.atualizaTelefone(valor);
		}
		}catch(Exception e){
			throw new AtualizaUsuarioException(e);
		}

	}

	public void atualizaPerfil(String atributo, String valor, String valor2)
			throws InfoUsuarioException {
		if (atributo.equals("Senha")) {
			usuarioLogado.atualizaSenha(valor, valor2);
		}

	}

	public void criaPost(String mensagem, String data) throws PostException,
			DataException {
		usuarioLogado.criaPost(mensagem, data);
	}

	public String getPost(int numeroPost) {
		return usuarioLogado.getPost(numeroPost);
	}

	public String getPost(String atributo, int numeroPost) {
		return usuarioLogado.getPost(atributo, numeroPost);
	}

	public String getConteudoPost(int indice, int numeroPost)
			throws PostException {
		return usuarioLogado.getConteudoPost(indice, numeroPost);
	}

	public void adicionaAmigo(String email) {
		usuarioLogado.NotificacaoAmizade(email, this.usuarioLogado, usuarios);
	}

	public int getNotificacoes() {
		return usuarioLogado.getNotificacoes();
	}

	public void aceitaAmizade(String email) {
		for (Usuario amigo : usuarios) {
			if (amigo.getEmail().equals(email)) {
				usuarioLogado.aceitaAmigo(amigo);
				amigo.aceitaAmigo(usuarioLogado);
				amigo.adicionaNotificacao(usuarioLogado.getNome()
						+ " aceitou sua amizade.");
			}
		}
	}

	public int getQtdAmigos() {
		return usuarioLogado.getQtdAmigos();
	}

	public void rejeitaAmizade(String email) throws UsuarioException {
		Usuario usuarioNome = buscaUsuario(email);
		if (usuarioNome == null) {
			throw new UsuarioException("Um usuarix com email " + email
					+ " nao esta cadastradx.");

		}
		boolean statusConvite = false;
		for (Usuario amigoFuturo : usuarioLogado.getNotificacaoAmizade()) {
			if (amigoFuturo.getEmail().equals(email)) {
				statusConvite = true;
				amigoFuturo.adicionaNotificacao(this.usuarioLogado.getNome()
						+ " rejeitou sua amizade.");
			} else {
				statusConvite = false;
			}
		}
		if (statusConvite == false) {
			throw new UsuarioException(usuarioNome.getNome()
					+ " nao lhe enviou solicitacoes de amizade.");
		}

	}

	public String getNextNotificacao() throws NotificacoesException {
		if (contadorNotificacao == getNotificacoes()) {
			throw new NotificacoesException();
		} else {
			contadorNotificacao += 1;
			return usuarioLogado.getNextNotificacao();
		}
	}

	public void removeAmigo(String email) {
		usuarioLogado.removeAmigo(email, this.usuarioLogado.getNome());
	}

	public void curtirPost(String email, int numeroPost) throws PostException {
		usuarioLogado.curtirPost(email, numeroPost,
				this.usuarioLogado.getNome());
	}

	public Usuario buscaUsuario(String email) {
		Usuario usuario = null;
		boolean status = false;
		for (Usuario usuarioLogado : usuarios) {
			if (usuarioLogado.getEmail().equals(email)) {
				usuario = usuarioLogado;
				status = true;
				return usuario;
			} else {
				status = false;
			}
		}
		return null;
	}

}
