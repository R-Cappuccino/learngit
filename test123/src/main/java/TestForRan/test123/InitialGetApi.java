package TestForRan.test123;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class InitialGetApi {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		getapi get = new getapi();
		get.callGetApi();
	}

}
