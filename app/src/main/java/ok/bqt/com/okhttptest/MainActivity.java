package ok.bqt.com.okhttptest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
	private String mBaseUrl = "http://api.95xiu.com/";

	private TextView mTv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] array = {"post方式提交键值对数据",
				"get方式提交键值对数据",
				"OkHttp官方Wiki之Recipes中的示例代码",
				"OkHttp官方Wiki之Interceptors",};
		mTv = new TextView(this);// 将内容显示在TextView中
		getListView().addFooterView(mTv);
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
				startActivity(new Intent(this, OkHttpRecipes_Activity.class));
				break;
			case 3:
				startActivity(new Intent(this, OkHttp_Activity.class));
				break;
		}
	}

	private void postWithParams() {
		String url = mBaseUrl + "user/loginv2.php";

		OkHttpClient okHttpClient = new OkHttpClient();
		FormBody formBody = new FormBody.Builder()
				.add("user", "103468")
				.add("pass", "103468")
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
						runOnUiThread(() -> mTv.setText(decodeUnicodeToString(responseString)));
					}
				});
	}

	private void getWithParams() {
		String url = mBaseUrl + "app/news/index.php"
				+ "?session_id=" + mUser.getMsg().getSession_id()
				+ "&uid=" + mUser.getMsg().getId();

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
						runOnUiThread(() -> mTv.setText(decodeUnicodeToString(responseString)));
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