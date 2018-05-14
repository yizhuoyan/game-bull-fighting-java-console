package com.neuedu.bull.model.bullType;

public class BullTypeBomb extends AbstractBullType  {
	public final int dot;
	public BullTypeBomb(int bombDot) {
		super("炸弹",10);
		this.dot=bombDot;
	}
	@Override
	public  int compareTo(AbstractBullType another) {
		if(another instanceof BullTypeBomb) {
			BullTypeBomb anotherUse=(BullTypeBomb) another;
			return dot-anotherUse.dot; 
		}
		return super.compareTo(another);
	}
	

}
