package com.example.scroviewscrenn.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS;
import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;


public class AppUtils {

    public static Context mContext = null;
    public static final String MY_TREE_URL = "http://api.zgjky.cn/H5AD/flowPath/index.html";
    public static final String MY_NEW_URL = "https://api.zgjky.cn/H5AD/whatMadeHealth/madeHealth.html";
    //广告页
    public static final String ADVERTISEMENT_URL = "http://api.zgjky.cn/H5AD/AD/index.html";
    //领取优惠卷
    public static final String PICKUP_COUPON_URL = "http://192.168.18.73:8081/#/activities/activitiesPage/activitiesIndex?devType=app";
    //来抽奖
    public static final String LUCK_DRAW_URL = "https://api.zgjky.cn/H5AD/A_getPrice/index.html";
    //public static final String YINDAO_URL = "http://api.zgjky.cn/H5AD/flowPage/index.html";
    public static final String YINDAO_URL = "https://api.zgjky.cn/H5AD/flowPageImg/index.html";
    //服务须知与保障
    public static final String REASSURANCE_URL = "https://api.zgjky.cn/H5AD/protect/heartFree.html";
    //优惠券设置提醒
    public static String CALANDER_URL = "content://com.android.calendar/calendars";
    public static String CALANDER_EVENT_URL = "content://com.android.calendar/events";
    public static String CALANDER_REMIDER_URL = "content://com.android.calendar/reminders";
    /**
     * 换行字符
     **/
    public static final String HUANHANG = "【换行】";
    //我的健康豆
    public static final String HEALTHBEAN_URL = "https://api.zgjky.cn/H5AD/Wbean/index.html";

    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;// 获取设备密度
        int px = (int) (dp * density + 0.5f);// 4.3, 4.9, 加0.5是为了四舍五入
        return px;
    }

    public static float px2dp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;// 获取设备密度
        float dp = px / density;
        return dp;
    }

    public static int dp2px(Context context, int dip) {
        Resources resources = context.getResources();
        int px = Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, resources.getDisplayMetrics()));
        return px;
    }

    // 屏幕高度（像素）
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    /**
     * 获取网络address地址对应的图片`
     *
     * @param address
     * @return bitmap的类型
     */
    public static Bitmap getImage(String address) {
        // 通过代码 模拟器浏览器访问图片的流程
        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            // 获取服务器返回回来的流
            InputStream is = conn.getInputStream();
            byte[] imagebytes = StreamTool.getBytes(is);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagebytes, 0,
                    imagebytes.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static class StreamTool {
        public static byte[] getBytes(InputStream is) throws Exception {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            is.close();
            bos.flush();
            byte[] result = bos.toByteArray();
            return result;
        }
    }


    public static void close(Closeable out) {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /***
     * 根据Uri取出路径
     *
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = mContext.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null,
                    null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /***
     * 设置Bitmap的大小(等比例缩放)
     *
     * @param path
     * @return
     */
    public static Bitmap decodeBitmap(String path) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高,返回的bitmap为null，但是宽和高则放入Options中
        BitmapFactory.decodeFile(path, op);
        int realHeight = op.outWidth;
        int realWidth = op.outHeight;
        int scale = (realHeight > realWidth ? realHeight : realWidth) / 400;
        if (scale <= 0) {
            scale = 1;
        }
        op.inSampleSize = scale;
        op.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, op);
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(path, options);
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, 240, 400);
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 将Bitmap保存成图片
     *
     * @param bitmap
     * @return
     */
    public static String savePictureBitmap(Bitmap bitmap) {
        String mFileName = System.currentTimeMillis() + ".png";
        String path = FileUtils.getPhotoCacheDir() + "/" + mFileName;
        try {
            FileOutputStream e = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, e);
            e.flush();
            e.close();
            return path;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }


    /**
     * 将bytes保存成图片
     *
     * @param bytes
     * @return
     */
    public static String savePictureBitmap(byte[] bytes) {
        String mFileName = System.currentTimeMillis() + ".png";
        String path = FileUtils.getPhotoCacheDir() + "/" + mFileName;
        try {

            FileOutputStream e = new FileOutputStream(path);
            e.write(bytes);
            e.flush();
            e.close();
            return path;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     *
     * @param bytes
     * @param opts
     * @return Bitmap
     */
    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null) {
            if (opts != null) {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            } else {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }
        return null;
    }

    /**
     * 将Bitmap保存成图片
     *
     * @param bitmap
     * @return
     */
    public static String savePictureBitmapElse(Bitmap bitmap) {
        String mFileName = System.currentTimeMillis() + ".jpg";
        String path = FileUtils.getPhotoCacheDir() + "/" + mFileName;
        try {
            FileOutputStream e = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, e);
            e.flush();
            e.close();
            return path;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    /**
     * 将Bitmap保存成图片
     *
     * @param bitmap
     * @return
     */
    public static String savePictureBitmap(Bitmap bitmap, int maxSize) {
        String mFileName = System.currentTimeMillis() + ".png";
        String path = FileUtils.getPhotoCacheDir() + "/" + mFileName;
        FileOutputStream e = null;
        try {
            e = new FileOutputStream(path);
            while (true) {
                if (bitmap.getByteCount() < maxSize) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, e);
                    return path;
                }
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, e);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        } finally {
            try {
                e.flush();
                e.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }


    /****
     * 计算Bitmap大小
     *
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    //计算图片的缩放值
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 检测某ActivityUpdate是否在当前Task的栈顶
     */
    public static boolean isTopActivy(String cmdName) {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if (null != runningTaskInfos) {
            cmpNameTemp = runningTaskInfos.get(0).topActivity.getClassName();
        }
        if (null == cmpNameTemp) return false;
        return cmpNameTemp.equals(cmdName);
    }


    /***
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void open(Context context, Class clzz) {
        Intent intent = new Intent(context, clzz);
        context.startActivity(intent);
    }

    public static void openForResult(Context context, Class clzz) {
        Activity activity = (Activity) context;
        Intent intent = new Intent(context, clzz);
        activity.startActivityForResult(intent, 1);
    }

    public static void openForResult(Context context, Class clzz, int requestCode) {
        Activity activity = (Activity) context;
        Intent intent = new Intent(context, clzz);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 判断给定字符串时间是否为今日(效率不是很高，不过也是一种方法)
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /***
     * 校验手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean checkMobile(String mobiles) {
        String regEx = "^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(mobiles).matches();
    }

    /***
     * 校验邮箱号码
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regEx = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(email).matches();
    }

    /***
     * 校验是否为中文
     *
     * @param txt
     * @return
     */
    public static boolean checkChinese(String txt) {
        String regEx = "^[\u4E00-\u9FA5\uF900-\uFA2D]+$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(txt).matches();
    }

    /**
     * 校验是否含有特殊符号
     *
     * @param text
     * @return
     */
    public static boolean checkSpecialSymbol(String text) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 校验是否全部数字
     *
     * @param text
     * @return
     */
    public static boolean checkAllNumber(String text) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /***
     * 检查密码规则
     *
     * @param pwd
     * @return
     */
    public static Boolean checkPwdRule(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        //包含数字
        boolean i = pwd.matches("(.*[0-9].*)");
        //包含字母
        boolean j = pwd.matches(".*[a-zA-Z]+.*");
        //包含特殊字符
        boolean k = pwd.matches(".*[\\x21-\\x2F\\x3A-\\x40\\x5B-\\x60\\x7B-\\x7E].*");
        //大于8位小于16位
        boolean s = pwd.length() >= 8 && pwd.length() <= 16;
        return i && j && k && s;
    }

    public static String encryptionMobile(String mobile) {
        return mobile.replaceAll("(\\d{4})\\d{4}(\\d{3})", "$1****$2");
    }


    /**
     * 获取软件渠道
     *
     * @return
     */
    public static String getChannelCode(Context mContext) {
        String code = getMetaData(mContext, "UMENG_CHANNEL");
        if (code != null) {
            Log.e("!!", "渠道号:" + code);
            return code;
        }
        return "ZGJKY";
    }

    private static String getMetaData(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            Object value = ai.metaData.get(key);
            if (value != null) {
                return value.toString();
            }
        } catch (Exception e) {
        }
        return null;
    }

    /***
     * 获取软件版本名称
     *
     * @param mContext
     * @return
     */
    public static String getVersionName(Context mContext) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /***
     * 获取当前应用版本号
     *
     * @return
     */
    public static int getVersionCode(Context mContext) {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /***
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
     *
     * @param str
     * @return 返回全部为全角字符的字符串
     */
    public static String toDBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }
    /**
     * 日期格式字符串转换成时间戳
     *
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static long timeStamp() {
        long time = System.currentTimeMillis();
        return time / 1000;
    }



    /**
     * 判断是否安装次包名的应用
     *
     * @param context
     * @param packageName
     * @return
     */
    private static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();//获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名
        //从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /**
     * 获取监测项的Code
     */
    private static String getDicCode(int i) {
        String dicCode = "";
        switch (i) {
            case 0:// 体重
                dicCode = "10003";
                break;
            case 1:// 体重指数
                dicCode = "10004";
                break;
            case 2:// 腰围
                dicCode = "10010";
                break;
            case 3:// 脉搏
                dicCode = "10013";
                break;
            case 4:// 心率
                dicCode = "10244";
                break;
            case 5:// 体温
                dicCode = "10002";
                break;
            case 6:// 血压
                dicCode = "10018,10023";
                break;
            case 7:// 血糖监测
                dicCode = "10144,10145,10142";
                break;
            case 8:// 甘油三酯
                dicCode = "10147";
                break;
            case 9:// 低密度胆固醇
                dicCode = "10148";
                break;
            case 10:// 高密度胆固醇
                dicCode = "10149";
                break;
            case 11:// 总胆固醇
                dicCode = "10151";
                break;
            case 12:// 膳食
                dicCode = "10769";
                break;
            case 13:// 饮酒(不要组合了)
                dicCode = "10767";
                break;
            case 14:// 吸烟
                dicCode = "10766";
                break;
            case 15://运动能耗
                dicCode = "10770";
                break;
            case 16://血氧
                dicCode = "11141";
                break;
            case 17://脂肪
                dicCode = "11143";
                break;
            default:
                break;
        }
        return dicCode;
    }

    /**
     * editatext输入小数
     *
     * @param editText
     */
    public static void setTextPoint(final EditText editText, final int i) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
//                editText.setTextSize(s.toString().equals("") ? 14 : 35);
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > i) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + (i + 1));
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }


    /**
     * editatext输入只能一位小数
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText, final int i) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                editText.setTextSize(s.toString().equals("") ? 14 : 35);
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > i) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + (i + 1));
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }


    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     * @author SHANHY
     * @date 2015-8-7
     */

    /**
     * 用来对double类型的数据做四舍五入的操作
     */
    //四舍五入double类型的数据
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bigData = new BigDecimal(value);
        bigData = bigData.setScale(scale, roundingMode);
        double dv = bigData.doubleValue();
        bigData = null;
        return dv;
    }

    /**
     * 判断是否符合身份证号码的规范
     *
     * @param IDCard
     * @return
     */
    public static boolean isIDCard(String IDCard) {
        if (IDCard != null) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }


    /**
     * <p>
     * 身份证合法性校验
     * </p>
     *
     * <pre>
     * --15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
     * --18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
     *    最后一位为校验位
     * </pre>
     *
     * @author 313921
     */

    /**
     * <pre>
     * 省、直辖市代码表：
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆
     *     71 : 台湾
     *     81 : 香港  82 : 澳门
     *     91 : 国外
     * </pre>
     */
    private static String[] cityCode = {"11", "12", "13", "14", "15", "21",
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82", "91"};

    /**
     * 每位加权因子
     */
    private static int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
            8, 4, 2};

    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard 身份证
     * @return 合法返回true，否则返回false
     */
    public static boolean isValidatedAllIdcard(String idcard) {
        if (idcard == null || "".equals(idcard)) {
            return false;
        }
        if (idcard.length() == 15) {
            return validate15IDCard(idcard);
        }
        return validate18Idcard(idcard);
    }

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard
     * @return
     */
    public static boolean validate18Idcard(String idcard) {
        if (idcard == null) {
            return false;
        }

        // 非18位为假
        if (idcard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, 17);

        // 前17位全部为数字
        if (!isDigital(idcard17)) {
            return false;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return false;
        }

        // 校验出生日期
        String birthday = idcard.substring(6, 14);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {// 出生年月日不正确
                return false;
            }

        } catch (ParseException e1) {

            return false;
        }

        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);

        char c[] = idcard17.toCharArray();

        int bit[] = converCharToInt(c);

        int sum17 = 0;

        sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        return idcard18Code.equalsIgnoreCase(checkCode);
    }

    /**
     * 校验15位身份证
     * <p>
     * <pre>
     * 只校验省份和出生年月日
     * </pre>
     *
     * @param idcard
     * @return
     */
    public static boolean validate15IDCard(String idcard) {
        if (idcard == null) {
            return false;
        }
        // 非15位为假
        if (idcard.length() != 15) {
            return false;
        }

        // 15全部为数字
        if (!isDigital(idcard)) {
            return false;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return false;
        }

        String birthday = idcard.substring(6, 12);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {// 身份证日期错误
                return false;
            }

        } catch (ParseException e1) {

            return false;
        }

        return true;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     */
    public String convertIdcarBy15bit(String idcard) {
        if (idcard == null) {
            return null;
        }

        // 非15位身份证
        if (idcard.length() != 15) {
            return null;
        }

        // 15全部为数字
        if (!isDigital(idcard)) {
            return null;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return null;
        }

        String birthday = idcard.substring(6, 12);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        Date birthdate = null;
        try {
            birthdate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthdate);
            if (!tmpDate.equals(birthday)) {// 身份证日期错误
                return null;
            }

        } catch (ParseException e1) {
            return null;
        }

        Calendar cday = Calendar.getInstance();
        cday.setTime(birthdate);
        String year = String.valueOf(cday.get(Calendar.YEAR));

        String idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

        char c[] = idcard17.toCharArray();
        String checkCode = "";

        // 将字符数组转为整型数组
        int bit[] = converCharToInt(c);

        int sum17 = 0;
        sum17 = getPowerSum(bit);

        // 获取和值与11取模得到余数进行校验码
        checkCode = getCheckCodeBySum(sum17);

        // 获取不到校验位
        if (null == checkCode) {
            return null;
        }
        // 将前17位与第18位校验码拼接
        idcard17 += checkCode;
        return idcard17;
    }

    /**
     * 校验省份
     *
     * @param provinceid
     * @return 合法返回TRUE，否则返回FALSE
     */
    private static boolean checkProvinceid(String provinceid) {
        for (String id : cityCode) {
            if (id.equals(provinceid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    private static boolean isDigital(String str) {
        return str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 用户头像 拍照裁剪图片的保存路径
     */
    public static final String SCR_IMAGE_PATH = Environment
            .getExternalStorageDirectory() + "/gutsMusic/screenshots/";

    /***
     * 将时间点转为整数(如：11:00转为22)
     *
     * @param time
     * @return
     */
    public static int timeToNum(String time) {
        String[] hourMinutes = time.split(":");
        int hour = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);
        if (minutes != 0) {
            return hour * 2 + 1;
        }
        return hour * 2;
    }

    /**
     * 根据人的体重计算目标步数
     * 后台返回默认值0.0时，计算默认目标步数
     */
    public static int getTargetStep(float weight) {
        if (weight == 0.0) {
            weight = 60;
        }
        return (int) (weight * 0.052 * 40 * 60);
    }

    /**
     * 根据人的体重计算目标卡路里
     * 后台返回默认值0.0时，计算默认目标卡路里
     */
    public static float getTargetCalorie(float weight) {
        if (weight == 0.0) {
            weight = 60;
        }
        int mTargetStep = (int) (weight * 0.052 * 40 * 60);
        return weight * 0.076f * (mTargetStep * 0.75f / 60);
    }
    public static boolean checkCharContainChinese(char checkChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(checkChar);
        if (CJK_UNIFIED_IDEOGRAPHS == ub || CJK_COMPATIBILITY_IDEOGRAPHS == ub || CJK_COMPATIBILITY_FORMS == ub ||
                CJK_RADICALS_SUPPLEMENT == ub || CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A == ub || CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B == ub) {
            return true;
        }
        return false;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 设置textView结尾...后面显示的文字和颜色
     *
     * @param context    上下文
     * @param textView   textview
     * @param minLines   最少的行数
     * @param originText 原文本
     * @param endText    结尾文字
     * @param endColorID 结尾文字颜色id
     * @param isExpand   当前是否是展开状态
     */
    public static void toggleEllipsize(final Context context,
                                       final TextView textView,
                                       final int minLines,
                                       final String originText,
                                       final String endText,
                                       final int endColorID,
                                       final boolean isExpand) {
        if (TextUtils.isEmpty(originText)) {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override

            public void onGlobalLayout() {
                if (isExpand) {
                    textView.setText(originText);
                } else {
                    int paddingLeft = textView.getPaddingLeft();
                    int paddingRight = textView.getPaddingRight();
                    TextPaint paint = textView.getPaint();
                    float moreText = textView.getTextSize() * endText.length();
                    float availableTextWidth = (textView.getWidth() - paddingLeft - paddingRight) * minLines - moreText;
                    CharSequence ellipsizeStr = TextUtils.ellipsize(originText, paint,
                            availableTextWidth, TextUtils.TruncateAt.END);
                    if (ellipsizeStr.length() < originText.length()) {
                        CharSequence temp = ellipsizeStr + endText;
                        SpannableStringBuilder ssb = new SpannableStringBuilder(temp);
                        ssb.setSpan(new ForegroundColorSpan(context.getResources().getColor(endColorID)),
                                temp.length() - endText.length(), temp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        textView.setText(ssb);
                    } else {
                        textView.setText(originText);
                    }
                }
                if (Build.VERSION.SDK_INT >= 16) {
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }
}
