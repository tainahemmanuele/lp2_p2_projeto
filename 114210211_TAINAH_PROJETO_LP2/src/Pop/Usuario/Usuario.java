/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import Pop.ArquivosPost.Arquivo;
import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Util.FormataData;
import Util.Verificacao;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private String telefone;
	private String imagem;
	private String senhaAtual;
	private Mural mural;
	private ArrayList<Usuario> amigos;
	private ArrayList<String> notificacoes;
	private FormataData util;
	private Verificacao verificacao;
	private String quebraLinha = System.getProperty("line.separator");
	private String novaNotificacao;
	private ArrayList<Usuario> notificacaoAmizade;
	private int contadorNotificacoes;

	public Usuario(String nome, String email, String senha,
			LocalDate dataNascimento, String imagem) throws Exception {

		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.imagem = imagem;
		this.mural = new Mural();
		this.notificacoes = new ArrayList<String>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacaoAmizade = new ArrayList<Usuario>();
		this.util = new FormataData();
		this.verificacao = new Verificacao();
		this.contadorNotificacoes =0;

	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getDataNascimento() {
		return dataNascimento.toString();
	}

	public String getTelefone() {
		return telefone;
	}

	public String getImagem() {
		return imagem;
	}

	public void atualizaNome(String nome) throws InfoUsuarioException {
		this.nome = verificacao.verificaNome(nome);
	}

	public void atualizaImagem(String imagem) {
		this.imagem = imagem;
	}

	// Mesma coisa com essa verificacao de formato de email.
	public void atualizaEmail(String email) throws InfoUsuarioException {
		this.email = verificacao.verificaEmail(email);
	}

	public void atualizaDataNascimento(String dataNascimento)
			throws DataException {
		this.dataNascimento = util.converteData(dataNascimento);
	}

	public void atualizaTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void atualizaSenha(String senhaNova, String senhaAtual)
			throws InfoUsuarioException {
		if (senhaAtual.equals(senha)) {
			senha = senhaNova;
		} else {
			throw new InfoUsuarioException(
					"Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
	}

	@Override
	public String toString() {
		return "Usuario [getNome()=" + getNome() + ", getEmail()=" + getEmail()
				+ ", getSenha()=" + getSenha() + ", getDataNascimento()="
				+ getDataNascimento() + ", getTelefone()=" + getTelefone()
				+ ", getImagem()=" + getImagem() + "]";
	}

	public void criaPost(String mensagem, String data) throws PostException,
			DataException {
		mural.criaPost(mensagem, data);
	}

	public String getPost(int numeroPost) {
		return mural.getPost(numeroPost);
	}

	public String getConteudoPost(int indice, int numeroPost)
			throws PostException {
		return mural.getConteudoPost(indice, numeroPost);
	}

	public String getPost(String atributo, int numeroPost) {
		return mural.getPost(atributo, numeroPost);
	}

	public void adicionaNotificacao(String notificacao) {
		notificacoes.add(notificacao);
	}

	public void NotificacaoAmizade(String emailUsuario, Usuario usuarioLogado,
			ArrayList<Usuario> usuarios) {
		String novaNotificacao = "";
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(emailUsuario)) {
				novaNotificacao += usuarioLogado.getNome()
						+ " quer sua amizade.";
				usuario.adicionaNotificacao(novaNotificacao);
				usuario.adicionaEmail(usuarioLogado);

			}
		}

	}

	public int getNotificacoes() {
		return notificacoes.size();
	}

	public String getNextNotificacao() throws NotificacoesException {
		novaNotificacao = "";
		for (String notificacao : notificacoes) {
			if (notificacoes.size() >= 2) {
				novaNotificacao = notificacoes.get(contadorNotificacoes);
				contadorNotificacoes += 1;
				return novaNotificacao;
			} else {
				novaNotificacao = notificacao;
			}
		}
		return novaNotificacao;
	}

	public void limpaNotificacoes() {
		notificacoes.clear();
	}

	public void limpaEmail() {
		notificacaoAmizade.clear();
	}

	public ArrayList<Usuario> getNotificacaoAmizade() {
		return notificacaoAmizade;
	}

	public void aceitaAmigo(Usuario usuario) {
		amigos.add(usuario);
	}

	public void adicionaEmail(Usuario usuario) {
		notificacaoAmizade.add(usuario);
	}

	public int getQtdAmigos() {
		return amigos.size();
	}

	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	public void removeAmigo(String nome) {
		Iterator<Usuario> itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = itr.next();
			if (usuario.getNome().equals(nome)) {
				itr.remove();
			}
		}
	}

	public void removeAmigo(String email, String usuarioLogadoNome) {
		Iterator<Usuario> itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = itr.next();
			if (usuario.getEmail().equals(email)) {
				usuario.adicionaNotificacao(usuarioLogadoNome
						+ " removeu a sua amizade.");
				itr.remove();
				usuario.removeAmigo(usuarioLogadoNome);

			}
		}
	}

	public void curtirPost(String email, int numeroPost, String nome)
			throws PostException {
		for (Usuario amigo : amigos) {
			if (amigo.getEmail().equals(email)) {
				amigo.getPost(numeroPost);
				amigo.adicionaNotificacao(nome + " curtiu seu post de "
						+ amigo.getPost("Data", numeroPost) + ".");
			}
		}
	}
}