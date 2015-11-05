/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.text.ParseException;

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
import Pop.Post.Post;
import Pop.Post.ArquivosPost.Arquivo;
import Pop.Usuario.TipoUsuario.Popularidade;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller;

	public Facade() {
		this.controller = new Controller();
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento, String telefone) throws Exception {
		return controller.cadastraUsuario(nome, email, senha, dataNascimento,
				telefone);
	}

	public String cadastraUsuario(String nome, String email, String senha,
			String dataNascimento) throws Exception {
		return controller.cadastraUsuario(nome, email, senha, dataNascimento);
	}

	public void login(String email, String senha) throws LoginException,
			UsuarioException {
		controller.login(email, senha);
	}

	public void logout() throws LogoutException {
		controller.logout();
	}

	public void atualizaPerfil(String atributo, String valor)
			throws ParseException, UsuarioException {
		controller.atualizaPerfil(atributo, valor);
	}

	public void atualizaPerfil(String atributo, String valor, String valor2)
			throws UsuarioException {
		controller.atualizaPerfil(atributo, valor, valor2);
	}

	public String getInfoUsuario(String atributo, String email)
			throws UsuarioException {
		return controller.getInfoUsuario(atributo, email);
	}

	public String getInfoUsuario(String atributo) throws UsuarioException {
		return controller.getInfoUsuario(atributo);
	}

	public void removeUsuario(String email) throws UsuarioException {
		controller.removeUsuario(email);
	}

	public void iniciaSistema() {
		controller.iniciaSistema();
	}

	public void fechaSistema() throws InfoUsuarioException {
		controller.fechaSistema();
	}

	public void criaPost(String mensagem, String data) throws PostException,
			DataException {
		controller.criaPost(mensagem, data);
	}

	public Post getPost(int numeroPost) {
		return controller.getPost(numeroPost);
	}

	public String getPost(String atributo, int numeroPost) {
		return controller.getPost(atributo, numeroPost);
	}

	public String getConteudoPost(int indice, int numeroPost)
			throws PostException {
		return controller.getConteudoPost(indice, numeroPost);
	}

	public void adicionaAmigo(String email) {
		controller.adicionaAmigo(email);
	}

	public int getNotificacoes() {
		return controller.getNotificacoes();
	}

	public String getNextNotificacao() throws NotificacoesException {
		return controller.getNextNotificacao();
	}

	public void aceitaAmizade(String email) {
		controller.aceitaAmizade(email);
	}

	public int getQtdAmigos() {
		return controller.getQtdAmigos();
	}

	public void rejeitaAmizade(String email) throws UsuarioException {
		controller.rejeitaAmizade(email);
	}

	public void removeAmigo(String email) {
		controller.removeAmigo(email);
	}

	public void curtirPost(String email, int numeroPost) throws PostException {
		controller.curtirPost(email, numeroPost);
	}
	
	public void rejeitarPost(String email, int numeroPost) throws PostException {
		controller.rejeitarPost(email, numeroPost);
	}


	public void atualizaRankings(){
		controller.atualizaRankings();
	}
	
	public Popularidade getPopularidade(){
		return controller.getPopularidade();
	}
	
	public void adicionaPops(int pops){
		controller.adicionaPops(pops);
	}
	public int getPopsUsuario(String email){
		return controller.getPopsUsuario(email);
	}
	
	public int getPopsPost(int numeroPost){
		return controller.getPopsPost(numeroPost);
	}
	
	public int qtdCurtidasDePost(int numeroPost){
		return controller.qtdCurtidasDePost(numeroPost);
	}
	
	public int qtdRejeicoesDePost(int numeroPost){
		return controller.qtdRejeicoesDePost(numeroPost);
	}
	
	public static void main(String[] args) throws UsuarioException,
			LoginException {
		args = new String[] { "Pop.Facade", "diretorio_testes/usecase_1.txt",
				"diretorio_testes/usecase_2.txt",
				"diretorio_testes/usecase_3.txt",
				"diretorio_testes/usecase_4.txt","diretorio_testes/usecase_5.txt","diretorio_testes/usecase_6.txt","diretorio_testes/usecase_7.txt" };
		EasyAccept.main(args);

	}
}
