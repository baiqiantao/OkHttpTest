package ok.bqt.com.okhttptest;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OkHttpRecipes_Activity extends ListActivity {
	private OkHttpClient client = new OkHttpClient();
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	private static final String IMGUR_CLIENT_ID = "...";
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"同步Get",
				"异步Get",
				"访问头部",
				"Post发送字符串",
				"Post发送流",
				"Post发送文件",
				"发布形式参数",
				"发布多请求体的请求",
				"使用Gson来解析JSON响应",
				"响应缓存",
				"取消一个请求",
				"响应超时",
				"配置Call",
				"处理身份验证",};
		for (int i = 0; i < array.length; i++) {
			array[i] = i + "、" + array[i];
		}
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if (position == 1) {//异步请求，可以在UI线程请求
			try {
				asynchronousGet();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {//同步请求，不可以在UI线程请求，否则直接异常退出
			new Thread(() -> {
				try {
					switch (position) {
						case 0:
							synchronousGet();
							break;
						case 2:
							accessingHeaders();
							break;
						case 3:
							postString();
							break;
						case 4:
							postStreaming();
							break;
						case 5:
							postFile();
							break;
						case 6:
							postFormParameters();
							break;
						case 7:
							postMultipartRequest();
							break;
						case 8:
							parseJSONResponseWithGson();
							break;
						case 9:
							responseCaching();
							break;
						case 10:
							cancelingCall();
							break;
						case 11:
							settTmeouts();
							break;
						case 12:
							perCallConfiguration();
							break;
						case 13:
							handlingAuthentication();
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	//同步Get
	public void synchronousGet() throws Exception {
		Request request = new Request.Builder()
				.url("http://publicobject.com/helloworld.txt")
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		Headers responseHeaders = response.headers();
		for (int i = 0; i < responseHeaders.size(); i++) {
			System.out.println("【" + responseHeaders.name(i) + "】" + responseHeaders.value(i));
		}
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//异步Get
	public void asynchronousGet() throws Exception {
		Request request = new Request.Builder()
				.url("http://publicobject.com/helloworld.txt")
				.build();
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
				
				Headers responseHeaders = response.headers();
				for (int i = 0, size = responseHeaders.size(); i < size; i++) {
					System.out.println("【" + responseHeaders.name(i) + "】" + responseHeaders.value(i));
				}
				
				System.out.println("【响应结果】" + response.body().string());
			}
		});
	}
	
	//访问头部
	public void accessingHeaders() throws Exception {
		Request request = new Request.Builder()
				.url("https://api.github.com/repos/square/okhttp/issues")
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【Server】" + response.header("Server"));
		System.out.println("【Date】" + response.header("Date"));
		System.out.println("【Vary】" + response.headers("Vary"));
	}
	
	//Post发送字符串
	public void postString() throws Exception {
		String postBody = ""
				+ "Releases\n"
				+ "--------\n"
				+ "\n"
				+ " * _1.0_ May 6, 2013\n"
				+ " * _1.1_ June 15, 2013\n"
				+ " * _1.2_ August 11, 2013\n";
		
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//Post发送流
	public void postStreaming() throws Exception {
		RequestBody requestBody = new RequestBody() {
			@Override
			public MediaType contentType() {
				return MEDIA_TYPE_MARKDOWN;
			}
			
			@Override
			public void writeTo(BufferedSink sink) throws IOException {
				sink.writeUtf8("Numbers\n");
				sink.writeUtf8("-------\n");
				for (int i = 2; i <= 997; i++) {
					sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
				}
			}
			
			private String factor(int n) {
				for (int i = 2; i < n; i++) {
					int x = n / i;
					if (x * i == n) return factor(x) + " × " + i;
				}
				return Integer.toString(n);
			}
		};
		
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(requestBody)
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//Post发送文件
	public void postFile() throws Exception {
		File file = new File(Environment.getExternalStorageDirectory(), "README.md");
		
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//发布形式参数
	public void postFormParameters() throws Exception {
		RequestBody formBody = new FormBody.Builder()
				.add("search", "Jurassic Park")
				.build();

		Request request = new Request.Builder()
				.url("https://en.wikipedia.org/w/index.php")
				.post(formBody)
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//发布多请求体的请求
	public void postMultipartRequest() throws Exception {
		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("title", "Square Logo")
				.addFormDataPart("image", "logo-square.png",
						RequestBody.create(MEDIA_TYPE_PNG, new File(Environment.getExternalStorageDirectory(), "logo.png"))).build();
		
		Request request = new Request.Builder()
				.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
				.url("https://api.imgur.com/3/image")
				.post(requestBody)
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//使用Gson来解析JSON响应
	public void parseJSONResponseWithGson() throws Exception {
		Request request = new Request.Builder()
				.url("https://api.github.com/gists/c2a7c39532239ff261be")
				.build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		Gist gist = new Gson().fromJson(response.body().charStream(), Gist.class);
		for (Map.Entry<String, Gist.GistFile> entry : gist.files.entrySet()) {
			System.out.println("【Key】" + entry.getKey());
			System.out.println("【Value】" + entry.getValue().content);
		}
	}
	
	static class Gist {
		Map<String, GistFile> files;

		static class GistFile {
			String content;
		}
	}
	
	//响应缓存
	public void responseCaching() throws Exception {
		Cache cache = new Cache(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "cache"),
				10 * 1024 * 1024);// 10 MiB

		client = new OkHttpClient.Builder()
				.cache(cache)
				.build();

		Request request = new Request.Builder()
				.url("http://publicobject.com/helloworld.txt")
				.build();
		
		Response response1 = client.newCall(request).execute();
		if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);
		
		String response1Body = response1.body().string();
		System.out.println("【Response 1 response】" + response1);
		System.out.println("【Response 1 cache response】" + response1.cacheResponse());
		System.out.println("【Response 1 network response】" + response1.networkResponse());
		
		Response response2 = client.newCall(request).execute();
		if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);
		
		String response2Body = response2.body().string();
		System.out.println("【Response 2 response】" + response2);
		System.out.println("【Response 2 cache response】" + response2.cacheResponse());
		System.out.println("【Response 2 network response】" + response2.networkResponse());
		
		System.out.println("【两次响应结果是否相同】" + response1Body.equals(response2Body));
	}

	//取消一个请求
	public void cancelingCall() throws Exception {
		Request request = new Request.Builder()
				.url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
				.build();
		
		final long startNanos = System.nanoTime();
		final Call call = client.newCall(request);
		
		// Schedule a job to cancel the call in 1 second.
		Executors.newScheduledThreadPool(1).schedule(() -> {
			System.out.printf("【】%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
			call.cancel();
			System.out.printf("【】%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
		}, 1, TimeUnit.SECONDS);
		
		try {
			System.out.printf("【】%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
			Response response = call.execute();
			System.out.printf("【】%.2f Call was expected to fail, but completed: %s%n", (System.nanoTime() - startNanos) / 1e9f, response);
		} catch (IOException e) {
			System.out.printf("【】%.2f Call failed as expected: %s%n", (System.nanoTime() - startNanos) / 1e9f, e);
		}
	}
	
	//响应超时
	public void settTmeouts() throws Exception {
		client = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(500, TimeUnit.MILLISECONDS)
				.build();

		Request request = new Request.Builder()
				.url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
				.build();
		
		Response response = client.newCall(request).execute();
		System.out.println("【响应结果】" + response.body().string());
	}
	
	//配置Call
	public void perCallConfiguration() throws Exception {

		OkHttpClient copyClient = client.newBuilder()// Copy to customize OkHttp for this request.
				.readTimeout(500, TimeUnit.MILLISECONDS)
				.build();

		Request request = new Request.Builder()
				.url("http://httpbin.org/delay/1") // This URL is served with a 1 second delay.
				.build();

		Response response = copyClient.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		System.out.println("【响应结果】" + response.body().string());
	}
	
	//处理身份验证
	public void handlingAuthentication() throws Exception {
		client = new OkHttpClient.Builder()
				.authenticator((route, response) -> {
					System.out.println("【Authenticating for response】" + response);
					System.out.println("【Challenges】" + response.challenges());
					return response.request()
							.newBuilder()
							.header("Authorization", Credentials.basic("jesse", "password1"))
							.build();
				})
				.build();

		Request request = new Request.Builder()
				.url("http://publicobject.com/secrets/hellosecret.txt")
				.build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println("【响应结果】" + response.body().string());
	}
}