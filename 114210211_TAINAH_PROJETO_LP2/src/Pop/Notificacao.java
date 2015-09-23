package Pop;

import java.util.ArrayList;

public class Notificacao {

	public Notificacao(){


	}
	
	public void adicionaNotificacaoAmizade(String emailUsuario, Usuario usuarioLogado, ArrayList <Usuario> usuarios) {
		String novaNotificacao = "";
		for (Usuario usuario: usuarios){
			if (usuario.getEmail().equals(emailUsuario)){
				novaNotificacao += usuarioLogado.getNome() + " quer sua amizade.";
				usuario.adicionaNotificacao(novaNotificacao);
				usuario.adicionaEmail(usuarioLogado);

			}
		}
		}
}
