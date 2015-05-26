package servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public void initParameter(HttpServletRequest _req,HttpServletResponse _res){
		this.request = _req;
		this.response = _res;
	}
	public abstract void execute()  throws Exception;
}
