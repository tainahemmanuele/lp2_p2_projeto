package Pop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

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
import Pop.Exceptions.ValidaException;

public class Controller {
	private Usuario usuario;
	private ArrayList <Usuario> usuarios;
	private boolean retorno;
	private boolean status;
	private String nomeUsuario;
	private boolean statusSistema;
	private boolean statusUsuario;
	private String data;
	private int contadorNotificacao;



	
	public Controller(){
		this.usuarios = new ArrayList<Usuario>();
		this.retorno = false;
		this.status = false;
		this.statusSistema = false;
		this.statusUsuario = false;
	}

	public void iniciaSistema(){
		statusSistema = true;
		
	}

	
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento, String imagem) throws Exception{
		this.usuario = new Usuario(nome,email,senha,dataNascimento,imagem);
		this.usuarios.add(this.usuario);
		return usuario.getEmail();
	}
	
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNascimento) throws Exception {
		this.usuario= new Usuario(nome,email,senha,dataNascimento);
		this.usuarios.add(this.usuario);
		return usuario.getEmail();
		
	}

		
	public String getNome(String email) throws UsuarioException {
		for(Usuario usuario: usuarios){
			if (usuario.getEmail().equals(email)){
				retorno = true;
				return usuario.getNome();
			}
			else{
				retorno = false;
			}
		}
		if (retorno == false){
			throw new UsuarioException ("Um usuarix com email "+email + " nao esta cadastradx.");
		}
	   return email;
			
	}
	    
	    public String pesquisaUsuario(String email, String senha) throws PesquisaUsuarioException {
	    	for (Usuario usuario :usuarios){
	    		if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
	    			status = true;
	    			return usuario.getEmail();
	    			
	    		}
	    		if ((usuario.getEmail().equals(email)) && !(usuario.getSenha().equals(senha))){
	    			throw new PesquisaUsuarioException("Nao foi possivel realizar login. Senha invalida.");
	    		}
	    	}
    		if (status == false){
    			throw new PesquisaUsuarioException("Nao foi possivel realizar login. Um usuarix com email " + email+ " nao esta cadastradx.");
    		}
			return usuario.getEmail();
	    }
	    
	    
	    public void login (String email, String senha) throws LoginException, PesquisaUsuarioException{
	    	if (status == false){
	           nomeUsuario = pesquisaUsuario(email, senha);
	           this.usuario = retornaUsuario(email);
	           this.contadorNotificacao =0;
	    	}else {
	    		throw new LoginException ("Nao foi possivel realizar login. Um usuarix ja esta logadx: " +usuario.getNome() + "." );
	    	}
	    }
	    	
	    public void logout() throws LogoutException{
	    		if (status == true){
	    			usuario.limpaNotificacoes();
	    			usuario.limpaEmail();
	    			status = false;
	    		} else{
	    			throw new LogoutException();
	    		}
	    	}
	    
	 
	    
	    public String getInfoUsuario(String atributo, String email) throws InfoUsuarioException{
	    	//Note que esse for se repete. Modularize isso por mei de um metodo:
	    	// buscaUsuario(String email);
	    	for(Usuario usuarioLogado : usuarios){
	    		if(usuarioLogado.getEmail().equals(email)){
	    			usuario = usuarioLogado;
	    			if (atributo.equals("Nome")){
		            	return usuario.getNome();
		            }
		            if (atributo.equals("Email")){
		            	return usuario.getEmail();
		            }
		            if (atributo.equals("Senha")){
		            	throw new InfoUsuarioException();
		            }
		            if(atributo.equals("Foto")){
		            	return usuario.getImagem();
		            }
		            if(atributo.equals("Data de Nascimento")){
		            	return usuario.getDataNascimento();
		            }	
	    	}
	    	}
	    		
	    	 throw new InfoUsuarioException("Um usuarix com email " + email + " nao esta cadastradx.");	
	

	    }
	   

	    public String getInfoUsuario(String atributo) throws InfoUsuarioException{
	    	if (status ==true){
    		for(Usuario usuarioLogado: usuarios){
    			if (usuarioLogado.getEmail().equals(nomeUsuario)){
    				usuario = usuarioLogado;
    			}
    		}
	    	}
        if (atributo.equals("Nome")){
        	return usuario.getNome();
        }
        if (atributo.equals("Email")){
        	return usuario.getEmail();
        }
        if (atributo.equals("Senha")){
        	throw new InfoUsuarioException();
        }
        if(atributo.equals("Foto")){
        	return usuario.getImagem();
        }
        if(atributo.equals("Data de Nascimento")){
        	return usuario.getDataNascimento();
        }	
      
		return usuario.getEmail();
    }
    	
	    
	    public void fechaSistema() throws InfoUsuarioException{
	    	//Nao precisa de um booleano. Pode manter uma referencia para um usuarioLogado.
	    	// Ao realizar login, buscamos esse usuario no cadastro e fazemos
	    	// usuarioLogado = usuarioEncontrado.
	    	// Ao fazer logout, fazemos: usuarioLogado = null;
	    	// Dai toda a verificacao para saber se temos alguem logado vira: usuarioLogado == null?
	    	if(status == true){
	    		throw new InfoUsuarioException("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	    	}else{
	    		statusSistema = false;
	    	}
	    }
	    
	    public void removeUsuario(String email)throws UsuarioException{
	    	if (status == false){
	    		/*for(Usuario usuarioLogado:usuarios){
	    			if(usuarioLogado.getEmail().equals(email)){
	    				usuario = usuarioLogado;
	    				usuarios.remove(usuario);
	    				statusUsuario = true;
	    			}
	    		}*/
             Iterator itr = usuarios.iterator(); 
             while(itr.hasNext()) {
                   Usuario usuario = (Usuario) itr.next();
                   if (usuario.getEmail().equals(email)){
                	   itr.remove();
                	   statusUsuario = true;
                   }
}
	    		
	    		if(statusUsuario == false){
	    			throw new UsuarioException("Um usuarix com email "+ email +"nao esta cadastradx.");
	    		}
	    	}
	    }
	    
	    public void atualizaPerfil(String atributo,String valor) throws ParseException, AtualizaUsuarioException{
	    	if(status == true){
	    		//Note que esse trecho se repete muito... crie um metodo para isso. ;)
	    		//Se esse metodo nao achar o usuario, retorne null.
	    		for(Usuario usuarioLogado: usuarios){
	    			if (usuarioLogado.getEmail().equals(nomeUsuario)){
	    				usuario = usuarioLogado;
	    			}
	    	}
	    	}
	    	if (status == false){
	    		throw new AtualizaUsuarioException("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
	    	}
	    	//Essa logica eh perigosa. Procure usar if/else, jah que a logica eh
	    	// atualizar apenas um deles por vez. Caso contrario, eu poderia
	    	// atualizar todos (vai que o equals dispara true em mais de um).	    	
	    	if (atributo.equals("Nome")){
	        	usuario.atualizaNome(valor);
	        }
	        if (atributo.equals("E-mail")){
	        	usuario.atualizaEmail(valor);
	        }
	    	if(atributo.equals("Foto")){
	        	usuario.atualizaImagem(valor);
	        }
	    	if(atributo.equals("Data de Nascimento")){
	        	usuario.atualizaDataNascimento(valor);
	        }
	        
	        
	    	if (atributo.equals("Telefone")){
	        	usuario.atualizaTelefone(valor);
	        }
	        	
	    	
	    }
	    
	    public void atualizaPerfil(String atributo,String valor,String valor2) throws InfoUsuarioException{
	    	if(status == true){
	    		for(Usuario usuarioLogado: usuarios){
	    			if (usuarioLogado.getEmail().equals(nomeUsuario)){
	    				usuario = usuarioLogado;
	    			}
	    	}
	    	}
	        if (atributo.equals("Senha")){
	        	usuario.atualizaSenha(valor,valor2);
	        }
	    	
	    }
	    
	    public void criaPost(String mensagem, String data) throws PostException, ParseException{
	    	usuario.criaPost(mensagem, data);
		}
	    
	    public Post getPost(int numeroPost){
			return usuario.getPost(numeroPost);
		}
	    
	    public String getPost(String atributo, int numeroPost){
	    	return usuario.getPost(atributo, numeroPost);
	    }
	    
	    public String getConteudoPost(int indice, int numeroPost) throws PostException{
			return usuario.getConteudoPost(indice, numeroPost);
		}
		public Usuario retornaUsuario(String email){
			for(Usuario usuario:usuarios){
				if(usuario.getEmail().equals(email)){
					return usuario;
				}
			}
			return usuario;
		}
		
		
		public void adicionaAmigo(String email){
              usuario.NotificacaoAmizade(email,this.usuario, usuarios);
			}
		
		public int getNotificacoes(){
			return usuario.getNotificacoes();
		}
		public void aceitaAmizade(String email){
			for (Usuario amigo : usuarios){
				if(amigo.getEmail().equals(email)){
					usuario.aceitaAmigo(amigo);
					amigo.aceitaAmigo(usuario);
					amigo.adicionaNotificacao(usuario.getNome() + " aceitou sua amizade.");
				}
			}
		}
		
		public int getQtdAmigos(){
			return usuario.getQtdAmigos();
		}
		
		public void rejeitaAmizade(String email) throws UsuarioException{
			String usuarioNome= getNome(email);
			boolean statusConvite = false;
			for (Usuario amigoFuturo: usuario.getNotificacaoAmizade()){
				if(amigoFuturo.getEmail().equals(email)){
					statusConvite = true;
					amigoFuturo.adicionaNotificacao(this.usuario.getNome() +" rejeitou sua amizade.");
				}else{
					statusConvite = false;
				}
			}if(statusConvite == false){
				throw new UsuarioException(usuarioNome + " nao lhe enviou solicitacoes de amizade.");
			}
			
			
		}
		
		
		public String getNextNotificacao() throws NotificacoesException{
			if(contadorNotificacao == getNotificacoes()){
				throw new NotificacoesException();
			}else{
			contadorNotificacao += 1;
			return usuario.getNextNotificacao();
			}
		}
		
		
		public void removeAmigo(String email){
			usuario.removeAmigo(email, this.usuario.getNome());
		}
		
		public void curtirPost(String email, int numeroPost){
			usuario.curtirPost(email, numeroPost, this.usuario.getNome());
		}
}
