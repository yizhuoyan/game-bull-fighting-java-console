package com.neuedu.bull.model.bullType;

import com.neuedu.bull.model.bull.Card;

public class BullTypeHasBull extends AbstractBullType {
	public final int bullDot;
	public final Card[] cards;
	public BullTypeHasBull(int bullDot,Card[] cards) {
		super(figureName(bullDot),bullDot);
		this.bullDot=bullDot;
		this.cards=cards;
	}
	private static String figureName(int bullDot) {
		if(bullDot==10) {
			return "牛牛";
		}
		if(bullDot==0) {
			return "无牛";
		}
		return "牛"+bullDot;
	}
	@Override
	public  int compareTo(AbstractBullType another) {
		if(another instanceof BullTypeHasBull) {
			BullTypeHasBull anotherUse=(BullTypeHasBull) another;
			if(bullDot==anotherUse.bullDot) {
				if(bullDot==0) {//无牛判断单牌
					return anotherUse.cards[0].compareTo(cards[0]);
				}
				//相等
				return 0;
			}
			return bullDot-anotherUse.bullDot; 
		}
		return super.compareTo(another);
	}
	

}
