package com.neuedu.bull.model.player;

import com.neuedu.bull.model.bull.Card;
import com.neuedu.bull.model.bull.HandCards;
import com.neuedu.bull.model.game.GameRound;

public abstract class Player {
	/**
	 * 玩家名称
	 */
	private String name;
	/**
	 * 当前筹码
	 */
	private int currentChip;
	/**
	 * 当前回合总下注（计算实际输赢）
	 */
	private int currentRoundChip;
	/**
	 * 手牌
	 */
	private HandCards cards;
	/**
	 * 玩家游戏回合
	 */
	private GameRound currentRound;
	
	
	public Player(String name) {
		super();
		this.name = name;
	}
	/**
	 * 玩家是否破产
	 * @return
	 */
	public boolean isBroken() {
		return this.currentChip<=0;
	}
	/**
	 * 玩家看自己牌
	 */
	abstract public void checkHandCards() ;
	/**
	 * 玩家下注
	 * @return int 下注数
	 * 
	 */
	abstract public int bet() ;
	
	abstract public void showHandCards();
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentChip() {
		return currentChip;
	}
	
	public void win(int chip) {
		this.currentChip+=chip;
	}
	public void bet(int chip) {
		this.currentRoundChip=chip;
		this.currentChip-=chip;
		
	}
	public void lose(int chip) {
		this.currentChip-=chip;
	}
	public int getCurrentRoundChip() {
		return currentRoundChip;
	}
	public void setCurrentRoundChip(int currentRoundChip) {
		this.currentRoundChip = currentRoundChip;
	}
	public void setCurrentChip(int currentChip) {
		this.currentChip = currentChip;
	}
	public HandCards getCards() {
		return cards;
	}
	public void setCards(HandCards cards) {
		this.cards = cards;
	}
	public GameRound getCurrentRound() {
		return currentRound;
	}
	public void setCurrentRound(GameRound currentRound) {
		this.currentRound = currentRound;
	}
	
	
	
}
