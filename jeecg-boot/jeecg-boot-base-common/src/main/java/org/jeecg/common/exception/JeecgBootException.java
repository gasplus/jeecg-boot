package org.jeecg.common.exception;

public class JeecgBootException extends RuntimeException {
	private static final long serialVersionUID = 1L;
        private int code = 500;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public JeecgBootException(String message, int code){
		super(message);
		this.code = code;
	}

	public JeecgBootException(String message){
		super(message);
	}
	
	public JeecgBootException(Throwable cause)
	{
		super(cause);
	}
	
	public JeecgBootException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
