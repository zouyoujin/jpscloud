package com.jpscloud.common.expression;

/**
 * 
 * @ClassName: ExpressionException   
 * @Description: 表达式异常类
 * @author: Kitty
 * @date: 2018年8月11日 上午10:37:03   
 *
 */
public class ExpressionException extends RuntimeException{

	private static final long serialVersionUID = 1L;  
	  
    public ExpressionException() {  
        super();  
    }  
  
    public ExpressionException(String msg) {  
        super(msg);  
    }  
      
    public ExpressionException(String msg, Throwable cause) {  
        super(msg,cause);  
    }  
      
    public ExpressionException(Throwable cause) {  
        super(cause);  
    }
}
