package com.neuedu.bull.model.bull;

public class Card implements Comparable<Card>{
	private static final String[] COLORS= {"♦","♣","♥","♠"};
	private static final String[] DOTS= {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	final public  int color;
	final public int dot;
	final public int value;
	
	public Card(int color, int dot) {
		super();
		this.color = color;
		this.dot = dot;
		this.value=dot>9?10:dot;
	}
	@Override
	public int compareTo(Card another) {
		if(this.dot==another.dot) {
			return another.color-this.color;
		}
		return another.dot-this.dot;
	}
	/**
	 * 展示每张牌
	 * @return
	 */
	public String show() {
		return COLORS[this.color-1]+DOTS[this.dot-1];
	}
}
