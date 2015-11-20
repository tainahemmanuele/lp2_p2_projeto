/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import Pop.TagPost;
import Pop.Exceptions.CurtidasException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PostException;
import Pop.Post.Post;
import Pop.Usuario.Feed.Feed;
import Pop.Usuario.TipoUsuario.CelebridadePop;
import Pop.Usuario.TipoUsuario.IconePop;
import Pop.Usuario.TipoUsuario.Normal;
import Pop.Usuario.TipoUsuario.Popularidade;
import Util.FormataData;
import Util.Verificacao;

/**
 * Classe criada com o objetivo de criar um objeto do tipo Usuario. Possui o
 * construtor de Usuario, alem de metodos que permite gerenciar as informacoes
 * do usuario, suas notificacoes, seus posts e todos os recursos que o +Pop
 * oferece aos usuarios.
 * 
 * @author Tainah Emmanuele Silva
 *
 */
public class Usuario implements Comparable<Usuario>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8126778503194560548L;
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private String imagem;
	private Mural mural;
	private ArrayList<Usuario> amigos;
	private ArrayList<String> notificacoes;
	private FormataData util;
	private Verificacao verificacao;
	private String novaNotificacao;
	private ArrayList<Usuario> notificacaoAmizade;
	private int contadorNotificacoes;
	private Popularidade popularidade;
	private int quantidadePops;
	private Feed feed;

	/**
	 * Contrutor usado para criar um usuario.
	 * 
	 * @param nome
	 *            : nome do usuario;
	 * @param email
	 *            : email do usuario;
	 * @param senha
	 *            : senha do usuario;
	 * @param dataNascimento
	 *            : data de nascimento do usuario;
	 * @param imagem
	 *            : imagem do usuario.
	 * @throws Exception
	 *             : Lanca excecoes caso o nome seja uma string vazia, o email
	 *             esteja em formato incorreto, a senha seja vazia e a data de
	 *             nascimento seja invalida.
	 */
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
		this.contadorNotificacoes = 0;
		this.popularidade = new Normal();
		this.quantidadePops = 0;
		this.feed = new Feed();

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

	public String getImagem() {
		return imagem;
	}

	/**
	 * Metodo utilizado para atualizar o nome do usuario.
	 * 
	 * @param nome
	 *            : nome do usuario.
	 * @throws InfoUsuarioException
	 *             : excecao lancada caso o nome seja vazio.
	 */
	public void atualizaNome(String nome) throws InfoUsuarioException {
		this.nome = verificacao.verificaNome(nome);
	}

	/**
	 * Metodo utilizado para atualizar o nome do usuario.
	 * 
	 * @param imagem
	 *            : imagem do usuario.
	 * @throws InfoUsuarioException
	 *             : excecao lancada caso a imagem seja vazia.
	 */
	public void atualizaImagem(String imagem) throws InfoUsuarioException {
		this.imagem = verificacao.verificaImagem(imagem);
	}

	/**
	 * Metodo utilizado para atualizar o nome do usuario.
	 * 
	 * @param email
	 *            : email do usuario.
	 * @throws InfoUsuarioException
	 *             : excecao lancada caso o email esteja em formato invalido.
	 */
	public void atualizaEmail(String email) throws InfoUsuarioException {
		this.email = verificacao.verificaEmail(email);
	}

	/**
	 * Metodo utilizado para atualizar a data de nascimento do usuario.
	 * 
	 * @param dataNascimento
	 *            : data de nascimento do usuario.
	 * @throws DataException
	 *             : excecao lancada caso a data esteja em formato invalido ou
	 *             nao exista.
	 */
	public void atualizaDataNascimento(String dataNascimento)
			throws DataException {
		this.dataNascimento = util.converteData(dataNascimento);
	}

	/**
	 * Metodo utilizado para atualizar a senha do usuario.
	 * 
	 * @param senhaNova
	 *            : senha que o usuario que usar;
	 * @param senhaAtual
	 *            : senha que o usuario usa.
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso a senha seja vazia.
	 */
	public void atualizaSenha(String senhaNova, String senhaAtual)
			throws InfoUsuarioException {
		if (senhaAtual.equals(senha)) {
			senha = senhaNova;
		} else {
			throw new InfoUsuarioException(
					"Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
	}

	/**
	 * Metodo utilizado para criar um post no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Mural.
	 * 
	 * @param mensagem
	 *            : mensagem do post;
	 * @param data
	 *            : data do post;
	 * @throws PostException
	 *             : Excecao lancada caso a mensagem do Post apresente algum
	 *             problema: hashtags sem # , por exemplo.
	 * @throws DataException
	 *             : Excecao lancada caso a data do post apresente problemas de
	 *             validacao e formatacao.
	 */
	public void criaPost(String mensagem, String data) throws PostException,
			DataException {
		mural.criaPost(mensagem, data);
	}

	/**
	 * Metodo utilizado para ver um determinado post criado no +Pop. Chama o
	 * metodo de mesmo nome que se encontra na classe Mural e retorna o retorno
	 * dele.
	 * 
	 * @param numeroPost
	 *            : numero do Post que se deseja ver.
	 * @return retorna um objeto do tipo Post com o conteudo do post.
	 */
	public Post getPost(int numeroPost) {
		return mural.getPost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver o conteudo de um post. Ex: arquivo e o texto do
	 * post sem as hashtags. Chama o metodo de mesmo nome que se encontra na
	 * classe Mural e retorna o retorno dele.
	 * 
	 * @param indice
	 *            : indice da lista de conteudos do post;
	 * @param numeroPost
	 *            : numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 * @throws PostException
	 *             : Excecao lancada caso o indice seja maior que o tamanho da
	 *             lista de conteudos ou indice menor que zero.
	 */
	public String getConteudoPost(int indice, int numeroPost)
			throws PostException {
		return mural.getConteudoPost(indice, numeroPost);
	}

	/**
	 * Metodo utilizado para ver determinadas partes de um post. Ex: data e
	 * mensagem do post. Chama o metodo de mesmo nome que se encontra na classe
	 * Mural e retorna o retorno dele.
	 * 
	 * @param atributo
	 *            : parte do post que se deseja ver. Ex: data.
	 * @param numeroPost
	 *            : numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 */
	public String getPost(String atributo, int numeroPost) {
		return mural.getPost(atributo, numeroPost);
	}

	/**
	 * Adiciona uma notificacao a lista de notificacoes.
	 * 
	 * @param notificacao
	 *            :notificacao a ser adicionada ao usuario.
	 */
	public void adicionaNotificacao(String notificacao) {
		notificacoes.add(notificacao);
	}

	/**
	 * Metodo utilizado para criar a notificacao de amizade e envia-la ao
	 * usuario a qual o convite foi feito.
	 * 
	 * @param emailUsuario
	 *            :email do usuario a ser adicionado como amigo;
	 * @param usuarioLogado
	 *            : usuario que envia a solicitacao;
	 * @param usuarios
	 *            : lista com os usuarios.
	 */
	public void NotificacaoAmizade(String emailUsuario, Usuario usuarioLogado,
			Usuario amigo) {
		String novaNotificacao = "";
		novaNotificacao += usuarioLogado.getNome() + " quer sua amizade.";
		amigo.adicionaNotificacao(novaNotificacao);
		amigo.adicionaEmail(usuarioLogado);

	}

	/**
	 * Metodo utilizado para ver quantidade de notificacoes do usuario.
	 * 
	 * @return: retorna quantidade de notificacoes do usuario.
	 */
	public int getNotificacoes() {
		return notificacoes.size();
	}

	/**
	 * Metodo utilizado para ver o conteudo das notificacoes do usuario.
	 * 
	 * @return: retorna uma string com a notificacao.
	 * @throws NotificacoesException
	 *             : Excecao lancada caso o usuario ja tenha visto todas as
	 *             notificacoes.
	 */
	public String getNextNotificacao() throws NotificacoesException {
		novaNotificacao = "";
		for (String notificacao : notificacoes) {
			if (notificacoes.size() >= 2) {
				novaNotificacao = notificacoes.get(contadorNotificacoes);
				if (contadorNotificacoes == notificacoes.size() - 1) {
					limpaNotificacoes();
				}
				contadorNotificacoes += 1;
				return novaNotificacao;
			} else {
				novaNotificacao = notificacao;
			}
		}
		return novaNotificacao;
	}

	/**
	 * Metodo utilizado para limpar a lista de notificacoes do usuario.
	 */
	public void limpaNotificacoes() {
		notificacoes.clear();
	}

	/**
	 * Metodo utilizado para limpar a lista de notificacoes de amizade do
	 * usuario.
	 */
	public void limpaEmail() {
		notificacaoAmizade.clear();
	}

	public ArrayList<Usuario> getNotificacaoAmizade() {
		return notificacaoAmizade;
	}

	/**
	 * Metodo utilizado para adicionar um usuario a lista de amigos.
	 * 
	 * @param usuario
	 *            : Usuario a ser adicionado.
	 */
	public void aceitaAmigo(Usuario usuario) {
		amigos.add(usuario);
	}

	/**
	 * Metodo utilizado para adicionar um usuario que enviou a notificacao para
	 * o outro usuario.
	 * 
	 * @param usuario
	 *            : Usuario a ser adicionado.
	 */
	public void adicionaEmail(Usuario usuario) {
		notificacaoAmizade.add(usuario);
	}

	/**
	 * Metodo utilizado para ver a quantidade de amigos de um usuario.Chama o
	 * metodo de mesmo nome que se encontra na classe Mural.
	 * 
	 * @return: retorna a quantidade de amigos de um usuario.
	 */
	public int getQtdAmigos() {
		return amigos.size();
	}

	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	/**
	 * Metodo utilizado para um usuario remover um amigo da lista de amigos.
	 * 
	 * @param nome
	 *            : nome do usuario que sera removido da lista de amigos.
	 */
	public void remove(String nome) {
		Iterator<Usuario> itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = itr.next();
			if (usuario.getNome().equals(nome)) {
				itr.remove();
			}
		}
	}

	/**
	 * Metodo utilizado para um usuario remover um amigo da lista de amigos.
	 * 
	 * @param email
	 *            : email do usuario que sera removido da lista de amigos.
	 */
	public void removeAmigo(String email) {
		Iterator<Usuario> itr = amigos.iterator();
		while (itr.hasNext()) {
			Usuario usuario = itr.next();
			if (usuario.getEmail().equals(email)) {
				usuario.adicionaNotificacao(nome + " removeu a sua amizade.");
				itr.remove();
				usuario.remove(nome);

			}
		}
	}

	/**
	 * Metodo utilizado para curtir um post de um amigo.
	 * 
	 * @param email
	 *            : email do amigo a qual se deseja curtir o post;
	 * @param numeroPost
	 *            : numero do post a ser curtido.
	 * @return: retorna a tag que foi adicionada no Post, caso o usuario que
	 *          curtiu seja um Icone Pop.
	 * @throws PostException
	 *             : Excecao lancada caso numero do post nao exista.
	 */
	public TagPost curtirPost(String email, int numeroPost)
			throws PostException {
		TagPost tag = null;
		for (Usuario amigo : amigos) {
			if (amigo.getEmail().equals(email)) {
				Post post = amigo.getPost(numeroPost);
				int tamanhoLista = post.getHashtags().size();
				int pontos = popularidade.adicionaPop(post);
				amigo.adicionaPops(pontos);
				amigo.adicionaNotificacao(nome + " curtiu seu post de "
						+ amigo.getPost("Data", numeroPost) + ".");
				if (post.getHashtags().size() > tamanhoLista) {
					tag = new TagPost(post.getHashtags().get(
							post.getHashtags().size() - 1));
				}
			}
		}
		return tag;
	}

	/**
	 * Metodo utilizado para rejeitar um post de um amigo.
	 * 
	 * @param email
	 *            : email do amigo a qual se deseja rejeitar o post;
	 * @param numeroPost
	 *            : numero do post a ser rejeitado.
	 * @return: retorna a tag que foi adicionada no Post, caso o usuario que
	 *          curtiu seja um Icone Pop.
	 * @throws PostException
	 *             : Excecao lancada caso numero do post nao exista.
	 */
	public TagPost rejeitarPost(String email, int numeroPost)
			throws PostException {
		TagPost tag = null;
		for (Usuario amigo : amigos) {
			if (amigo.getEmail().equals(email)) {
				Post post = amigo.getPost(numeroPost);
				int tamanhoLista = post.getHashtags().size();
				int pontos = popularidade.diminuiPop(post);
				amigo.diminuiPops(pontos);
				amigo.adicionaNotificacao(nome + " descurtiu seu post de "
						+ amigo.getPost("Data", numeroPost) + ".");
				if (post.getHashtags().size() > tamanhoLista) {
					tag = new TagPost(post.getHashtags().get(0));
				}
			}
		}
		return tag;
	}

	/**
	 * Metodo utilizado para adicionar pops ao usuario. Alem disso, possui a
	 * funcionalidade de transformar um usuario Normal em Icone Pop ou
	 * Celebridade Pop, usando para isso a quantidade de pontos que o usuario
	 * possui.
	 * 
	 * @param pops
	 *            : quantidade de pops a ser adicionado.
	 */
	public void adicionaPops(int pops) {
		this.quantidadePops += pops;
		if ((getQuantidadePops() >= 500 && getQuantidadePops() <= 1000)
				&& !(this.popularidade instanceof CelebridadePop)) {
			this.popularidade = new CelebridadePop();
		} else if (getQuantidadePops() >= 1000
				&& !(this.popularidade instanceof IconePop)) {
			this.popularidade = new IconePop();
		}
	}

	/**
	 * Metodo utilizado para diminuir pops do usuario.Alem disso, possui a
	 * funcionalidade de transformar um usuario Icone Pop em Normal ou
	 * Celebridade Pop, usando para isso a quantidade de pontos que o usuario
	 * possui.
	 * 
	 * @param pops
	 *            : quantidade de pops a ser adicionado.
	 */
	public void diminuiPops(int pops) {
		this.quantidadePops -= pops;
		if (getQuantidadePops() <= 500
				&& !(this.popularidade instanceof Normal)) {
			this.popularidade = new Normal();
		} else if ((getQuantidadePops() >= 500 && getQuantidadePops() <= 1000)
				&& !(this.popularidade instanceof CelebridadePop)) {
			this.popularidade = new CelebridadePop();
		}
	}

	/**
	 * Metodo que mostra a quantidade de pops de um usuario.
	 * 
	 * @return: quantidade de pops do usuario.
	 */
	public int getQuantidadePops() {
		return quantidadePops;
	}

	public ArrayList<String> getHashtags() {
		return mural.getHashtags();
	}

	@Override
	public String toString() {
		return "nome=" + nome + " email=" + email + " amigos=" + amigos
				+ " popularidade=" + popularidade + " quantidadePops "
				+ quantidadePops;
	}

	/**
	 * Metodo utilizado para comparar dois objetos do tipo Usuario. Um objeto
	 * Usuario e maior do que o outro se possuirem quantidade de pops distintas,
	 * e a quantidade de pops de a for maior que a de b. Se as ocorrencias forem
	 * iguais, o criterio de desempate e o email do usuario (String) do objeto
	 * Usuario que esta sendo comparado.
	 */
	@Override
	public int compareTo(Usuario usuario) {
		if (Integer.compare(quantidadePops, usuario.getQuantidadePops()) == 0) {
			return email.compareTo(usuario.getEmail());
		}
		return Integer.compare(quantidadePops, usuario.getQuantidadePops());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Equals de usuario. Dois usuario sao iguais se possuirem mesmo nome e
	 * mesmo email.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {

			Usuario usuario = (Usuario) obj;
			if (usuario.getNome().equals(this.getNome())
					&& usuario.getEmail() == this.getEmail()) {
				return true;

			} else {

				return false;
			}
		}
		return false;
	}

	/**
	 * Metodo utilizado para mostrar a popularidade de um usuario. Se ele e um
	 * usuario normal, celebridade pop ou icone pop.
	 * 
	 * @return retorna a popularidade do usuario.
	 */
	public Popularidade getPopularidade() {
		return popularidade;
	}

	/**
	 * Metodo utilizado para ver quantidade de pops de um post.Chama o metodo de
	 * mesmo nome que se encontra na classe Mural e retorna o retorno dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver a quantidade de pops.
	 * @return: retorna a quantidade de pops do post.
	 */
	public int getPopsPost(int numeroPost) {
		return mural.getPopsPost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver a quantidade de curtidas de um post.Chama o
	 * metodo de mesmo nome que se encontra na classe Mural e retorna o retorno
	 * dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as curtidas.
	 * @return: retorna o numero de curtidas do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as curtidas de um post
	 *             que nao existe.
	 */
	public int qtdCurtidasDePost(int numeroPost) throws CurtidasException {
		return mural.qtdCurtidasDePost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver a quantidade de rejeicoes de um post.Chama o
	 * metodo de mesmo nome que se encontra na classe Mural e retorna o retorno
	 * dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as rejeicoes.
	 * @return: retorna o numero de rejeicoes do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as rejeicoes de um post
	 *             que nao existe.
	 */
	public int qtdRejeicoesDePost(int numeroPost) throws CurtidasException {
		return mural.qtdRejeicoesDePost(numeroPost);
	}

	/**
	 * Metodo que permite que o usuario acesse sua lista de posts.
	 * 
	 * @return: lista de posts.
	 */
	public ArrayList<Post> getPosts() {
		return mural.getPosts();
	}

	/**
	 * Metodo utilizado para que um usuario veja a quantidade de posts que
	 * possui.Chama o metodo de mesmo nome que se encontra na classe Mural.
	 * 
	 * @return: Quantidade de posts que o usuario possui.
	 */
	public int getTotalPosts() {
		return mural.getTotalPosts();
	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por tempo. Chama o metodo de mesmo nome que se encontra
	 * na classe Feed.
	 * 
	 * @param post
	 *            : numero do post que o usuario quer ver.
	 * @return: post.
	 */

	public Post getPostFeedNoticiasRecentes(int post) {
		return feed.getPostFeedNoticiasRecentes(amigos, post);

	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por popularidade.Chama o metodo de mesmo nome que se encontra
	 * na classe Feed.
	 * 
	 * @param post
	 *            : numero do post que o usuario quer ver.
	 * @return: post.
	 */
	public Post getPostFeedNoticiasMaisPopulares(int post) {
		return feed.getPostFeedNoticiasMaisPopulares(amigos, post);
	}

}
