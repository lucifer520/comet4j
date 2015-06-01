package mangues.web.crawler;
import java.sql.SQLException;  

     
public class Test  
{  
  public static void main(String[] args) throws SQLException  
  {  
      String url = "http://username2.iteye.com/blog/1664995";  
      String url1 = "http://username2.iteye.com/blog/2146949";  
      String url2 = "http://blog.csdn.net/wangpeng047/article/details/6877720";  
      String url3 = "http://blog.csdn.net/wangpeng047/article/details/7218967";  

      UrlQueue.addElem(url);  
      UrlQueue.addElem(url1);  
     // UrlQueue.addElem(url2);  
    //  UrlQueue.addElem(url3);  
           
      UrlDataHanding[] url_Handings = new UrlDataHanding[10];  
           
          for(int i = 0 ; i < 10 ; i++)  
          {  
              url_Handings[i] = new UrlDataHanding();  
              new Thread(url_Handings[i]).start();  
          }  
     
  }  
}