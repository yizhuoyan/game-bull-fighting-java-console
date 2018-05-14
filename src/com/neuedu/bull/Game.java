package com.neuedu.bull;

import java.util.ArrayList;
import java.util.List;

import com.neuedu.bull.model.bull.Card;
import com.neuedu.bull.model.game.GameRound;
import com.neuedu.bull.model.player.ComputerPlayer;
import com.neuedu.bull.model.player.GameDealer;
import com.neuedu.bull.model.player.UserPlayer;

public class Game {
	private GameDealer dealer;
	private UserPlayer user;
	private ComputerPlayer computer;
	//一副牌
	private List<Card> gameCards;
	
	public GameDealer getDealer() {
		return dealer;
	}
	public void setDealer(GameDealer dealer) {
		this.dealer = dealer;
	}
	public UserPlayer getUser() {
		return user;
	}
	public void setUser(UserPlayer user) {
		this.user = user;
	}
	public ComputerPlayer getComputer() {
		return computer;
	}
	public void setComputer(ComputerPlayer computer) {
		this.computer = computer;
	}
	
	public List<Card> getGameCards() {
		return gameCards;
	}
	public void setGameCards(List<Card> gameCards) {
		this.gameCards = gameCards;
	}
	public Game() {
		init();
	}
	/**
	 * 游戏初始化过程
	 */
	private void init() {
		//1 创建荷官
		dealer=new GameDealer(this);
		//2创建游戏玩家
		//2.1 创建用户玩家
		user=new UserPlayer("张三");
		//设置玩家初始筹码
		user.setCurrentChip(800);
		//2.2创建电脑玩家
		computer=new ComputerPlayer();
		//3 创建游戏牌
		gameCards=createGameCards();
		
	}
	private List<Card> createGameCards() {
		List<Card> cards=new ArrayList<Card>(52);
		for(int color=1;color<5;color++) {
			for(int dot=1;dot<14;dot++) {
				cards.add(new Card(color, dot));
			}
		}
		return cards;
	}
	/**
	 * 游戏开始
	 */
	public void start() {
		while(true) {
			GameRound round=dealer.newGameRound();
			//添加游戏玩家
			round.addPlayer(user);
			round.addPlayer(computer);
			
			round.run();
			if(user.isBroken()) {
				System.out.print("你已破产");
				break;
			}else if(computer.isBroken()) {
				System.out.print(computer.getName()+"已破产");
				break;
			}
		}
		System.out.println("，游戏结束");
	}
}
