package xadrez;

import tabuleiro.Posicao;

			//ChessPosition
public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna >'h' || linha < 1 || linha > 8) {
			throw new XadrezException("ERRO: Lendo a PosiÃ§Ã£o. forneca valores entre a1 e h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	
	protected Posicao toPosicao() {//pega as posiÃ§Ãµes do jeito que o usuario informa e transforma para uma forma que o compilador entenda, ja que estamos trabalhando com as posiÃ§Ãµes de uma matriz
		return new Posicao(8 - linha, coluna - 'a');
		
	}
	
	protected static PosicaoXadrez fromPosicao(Posicao posicao) { //aqui faz o contrario do metodo anterior, pega as possiÃ§oes como (0 ,2) e tranforma em 'a2 por exemplo
		return new PosicaoXadrez((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	public String toString() {
		return "" + coluna + linha;// coloca a string vazia antes para o compilador entender que esse retorno Ã© uma string 
		
	}
	
}
