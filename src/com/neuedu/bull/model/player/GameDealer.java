package com.neuedu.bull.model.player;
/**
 * 游戏荷官
 * @author Administrator
 *
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.neuedu.bull.Game;
import com.neuedu.bull.model.bull.Card;
import com.neuedu.bull.model.bull.HandCards;
import com.neuedu.bull.model.game.GameRound;
/**
 * 荷官
 * @author Administrator
 *
 */
public class GameDealer {
	final private Game game;
	private GameRound currentRound;
	private int roundCount=0;
	
	private int nextDealCardsIndex=0;
	
	public GameDealer(Game g) {
		this.game=g;
	}

	public GameRound getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(GameRound currentRound) {
		this.currentRound = currentRound;
	}
	/**
	 * 开始一个新回合
	 * @return
	 */
	public GameRound newGameRound() {
		this.currentRound=new GameRound(this.game,++roundCount);
		this.nextDealCardsIndex=0;
		return this.currentRound;
	}
	/**
	 * 洗牌
	 */
	public void shuffleCards() {
		Collections.shuffle(game.getGameCards());
	}
	/**
	 * 发牌
	 * @return
	 */
	public HandCards dealCards() {
		List<Card> allCards=game.getGameCards();
		Card[] cards=new Card[5];
		for(int i=0;i<cards.length;i++) {
			cards[i]=allCards.get(nextDealCardsIndex++);
		}
		HandCards handCards=new HandCards(cards);
		return handCards;
	}
	/**
	 * 判断谁胜利了
	 * @return
	 */
	public Player whoWin(){
		//1拿到所有玩家
		Player[] playse=this.getCurrentRound().getPlayers();
		Arrays.sort(playse,new PlayerHandCardsCompartor());
		return playse[0];
	}
	
}
class PlayerHandCardsCompartor implements Comparator<Player>{
	@Override
	public int compare(Player a, Player b) {
		HandCards cardsB = b.getCards();
		HandCards cardsA = a.getCards();
		return cardsB.compareTo(cardsA);
	}
	
}
