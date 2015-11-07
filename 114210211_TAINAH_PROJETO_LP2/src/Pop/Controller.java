/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.CurtidasException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.LoginException;
import Pop.Exceptions.LogoutException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PopsException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Pop.Exceptions.PesquisaUsuarioException;
import Pop.Exceptions.UsuarioLogadoException;
import Pop.Post.FactoryPost;
import Pop.Post.Post;
import Pop.Post.ArquivosPost.Arquivo;
import Pop.Usuario.Usuario;
import Pop.Usuario.TipoUsuario.Popularidade;
import Util.FormataData;
import Util.Verificacao;
/**
 * Classe criada com o objetivo de gerenciar todo o sistema do +Pop. A partir das funcionalidades dessa
 * classe que se pode cadastrar um usuario, criar um post e administrar as informacoes acerca do sistema
 * +Pop;
 * @author Tainah Emmanuele
 *
 */
public class Controller implements Serializable{
	private Usuario usuarioLogado;
	private ArrayList<Usuario> usuarios;
	private boolean statusSistema;
	private int contadorNotificacao;
	private Verificacao verificacao;
	private FormataData formataData;
	private ArrayList <tagPost> hashtags;
	private String usuariosMaisPopulares;
	private String usuariosMenosPopulares;
	private String hashtagsTop;
	



/**
 * Construtor da classe Controller.
 */
	public Controller() {
		this.usuarios = new ArrayList<Usuario>();
		this.statusSistema = false;
		this.usuarioLogado = null;
		this.hashtags = new ArrayList<tagPost>();
		this.hashtagsTop = "Trending Topics: ";

	}

	/**
	 * Metodo utilizado para iniciar o sistema +Pop.
	 */
	public void iniciaSistema() {
		this.verificacao = new Verificacao();
		this.formataData = new FormataData();
		statusSistema = true;

	}

	/**
	 *  Metodo utilizado para cadastrar um usuario no +Pop. Cria uma instancia de objeto do tipo Usuario.
 * @param nome: nome do usuario;
 * @param email: email do usuario;
 * @param senha: senha do usuario;
 * @param dataNascimento: data de nascimento do usuario.
 * @return : retorna uma String com o email do usuario. Esse retorno serve para sinalizar que 
 * deu certo o cadastro do usuario.
 * @throws Exception: Lanca excecoes caso o nome seja uma string vazia, o email esteja em formato incorreto,
 * a senha seja vazia e a data de nascimento seja invalida.
	 */
	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String imagem) throws Exception {
		try{
			
			Usuario usuario = new Usuario(verificacao.verificaNome(nome),
					verificacao.verificaEmail(email),
					verificacao.verificaSenha(senha),
					formataData.converteData(dataNascimento), verificacao.verificaImagem(imagem));
			this.usuarios.add(usuario);
			return usuario.getEmail();
		}catch(Exception e){
			throw new CadastroUsuarioException(e);
		}
	}

	/**
	 *  Metodo utilizado para cadastrar um usuario no +Pop. Cria uma instancia de objeto do tipo Usuario.
	 *  Para isso, utiliza sobrecarga de metodo, ao utilizar como retorno a chamada do metodo de mesmo nome
	 *  mas de assinatura distinta que se encontra no Controller.
 * @param nome: nome do usuario;
 * @param email: email do usuario;
 * @param senha: senha do usuario;
 * @param dataNascimento: data de nascimento do usuario.
 * @return : retorna uma String com o email do usuario. Esse retorno serve para sinalizar que 
 * deu certo o cadastro do usuario.
 * @throws Exception: Lanca excecoes caso o nome seja uma string vazia, o email esteja em formato incorreto,
 * a senha seja vazia e a data de nascimento seja invalida.
	 */
	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws Exception {
		return cadastraUsuario(nome, email, senha, dataNascimento,
				"resources/default.jpg");

	}

	
	/**
	 * Metodo utilizado para um usuario logar no sistema +Pop.
	 * @param email: email do usuario;
	 * @param senha: senha do usuario.
	 * @throws LoginException :Excecao lancada caso ja tenha um usuario logado.
	 * @throws UsuarioException: Excecao lancada caso o usuario nao esteja cadastrado no sistema +Pop.
	 */
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

	/**
	 * Metodo utilizado para um usuario deslogar (sair) do sistema +Pop.
	 * @throws LogoutException:Excecao lancada caso se tente sair do sistema , mas nao exista usuario
	 * logado.
	 */
	public void logout() throws LogoutException {
		if (usuarioLogado != null) {
			usuarioLogado.limpaNotificacoes();
			usuarioLogado.limpaEmail();
			usuarioLogado = null;
		} else {
			throw new LogoutException();
		}
	}

	/**
	 * Metodo utilizado para ver a informacao de um usuario que possua cadastro no +Pop.
	 * @param atributo: informacao que se deseja ver. Ex: nome, email...
	 * @param email: email do usuario que se deseja ver a informacao;
	 * @return: retorna a informacao pesquisada.
	 * @throws UsuarioException: Excecao lancada caso o usuario nao possua cadastro no +Pop.
	 */
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

	/**
	 * Metodo utilizado para ver a informacao de um usuario que esta logado no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param atributo: informacao que se deseja ver. Ex nome, email...
	 * @return:  retorna a informacao pesquisada.
	 * @throws UsuarioException: Excecao lancada caso se deseje ver a senha do usuario.
	 */
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

	/**
	 * Metodo utilizado parafechar o sistema +Pop.
	 * @throws InfoUsuarioException: Excecao ancada caso algum usuario ainda esteja logado no sistema.
	 */
	public void fechaSistema() throws InfoUsuarioException {
		if (usuarioLogado != null) {
			throw new InfoUsuarioException(
					"Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		} else {
			statusSistema = false;

		}
	}

	/**
	 * Metodo utilizado para remover o cadastro de um usuario do +Pop.
	 * @param email: email do usuario que se deseja remover.
	 * @throws UsuarioException: Excecao lancada caso o usuario a ser removido, nao esteja cadastrado 
	 * no +Pop.
	 */
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

	/**
	 * Metodo utilizado para que um usuario atualize suas informacoes pessoais ou as adicione no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param atributo: informacao a ser atualizada. ex: telefone, email...
	 * @param valor: nova informacao atualizada.
	 * @throws UsuarioException: Excecao lancada caso o usuario tente mudar as informacoes, sem estar logado no
	 * sistema +Pop.
	 */
	public void atualizaPerfil(String atributo, String valor)
			throws  AtualizaUsuarioException, UsuarioLogadoException {
		try{
		if (usuarioLogado == null) {
			throw new  UsuarioLogadoException();
					
		}
		if (atributo.equals("Nome")) {
			usuarioLogado.atualizaNome(valor);
		} else if (atributo.equals("E-mail")) {
			usuarioLogado.atualizaEmail(valor);
		} else if (atributo.equals("Foto")) {
			usuarioLogado.atualizaImagem(valor);
		} else if (atributo.equals("Data de Nascimento")) {
			usuarioLogado.atualizaDataNascimento(valor);
		}

		}catch(Exception e){
			throw new AtualizaUsuarioException(e);
		}

	}

	/**
	 * Metodo utilizado para atualizar/modificar senha do usuario.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param atributo: informacao a ser atualizada, no caso, a senha;
	 * @param valor: senha atual;
	 * @param valor2: senha que o usuario deseja utilizar.
	 * @throws UsuarioException: Excecao lancada caso o usuario nao coloque a senha atual igual a que ele logou no sistema.
	 */
	public void atualizaPerfil(String atributo, String valor, String valor2)
			throws InfoUsuarioException {
		if (atributo.equals("Senha")) {
			usuarioLogado.atualizaSenha(valor, valor2);
		}

	}

	/**
	 * Metodo utilizado para criar um post no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param mensagem: mensagem do post;
	 * @param data: data do post;
	 * @throws PostException: Excecao lancada caso a mensagem do Post apresente algum problema: hashtags
	 * sem # , por exemplo.
	 * @throws DataException: Excecao lancada caso a data do post apresente problemas de validacao e formatacao.
	 */
	public void criaPost(String mensagem, String data) throws PostException,
			DataException {
		usuarioLogado.criaPost(mensagem, data);
		adicionaHashtag();	
	}

	/**
	 * Metodo utilizado para ver um determinado post criado no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param numeroPost: numero do Post que se deseja ver.
	 * @return retorna um objeto do tipo Post com o conteudo do post.
	 */
	public Post getPost(int numeroPost) {
		return usuarioLogado.getPost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver determinadas partes de um post. Ex: data e mensagem do post.
	 * Chama o metodo de mesmo nome que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param atributo: parte do post que se deseja ver. Ex: data.
	 * @param numeroPost: numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 */
	public String getPost(String atributo, int numeroPost) {
		return usuarioLogado.getPost(atributo, numeroPost);
	}

	/**
	 * Metodo utilizado para ver o conteudo de um post. Ex: arquivo e o texto do post sem as hashtags.
	 * Chama o metodo de mesmo nome que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param indice: indice da lista de conteudos do post;
	 * @param numeroPost: numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 * @throws PostException: Excecao lancada caso o indice seja maior que o tamanho da lista de conteudos ou indice menor que zero.
	 */
	public String getConteudoPost(int indice, int numeroPost)
			throws PostException {
		return usuarioLogado.getConteudoPost(indice, numeroPost);
	}

	/**
	 * Metodo utilizado para adicionar um amigo no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param email: email do usuario que se deseja adicionar como amigo.
	 */
	public void adicionaAmigo(String email) {
		usuarioLogado.NotificacaoAmizade(email, this.usuarioLogado, usuarios);
	}

	/**
	 * Metodo utilizado para ver quantidade de notificacoes do usuario. Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @return: retorna quantidade de notificacoes do usuario.
	 */
	public int getNotificacoes() {
		return usuarioLogado.getNotificacoes();
	}

	/**
	 * Metodo utilizado para um usuario aceitar ser amigo de outro usuario.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param email: email do usuario a ser adicionado.
	 * @throws UsuarioException: Excecao lancada caso deseje adicionar amizade de um usuario que
	 * nao enviou solicitacao.
	 */
	public void aceitaAmizade(String email) throws UsuarioException{
		Usuario usuarioNome = buscaUsuario(email);
		if (usuarioNome == null) {
			throw new UsuarioException("Um usuarix com email " + email
					+ " nao esta cadastradx.");

		}
		boolean statusConvite = false;
		for (Usuario amigoFuturo : usuarioLogado.getNotificacaoAmizade()) {
			if (amigoFuturo.getEmail().equals(email)) {
				statusConvite = true;
				amigoFuturo.aceitaAmigo(usuarioLogado);
				usuarioLogado.aceitaAmigo(amigoFuturo);
				amigoFuturo.adicionaNotificacao(this.usuarioLogado.getNome()
						+ " aceitou sua amizade.");
		}
		}
		if (statusConvite == false) {
			throw new UsuarioException(usuarioNome.getNome()
					+ " nao lhe enviou solicitacoes de amizade.");
		}
	}

	/**
	 * Metodo utilizado para ver a quantidade de amigos de um usuario.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @return: retorna a quantidade de amigos de um usuario.
	 */
	public int getQtdAmigos() {
		return usuarioLogado.getQtdAmigos();
	}

	
	/**
	 * Metodo utilizado para que um usuario rejeite ser amigo de outro usuario.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param email: email do usuario que deseja recusar a amizade.
	 * @throws UsuarioException: Excecao lancada caso deseje recusar a amizade de um usuario que
	 * nao enviou solicitacao.
	 */
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
		}
		}
		if (statusConvite == false) {
			throw new UsuarioException(usuarioNome.getNome()
					+ " nao lhe enviou solicitacoes de amizade.");
		}

	}

	
	/**
	 * Metodo utilizado para ver o conteudo das notificacoes do usuario.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @return: retorna uma string com a notificacao.
	 * @throws NotificacoesException: Excecao lancada caso o usuario ja tenha visto todas as notificacoes.
	 */
	public String getNextNotificacao() throws NotificacoesException {
		if (contadorNotificacao == getNotificacoes()) {
			throw new NotificacoesException();
		} else {
			contadorNotificacao += 1;
			return usuarioLogado.getNextNotificacao();
		}
	}

	/**
	 * Metodo utilizado para um usuario remover um amigo da lista de amigos.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema.
	 * @param email: email do usuario que sera removido da lista de amigos.
	 */
	public void removeAmigo(String email) {
		usuarioLogado.removeAmigo(email);
	}

	
	/**
	 * Metodo utilizado para curtir um post de um amigo.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema. Chama  o metodo adicionaHashtag() para adicionar as hashtags, caso existam,
	 * adicionadas ao post na lista de hashtags do Controller.
	 * @param email: email do amigo a qual se deseja curtir o post;
	 * @param numeroPost: numero do post a ser curtido.
	 * @throws PostException: Excecao lancada caso numero do post nao exista.
	 */
	public void curtirPost(String email, int numeroPost) throws PostException {
		tagPost tag= usuarioLogado.curtirPost(email, numeroPost);
		 adicionaHashtag(tag);
	
	}

	/**
	 * Metodo utilizado para rejeitar um post de um amigo.Chama o metodo de mesmo nome
	 * que se encontra na classe Usuario.Essa chamada de metodo so e possivel se tiver um usuario logado
	 * no sistema. Adiciona a hashtag recem adicionada no post a lista de hashtags do Controller.
	 * @param email: email do amigo a qual se deseja curtir o post;
	 * @param numeroPost: numero do post a ser curtido.
	 * @throws PostException: Excecao lancada caso numero do post nao exista.
	 */
	public void rejeitarPost(String email, int numeroPost) throws PostException {
		tagPost tag =usuarioLogado.rejeitarPost(email, numeroPost);
		adicionaHashtag(tag);

	}

	/**
	 * Metodo utilizado para buscar um usuario cadastrado no +Pop.
	 * @param email: email do usuario que se deseja buscar.
	 * @return: retorna um objeto do tipo Usuario.
	 */
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


	/**
	 * Metodo utilizado para adicionar uma hashtag na lista de hashtags  do Controller. Essas hashtags que sao
	 * adicionadas na lista refere-se as hashtags que sao colocadas nos post. O sistema +Pop armazena
	 * todas que sao utilizadas.
	 */
	private void adicionaHashtag(){
		int ocorrencia = 1;
		for (String hashtag: usuarioLogado.getHashtags()){
			 tagPost tag = new tagPost(hashtag);
			 if (hashtags.contains(tag)){
				 for (tagPost tagAntiga: hashtags){
					 if (tagAntiga.equals(tag)){
						 tagAntiga.adicionaOcorrencia();
					 }
				 }
			 }else{
				 hashtags.add(tag);
			 }

		}
		}
	
	/**
	 * Metodo utilizado para adicionar uma hashtag na lista de hashtags  do Controller. Essas hashtags que sao
	 * adicionadas na lista refre-se as hashtags que sao colocadas nos post. O sistema +Pop armazena
	 * todas que sao utilizadas.
	 * @param tag: hashtag a ser adicionada na lista.
	 */
	private void adicionaHashtag(tagPost tag){
		if (tag!=null){
			if(hashtags.contains(tag)){
				for (tagPost tagAntiga: hashtags){
					 if (tagAntiga.equals(tag)){
						 tagAntiga.adicionaOcorrencia();
					 }
				 }
			}else{
				hashtags.add(tag);
			}
		}
		}
	
	/**
	 * Metodo utilizado para ordenar a lista de usuarios mais populares e menos populares do +Pop,
	 * baseado na quantidade de pops de cada um.
	 * @return: Retorna uma string com os 3 usuarios mais populares e os 3 usuarios menos populares.
	 */
	public String atualizaRankings(){
		this.usuariosMaisPopulares = "Mais Populares: ";
		this.usuariosMenosPopulares = "Menos Populares: ";
		Collections.sort(usuarios);
		for (int i=0; i<3; i++){
			if (usuarios.size()>i){
				usuariosMenosPopulares+= "("+(i+1)+") "+usuarios.get(i).getNome()+" "+usuarios.get(i).getQuantidadePops()+ "; ";
			}
			
		}
		Collections.reverse(usuarios);
		for (int i=0; i<3; i++){
			if (usuarios.size()>i){
				usuariosMaisPopulares+= "("+(i+1)+") "+usuarios.get(i).getNome()+" "+usuarios.get(i).getQuantidadePops()+ "; ";
			}
			
		}
		return usuariosMaisPopulares +"| "+usuariosMenosPopulares.substring(0, (usuariosMenosPopulares.length()-1)) ;
		
	}
	
	/**
	 * Metodo utilizado para ordenar a lista de hashtags do +Pop.
	 * @return: uma string com as 3 hashtags mais populares e com as 3 hashtags menos populares.
	 */
	public String atualizaTrendingTopics(){
		this.hashtagsTop = "Trending Topics:  ";
		Collections.sort(hashtags);
		for (int i=0; i<3; i++){
			hashtagsTop+="("+(i+1)+") "+hashtags.get(i)+"; ";
		}
		return hashtagsTop.substring(0, (hashtagsTop.length()-1));
	}


	/**
	 * Lista de hashtags do Controller.
	 * @return: retorna uma lista do tipo tagPost.
	 */
	public ArrayList<tagPost> getHashtags() {
		return hashtags;
	}


	/**
	 * Metodo utilizado para mostrar a popularidade de um usuario. Se ele é um usuario normal, celebridade pop
	 * ou icone pop.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @return retorna a popularidade do usuario.
	 */
	public Popularidade getPopularidade(){
		return usuarioLogado.getPopularidade();
	}

	/**
	 * Metodo utilizado para adicionar pops ao usuario.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @param pops: quantidade de pops a ser adicionado.
	 */
	public void adicionaPops(int pops){
		usuarioLogado.adicionaPops(pops);
	}
	
	/**
	 * Metodo utilizado para ver a quantidade de pops de um determinado usuario.
	 * @param email: email do usuario.
	 * @return: retorna a quantidade de pops.
	 * @throws PopsException: Excecao lancada caso se deseje ver a quantidade de pops de um usuario, mas ele
	 * esteja logado no sistema.
	 */
	public int getPopsUsuario(String email) throws PopsException{
		if (usuarioLogado ==null){
		Usuario usuario = buscaUsuario(email);
		return usuario.getQuantidadePops();
		}else{
			throw new PopsException("Erro na consulta de Pops. Um usuarix ainda esta logadx.");

		}
	}
	
	/**
	 * Metodo utilizado para ver quantidade de pops de um post.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @param numeroPost: numero do post que se deseja ver a quantidade de pops.
	 * @return: retorna a quantidade de pops do post.
	 */
	public int getPopsPost(int numeroPost){
		return usuarioLogado.getPopsPost(numeroPost);
	}
	
	/**
	 * Metodo utilizado para ver a quantidade de curtidas de um post.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @param numeroPost: numero do post que se deseja ver as curtidas.
	 * @return: retorna o numero de curtidas do post.
	 * @throws CurtidasException:Excecao lancada caso se deseje ver as curtidas de um post
	 * que nao existe.
	 */
	public int qtdCurtidasDePost(int numeroPost) throws CurtidasException{
		return usuarioLogado.qtdCurtidasDePost(numeroPost);
	}
	

	/**
	 * Metodo utilizado para ver a quantidade de rejeicoes de um post.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @param numeroPost: numero do post que se deseja ver as rejeicoes.
	 * @return: retorna o numero de rejeicoes do post.
	 * @throws CurtidasException:Excecao lancada caso se deseje ver as rejeicoes de um post
	 * que nao existe.
	 */
	public int qtdRejeicoesDePost(int numeroPost) throws CurtidasException{
		return usuarioLogado.qtdRejeicoesDePost(numeroPost);
	}
	
	/**
	 * Metodo utilizado para ver a quantidade de pops do usuario que esta logado.Chama o metodo de mesmo nome que se encontra na classe Usuario.
	 * Essa chamada de metodo so e possivel se tiver um usuario logado no sistema.
	 * @return : quantidade de pops.
	 */
	public int getPopsUsuario(){
		return usuarioLogado.getQuantidadePops();
	}
}
