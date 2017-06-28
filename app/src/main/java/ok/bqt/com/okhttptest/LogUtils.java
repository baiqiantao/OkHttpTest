package ok.bqt.com.okhttptest;

import android.util.Log;

import java.util.Collection;

public class LogUtils {
	public final static boolean DEBUG = BuildConfig.DEBUG;
	
	public static void v(Object... message) {
		if (DEBUG) log(Log.VERBOSE, formatMessage(message));
	}
	
	public static void d(Object... message) {
		if (DEBUG) log(Log.DEBUG, formatMessage(message));
	}
	
	public static void i(Object... message) {
		if (DEBUG) log(Log.INFO, formatMessage(message));
	}
	
	public static void w(Object... message) {
		log(Log.WARN, formatMessage(message));
	}
	
	public static void e(Object... message) {
		log(Log.ERROR, formatMessage(message));
	}
	
	/**
	 * 根据type输出日志消息，包括方法名，方法行数，Message
	 *
	 * @param type    日志类型，如Log.INFO
	 * @param message 日志内容
	 */
	private static void log(int type, String message) {
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
		String className = stackTrace.getClassName();
		String tag = className.substring(className.lastIndexOf('.') + 1);
		StringBuilder sb = new StringBuilder();
		
		sb.append("包青天")
				.append("(")
				.append(stackTrace.getFileName())//文件名
				.append(":")
				.append(stackTrace.getLineNumber())//行号
				.append(")")
				.append("#")
				.append(stackTrace.getMethodName())//方法名
				.append("【")
				.append(message)//消息
				.append("】");
		
		switch (type) {
			case Log.DEBUG:
				Log.d(tag, sb.toString());
				break;
			case Log.INFO:
				Log.i(tag, sb.toString());
				break;
			case Log.WARN:
				Log.w(tag, sb.toString());
				break;
			case Log.ERROR:
				Log.e(tag, sb.toString());
				break;
			case Log.VERBOSE:
				Log.v(tag, sb.toString());
				break;
		}
	}
	
	private static String formatMessage(Object... messages) {
		String out = objToString(messages);
		if (out.startsWith("\n")) out = out.substring(1);//******************************************可以优化
		if (out.endsWith("\n")) out = out.substring(0, out.length() - 1);//*************************可以优化
		return out;
	}

	//******************************************************************************************
	private static String objToString(Object obj) {
		return objToString(obj, 0);
	}

	private static String objToString(Object obj, int currentLvel) {
		if (obj == null) return "";
		currentLvel++;
		StringBuilder sb = new StringBuilder();
		if (obj instanceof String) {//字符串
			sb.append(getSpace(currentLvel));
			sb.append(obj);
		} else if (obj instanceof Object[]) {//数组
			Object[] array = (Object[]) obj;
			for (int i = 0; i < array.length; i++) {
				sb.append(i)
						.append(objToString(array[i], currentLvel));//递归
			}
		} else if (obj instanceof Collection) {//集合
			if (!sb.toString().endsWith("\n")) sb.append("\n");//************************************可以优化
			for (Object mobj : (Collection) obj) {
				sb.append(objToString(mobj, currentLvel));//递归
			}
		} else {//其他对象
			sb.append(getSpace(currentLvel));
			sb.append(obj.toString());
		}
		if (!sb.toString().endsWith("\n")) sb.append("\n");//***************************************可以优化
		return sb.toString();
	}

	/***
	 * 格式化目录
	 *
	 * @param level 目录层次，也即"| _ _"的个数
	 */
	private static String getSpace(int level) {
		StringBuilder sb = new StringBuilder();
		for (int x = 2; x < level; x++) {
			sb.append("| _ _ ");
		}
		return sb.toString();
	}
}
