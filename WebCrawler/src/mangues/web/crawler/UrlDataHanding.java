package mangues.web.crawler;

import java.util.Random;

public class UrlDataHanding implements Runnable  
{  
    /**  
     * 下载对应页面并分析出页面对应的URL放在未访问队列中。  
     * @param url  
     */
    public void dataHanding(String url)  
    {  
            HrefOfPage.getHrefOfContent(DownloadPage.getContentFormUrl(url));  
    }  
             
    public void run()  
    {  
        while(!UrlQueue.isEmpty())  
        {  
           dataHanding(UrlQueue.outElem());  
          
           try {

        
           //  System.out.println((int)Math.random()*100);
			Thread.sleep((int)Math.random()*3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }  
    }  
}