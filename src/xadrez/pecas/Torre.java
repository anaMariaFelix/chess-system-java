package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Color corDaPeca) {
		super(tabuleiro, corDaPeca);
		
	}

	@Override
	public String toString() {//essa letra vai representar a peça que se movimenta no tabuleiro
		return "T";
		
	}
	
	@Override
	public boolean[][] possívelMovimento() {
		boolean[][] matrizPossibilidadesTorre = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		return matrizPossibilidadesTorre;
	}

}
