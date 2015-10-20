/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Util.Util;

public class FactoryPost {
	   private ArrayList<String> conteudoPost ;
	   private String mensagemPost;
	   private String hashtag;


	
	public FactoryPost(){
	
	}
	

	public Post criaPost(String texto, LocalDateTime data) throws DataException, PostException {
		this.conteudoPost= new ArrayList<String>();
		separaString(texto);
		Post post = new Post(texto,conteudoPost, mensagemPost, data, testaHashtag(hashtag));
		return post;
	}
	
	public String testaHashtag(String hashtag) throws PostException{
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
		return hashtagNova.substring(0, hashtagNova.length() -1);
		
	}
	
	
	public void adicionaConteudo(String arquivoMensagem){
		String [] separaArquivo= arquivoMensagem.split(" ");
		for (int i = 1; i<separaArquivo.length; i ++){
			String arquivoFormatado ="";
			if(separaArquivo[i].contains("<imagem>")){
				String arquivo = separaArquivo[i];
				arquivoFormatado += "$arquivo_imagem:"+formataArquivo(arquivo);
				conteudoPost.add(arquivoFormatado);
			}
			if(separaArquivo[i].contains("<audio>")){
				String arquivo = separaArquivo[i];
				arquivoFormatado += "$arquivo_audio:"+formataArquivo(arquivo);
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
		this.hashtag = hashtag;

		

		if(mensagem.contains("#")){
			int tamanho = mensagem.length() - (texto.length()+arquivo.length());
			 for (int i = 0; i < mensagem.indexOf(" #") ; i++){
					mensagemPost += mensagem.charAt(i);
				} 
		 }

		 this.mensagemPost = mensagemPost;

         tamanhoString(mensagem,hashtag, arquivo);
         conteudoPost.add(texto);
         adicionaConteudo(arquivo);
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
	 
		
	public String formataArquivo(String arquivo){
		String arquivoFormatado = "";
		for (int j = arquivo.indexOf(">")+1; j < arquivo.lastIndexOf("</") ; j++){
			arquivoFormatado += arquivo.charAt(j);
		}
		return arquivoFormatado;
	}
	


}
