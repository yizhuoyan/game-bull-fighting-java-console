package com.neuedu.bull.model.bull;

import java.util.Arrays;
import java.util.Comparator;

import com.neuedu.bull.model.bullType.AbstractBullType;

/**
 * 玩家每回合分发的牌
 * @author Administrator
 *
 */
public class HandCards implements Comparable<HandCards> {
	/**
	 *手牌
	 */
	final private Card[] cards;
	/**
	 * 牌型
	 */
	private AbstractBullType bullType;
	
	public HandCards(Card[] cards) {
		super();
		this.cards = cards;
		sortCards();
	}
	//排序手牌
	private void sortCards() {
		Arrays.sort(this.cards);
	}
	
	
	public String show() {
		String result="";
		Card[] cs=this.cards;
		for(int i=0;i<cs.length;i++) {
			result+=cs[i].show();
			result+=" ";
		}
		return result;
	}
	
	public AbstractBullType geBullType() {
		if(this.bullType==null) {
			return this.bullType=BullRule.judgeBullType(cards);
		}
		return this.bullType;
	}
	@Override
	public int compareTo(HandCards another) {
		AbstractBullType thisBullType=this.geBullType();
		AbstractBullType anotherBullType=another.geBullType();
		return thisBullType.compareTo(anotherBullType);
	}
	
	
}
