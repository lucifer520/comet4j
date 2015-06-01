package com.geetest.sdk.jav;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 244554953219893949L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO： replace your own Key  here after create a Captcha App in
		// 'my.geetest.com'
		String private_key = "24a612ae8c62203f724c81a5a9b4e761";
		GeetestLib geetest = new GeetestLib(private_key);

		String gtResult = "fail";

		if (geetest.resquestIsLegal(request)) {
			gtResult = geetest.enhencedValidateRequest(request);
			System.out.println(gtResult);
		} else {
			// TODO use you own system when geetest-server is down:failback
			gtResult = "fail";

		}

		if (gtResult.equals("success")) {
			// TODO handle the Success result

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + "success!  with SDK version:   "
					+ geetest.getVersionInfo() + "</h1>");

		} else if (gtResult.equals("forbidden")) {
			// TODO handle the Forbidden result

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + "forbidden!  with SDK version:   "
					+ geetest.getVersionInfo() + "</h1>");
		} else {
			// TODO handle the Fail result
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + "fail!  with SDK version:"
					+ geetest.getVersionInfo() + "</h1>");
		}

	}
}
