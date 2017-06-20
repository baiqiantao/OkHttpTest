package ok.bqt.com.okhttptest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public class OkHttp_Activity extends ListActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"应用程序拦截器",
				"网络拦截器",
				"Rewriting Requests，重写请求",
				"Rewriting Responses，重写响应结果",};
		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		new Thread(() -> {
			try {
				switch (position) {
					case 0:
						loggingInterceptor(true, new LoggingInterceptor());
						break;
					case 1:
						loggingInterceptor(false, new LoggingInterceptor());
						break;
					case 2:
						loggingInterceptor(new Random().nextBoolean(), new GzipRequestInterceptor());
						break;
					case 3:
						loggingInterceptor(new Random().nextBoolean(), new ReWriteCacheControlInterceptor());
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

	}

	private void loggingInterceptor(boolean isApplicationInterceptor, Interceptor interceptor) throws IOException {
		OkHttpClient client;
		if (isApplicationInterceptor) client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		else client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();

		Request request = new Request.Builder()
				.url("http://publicobject.com/helloworld.txt")
				.header("User-Agent", "OkHttp Example")
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		System.out.println("【响应url】" + response.request().url());
		response.body().close();
	}

	//使用一个自定义的TLS版本和密码组来构建你自己的连接规范
	private void connectionSpecs() {
		ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
				.tlsVersions(TlsVersion.TLS_1_2)
				.cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
						CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
						CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
				.build();

		OkHttpClient client = new OkHttpClient.Builder()
				.connectionSpecs(Collections.singletonList(spec))
				.build();
	}

	//Certificate Pinning，证书锁定
	public void certificatePinner() throws Exception {
		CertificatePinner certificatePinner = new CertificatePinner.Builder()
				.add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
				.build();

		OkHttpClient client = new OkHttpClient.Builder()
				.certificatePinner(certificatePinner)
				.build();

		Request request = new Request.Builder()
				.url("https://publicobject.com/robots.txt")
				.build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		for (java.security.cert.Certificate certificate : response.handshake().peerCertificates()) {
			System.out.println("【】" + CertificatePinner.pin(certificate));
		}
	}

	//********************************************************拦截器********************************************************
	class LoggingInterceptor implements Interceptor {
		@Override
		public Response intercept(Interceptor.Chain chain) throws IOException {
			Request request = chain.request();//发出的请求
			long t1 = System.nanoTime();
			System.out.println("【请求】" + String.format("Sending request %s on %s%n%s",
					request.url(), chain.connection(), request.headers()));

			Response response = chain.proceed(request);//生成与请求对应的响应。这里是所有HTTP工作发生的地方
			long t2 = System.nanoTime();
			System.out.println("【响应】" + String.format("Received response for %s in %.1fms%n%s",
					response.request().url(), (t2 - t1) / 1e6d, response.headers()));

			return response;
		}
	}

	/**
	 * This interceptor compresses the HTTP request body. Many webservers can't handle this!
	 */
	class GzipRequestInterceptor implements Interceptor {
		@Override
		public Response intercept(Interceptor.Chain chain) throws IOException {
			Request originalRequest = chain.request();
			if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
				return chain.proceed(originalRequest);//生成与请求对应的响应
			}

			RequestBody compressedRequestBody = new RequestBody() {
				@Override
				public MediaType contentType() {
					return originalRequest.body().contentType();
				}

				@Override
				public long contentLength() {
					return -1; // We don't know the compressed length in advance(提前)!
				}

				@Override
				public void writeTo(BufferedSink sink) throws IOException {
					BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
					originalRequest.body().writeTo(gzipSink);
					gzipSink.close();
				}
			};

			Request compressedRequest = originalRequest.newBuilder()
					.header("Content-Encoding", "gzip")//添加、删除或者替换请求头
					.method(originalRequest.method(), compressedRequestBody)//改变请求体
					.build();
			return chain.proceed(compressedRequest);//重新生成与请求对应的响应
		}
	}

	/**
	 * Dangerous interceptor that rewrites the server's cache-control header.
	 */
	class ReWriteCacheControlInterceptor implements Interceptor {
		@Override
		public Response intercept(Interceptor.Chain chain) throws IOException {
			Response originalResponse = chain.proceed(chain.request());
			return originalResponse.newBuilder()
					.header("Cache-Control", "max-age=60")//重写响应头部并改变响应体
					.build();
		}
	}
}