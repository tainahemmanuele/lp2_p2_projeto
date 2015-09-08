package Pop;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.InfoUsuarioException;
import Pop.Exceptions.UsuarioException;
import Pop.Exceptions.ValidaException;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String dataNascimento;
    private String telefone;
    private String imagem;
    private String atualiza;
    private String senhaAtual;
    SimpleDateFormat data1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
	private Scanner ler = new Scanner(System.in);
	private boolean statusData = false;

    
    public Usuario(String nome, String email, String senha, String dataNascimento, String imagem) throws Exception{
    	if(nome.equals("")){
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
    	}else if(nome.startsWith(" ")){
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
    	}else{
        	this.nome = nome;
    	}
    	
    	this.dataNascimento = converteData(dataNascimento);
    	
    	if((email.endsWith(".com")== true) && (email.endsWith(".com.br")==false)){
    		this.email = email;
    	}
    	else if((email.endsWith(".com")== false) && (email.endsWith(".com.br")==true)){
    		this.email = email;
    	}else{
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
    	}
    	
    	this.senha = senha;
    	this.imagem = imagem;
    	
    }


    public Usuario(String nome, String email, String senha, String dataNascimento) throws Exception {
    	if(nome.equals("")){
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
    	}else if(nome.startsWith(" ")){
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
    	}else{
        	this.nome = nome;
    	}

    	this.dataNascimento = converteData(dataNascimento);
    	
    	if((email.endsWith(".com")== true) && (email.endsWith(".com.br")==false) && (email.matches("(.*)@(.*)")) == true){
    		this.email = email;
    	}
    	else if ((email.endsWith(".com")== false) && (email.endsWith(".com.br")==true)&& (email.matches("(.*)@(.*)")) == true){
    		this.email = email;
    	//}else if((email.matches("(.*)@(.*)")) == true){
     	   //this.email = email;
       	}else{
    		throw new CadastroUsuarioException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
    	}

    	this.senha = senha;

    	this.imagem = "resources/default.jpg";
    	
    }
	
  
   public String converteData(String dataNascimento) throws ParseException {
	    data1.setLenient(true);
		data1.parse(dataNascimento);
		String[] s =dataNascimento.split("/");
		if ((s[0].length() == 2) && (s[1].length() == 2) && (s[2].length() == 4)){
			validaData(dataNascimento);
		}else{
			throw new DataException("Erro no cadastro de Usuarios. Formato de data esta invalida.", 2);
		}
	

	return data2.format(data1.parse(dataNascimento));	
}

public String validaData(String dataNascimento) throws ParseException{
	try {  
	    Calendar dataValida = Calendar.getInstance();
	    dataValida.setLenient(true);
	    data1.setLenient(false);
		dataValida.setTime(data1.parse(dataNascimento));
     } catch (ParseException e){  
         throw new DataException("Erro no cadastro de Usuarios. Data nao existe.",1);
        }
	return dataNascimento;
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
		return dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getImagem() {
		return imagem;
	}
	
	
	public void atualizaNome(String nome) throws AtualizaUsuarioException{
		if(nome.equals("")){
    		throw new AtualizaUsuarioException("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
    	}else if(nome.startsWith(" ")){
    		throw new AtualizaUsuarioException("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio." );
    	}else{
        	this.nome = nome;
    	}
	}

	public void atualizaImagem(String imagem){
		this.imagem = imagem;
	}
	
	public void atualizaEmail(String email) throws AtualizaUsuarioException{
		if((email.endsWith(".com")== true) && (email.endsWith(".com.br")==false) && (email.matches("(.*)@(.*)")) == true){
    		this.email = email;
    		System.out.println(email);
    	}
		else if((email.endsWith(".com")== false) && (email.endsWith(".com.br")==true) && (email.matches("(.*)@(.*)")) == true){
    		this.email = email;
    	}
        else{
    		throw new AtualizaUsuarioException("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
    	}
	}
	
	public void atualizaDataNascimento(String dataNascimento) throws ParseException{
		this.dataNascimento = converteDataAtualizacao(dataNascimento);
	}
	
	public void atualizaTelefone(String telefone){
		this.telefone = telefone;
	}
	
	public void atualizaSenha(String senhaNova,String senhaAtual) throws InfoUsuarioException{
		if (senhaAtual.equals(senha)){
			senha = senhaNova;
		}else {
			throw new InfoUsuarioException("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
	}
	
	 public String converteDataAtualizacao(String dataNascimento) throws ParseException {
		    data1.setLenient(true);
			data1.parse(dataNascimento);
			String[] s =dataNascimento.split("/");
			if ((s[0].length() == 2) && (s[1].length() == 2) && (s[2].length() == 4)){
				if ((s[0].matches("\\d+")) == true && (s[1].matches("\\d+")) == true && (s[2].matches("\\d+")) == true){
					validaDataAtualizacao(dataNascimento);
				}
				else{
					throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
				}
			}else{
				throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
			}
		
		return data2.format(data1.parse(dataNascimento));	
	}

	public String validaDataAtualizacao(String dataNascimento) throws ParseException{
		try {  
		    Calendar dataValida = Calendar.getInstance();
		    dataValida.setLenient(true);
		    data1.setLenient(false);
			dataValida.setTime(data1.parse(dataNascimento));
	     } catch (ParseException e){  
	         throw new DataException("Erro na atualizacao de perfil. Data nao existe.",1);
	        }
		return dataNascimento;
	}
	@Override
	public String toString() {
		return "Usuario [getNome()=" + getNome() + ", getEmail()=" + getEmail()
				+ ", getSenha()=" + getSenha() + ", getDataNascimento()="
				+ getDataNascimento() + ", getTelefone()=" + getTelefone()
				+ ", getImagem()=" + getImagem() + "]";
	}
    
	
   
}
