package Pop;

import java.text.ParseException;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.LoginException;
import Pop.Exceptions.LogoutException;
import Pop.Exceptions.NotificacoesException;
import Pop.Exceptions.PostException;
import Pop.Exceptions.UsuarioException;
import Pop.Exceptions.PesquisaUsuarioException;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller;
	
	public Facade(){
		this.controller = new Controller();
	}
	

	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento, String telefone) throws Exception{
		return controller.cadastraUsuario(nome, email, senha, dataNascimento, telefone);
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento) throws Exception{
		return controller.cadastraUsuario(nome, email, senha, dataNascimento);
	}
	
	public String getNome(String email) throws UsuarioException {
		return controller.getNome(email);
	}
	
	public void login (String email, String senha) throws LoginException, PesquisaUsuarioException{
		controller.login(email, senha);
	}
	
	public void logout() throws LogoutException{
	    controller.logout();
	}
	
	
	public void atualizaPerfil(String atributo,String valor) throws ParseException, UsuarioException{
		controller.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo,String valor,String valor2) throws UsuarioException{
		controller.atualizaPerfil(atributo, valor, valor2);
	}
	public String getInfoUsuario(String atributo,String email) throws UsuarioException{
		return controller.getInfoUsuario(atributo,email);
	}
	
	public String getInfoUsuario(String atributo) throws UsuarioException{
		return controller.getInfoUsuario(atributo);
	}
	
	public void removeUsuario(String email) throws UsuarioException{
		controller.removeUsuario(email);
	}
	
	public void iniciaSistema(){
		controller.iniciaSistema();
	}
	
	public void fechaSistema() throws InfoUsuarioException{
		controller.fechaSistema();
	}
	
	 public void criaPost(String mensagem, String data) throws PostException, ParseException{
			controller.criaPost(mensagem, data);
		}
	 
	 public String getPost(int numeroPost){
			return controller.getPost(numeroPost);
		}
	
	 public String getPost(String atributo, int numeroPost){
	    	return controller.getPost(atributo, numeroPost);
	    }
	 
	 public String getConteudoPost(int indice, int numeroPost) throws PostException{
			return controller.getConteudoPost(indice, numeroPost);
		}
	 
	 
	 public void adicionaAmigo(String email){
		 controller.adicionaAmigo(email);
	 }
	 
	 public int getNotificacoes(){
		 return controller.getNotificacoes();
	 }
	 
	 public String getNextNotificacao() throws NotificacoesException{
		 return controller.getNextNotificacao();
	 }
	 
	 
	 public void aceitaAmizade(String email){
		 controller.aceitaAmizade(email);
	 }
	 
	 public int getQtdAmigos(){
		 return controller.getQtdAmigos();
	 }
	 
	 public void rejeitaAmizade(String email) throws UsuarioException{
		 controller.rejeitaAmizade(email);
	 }
	 
	 public void removeAmigo(String email){
		 controller.removeAmigo(email);
	 }
	 
	 public void curtirPost(String email, int numeroPost) throws PostException{
			controller.curtirPost(email, numeroPost);
		}
	public static void main(String[] args) throws UsuarioException, LoginException {
		Facade facade = new Facade();
		//facade.cadastraUsuario("Tainah", "tainah@email.com", "21563", "19111995", "25689347", "foto");
		//facade.login("tainah@email.com", "21563");

		
		//System.out.println(facade.getNome("marina@email.com"));
		args = new String[] {"Pop.Facade", "diretorio_testes/usecase_1.txt","diretorio_testes/usecase_2.txt","diretorio_testes/usecase_3.txt","diretorio_testes/usecase_4.txt"};
	    EasyAccept.main(args);

	}
}
