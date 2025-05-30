package xadrez.pecas;

import tabuleiro.Posicao;
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
	public boolean[][] possívelMovimento() { //a torre pode andar para a direta, a esquerda e para cima.
		boolean[][] matrizPossibilidadesTorre = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];//matriz instanciada com as mesmas medidas do tabuleiro
		
		Posicao pecaAux = new Posicao(0,0);
		
		//peças que estão ACIMA da peça torre
		pecaAux.setValores(posicao.getLinha() - 1, posicao.getColuna());//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setLinha(pecaAux.getLinha() - 1);//e a peça pode subir para a prossima casa
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//Peças que estao a DIREITA da torre
		pecaAux.setValores(posicao.getLinha(), posicao.getColuna() + 1);//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setColuna(pecaAux.getColuna() + 1);//e a peça pode subir para a prossima casa
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//Peças que estao a ESQUERDA da torre
		pecaAux.setValores(posicao.getLinha(), posicao.getColuna() - 1);//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setColuna(pecaAux.getColuna() - 1);//e a peça pode subir para a prossima casa
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		
		//Peças que estao a ACIMA da torre
		pecaAux.setValores(posicao.getLinha() + 1, posicao.getColuna());//vai subindo nas casas acima da peça
		
		while(getTabuleiro().existePosicao(pecaAux) && !getTabuleiro().existePecaNessaPosicao(pecaAux)) { //enquanto existe essa posição e enquanto n tiver peças nelas entao a torre pode andar
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true; //true significa que ela pode andar
			pecaAux.setLinha(pecaAux.getLinha() + 1);//e a peça pode subir para a prossima casa
		}
		
		if(getTabuleiro().existePosicao(pecaAux) && existeUmaPecaAdversaria(pecaAux) ) {//se existir pessas adversarias a torre tbm pode andar, sendo assim colocasse true.
			matrizPossibilidadesTorre[pecaAux.getLinha()][pecaAux.getColuna()] = true;
		}
		
		return matrizPossibilidadesTorre;
		
	}

}
