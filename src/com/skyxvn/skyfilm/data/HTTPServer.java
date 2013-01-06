package com.skyxvn.skyfilm.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;

import com.skyxvn.skyfilm.bean.Logger;

public class HTTPServer {
	public static int DEFAULT_TIMEOUT_DURATION = 20000;
	public final static String ENCODING = "UTF-8";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";

	public HTTPServer() {
	}

	public String getResponse(String url, boolean gzip) {
		InputStream inputStream = null;
		String jsonString = "";
		AndroidHttpClient httpClient = null;
		try {
			httpClient = AndroidHttpClient
					.newInstance(USER_AGENT, DEFAULT_TIMEOUT_DURATION);
			httpClient
			.getParams()
			.setParameter(
					CoreProtocolPNames.USER_AGENT,
					USER_AGENT);
			
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), 10000);
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
					10000);

			HttpGet method = new HttpGet(url);
			HttpResponse response = httpClient.execute(method);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
			if (gzip) {
				inputStream = new GZIPInputStream(inputStream);
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, ENCODING), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			jsonString = sb.toString();
			Logger.i("getResponse - json", "URL:" + url);
			Logger.i("getResponse - json", "" + jsonString);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception closeException) {
					closeException.printStackTrace();
				}
			}

			if (httpClient != null)
				httpClient.close();
		}
		return jsonString;
	}



	public String md5(String str) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (byte b : md5(str.getBytes()))
			sb.append(Integer.toHexString(0x100 + (b & 0xff)).substring(1));
		return sb.toString();
	}

	public byte[] md5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(data);
		return md5.digest();
	}

}
