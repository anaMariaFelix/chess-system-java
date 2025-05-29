package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

			//ChessPiece
public class PecaXadrez extends Peca{

	private Color corDaPeca;
	
	public PecaXadrez(Tabuleiro tabuleiro,Color corDaPeca) {
		super(tabuleiro);
		this.corDaPeca = corDaPeca;
		
	}

	public Color getCorDaPeca() {
		return corDaPeca;
	}

}
