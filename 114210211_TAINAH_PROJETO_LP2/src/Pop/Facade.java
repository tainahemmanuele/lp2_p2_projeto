/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Pop.Exceptions.CurtidasException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.LoginException;
import Pop.Exceptions.LogoutException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PopsException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Pop.Post.Post;
import Pop.Usuario.TipoUsuario.Popularidade;
import easyaccept.EasyAccept;

/**
 * Classe criada com o objeto de delegar as chamadas de metodo para a classe
 * controller. Lanca as Exceptions.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Facade {
	private Controller controller;

	/**
	 * Construtor da classe Facade.
	 */
	public Facade() {
		this.controller = new Controller();
	}

	/**
	 * Metodo utilizado para cadastrar um usuario no +Pop. Chama o metodo de
	 * mesmo nome que se encontra na classe Controller. A Facade delega
	 * (forwarding) para a classe Controller.
	 * 
	 * @param nome
	 *            : nome do usuario;
	 * @param email
	 *            : email do usuario;
	 * @param senha
	 *            : senha do usuario;
	 * @param dataNascimento
	 *            : data de nascimento do usuario;
	 * @param telefone
	 *            : telefone do usuario.
	 * @return : retorna uma String com o email do usuario. Esse retorno serve
	 *         para sinalizar que deu certo o cadastro do usuario.
	 * @throws Exception
	 *             : Lanca excecoes caso o nome seja uma string vazia, o email
	 *             esteja em formato incorreto, a senha seja vazia e a data de
	 *             nascimento seja invalida.
	 */
	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String telefone) throws Exception {
		return controller.cadastraUsuario(nome, email, senha, dataNascimento,
				telefone);
	}

	/**
	 * Metodo utilizado para cadastrar um usuario no +Pop. Chama o metodo de
	 * mesmo nome que se encontra na classe Controller. A Facade delega
	 * (forwarding) para a classe Controller.
	 * 
	 * @param nome
	 *            : nome do usuario;
	 * @param email
	 *            : email do usuario;
	 * @param senha
	 *            : senha do usuario;
	 * @param dataNascimento
	 *            : data de nascimento do usuario.
	 * @return : retorna uma String com o email do usuario. Esse retorno serve
	 *         para sinalizar que deu certo o cadastro do usuario.
	 * @throws Exception
	 *             : Lanca excecoes caso o nome seja uma string vazia, o email
	 *             esteja em formato incorreto, a senha seja vazia e a data de
	 *             nascimento seja invalida.
	 */

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws Exception {
		return controller.cadastraUsuario(nome, email, senha, dataNascimento);
	}

	/**
	 * Metodo utilizado para um usuario logar no sistema +Pop. Chama o metodo de
	 * mesmo nome que se encontra no Controller.
	 * 
	 * @param email
	 *            : email do usuario;
	 * @param senha
	 *            : senha do usuario.
	 * @throws LoginException
	 *             :Excecao lancada caso ja tenha um usuario logado.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario nao esteja cadastrado no
	 *             sistema +Pop.
	 */
	public void login(String email, String senha) throws LoginException,
			UsuarioException {
		controller.login(email, senha);
	}

	/**
	 * Metodo utilizado para um usuario deslogar (sair) do sistema +Pop.Chama o
	 * metodo de mesmo nome que se encontra no Controller.
	 * 
	 * @throws LogoutException
	 *             : Excecao lancada caso se tente sair do sistema , mas nao
	 *             exista usuario logado.
	 */

	public void logout() throws LogoutException {
		controller.logout();
	}

	/**
	 * Metodo utilizado para que um usuario atualize suas informacoes pessoais
	 * ou as adicione no +Pop. Chama o metodo de mesmo nome que se encontra no
	 * Controller e retorna o retorno dele.
	 * 
	 * @param atributo
	 *            : informacao a ser atualizada. ex: telefone, email...
	 * @param valor
	 *            : nova informacao atualizada.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario tente mudar as informacoes,
	 *             sem estar logado no sistema +Pop.
	 */
	public void atualizaPerfil(String atributo, String valor)
			throws UsuarioException {
		controller.atualizaPerfil(atributo, valor);
	}

	/**
	 * Metodo utilizado para atualizar/modificar senha do usuario.Chama o metodo
	 * de mesmo nome que se encontra no Controller.
	 * 
	 * @param atributo
	 *            : informacao a ser atualizada, no caso, a senha;
	 * @param valor
	 *            : senha atual;
	 * @param valor2
	 *            : senha que o usuario deseja utilizar.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario nao coloque a senha atual
	 *             igual a que ele logou no sistema.
	 */
	public void atualizaPerfil(String atributo, String valor, String valor2)
			throws UsuarioException {
		controller.atualizaPerfil(atributo, valor, valor2);
	}

	/**
	 * Metodo utilizado para ver a informacao de um usuario que possua cadastro
	 * no +Pop. Chama o metodo de mesmo nome que se encontra no Controller e
	 * retorna o retorno dele.
	 * 
	 * @param atributo
	 *            : informacao que se deseja ver. Ex: nome, email...
	 * @param email
	 *            : email do usuario que se deseja ver a informacao;
	 * @return: retorna a informacao pesquisada.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario nao possua cadastro no +Pop.
	 */
	public String getInfoUsuario(String atributo, String email)
			throws UsuarioException {
		return controller.getInfoUsuario(atributo, email);
	}

	/**
	 * Metodo utilizado para ver a informacao de um usuario que esta logado no
	 * +Pop. Chama o metodo de mesmo nome que se encontra no Controller e
	 * retorna o retorno dele.
	 * 
	 * @param atributo
	 *            : informacao que se deseja ver. Ex nome, email...
	 * @return: retorna a informacao pesquisada.
	 * @throws UsuarioException
	 *             : Excecao lancada caso se deseje ver a senha do usuario.
	 */
	public String getInfoUsuario(String atributo) throws UsuarioException {
		return controller.getInfoUsuario(atributo);
	}

	/**
	 * Metodo utilizado para remover o cadastro de um usuario do +Pop.Chama o
	 * metodo de mesmo nome que se encontra no Controller.
	 * 
	 * @param email
	 *            : email do usuario que se deseja remover.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario a ser removido, nao esteja
	 *             cadastrado no +Pop.
	 */
	public void removeUsuario(String email) throws UsuarioException {
		controller.removeUsuario(email);
	}

	/**
	 * Metodo utilizado para iniciar o sistema +Pop.Chama o metodo de mesmo nome
	 * que se encontra no Controller.
	 */
	public void iniciaSistema() {
		File diretorio = new File("SystemData");
		diretorio.mkdir();
		File arquivo = new File(diretorio, "Dados.dat");
		try {
			if (arquivo.createNewFile() == false) {
				FileInputStream fluxoSaida = new FileInputStream(arquivo);
				ObjectInputStream fluxoObjeto = new ObjectInputStream(
						fluxoSaida);
				this.controller = (Controller) fluxoObjeto.readObject();
				fluxoObjeto.close();
				fluxoSaida.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.iniciaSistema();
	}

	/**
	 * Metodo utilizado parafechar o sistema +Pop.Chama o metodo de mesmo nome
	 * que se encontra no Controller.
	 * 
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso algum usuario ainda esteja logado no
	 *             sistema.
	 */
	public void fechaSistema() throws InfoUsuarioException {
		File diretorio = new File("SystemData");
		diretorio.mkdir();
		File arquivo = new File(diretorio, "Dados.dat");
		try {
			arquivo.createNewFile();
			FileOutputStream fluxoSaida = new FileOutputStream(arquivo);
			ObjectOutputStream fluxoObjeto = new ObjectOutputStream(fluxoSaida);
			fluxoObjeto.writeObject(controller);
			fluxoObjeto.flush();
			fluxoObjeto.close();
			fluxoSaida.flush();
			fluxoSaida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.fechaSistema();
	}

	/**
	 * Metodo utilizado para criar um post no +Pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Controller.
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
		controller.criaPost(mensagem, data);
	}

	/**
	 * Metodo utilizado para ver um determinado post criado no +Pop. Chama o
	 * metodo de mesmo nome que se encontra na classe Controller e retorna o
	 * retorno dele.
	 * 
	 * @param numeroPost
	 *            : numero do Post que se deseja ver.
	 * @return retorna um objeto do tipo Post com o conteudo do post.
	 */
	public Post getPost(int numeroPost) {
		return controller.getPost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver determinadas partes de um post. Ex: data e
	 * mensagem do post. Chama o metodo de mesmo nome que se encontra na classe
	 * Controller e retorna o retorno dele.
	 * 
	 * @param atributo
	 *            : parte do post que se deseja ver. Ex: data.
	 * @param numeroPost
	 *            : numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 */
	public String getPost(String atributo, int numeroPost) {
		return controller.getPost(atributo, numeroPost);
	}

	/**
	 * Metodo utilizado para ver o conteudo de um post. Ex: arquivo e o texto do
	 * post sem as hashtags. Chama o metodo de mesmo nome que se encontra na
	 * classe Controller e retorna o retorno dele.
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
		return controller.getConteudoPost(indice, numeroPost);
	}

	/**
	 * Metodo utilizado para adicionar um amigo no +Pop.Chama o metodo de mesmo
	 * nome que se encontra na classe Controller.
	 * 
	 * @param email
	 *            : email do usuario que se deseja adicionar como amigo.
	 */
	public void adicionaAmigo(String email) {
		controller.adicionaAmigo(email);
	}

	/**
	 * Metodo utilizado para ver quantidade de notificacoes do usuario.Chama o
	 * metodo de mesmo nome que se encontra na classe Controller e retorna o
	 * retorno dele.
	 * 
	 * @return: retorna quantidade de notificacoes do usuario.
	 */
	public int getNotificacoes() throws NotificacoesException {
		return controller.getNotificacoes();
	}

	/**
	 * Metodo utilizado para ver o conteudo das notificacoes do usuario.Chama o
	 * metodo de mesmo nome que se encontra na classe Controller e retorna o
	 * retorno dele.
	 * 
	 * @return: retorna uma string com a notificacao.
	 * @throws NotificacoesException
	 *             : Excecao lancada caso o usuario ja tenha visto todas as
	 *             notificacoes.
	 */
	public String getNextNotificacao() throws NotificacoesException {
		return controller.getNextNotificacao();
	}

	/**
	 * Metodo utilizado para um usuario aceitar ser amigo de outro usuario.Chama
	 * o metodo de mesmo nome que se encontra na classe Controller.
	 * 
	 * @param email
	 *            : email do usuario a ser adicionado.
	 * @throws UsuarioException
	 *             : Excecao lancada caso o usuario nao seja cadastrado no +Pop.
	 */
	public void aceitaAmizade(String email) throws UsuarioException {
		controller.aceitaAmizade(email);
	}

	/**
	 * Metodo utilizado para ver a quantidade de amigos de um usuario.Chama o
	 * metodo de mesmo nome que se encontra na classe Controller.
	 * 
	 * @return: retorna a quantidade de amigos de um usuario.
	 */
	public int getQtdAmigos() {
		return controller.getQtdAmigos();
	}

	/**
	 * Metodo utilizado para que um usuario rejeite ser amigo de outro
	 * usuario.Chama o metodo de mesmo nome que se encontra na classe
	 * Controller.
	 * 
	 * @param email
	 *            : email do usuario que deseja recusar a amizade.
	 * @throws UsuarioException
	 *             : Excecao lancada caso deseje recusar a amizade de um usuario
	 *             que nao enviou solicitacao.
	 */
	public void rejeitaAmizade(String email) throws UsuarioException {
		controller.rejeitaAmizade(email);
	}

	/**
	 * Metodo utilizado para um usuario remover um amigo da lista de
	 * amigos.Chama o metodo de mesmo nome que se encontra na classe Controller.
	 * 
	 * @param email
	 *            : email do usuario que sera removido da lista de amigos.
	 */
	public void removeAmigo(String email) {
		controller.removeAmigo(email);
	}

	/**
	 * Metodo utilizado para curtir um post de um amigo.Chama o metodo de mesmo
	 * nome que se encontra na classe Controller.
	 * 
	 * @param email
	 *            : email do amigo a qual se deseja curtir o post;
	 * @param numeroPost
	 *            : numero do post a ser curtido.
	 * @throws PostException
	 *             : Excecao lancada caso numero do post nao exista.
	 */
	public void curtirPost(String email, int numeroPost) throws PostException {
		controller.curtirPost(email, numeroPost);
	}

	/**
	 * Metodo utilizado para rejeitar um post de um amigo.Chama o metodo de
	 * mesmo nome que se encontra no Controller.
	 * 
	 * @param email
	 *            : email do amigo a qual se deseja curtir o post;
	 * @param numeroPost
	 *            : numero do post a ser curtido.
	 * @throws PostException
	 *             : Excecao lancada caso numero do post nao exista.
	 */
	public void rejeitarPost(String email, int numeroPost) throws PostException {
		controller.rejeitarPost(email, numeroPost);
	}

	/**
	 * Metodo utilizado para ordenar a lista de usuarios mais populares e menos
	 * populares do +Pop, baseado na quantidade de pops de cada um.Chama o
	 * metodo de mesmo nome que se encontra no Controller e retorna o retorno
	 * dele.
	 * 
	 * @return: Retorna uma string com os 3 usuarios mais populares e os 3
	 *          usuarios menos populares.
	 */
	public String atualizaRanking() {
		return controller.atualizaRankings();
	}

	/**
	 * Metodo utilizado para mostrar a popularidade de um usuario. Se ele � um
	 * usuario normal, celebridade pop ou icone pop.Chama o metodo de mesmo nome
	 * que se encontra na classe Controller e retorna o retorno dele.
	 * 
	 * @return retorna a popularidade do usuario.
	 */
	public Popularidade getPopularidade() {
		return controller.getPopularidade();
	}

	/**
	 * Metodo utilizado para adicionar pops ao usuario.Chama o metodo de mesmo
	 * nome que se encontra na classe Controller.
	 * 
	 * @param pops
	 *            : quantidade de pops a ser adicionado.
	 */
	public void adicionaPops(int pops) {
		controller.adicionaPops(pops);
	}

	/**
	 * Metodo utilizado para ver a quantidade de pops de um determinado
	 * usuario.Chama o metodo de mesmo nome que se encontra na classe Controller
	 * e retorna o retorno dele.
	 * 
	 * @param email
	 *            : email do usuario.
	 * @return: retorna a quantidade de pops.
	 * @throws PopsException
	 *             : Excecao lancada caso se deseje ver a quantidade de pops de
	 *             um usuario, mas ele esteja logado no sistema.
	 */
	public int getPopsUsuario(String email) throws PopsException {
		return controller.getPopsUsuario(email);
	}

	/**
	 * Metodo utilizado para ver quantidade de pops de um post.Chama o metodo de
	 * mesmo nome que se encontra na classe Controller e retorna o retorno dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver a quantidade de pops.
	 * @return: retorna a quantidade de pops do post.
	 */
	public int getPopsPost(int numeroPost) {
		return controller.getPopsPost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver a quantidade de curtidas de um post.Chama o
	 * metodo de mesmo nome que se encontra na classe Controller e retorna o
	 * retorno dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as curtidas.
	 * @return: retorna o numero de curtidas do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as curtidas de um post
	 *             que nao existe.
	 */
	public int qtdCurtidasDePost(int numeroPost) throws CurtidasException {
		return controller.qtdCurtidasDePost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver a quantidade de rejeicoes de um post.Chama o
	 * metodo de mesmo nome que se encontra na classe Controller e retorna o
	 * retorno dele.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as rejeicoes.
	 * @return: retorna o numero de rejeicoes do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as rejeicoes de um post
	 *             que nao existe.
	 */
	public int qtdRejeicoesDePost(int numeroPost) throws CurtidasException {
		return controller.qtdRejeicoesDePost(numeroPost);
	}

	/**
	 * Metodo utilizado para ver a quantidade de pops do usuario que esta
	 * logado.Chama o metodo de mesmo nome que se encontra na classe Controller
	 * e retorna o retorno dele.
	 * 
	 * @return : quantidade de pops.
	 */
	public int getPopsUsuario() {
		return controller.getPopsUsuario();
	}

	/**
	 * Metodo utilizado para ordenar a lista de hashtags do +Pop. Chama o metodo
	 * de mesmo nome que se encontra na classe Controller e retorna o retorno
	 * dele.
	 * 
	 * @return: uma string com as 3 hashtags mais populares e com as 3 hashtags
	 *          menos populares.
	 */
	public String atualizaTrendingTopics() {
		return controller.atualizaTrendingTopics();
	}

	/**
	 * Metodo utilizado para que um usuario salve seus posts em um arquivo.
	 * Chama o metodo de mesmo nome que se encontra na classe Controller.
	 * 
	 * @throws PostException
	 *             : Excecao lancada caso o usuario nao tenha posts.
	 */
	public void baixaPosts() throws PostException {
		controller.baixaPosts();
	}

	/**
	 * Metodo utilizado para que um usuario veja a quantidade de posts que
	 * possui. Chama o metodo de mesmo nome que se encontra na classe
	 * Controller.
	 * 
	 * @return: Quantidade de posts que o usuario possui.
	 */
	public int getTotalPosts() {
		return controller.getTotalPosts();
	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por tempo. Chama o metodo de mesmo nome que se encontra na
	 * classe Controller.
	 * 
	 * @param post
	 *            : numero do post que o usuario quer ver.
	 * @return: post.
	 */
	public Post getPostFeedNoticiasRecentes(int post) {
		return controller.getPostFeedNoticiasRecentes(post);

	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por popularidade. Chama o metodo de mesmo nome que se encontra
	 * na classe Controller.
	 * 
	 * @param post
	 *            : numero do post que o usuario quer ver.
	 * @return: post.
	 */
	public Post getPostFeedNoticiasMaisPopulares(int post) {
		return controller.getPostFeedNoticiasMaisPopulares(post);
	}

	public static void main(String[] args) throws UsuarioException,
			LoginException {
		args = new String[] { "Pop.Facade", "testes/usecase_1.txt",
				"testes/usecase_2.txt", "testes/usecase_3.txt",
				"testes/usecase_4.txt", "testes/usecase_5.txt",
				"testes/usecase_6.txt", "testes/usecase_7.txt",
				"testes/usecase_8.txt", "testes/usecase_9.txt",
				"testes/usecase_10.txt" };
		EasyAccept.main(args);

	}
}
