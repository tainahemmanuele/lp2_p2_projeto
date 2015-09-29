package Pop;

import java.util.ArrayList;

public class Notificacao {

	public Notificacao(){


	}
	
	//Aquilo que conversamos, como temos varias notificacoes, e elas
	// acho nao precisam de comportamentos alem de impressao, nao eh
	// necessario ter um encapsulamento para Notificacoes (ou seja, uma classe).
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
