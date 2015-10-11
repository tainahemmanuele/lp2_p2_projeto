package Pop;

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

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Pop.Exceptions.ValidaException;
import Util.Util;
import Util.Verificacao;

public class Usuario {
	// Voce tem muitos atributos de auxilio.
	// Recomendo esquecer essa string data. Faz
	// com que a data seja um objeto LocalDate
	// que jah vem pronto. Ou seja, transforma as
	// datas de String no Controller. Ou melhor ainda,
	// Numa classe util que faz essas conversoes de String em objetos como
	// LocalDate e LocalDateTime.
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNascimento; // <-- isso vira objeto.
	private String telefone;
	private String imagem;
	private String atualiza;
	private String senhaAtual;
	SimpleDateFormat data1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Scanner ler = new Scanner(System.in);
	private boolean statusData = false;
	private Mural mural;
	private ArrayList<Usuario> amigos;
	private ArrayList<String> notificacoes;
	private LocalDate data = LocalDate.now();
	private Util  util = new Util();
	private Verificacao verificacao = new Verificacao();
	private String quebraLinha = System.getProperty("line.separator"); // <--
																		// isso
																		// pode
																		// ser
																		// constante.
																		// Bota
																		// um
																		// final.
	private String novaNotificacao;
	//Voce precisa desse contador? Se eu quero saber quantas notificacoes
	// tenho, posso usar uma lista de notificacoes. Note que o usuario pode ter varias notificacoes de curtida, por exemplo.
	private ArrayList<Usuario> notificacaoAmizade;
	private int contadorNotificacoes = 0;

	// private ArrayList <Post> posts;

	// Refatora para melhorar essa verificacao. Pensa numa classe cuja unica
	// responsabilidade
	// eh verificar esses foramtos. Pode ateh ser uma classe com metodos
	// estaticos para facilitar
	// o acesso.
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

	}

	public Usuario(String nome, String email, String senha,
			LocalDate dataNascimento) throws Exception {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.senha = senha;
		this.imagem = "resources/default.jpg";
		this.mural = new Mural();
		this.notificacoes = new ArrayList<String>();
		this.amigos = new ArrayList<Usuario>();
		this.notificacaoAmizade = new ArrayList<Usuario>();
		
	}


	// Isso pode ser feito jah na camada de Controller e passado para o usuario
	// jah como uma data.
	// Pensando em Coesao: Eh responsabilidade de um usuario converter Strings
	// para uma data?
	//R:Nao, por isso modifiquei os metodos.

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

	public void atualizaNome(String nome) throws AtualizaUsuarioException {
		this.nome = verificacao.verificaNomeAtualizacao(nome);
	}

	public void atualizaImagem(String imagem) {
		this.imagem = imagem;
	}

	//Mesma coisa com essa verificacao de formato de email.
	public void atualizaEmail(String email) throws AtualizaUsuarioException {
		this.email = verificacao.verificaEmailAtualizacao(email);
	}

	public void atualizaDataNascimento(String dataNascimento)
			throws ParseException {
		this.dataNascimento = util.converteDataAtualizacao(dataNascimento);
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
			ParseException {
		mural.criaPost(mensagem, data);
	}

	public Post getPost(int numeroPost) {
		return mural.getPost(numeroPost);
	}

	// Esse forwarding tah bem bonito. :)
	// Jah que o Usuario eh algo complexo, o mural facilita muito em modularizar
	// e deixar
	// o usuario Mural mais enxuto.
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
		for (Usuario usuario: usuarios){
			if (usuario.getEmail().equals(emailUsuario)){
				novaNotificacao += usuarioLogado.getNome() + " quer sua amizade.";
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
		Iterator itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = (Usuario) itr.next();
			if (usuario.getNome().equals(nome)) {
				itr.remove();
			}
		}
	}

	public void removeAmigo(String email, String usuarioLogadoNome) {
		Iterator itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = (Usuario) itr.next();
			if (usuario.getEmail().equals(email)) {
				usuario.adicionaNotificacao(usuarioLogadoNome
						+ " removeu a sua amizade.");
				itr.remove();
				usuario.removeAmigo(usuarioLogadoNome);

			}
		}
	}

	public void curtirPost(String email, int numeroPost, String nome) {
		for (Usuario amigo : amigos) {
			if (amigo.getEmail().equals(email)) {
				Post post = amigo.getPost(numeroPost);
				amigo.adicionaNotificacao(nome + " curtiu seu post de "
						+ post.getDataPost() + ".");
			}
		}
	}
}
