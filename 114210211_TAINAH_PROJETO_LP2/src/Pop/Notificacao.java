package Pop;

import java.util.ArrayList;

public class Notificacao {

	public Notificacao(){


	}
	
	public void adicionaNotificacaoAmizade(String emailUsuario, String nomeUsuario, ArrayList <Usuario> usuarios) {
		String novaNotificacao = "";
		for (Usuario usuario: usuarios){
			if (usuario.getEmail().equals(emailUsuario)){
				novaNotificacao += nomeUsuario + " quer sua amizade.";
				usuario.adicionaNotificacao(novaNotificacao);

			}
		}
		}
}
