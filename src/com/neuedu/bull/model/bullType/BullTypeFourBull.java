package com.neuedu.bull.model.bullType;

import com.neuedu.bull.model.bull.Card;

public class BullTypeFourBull extends AbstractBullType {
	public final Card[] cards;
	public BullTypeFourBull(Card[] cards) {
		super("四花牛",7);
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
