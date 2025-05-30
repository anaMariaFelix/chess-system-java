package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Color corDaPeca) {
		super(tabuleiro, corDaPeca);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possívelMovimento() {
		boolean[][] matrizPossibilidadesRei = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		return matrizPossibilidadesRei;
	}

}
