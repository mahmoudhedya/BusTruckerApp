package com.inspirecore.bustruckerapp.controls;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ServerCall {

	public static String postData(String Link,List<NameValuePair> Listparams) {

        String responseBody = null;
        try {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(Link);
            UrlEncodedFormEntity ent;
            ent = new UrlEncodedFormEntity(Listparams, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            responseBody = EntityUtils.toString(resEntity);

            Log.v("responseBody", responseBody);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseBody;
	}

	public static String getData(String Link) {

        Log.i("RequestLink", Link);

        String responseBody = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            try {
                request.setURI(new URI(Link));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpResponse response = client.execute(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;

	}
}
