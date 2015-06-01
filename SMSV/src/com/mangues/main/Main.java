package com.mangues.main;

import com.mangues.main.client.AbsRestClient;
import com.mangues.main.client.JsonReqClient;
import com.mangues.main.client.XmlReqClient;

import sun.org.mozilla.javascript.internal.json.JsonParser;

public class Main {
	public static final boolean Json = true;
	public static void main(String[] args) {
		String accountSid="8ce212079bd7cc10747aff742456aa62";
		String token="d5ca9ea571b7fed2df7ce78dce9e157e";
		String appId="16b362f1836b4f368f44d8e36d9ae8de";
		String templateId="6765";
		String to="13813010056";
		String para="淘客,183722,1";
		testTemplateSMS(Main.Json, accountSid,token,appId, templateId,to,para);
	}
	public static void testTemplateSMS(boolean json,String accountSid,String authToken,String appId,String templateId,String to,String param){
		try {
			String result=InstantiationRestAPI(json).templateSMS(accountSid, authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	static AbsRestClient InstantiationRestAPI(boolean enable) {
		if(enable) {
			return new JsonReqClient();
		} else {
			return new XmlReqClient();
		}
	}
}
