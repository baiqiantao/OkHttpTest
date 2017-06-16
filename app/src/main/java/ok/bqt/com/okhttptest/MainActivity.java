package ok.bqt.com.okhttptest;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends ListActivity {
	private User mUser;
	private String user = "103468";
	private String pass = "103468";
	private String session_id;
	private String uid;
	private String mBaseUrl = "http://api.95xiu.com/";

	private TextView mTv;
	private ImageView mImageView;
	private ProgressBar mProgressBar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"post方式提交键值对数据",
				"get方式提交键值对数据",
				"post方式提交键值对数据文件",
				"post方式提交文件",
				"post方式提交Json数据",
				"get方式获取图片",
				"get方式下载文件",
				"清除Session",};
		for (int i = 0; i < array.length; i++) {
			array[i] = i + "、" + array[i];
		}
		mTv = new TextView(this);// 将内容显示在TextView中
		mTv.setTextColor(Color.BLUE);
		mTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		mTv.setPadding(20, 10, 20, 10);
		mImageView = new ImageView(this);
		mProgressBar = new ProgressBar(this);
		mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
		mProgressBar.setMax(100);
		getListView().addFooterView(mProgressBar);
		getListView().addFooterView(mTv);
		getListView().addFooterView(mImageView);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				postWithParams(); //post方式提交键值对数据
				break;
			case 1:
				getWithParams(); //get方式提交键值对数据
				break;
			case 2:
				postParamsAndFile(); //post方式提交键值对数据，同时提交文件
				break;
			case 3:
				postFile();//post方式提交文件---------------------用不到
				break;
			case 4:
				postJson();//post方式提交Json数据
				break;
			case 5:
				getImage();//get方式获取图片
				break;
			case 6:
				downloadFile();//get方式下载文件
				break;
			case 7:
				clearSession();//清除Session
				break;
		}
	}

	private void clearSession() {

	}

	private void downloadFile() {

	}

	private void getImage() {

	}

	private void postJson() {

	}

	private void postFile() {

	}

	private void postParamsAndFile() {

	}

	private void getWithParams() {
		String url = mBaseUrl + "app/news/index.php"
				+ "?session_id=" + session_id
				+ "&uid=" + uid;

		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();
		okHttpClient.newCall(request)
				.enqueue(new okhttp3.Callback() {
					@Override
					public void onFailure(@NonNull Call call, @NonNull IOException e) {

					}

					@Override
					public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
						final String responseString = response.body().string();
						Log.i("bqt", "【body】" + decodeUnicodeToString(responseString));//{"result":0,"msg":"用户不存在"}
					}
				});
	}

	private void postWithParams() {
		String url = mBaseUrl + "user/loginv2.php";

		OkHttpClient okHttpClient = new OkHttpClient();
		FormBody formBody = new FormBody.Builder()
				.add("user", user)
				.add("pass", pass)
				.build();
		Request request = new Request.Builder().url(url).post(formBody).build();
		okHttpClient.newCall(request)
				.enqueue(new okhttp3.Callback() {
					@Override
					public void onFailure(@NonNull Call call, @NonNull IOException e) {

					}

					@Override
					public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
						Log.i("bqt", "【message】" + response.message());//ok
						final String responseString = response.body().string();
						Log.i("bqt", "【body】" + decodeUnicodeToString(responseString));//{"result":0,"msg":"用户不存在"}

						mUser = new Gson().fromJson(responseString, User.class);
						uid = mUser.getMsg().getId();
						session_id = mUser.getMsg().getSession_id();

						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mTv.setText("uid=" + uid + "\nsession_id=" + session_id + "\n" + decodeUnicodeToString(responseString));
							}
						});
					}
				});
	}

	/**
	 * 将Unicode编码解析成字符串形式（如汉字）
	 */
	public static String decodeUnicodeToString(String uString) {
		StringBuilder sb = new StringBuilder();
		int i = -1, pos = 0;
		while ((i = uString.indexOf("\\u", pos)) != -1) {
			sb.append(uString.substring(pos, i));
			if (i + 5 < uString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(uString.substring(i + 2, i + 6), 16));
			}
		}
		sb.append(uString.substring(pos));
		return sb.toString();
	}
}