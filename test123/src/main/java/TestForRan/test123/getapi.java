package TestForRan.test123;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class getapi {
	//请求URL
	static String url = "https://www.okex.com/api/v1/future_ticker.do?symbol=btc_usd&contract_type=this_week";
	
	//建立连接请求API
	public void callGetApi() throws ClientProtocolException, IOException 
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();	
		HttpGet httpGet = new HttpGet(url);
		System.out.println("calling api:"+url);  
		CloseableHttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println("calling status:"+response.getStatusLine());
		String result=EntityUtils.toString(entity);
		if (entity != null) {  
            // 输出响应内容到console 
            System.out.println("Response content: " + result);  
        }  
        
    	//解析response为json格式
    	JSONObject json= JSONObject.fromObject(result); 
		Set<String> set = json.keySet();
    	
        File writename = new File("output.txt");
        writename.createNewFile();       
    	for(String key : set)
    	{
    		String s = null;
    		String value = json.getString(key);
    	     s = "key=" + key + "  value=" + value + "\n";    		
    		//System.out.println(s);
    		writeFile(writename,s);
    	} 	
        
        //关闭连接
        response.close();
        httpclient.close();
	}
	
	public void writeFile(File file,String s) throws IOException {
        //将response写入output.txt中
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));  
        out.write(s); 
        out.flush();         
        out.close(); 
    }
}
