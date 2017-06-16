//package ok.bqt.com.okhttptest;
//
//import android.app.ListActivity;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import okhttp3.CookieJar;
//import okhttp3.MediaType;
//
//public class OkHttpUtils_Activity extends ListActivity {
//	private User mUser;
//	private String user = "103468";
//	private String pass = "103468";
//	private String session_id;
//	private String uid;
//	private String mBaseUrl = "http://tapi.95xiu.com/";
//
//	private TextView mTv;
//	private ImageView mImageView;
//	private ProgressBar mProgressBar;
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		String[] array = { "post方式提交键值对数据", "get方式提交键值对数据", "post方式提交键值对数据文件", "post方式提交文件",//
//				"post方式提交Json数据", "get方式获取图片", "get方式下载文件", "清除Session", };
//		for (int i = 0; i < array.length; i++) {
//			array[i] = i + "、" + array[i];
//		}
//		mTv = new TextView(this);// 将内容显示在TextView中
//		mTv.setTextColor(Color.BLUE);
//		mTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//		mTv.setPadding(20, 10, 20, 10);
//		mImageView = new ImageView(this);
//		mProgressBar = new ProgressBar(this);
//		mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
//		mProgressBar.setMax(100);
//		getListView().addFooterView(mProgressBar);
//		getListView().addFooterView(mTv);
//		getListView().addFooterView(mImageView);
//		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));
//	}
//
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		switch (position) {
//			case 0:
//				postWithParams(); //post方式提交键值对数据
//				break;
//			case 1:
//				getWithParams(); //get方式提交键值对数据
//				break;
//			case 2:
//				postParamsAndFile(); //post方式提交键值对数据，同时提交文件
//				break;
//			case 3:
//				postFile();//post方式提交文件---------------------用不到
//				break;
//			case 4:
//				postJson();//post方式提交Json数据
//				break;
//			case 5:
//				getImage();//get方式获取图片
//				break;
//			case 6:
//				downloadFile();//get方式下载文件
//				break;
//			case 7:
//				clearSession();//清除Session
//				break;
//		}
//	}
//
//	//post方式提交键值对数据
//	public void postWithParams() {
//		String url = mBaseUrl + "user/loginv2.php";
//		OkHttpUtils.post().url(url).addParams("user", user).addParams("pass", pass).build().execute(new StringCallback() {
//
//			@Override
//			public void onBefore(Request request, int id) {
//				mTv.setText("onBefore...\n\n");
//			}
//
//			@Override
//			public void onAfter(int id) {
//				mTv.append("onAfter...");
//			}
//
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				e.printStackTrace();
//				mTv.append("onError:" + e.getMessage() + "\n\n");
//			}
//
//			@Override
//			public void onResponse(String response, int id) {
//				mTv.append(response + "\n\n");
//				Log.i("bqt", response);
//				mUser = new Gson().fromJson(response, User.class);
//				uid = mUser.getMsg().getId();
//				session_id = mUser.getMsg().getSession_id();
//			}
//		});
//	}
//
//	//get方式提交键值对数据
//	public void getWithParams() {
//		String url = mBaseUrl + "app/news/index.php";
//		OkHttpUtils.get().url(url).id(100).addParams("session_id", session_id).addParams("uid", uid).build().execute(new MyStringCallback());
//	}
//
//	//post方式提交键值对数据，同时提交文件
//	public void postParamsAndFile() {
//		String url = mBaseUrl + "myprofile/editinfo.php";
//		File file = new File(Environment.getExternalStorageDirectory(), "bqt.jpg");
//		if (!file.exists()) {
//			Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//			return;
//		}
//
//		Map<String, String> params = new HashMap<>();
//		params.put("session_id", session_id);
//		params.put("uid", uid);
//
//		OkHttpUtils.post().addFile("image", "文件名", file).url(url).params(params).build().execute(new MyStringCallback());//可以提交多个文件
//	}
//
//	//post方式提交文件---------------------用不到
//	public void postFile() {
//		String url = "http://app.fulijr.com/api/v1.3.4/a61000b6a80a";
//		File file = new File(Environment.getExternalStorageDirectory(), "bqt.jpg");
//		if (!file.exists()) {
//			Toast.makeText(MainActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//			return;
//		}
//		OkHttpUtils.postFile().url(url).file(file).build().execute(new MyStringCallback());
//	}
//
//	//post方式提交Json数据
//	public void postJson() {
//		String url = mBaseUrl + "user/loginv2.php";
//		OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json; charset=utf-8")).content(new Gson().toJson(mUser)).build()
//				.execute(new MyStringCallback());
//	}
//
//	//get方式获取图片
//	public void getImage() {
//		String url = "http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6cc15d5839816fdfaae516756.jpg";
//		OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000).readTimeOut(20000).writeTimeOut(20000)//
//				.execute(new BitmapCallback() {
//					@Override
//					public void onError(Call call, Exception e, int id) {
//						mTv.setText("onError:" + e.getMessage());
//					}
//
//					@Override
//					public void onResponse(Bitmap bitmap, int id) {
//						mImageView.setImageBitmap(bitmap);
//					}
//				});
//	}
//
//	//get方式下载文件
//	public void downloadFile() {
//		String url = "http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6cc15d5839816fdfaae516756.jpg";
//		String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//		OkHttpUtils.get().url(url).build().execute(new FileCallBack(filePath, "包青天.jpg") {
//
//			@Override
//			public void onBefore(Request request, int id) {
//				mTv.setText("onBefore...\n\n");
//			}
//
//			@Override
//			public void onAfter(int id) {
//				mTv.append("onAfter...");
//			}
//
//			@Override
//			public void inProgress(float progress, long total, int id) {
//				mProgressBar.setProgress((int) (100 * progress));
//				mTv.append(progress + "\n");
//			}
//
//			@Override
//			public void onError(Call call, Exception e, int id) {
//				e.printStackTrace();
//				mTv.append("onError:" + e.getMessage() + "\n\n");
//			}
//
//			@Override
//			public void onResponse(File file, int id) {
//				mTv.append(file.getAbsolutePath() + "\n\n");
//			}
//		});
//	}
//
//	//清除Session
//	public void clearSession() {
//		CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
//		if (cookieJar instanceof CookieJarImpl) {
//			((CookieJarImpl) cookieJar).getCookieStore().removeAll();
//		}
//	}
//
//	//其他请求方式
//	public void otherRequestDemo() {
//		//also can use delete ,head , patch
//		OkHttpUtils.put().url("http://11111.com").requestBody("may be something").build()//
//				.execute(new MyStringCallback());
//
//		try {
//			OkHttpUtils.head().url("http://11111.com").addParams("name", "zhy").build().execute();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		OkHttpUtils.getInstance().cancelTag(this);
//	}
//
//	public class MyStringCallback extends StringCallback {
//
//		@Override
//		public void onError(Call call, Exception e, int id) {
//			e.printStackTrace();
//			mTv.setText("onError:" + e.getMessage());
//		}
//
//		@Override
//		public void onResponse(String response, int id) {
//			mTv.setText("onResponse:" + decodeUnicodeToString(response));
//		}
//	}
//
//	/**将Unicode编码解析成字符串形式（如汉字） */
//	public static String decodeUnicodeToString(String uString) {
//		StringBuilder sb = new StringBuilder();
//		int i = -1, pos = 0;
//		while ((i = uString.indexOf("\\u", pos)) != -1) {
//			sb.append(uString.substring(pos, i));
//			if (i + 5 < uString.length()) {
//				pos = i + 6;
//				sb.append((char) Integer.parseInt(uString.substring(i + 2, i + 6), 16));
//			}
//		}
//		sb.append(uString.substring(pos));
//		return sb.toString();
//	}
//}