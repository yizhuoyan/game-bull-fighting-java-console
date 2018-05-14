package com.neuedu.bull.model.player;

import com.neuedu.bull.model.bull.HandCards;
import com.neuedu.bull.model.bullType.AbstractBullType;
import com.neuedu.bull.model.util.GameUtil;

public class UserPlayer extends Player {

	public UserPlayer(String name) {
		super(name);
	}

	public void checkHandCards() {
		System.out.print("您的牌是：");
		HandCards handCards=this.getCards();
		String result=handCards.show();
		System.out.print(result);
		//判断牌型
		AbstractBullType type=handCards.geBullType();
		System.out.println("("+type.name+")");
	}
	@Override
	public void showHandCards() {
		
	}
	
	@Override
	public int bet() {
		System.out.print("您当前拥有"+this.getCurrentChip()+",");
		int chip;
		do {
			chip=GameUtil.promptInt("请下注");
			if(chip>0) {
				break;
			}
			System.out.println("请输入正整数");
		}while(true);
		
		if(chip>this.getCurrentChip()) {
			chip=this.getCurrentChip();
			System.out.println("你没有这么多筹码,你只能Allin="+chip);
		}
		this.bet(chip);
		return chip;
	}
	
	
	
}
