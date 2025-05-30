package tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
		
	}
	
	public abstract boolean[][] possívelMovimento(); //assinatura de um metodo abstract, ela impoe que as classes que herdam dessa sobreescrevam esse metodo
	
	public boolean 	possívelMovimento(Posicao posicao) { 
		
		boolean[][] matrizPossibilidades = possívelMovimento();//hock(gancho) metodos, um metodo que usa um metodo abstract, faz um gancho com a subclasse, esse hock metods lembra o padrao de projeto templat metod
		return matrizPossibilidades[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeAlgumaMovimentaçãoPossível() {
		
		boolean[][] matrizPossibilidades = possívelMovimento();
		
		for(int i = 0; i < matrizPossibilidades.length; i++) {
			for(int j = 0;j < matrizPossibilidades.length; j++) {
				if(matrizPossibilidades[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
}
