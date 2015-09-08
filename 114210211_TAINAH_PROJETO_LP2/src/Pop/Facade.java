package Pop;

import java.text.ParseException;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.LoginException;
import Pop.Exceptions.LogoutException;
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
	
	
	public void atualizaPerfil(String atributo,String valor) throws ParseException, AtualizaUsuarioException{
		controller.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo,String valor,String valor2) throws InfoUsuarioException{
		controller.atualizaPerfil(atributo, valor, valor2);
	}
	public String getInfoUsuario(String atributo,String email) throws InfoUsuarioException{
		return controller.getInfoUsuario(atributo,email);
	}
	
	public String getInfoUsuario(String atributo) throws InfoUsuarioException{
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
	
	public static void main(String[] args) throws UsuarioException, LoginException {
		Facade facade = new Facade();
		//facade.cadastraUsuario("Tainah", "tainah@email.com", "21563", "19111995", "25689347", "foto");
		//facade.login("tainah@email.com", "21563");

		
		//System.out.println(facade.getNome("marina@email.com"));
		args = new String[] {"Pop.Facade", "diretorio_testes/usecase_1.txt","diretorio_testes/usecase_2.txt"};
	    EasyAccept.main(args);

	}
}
