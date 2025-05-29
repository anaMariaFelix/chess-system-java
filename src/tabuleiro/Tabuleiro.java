package tabuleiro;
			//Board
public class Tabuleiro {
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
			
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 && colunas < 1) {
			throw new TabuleiroException("ERRO na criação do tabuleiro: É necessario que exista pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		this.pecas = new Peca[linhas][colunas];
	}
	
	public int getLinhas() {
		return linhas;
	}
	
	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if(!existePosicao(linha, coluna)) {
			throw new TabuleiroException("Posição não existente no tabuleiro!");
		}
		
		return pecas[linha][coluna];
	}
	
	
	public Peca peca(Posicao posicao) {//retorna a peça que esta nessa posiçao
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Posição não existente no tabuleiro!");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
		
	}
	
	public void colocaPecaNaPosicao(Peca peca, Posicao posicao) {//muda a peça de posição de acordo com a posição que chega
		if(existePecaNessaPosicao(posicao)) {
			throw new TabuleiroException("já existe peças nessa posição: "+ posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
				
		peca.posicao = posicao;		
		
	}
	
	private boolean existePosicao(int linha, int coluna) {
		
		return linha >= 0 && linha < this.linhas && coluna >= 0 && coluna < this.colunas;
	}
	
	private boolean existePosicao(Posicao posicao) {
		return existePosicao(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean existePecaNessaPosicao(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Posição não existente no tabuleiro!");
		}
		return peca(posicao) != null;
	}
	
}
