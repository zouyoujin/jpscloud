package com.jpscloud.common.expression;

/**
 * 
 * @ClassName: ExpressionNodeType
 * @Description: 表达式各个字符节点的类型枚举类
 * @author: Kitty
 * @date: 2018年8月11日 上午10:32:14
 *
 */
public enum ExpressionNodeType {
	Unknown, Plus, // +
	Subtract, /// -
	MultiPly, // *
	Divide, // /
	LParentheses, // (
	RParentheses, /// )
	Mod, // % (求模,取余)
	Power, // ^ (次幂)
	BitwiseAnd, /// & (按位与)
	BitwiseOr, /// | (按位或)
	And, // && (逻辑与)
	Or, /// || (逻辑或)
	Not, /// ! (逻辑非)
	Equal, /// == (相等)
	Unequal, /// != 或 <> (不等于)
	GT, /// > (大于)
	LT, /// < (小于)
	GTOrEqual, /// >= (大于等于)
	LTOrEqual, /// <= (小于等于)
	LShift, /// << (左移位)
	RShift, /// >> (右移位)
	Numeric, /// 数值,
	String, Date, Like, // 包含
	NotLike, // 不包含
	StartWith, // 已什么开始
	EndWith// 已什么结尾
}
