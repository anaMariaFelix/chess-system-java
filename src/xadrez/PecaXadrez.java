package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

					//ChessPiece
public abstract class PecaXadrez extends Peca{

	private Color corDaPeca;
	
	public PecaXadrez(Tabuleiro tabuleiro,Color corDaPeca) {
		super(tabuleiro);
		this.corDaPeca = corDaPeca;
		
	}

	public Color getCorDaPeca() {
		return corDaPeca;
	}
	
	protected PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);//essa posição vem da herança
	}

	protected boolean existeUmaPecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCorDaPeca() != corDaPeca;
	}
	
}
