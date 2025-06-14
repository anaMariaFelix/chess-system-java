package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

			//chessMatch
public class PartidaDeXadrez { 

	private int turno;
	private Color jogadorAtual; //currentPlay
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassanVunerable;
	private PecaXadrez promocao;
	
	
	List<Peca> listaPecasDoTabuleiro = new ArrayList<>();
	List<Peca> listaPecasCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Color.WHITE;
		check = false; 
		
		configuracaoInicial();
		
	}
	
	public int getTurno() {
		return turno;
	}

	public Color getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean  getCheck() {
		return this.check;
	}
	
	public boolean  getCheckMate() {
		return this.checkMate;
	}
	
	public PecaXadrez  getEnPassanVunerable() {
		return this.enPassanVunerable;
	}
	
	public PecaXadrez  getPromocao() {
		return this.promocao;
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
	
	public boolean[][] possivelMovimento(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validaPosiçãoDeorigem(posicao);
		
		return tabuleiro.peca(posicao).possívelMovimento();
	}
	
	public PecaXadrez perfomaceParaMoverAPeca(PosicaoXadrez posiçãoDeorigem, PosicaoXadrez posiçãoAlvo) {
		
		Posicao origem = posiçãoDeorigem.toPosicao();
		Posicao alvo = posiçãoAlvo.toPosicao();
		
		validaPosiçãoDeorigem(origem);
		validaPosiçãoDeAlvo(origem,alvo);
		
		Peca capturaPeca = facaMovimento(origem, alvo);
		
		if(testeCheck(jogadorAtual)) {//testa se o jogador não se colocou em check
			desfazerMovimento(origem, alvo, capturaPeca);
			
			throw new XadrezException("Você não pode se colocar em check!");
		}
		PecaXadrez movePeca = (PecaXadrez)tabuleiro.peca(alvo);
		
		//PROMOÇÃO
		this.promocao = null;
		if(movePeca instanceof Peao) {
			if(movePeca.getCorDaPeca() == Color.WHITE && alvo.getLinha() == 0 || movePeca.getCorDaPeca() == Color.BLACK && alvo.getLinha() == 7) {
				this.promocao = (PecaXadrez)tabuleiro.peca(alvo);
				this.promocao = substituirPeçaPromovida("D");
			}
		}
		
		this.check = (testeCheck(oponente(jogadorAtual)))? true : false; //testa se o oponente do jogador atual se colocou em check se tiver a raviavel checke recebe true se n recebe false
		
		if(testeCheckMate(oponente(jogadorAtual))) {//verifica se o jogador estar em checkmate
			this.checkMate = true;
		}else {
			nextTurno();//se n tiver o jogo pode continuar
		}
		
		//en Pasant
		if(movePeca instanceof Peao && (alvo.getLinha() == origem.getLinha() - 2 || alvo.getLinha() == origem.getLinha() + 2)) {
			this.enPassanVunerable = movePeca;
		}else {
			this.enPassanVunerable = null;
		}
		
		return (PecaXadrez)capturaPeca;
	}
	
	public PecaXadrez substituirPeçaPromovida(String tipo) {
		if(this.promocao == null) {
			throw new IllegalStateException("Não há peças para serem promovidas!");
		}
		
		if(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") && !tipo.equals("D")) {
			return this.promocao;
		}
		
		Posicao pos = this.promocao.getPosicaoXadrez().toPosicao();
		Peca p = tabuleiro.removePeca(pos);
		listaPecasDoTabuleiro.remove(p);
		
		PecaXadrez novaPeca = novaPeca(tipo, this.promocao.getCorDaPeca());
		tabuleiro.coloquePeca(novaPeca, pos);
		listaPecasDoTabuleiro.add(novaPeca);
		
		return novaPeca;
		
	}
	
	public PecaXadrez novaPeca(String tipo, Color cor) {
		
		if(tipo.equals("B")) return new Bispo(tabuleiro, cor);
		if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
		if(tipo.equals("D")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
					//makeMove
	private Peca facaMovimento(Posicao origem, Posicao alvo) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(origem); //remove a peça que o usuario que mecher
		p.incremetaContagemMovimentos();
		
		Peca pecaCapturada = tabuleiro.removePeca(alvo);//remove a peça que esta no lugar que o usuario que colocar a peça que ele esta movendo
		
		tabuleiro.coloquePeca(p, alvo);
		
		if(pecaCapturada != null) {//sempre que a peça capturada for diferente de nulo ela é removida da lista de peças do tabuleiro e adicionada na lista de peças capturadas
			listaPecasDoTabuleiro.remove(pecaCapturada);
			listaPecasCapturadas.add(pecaCapturada);
		}
		
		//rock pequeno
		if(p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao alvoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			PecaXadrez rook = (PecaXadrez)tabuleiro.removePeca(origemTorre);
			tabuleiro.coloquePeca(rook, alvoTorre);
			rook.incremetaContagemMovimentos();
			
		}
		
		//rock grande
		if(p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao alvoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			PecaXadrez rook = (PecaXadrez)tabuleiro.removePeca(origemTorre);
			tabuleiro.coloquePeca(rook, alvoTorre);
			rook.incremetaContagemMovimentos();
			
		}
		
		//En Pasant
		if(p instanceof Peao) {
			if(origem.getColuna() != alvo.getColuna() && pecaCapturada == null) {//se o meu peao mudou de coluna e n capturou nenhuma peça ele vai da um en passant
				Posicao peaoPosicao;
				if(p.getCorDaPeca() == Color.WHITE) {
					peaoPosicao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());//se for uma peça branca a peça capturada vai esta abaixo do peao
					
				}else {
					peaoPosicao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
				}
				pecaCapturada = tabuleiro.removePeca(peaoPosicao);
				listaPecasCapturadas.add(pecaCapturada);
				listaPecasDoTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
					//undoMove
	private void desfazerMovimento(Posicao origem, Posicao alvo, Peca pecaCapturada) {//desfaz um movimento de uma peça 
		
		PecaXadrez p = (PecaXadrez)tabuleiro.removePeca(alvo);
		p.decremetaContagemMovimentos();
		
		tabuleiro.coloquePeca(p, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.coloquePeca(pecaCapturada, alvo);
			listaPecasCapturadas.remove(pecaCapturada);
			listaPecasDoTabuleiro.add(pecaCapturada);
		}
		
		//DESFAZENDO rock pequeno
		if(p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao alvoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			PecaXadrez rook = (PecaXadrez)tabuleiro.removePeca(alvoTorre);
			tabuleiro.coloquePeca(rook, origemTorre);
			rook.decremetaContagemMovimentos();
			
		}
		
		//DESFAZENDO rock grande
		if(p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao alvoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			PecaXadrez rook = (PecaXadrez)tabuleiro.removePeca(alvoTorre);
			tabuleiro.coloquePeca(rook, origemTorre);
			rook.decremetaContagemMovimentos();
			
		}
		//EN PASSANT
		if(p instanceof Peao) {
			if(origem.getColuna() != alvo.getColuna() && pecaCapturada == this.enPassanVunerable) {//se o meu peao mudou de coluna e n capturou nenhuma peça ele vai da um en passant
				PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(alvo);				
				Posicao peaoPosicao;
				
				if(p.getCorDaPeca() == Color.WHITE) {
					peaoPosicao = new Posicao(3, alvo.getColuna());//se for uma peça branca a peça capturada vai esta abaixo do peao
					
				}else {
					peaoPosicao = new Posicao(4, alvo.getColuna());
				}
				tabuleiro.coloquePeca(peao,peaoPosicao);
				
			}
		}
	}

	private void validaPosiçãoDeorigem(Posicao origem) {
		if(!tabuleiro.existePecaNessaPosicao(origem)) {
			throw new XadrezException("Não há nenhuma peça na posição: ");
		}
							//Dwon Cast
		if(jogadorAtual != ((PecaXadrez) tabuleiro.peca(origem)).getCorDaPeca()) {//se a cor do jogador atual for deiferente da cor do jogador que estaa na posição de origem
			throw new XadrezException("Essa peça não é sua!");
		}
		
		if(!tabuleiro.peca(origem).existeAlgumaMovimentaçãoPossível()) {
			throw new XadrezException("Não existe movimentos possiveis para a peça escolhida! ");
		}
		
	}
	
	private void validaPosiçãoDeAlvo(Posicao origem, Posicao alvo) {
		if(!tabuleiro.peca(origem).possívelMovimento(alvo)) {//se a posição de destino não é um movimento possivel eu não posso mover a peça para la
			throw new XadrezException("A peça não pode se mover para a posição de destino!");
		}
			
	}
	
	private void nextTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE)? Color.BLACK : Color.WHITE; //if ternario para mudar a cor do proximo jogador
	}
	
	private Color oponente(Color cor) {
		return (cor == Color.WHITE)? Color.BLACK : Color.WHITE;
				
	}
	
	private PecaXadrez rei(Color cor) {
		List<Peca> list = listaPecasDoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCorDaPeca() == cor).collect(Collectors.toList());//filtrando as peças pela cor
		
		for(Peca p : list) {
			
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe um rei com essa cor "+ cor +  " no tabuleiro!");
	}
	
	private boolean testeCheck(Color cor) {//testa os movimentos possiveis das peças do oponente do rei, para ver se alguma tem acesso a ele
		PecaXadrez px = rei(cor);
		PosicaoXadrez px1 = px.getPosicaoXadrez();		
		Posicao posicaoRei = px1.toPosicao();
		
		List<Peca> listPecasOponentes = listaPecasDoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCorDaPeca() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : listPecasOponentes){
			boolean[][] mat = p.possívelMovimento();
			
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean testeCheckMate(Color cor) {//logica do checkMate
		if(!testeCheck(cor)) {//verifica se a peça esta em check se n tiver nem precisa fazer mais nada nesse metodo
			return false;
		}
		
		List<Peca> list = listaPecasDoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCorDaPeca() == cor).collect(Collectors.toList());//filtra as peças pela cor
		
		for(Peca p : list) {
			
			boolean[][] mat = p.possívelMovimento();
			
			for(int i = 0; i < tabuleiro.getLinhas(); i ++ ) {
				for(int j = 0; j < tabuleiro.getLinhas(); j ++ ) {
					
					if(mat[i][j]) {
						
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao alvo = new Posicao(i, j);
						
						Peca pecaCapturada = facaMovimento(origem,alvo);
						
						boolean testCheck = testeCheck(cor);
						
						desfazerMovimento(origem, alvo, pecaCapturada);
						
						if(!testCheck) {
							return true;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void coloqueNovaPeça(char coluna, int linha, PecaXadrez peca) {
		PosicaoXadrez posicaoXadrez = new PosicaoXadrez(coluna, linha);
		tabuleiro.coloquePeca(peca, posicaoXadrez.toPosicao());
		
		listaPecasDoTabuleiro.add(peca);
	}
	
	private void configuracaoInicial() {
		
		coloqueNovaPeça('a', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('b', 1, new Cavalo(tabuleiro, Color.WHITE));
		coloqueNovaPeça('c', 1, new Bispo(tabuleiro, Color.WHITE));
		coloqueNovaPeça('d', 1, new Rainha(tabuleiro, Color.WHITE));
		coloqueNovaPeça('e', 1, new Rei(tabuleiro, Color.WHITE, this));
		coloqueNovaPeça('f', 1, new Bispo(tabuleiro, Color.WHITE));
		coloqueNovaPeça('g', 1, new Cavalo(tabuleiro, Color.WHITE));
		coloqueNovaPeça('h', 1, new Torre(tabuleiro, Color.WHITE));
		coloqueNovaPeça('a', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('b', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('c', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('d', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('e', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('f', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('g', 2, new Peao(tabuleiro, Color.WHITE, this));
        coloqueNovaPeça('h', 2, new Peao(tabuleiro, Color.WHITE, this));

        coloqueNovaPeça('a', 8, new Torre(tabuleiro, Color.BLACK));
        coloqueNovaPeça('b', 8, new Cavalo(tabuleiro, Color.BLACK));
        coloqueNovaPeça('c', 8, new Bispo(tabuleiro, Color.BLACK));
        coloqueNovaPeça('d', 8, new Rainha(tabuleiro, Color.BLACK));
        coloqueNovaPeça('e', 8, new Rei(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('f', 8, new Bispo(tabuleiro, Color.BLACK));
        coloqueNovaPeça('g', 8, new Cavalo(tabuleiro, Color.BLACK));
        coloqueNovaPeça('h', 8, new Torre(tabuleiro, Color.BLACK));
        coloqueNovaPeça('a', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('b', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('c', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('d', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('e', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('f', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('g', 7, new Peao(tabuleiro, Color.BLACK, this));
        coloqueNovaPeça('h', 7, new Peao(tabuleiro, Color.BLACK, this));
		
	}
	
	
}
