package Pop;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Util.Util;

public class FactoryPost {
	//Tainah, isso nao eh responsabilidade de Factory. Ela nao deve compor
	// e funcionar como uma criacao e um acesso... ela deve apenas criar.
	// Pode remover essa composicao, e usar o Mural para compor uma lista de posts.
	// Depois que o post eh criado, passa para o Mural, e jah coloca ele na lista.
	// Qualquer acesso a esse post criado deve ser feito na lista e nao pela Factory.
	 private Util util = new Util();
	   private ArrayList<String> posts;
	   private ArrayList<String> conteudoPost ;
	   private ArrayList<String> mensagemPost;
	   private ArrayList<String> datas;
	   private ArrayList<String> hashtags;
	  private ArrayList <ArrayList<String>> postsConteudos;


	
	public FactoryPost(){
		this.posts = new ArrayList<String>();
		this.datas=new ArrayList<String>();
		this.hashtags=new ArrayList<String>();
		this.postsConteudos=new ArrayList<ArrayList<String>>();
		this.mensagemPost = new ArrayList<String>();
		
	}
	

	public Post criaPost(String texto, String data) throws DataException, PostException {
		this.conteudoPost= new ArrayList<String>();
		separaString(texto);
		datas.add(data);
		Post post = new Post(postsConteudos, mensagemPost, datas, hashtags);
		return post;
	}
	
	public void testaHashtag(String hashtag) throws PostException{
		String [] novaHashtag= hashtag.split(" ");
		String hashtagNova="";
		for (int i=0 ; i< novaHashtag.length; i++){
			if(novaHashtag[i].startsWith("#")){
				boolean status = true;
				hashtagNova += novaHashtag[i]+",";
			}else{
				throw new PostException ("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: "+ "'" + novaHashtag[i]+"'.");
			}
		}
		hashtags.add(hashtagNova.substring(0, hashtagNova.length() -1));
		
	}
	
	
	public void adicionaConteudo(String arquivoMensagem){
		String [] separaArquivo= arquivoMensagem.split(" ");
		int indice =0;
		for (int i = 1; i<separaArquivo.length; i ++){
			indice += 1;
			String arquivoFormatado ="";
			if(separaArquivo[i].contains("<imagem>")){
				String arquivo = separaArquivo[i];
				arquivoFormatado += "$arquivo_imagem:";
				for (int j = arquivo.indexOf(">")+1; j < arquivo.lastIndexOf("</") ; j++){
					arquivoFormatado += arquivo.charAt(j);
				}
				conteudoPost.add(arquivoFormatado);
			}
			if(separaArquivo[i].contains("<audio>")){
				String arquivo = separaArquivo[i];
				arquivoFormatado += "$arquivo_audio:";
				for (int j = arquivo.indexOf(">")+1; j < arquivo.lastIndexOf("</") ; j++){
					arquivoFormatado += arquivo.charAt(j);
				}
				conteudoPost.add(arquivoFormatado);
			}
		}

	}


	public String separaString(String mensagem) throws PostException{
		 int contador = 0;
		 String texto ="";
		 String arquivo ="";
		 String hashtag="";
		 String mensagemPost ="";
		 
		 if(mensagem.contains("<") && mensagem.contains("#")){
		 for (int i =0; i < mensagem.indexOf(" <") ; i++){
				texto += mensagem.charAt(i);
			}
		 }
		 if(!(mensagem.contains("<")) && mensagem.contains("#")){
			 for (int i =0; i < mensagem.indexOf(" #") ; i++){
					texto += mensagem.charAt(i);
				}

			 }
		 if(mensagem.contains("<")){
				int tamanho = mensagem.length() - texto.length();
				 for (int i =mensagem.indexOf(" <"); i < mensagem.indexOf(" #"); i++){
						arquivo += mensagem.charAt(i);
					} 
			 
			}


		if(mensagem.contains("#")){
			int tamanho = mensagem.length() - (texto.length()+arquivo.length());
			 for (int i = mensagem.indexOf("#"); i < mensagem.lastIndexOf("") ; i++){
					hashtag += mensagem.charAt(i);
				} 
		 }

		

		if(mensagem.contains("#")){
			int tamanho = mensagem.length() - (texto.length()+arquivo.length());
			 for (int i = 0; i < mensagem.indexOf(" #") ; i++){
					mensagemPost += mensagem.charAt(i);
				} 
		 }


         tamanhoString(mensagem,hashtag, arquivo);
         conteudoPost.add(texto);
         adicionaConteudo(arquivo);
         testaHashtag(hashtag);
         //conteudoPost.add(hashtag);
         this.mensagemPost.add(mensagemPost);
         postsConteudos.add(conteudoPost);
		 return texto;
	 
	 }

	 
	 
	 public int tamanhoString(String mensagem, String hashtag,String arquivo) throws PostException{
		 int tamanhoString = mensagem.length() - (hashtag.length()+arquivo.length());
		 if((tamanhoString>200)){
	    	  throw new PostException ("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
	 }else{
			return tamanhoString;
	 }

	 }
		
	
	


}
