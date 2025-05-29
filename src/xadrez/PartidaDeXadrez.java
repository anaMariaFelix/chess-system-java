package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

			//chessMatch
public class PartidaDeXadrez { 

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
		
	}
	
	public PecaXadrez[][] getPecas() {//retornar a matriz com as peças do jogo
		
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				
				matriz[i][j] = (PecaXadrez)tabuleiro.peca(i, j);
			}
		}
		
		return matriz;
	}
	
	private void coloqueNovaPeça(char coluna, int linha, PecaXadrez peca) {
		PosicaoXadrez posicaoXadrez = new PosicaoXadrez(coluna, linha);
		tabuleiro.colocaPecaNaPosicao(peca, posicaoXadrez.toPosicao());
	}
	
	private void configuracaoInicial() {
		coloqueNovaPeça('c', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('c', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 2, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 1, new Rei(tabuleiro, Color.WHITE));

		coloqueNovaPeça('c', 7, new Torre(tabuleiro, Color.BLACK));
		coloqueNovaPeça('c', 8, new Torre(tabuleiro, Color.BLACK));
		coloqueNovaPeça('d', 7, new Torre(tabuleiro, Color.BLACK));
		coloqueNovaPeça('e', 7, new Torre(tabuleiro, Color.BLACK));
		coloqueNovaPeça('e', 8, new Torre(tabuleiro, Color.BLACK));
		coloqueNovaPeça('d', 8, new Rei(tabuleiro, Color.BLACK));
		
	}
	
	
}
