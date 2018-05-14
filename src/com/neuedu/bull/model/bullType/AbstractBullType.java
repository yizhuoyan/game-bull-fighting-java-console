package com.neuedu.bull.model.bullType;

public abstract class AbstractBullType implements Comparable<AbstractBullType> {
	//牌型名称
	final public String name;
	//类型权值
	final public int weight;
	
	public AbstractBullType(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	@Override
	public int compareTo(AbstractBullType another) {
		return weight-another.weight;
	}
	
	public String toString() {
		return this.name;
	}
	
	
	
	
}
