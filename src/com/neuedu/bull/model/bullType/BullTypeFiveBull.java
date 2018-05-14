package com.neuedu.bull.model.bullType;

import com.neuedu.bull.model.bull.Card;

public class BullTypeFiveBull extends AbstractBullType {
	public final Card[] cards;
	public BullTypeFiveBull(Card[] cards) {
		super("五花牛",8);
		this.cards=cards;
	}
	
	@Override
	public int compareTo(AbstractBullType another) {
		if(another instanceof BullTypeWuXiao) {
			BullTypeWuXiao anotherUse=(BullTypeWuXiao) another;
			Card[] cardsAnother=anotherUse.cards;
			return cards[0].compareTo(cardsAnother[0]);
		}
		return super.compareTo(another);
	}

}
