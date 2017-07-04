/*package com.yz.并发编程;

import java.math.BigInteger;
import javax.servlet.*;

*//**
 * StatelessFactorizer
 * 
 * A stateless servlet
 * 
 * @author Brian Goetz and Tim Peierls
 *//*

public class StatelessFactorizer extends GenericServlet implements Servlet {

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[] { i };
	}
}
*/