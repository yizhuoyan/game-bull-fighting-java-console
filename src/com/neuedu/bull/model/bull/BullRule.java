package com.neuedu.bull.model.bull;

import java.util.Arrays;

import com.neuedu.bull.model.bullType.AbstractBullType;
import com.neuedu.bull.model.bullType.BullTypeBomb;
import com.neuedu.bull.model.bullType.BullTypeFiveBull;
import com.neuedu.bull.model.bullType.BullTypeFourBull;
import com.neuedu.bull.model.bullType.BullTypeHasBull;
import com.neuedu.bull.model.bullType.BullTypeWuXiao;
import com.neuedu.bull.model.util.GameUtil;

public class BullRule {
	/**
	 * 从5张牌中任选3张的选法
	 */
	private static final int[][] CHOICES_ARRAY = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 },
			{ 0, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

	/**
	 * * 判断大小 牌型：炸弹> 五小 > 五花 > 四花 > 牛牛 > 有牛 > 没牛。 花色： 黑桃 > 红桃 > 草花 > 方块。 单张：K >
	 * Q > J > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3 > 2 > A。 同牌型间的比较： 无牛：比单张大小。
	 * 有牛：比分数大小，牛九 > 牛八 > 牛七 > 牛六 > 牛五 > 牛四 > 牛三 > 牛二 > 牛一。 牛牛：比单张+花色大小。
	 * 四花：比单张+花色大小。 五花：比单张+花色大小。 五小：比点数+单张+花色大小。（有些地区不支持。） 炸弹：大牌吃小牌，K最大，A最小
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int whoWin(Card[] a, Card[] b) {
		AbstractBullType bullTypeA = judgeBullType(a);
		AbstractBullType bullTypeB = judgeBullType(b);
		return bullTypeA.compareTo(bullTypeB);
	}

	/**
	 * 炸弹> 五小 > 五花 > 四花 > 牛牛 > 有分 > 没分
	 * 
	 * @param fiveCards
	 * @return
	 */
	public static AbstractBullType judgeBullType(Card[] fiveCards) {
		// > 五小 > 五花 > 四花 > 牛牛 > 有牛 > 没牛。
		AbstractBullType type = isBomb(fiveCards);
		if (type == null) {
			type = isWuXiao(fiveCards);
			if (type == null) {
				type = isFiveBull(fiveCards);
				if (type == null) {
					type = isFourBull(fiveCards);
					if (type == null) {
						type = isBull(fiveCards);
					}
				}
			}
		}
		return type;
	}

	public static AbstractBullType judgeBullType0(Card[] fiveCards) {
		// > 五小 > 五花 > 四花 > 牛牛 > 有牛 > 没牛。
		AbstractBullType type = isBomb(fiveCards);
		if (type != null)
			return type;
		type = isWuXiao(fiveCards);
		if (type != null)
			return type;
		type = isFiveBull(fiveCards);
		if (type != null)
			return type;
		type = isFourBull(fiveCards);
		if (type != null)
			return type;
		return isBull(fiveCards);
	}

	/**
	 * 判断是否炸弹
	 * 
	 * @return
	 */
	private static AbstractBullType isBomb(Card[] fiveCards) {
		if (fiveCards[1].dot == fiveCards[2].dot && fiveCards[2].dot == fiveCards[3].dot) {
			if (fiveCards[0].dot == fiveCards[1].dot) {
				return new BullTypeBomb(fiveCards[0].dot);
			} else if (fiveCards[3].dot == fiveCards[4].dot) {
				return new BullTypeBomb(fiveCards[4].dot);
			}
		}
		return null;
	}

	/**
	 * 判断是否五小
	 * 
	 * @return
	 */
	private static BullTypeWuXiao isWuXiao(Card[] fiveCards) {
		if (fiveCards[0].dot < 5) {
			int sum = 0;
			for (Card c : fiveCards) {
				sum += c.dot;
			}
			if (sum == 10) {
				return new BullTypeWuXiao(fiveCards);
			}
		}
		return null;
	}

	/**
	 * 判断是否5花牛
	 * 
	 * @return
	 */
	private static BullTypeFiveBull isFiveBull(Card[] fiveCards) {
		for (Card c : fiveCards) {
			if (c.dot <= 10) {
				return null;
			}
		}
		return new BullTypeFiveBull(fiveCards);
	}

	/**
	 * 判断是否4花牛
	 * 
	 * @return
	 */
	private static BullTypeFourBull isFourBull(Card[] fiveCards) {
		if (fiveCards[4].dot == 10) {
			for (int i = 0; i < 4; i++) {
				if (fiveCards[i].dot <= 10) {
					return null;
				}
			}
			return new BullTypeFourBull(fiveCards);
		}
		return null;
	}

	/**
	 * 判断是否有牛，
	 * 
	 * @return 有则返回对应数字，否则返回0=表示牛牛 -1表示无牛
	 */
	private static BullTypeHasBull isBull(Card[] fiveCards) {
		int result = -1;
		// 计算总和
		int totalSum = 0;
		for (int i = fiveCards.length; i-- > 0;) {
			totalSum += fiveCards[i].value;
		}
		for (int[] choices : CHOICES_ARRAY) {
			int bullSum = 0;
			for (int i = choices.length; i-- > 0;) {
				bullSum += fiveCards[choices[i]].value;
			}
			if (bullSum % 10 != 0) {
				// 无牛，继续计算
			} else {// 有牛
					// 求牛几
				int currentBull = totalSum % 10;
				if (result == -1) {
					result = currentBull;
				} else {// 之前有值，保留最大值
					if (currentBull > result) {
						result = currentBull;
					}
				}
			}
		}
		if (result == 0)
			result = 10;
		if (result == -1)
			result = 0;
		return new BullTypeHasBull(result, fiveCards);
	}

}
