import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Util {
	
	//array para armazenar objetos do tipo BilheteUnico
	private BilheteUnico [] bd = new BilheteUnico[5];
	
	//variavel para controlar as posições do array
	private int index = 0;
	
	public void menuPrincipal() {
		String aux = "Escolha uma operação\n";
		aux += "1. Adiministrador\n";
		aux += "2. Usuário";
		aux += "\n3. Finalizar";
		int opcao=0;
		
		do {
			opcao = parseInt(showInputDialog(aux));
			if(opcao <1 || opcao > 3) {
				showMessageDialog(null, "Opção Inválida!");
			} else {
				if(opcao == 1) {
					menuAdmin();
				} else if(opcao == 2) {
					menuUsuario();
				}else {
					showMessageDialog(null, "Programa finalizado!");
				}
			}

			
		} while(opcao != 3);
		
	}
	
	public void menuAdmin() {
		String aux = "Escolha uma operação\n";
		aux += "1. Cadastrar Bilhete\n";
		aux += "2. Consultar Bilhete\n";
		aux += "3. Sair";
		int opcao=0;
		
		do {
			opcao = parseInt(showInputDialog(aux));
			if(opcao <1 || opcao > 3) {
				showMessageDialog(null, "Opção Inválida!");
			} else {
				if(opcao == 1) {
					cadastrarBilhete();
				} else if(opcao == 2) {
					consultarBilhete();
				}
			}

			
		} while(opcao != 3);
		
	}
	
	public void menuUsuario() {
		String aux = "Escolha uma operção\n";
		aux += "1. Consultar Saldo\n";
		aux += "2. Carregar Bilhete\n";
		aux += "3. Passar na Catraca\n";
		aux += "4. Sair";
		int opcao=0;
		
		do {
			opcao = parseInt(showInputDialog(aux));
			if(opcao <1 || opcao > 4) {
				showMessageDialog(null, "Opção Inválida!");
			} else {
				if(opcao == 1) {
					consultarSaldo();
				} else if(opcao == 2) {
					carregarBilhete();
				} else if(opcao == 3) {
					passarNaCatraca();
				}
			}

			
		} while(opcao != 4);
		
	}
	
	public void cadastrarBilhete() {
		if(index < bd.length) {
			String nome = showInputDialog("Nome: ");
			String cpf = showInputDialog("CPF: ");
			String tipo = showInputDialog("Tipo (Estudante/Professor/Normal): ");
			bd[index] = new BilheteUnico(nome, cpf, tipo);
			index++;
		} else {
			showMessageDialog(null, "Sem espaço para novos bilhetes");
		}
	}
	
	// Método auxiliar para pesquisar um bilhete pelo cpf do usuário
	// método retorna a posição válida do array ou -1 se não encontrar o objeto
	public int pesquisar(String cpf) {
		int aux=-1;
		for(int i=0; i<index;i++) {
			if(bd[i].usuario.cpf.equalsIgnoreCase(cpf)) {
				aux =  i;
				break;
			}
		}
		return aux;
	}

	public void consultarBilhete() {
		String cpf = "Digite o CPF do bilhete que deseja consultar:";
		String aux = "";
		int posicao = pesquisar(showInputDialog(cpf));
		if(posicao == -1) {
			showMessageDialog(null, cpf + "não encontrado");
		} else {
			aux = "Número do bilhete: "+bd[posicao].numero;
			aux += "\nNome do usuário: "+bd[posicao].usuario.nome;
			aux += "\nCPF do usuário: "+bd[posicao].usuario.cpf;
			aux += "\nSaldo do bilhete: R$"+String.format("%.2f", bd[posicao].saldo);
			aux += "\nTipo da tarifa: "+bd[posicao].usuario.tipo;
			showMessageDialog(null,aux);
		}
		
	}
	
	public void consultarSaldo() {
		String cpf = "Digite o CPF do bilhete que deseja consultar:";
		int posicao = pesquisar(showInputDialog(cpf));
		if(posicao == -1) {
			showMessageDialog(null, cpf + "não encontrado");
		} else {
			showMessageDialog(null,"Saldo do bilhete: R$"+String.format("%.2f",bd[posicao].consultarSaldo()));
		}
	}
	
	public void carregarBilhete() {
		String cpf = "Digite o CPF do bilhete que deseja carregar o saldo:";
		int posicao = pesquisar(showInputDialog(cpf));
		double saldo=0;
		if(posicao == -1) {
			showMessageDialog(null, cpf + "não encontrado");
		} else {
			saldo = parseDouble(showInputDialog("Digite a quantia que deseja adicionar: "));
			bd[posicao].carregarBilhete(saldo);
			showMessageDialog(null, "Saldo adicionado");
		}
	}

	public void passarNaCatraca() {
		String cpf = "Digite o CPF do bilhete para passar na catraca:?";
		int posicao = pesquisar(showInputDialog(cpf));
		if(posicao == -1) {
			showMessageDialog(null, cpf + "não encontrado");
		} else {
			bd[posicao].passarNaCatraca();
		}
	}
	
}
