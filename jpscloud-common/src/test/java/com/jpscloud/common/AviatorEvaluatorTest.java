package com.jpscloud.common;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;

public class AviatorEvaluatorTest {

	@Test
	public void testAviatorEvaluator() {

		int[] a = new int[] { 1, 2, 3 };
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", a);

		Assert.assertEquals(6L, AviatorEvaluator.execute("1 + 2 + 3"));
		Assert.assertEquals(102L, AviatorEvaluator.execute("a[1] + 100", env));
		Assert.assertEquals("a[1]=2", AviatorEvaluator.execute("'a[1]=' + a[1]", env));
		// 求数组长度
		Assert.assertEquals(3L, AviatorEvaluator.execute("count(a)", env));
		// 求数组总和
		Assert.assertEquals(6L, AviatorEvaluator.execute("reduce(a, +, 0)", env));
		// 检测数组每个元素都在 0 <= e < 10 之间。
		Assert.assertEquals(true, AviatorEvaluator.execute("seq.every(a, seq.and( seq.ge(0), seq.lt(10)))", env));
		// Lambda 求和
		Assert.assertEquals(6L, AviatorEvaluator.execute("reduce(a, lambda( x , y ) -> x + y end, 0)", env));
	}
}
