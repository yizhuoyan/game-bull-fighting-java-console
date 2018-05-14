package com.neuedu.bull.model.player;

import com.neuedu.bull.model.bullType.AbstractBullType;
import com.neuedu.bull.model.bullType.BullTypeBomb;
import com.neuedu.bull.model.bullType.BullTypeFiveBull;
import com.neuedu.bull.model.bullType.BullTypeFourBull;
import com.neuedu.bull.model.bullType.BullTypeHasBull;
import com.neuedu.bull.model.bullType.BullTypeWuXiao;

public class ComputerPlayer extends Player{

	public ComputerPlayer() {
		super("电脑");
		this.setCurrentChip(1000);
	}
	public void checkHandCards() {
		//判断牌型
	}
	
	@Override
	public int bet() {
		int betChip=judgeChip();
		this.bet(betChip);
		return betChip;
	}
	/**
	 * 让电脑更加牌型判断胜率下筹码
	 * @return
	 */
	private int judgeChip() {
		AbstractBullType type=this.getCards().geBullType();
		int totalChip=this.getCurrentChip();
		
		if(type instanceof BullTypeHasBull) {
			BullTypeHasBull hasBull=(BullTypeHasBull)type;
			if(totalChip<100) {
				//小于100，搏一把all in
				return totalChip;
			}
			switch (hasBull.bullDot) {
			case 10:
				//全下
				return totalChip;
			case 0:
				return totalChip/10;
			default:
				return totalChip*hasBull.bullDot/10;
			}
		}
		return totalChip;
	}
	@Override
	public void showHandCards() {
		System.out.print(this.getName()+"的牌是:");
		String result=this.getCards().show();
		System.out.print(result);
		System.out.println(this.getCards().geBullType().name);
		
	}
}
