package com.neuedu.bull.model.game;
/**
 * 游戏回合
 * @author Administrator
 *
 */

import com.neuedu.bull.Game;
import com.neuedu.bull.model.bull.HandCards;
import com.neuedu.bull.model.player.GameDealer;
import com.neuedu.bull.model.player.Player;
/**
 * 游戏回合
 * @author Administrator
 *
 */
public class GameRound {
	final private Game game;
	private int no;
	private int totalChip=0;
	private Player[] players;
	//游戏玩家数量
	private int playersCount=0;
	
	private static final int MAX_PLAYER_COUNT=5;
	
	public GameRound(Game g,int no) {
		this.game=g;
		this.no=no;
		players=new Player[MAX_PLAYER_COUNT];
	}
	/**
	 * 进行次回合
	 */
	public void run() {
		System.out.println("-----------回合开始----------------");
		Player[] acturePlayes=new Player[this.playersCount];
		for(int i=0;i<this.playersCount;i++) {
			acturePlayes[i]=this.players[i];
		}
		
		this.players=acturePlayes;
		
		GameDealer dealer=this.game.getDealer();
		//1洗牌
		dealer.shuffleCards();
		//2发牌
		for(int i=players.length;i-->0;) {
			HandCards handCards=dealer.dealCards();
			players[i].setCards(handCards);
			
		}
		//3用户看牌（告诉用户牌型）
		for(int i=players.length;i-->0;) {
			players[i].checkHandCards();
		}
		//4让用户下注
		int betChip=0;
		for(int i=players.length;i-->0;) {
			//4.1告知所有用户的下注
			betChip=players[i].bet();
			totalChip+=betChip;
			System.out.println(players[i].getName()+"下注"+betChip);
		}
		
		//5判断输赢
		Player winner=dealer.whoWin();
		winner.win(this.totalChip);
		//6所有玩家亮牌
		for(int i=players.length;i-->0;) {
			players[i].showHandCards();
		}
		//7告诉用户结果
		int winChip=this.totalChip-winner.getCurrentRoundChip();
		System.out.println(winner.getName()+"赢了"+winChip);
		
		
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTotalChip() {
		return totalChip;
	}

	public void setTotalChip(int totalChip) {
		this.totalChip = totalChip;
	}

	public Player[] getPlayers() {
		return players;
	}
	/**
	 * 添加一个玩家
	 * @param p
	 */
	public void addPlayer(Player p) {
		if(this.playersCount<MAX_PLAYER_COUNT) {
			this.players[this.playersCount++]=p;
		}
	}
	
}
