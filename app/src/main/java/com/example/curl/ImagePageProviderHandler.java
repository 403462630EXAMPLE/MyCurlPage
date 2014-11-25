package com.example.curl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.example.curl.adapter.handler.BasePageProviderHandler;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rjhy on 14-11-25.
 */
public class ImagePageProviderHandler extends BasePageProviderHandler {
    private Context context;
    private Bitmap loadBitmap;
    private Bitmap errorBitmap;
    public ImagePageProviderHandler(Context context, CurlView curlView) {
        super(curlView);
        this.context = context;
        loadBitmap = canvasBitmap("图片正在加载");
        errorBitmap = canvasBitmap("加载图片出错");
    }

    private Bitmap canvasBitmap(String str) {
        Bitmap bitmap = Bitmap.createBitmap(256,40, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30f);
        textPaint.setColor(Color.GREEN);
        c.translate(0, 0);
        StaticLayout staticLayout = new StaticLayout(str, textPaint, 256, Layout.Alignment.ALIGN_CENTER, 1.3f, 0, false);
        staticLayout.draw(c);
        return bitmap;
    }

    @Override
    public Bitmap onLoading(int index, boolean isBack) {
        return loadBitmap;
    }

    @Override
    public Bitmap onError(int index, boolean isBack) {
        return errorBitmap;
    }

    @Override
    public Bitmap fetchData(int index, boolean isBack, Object data) throws Exception{
        HttpURLConnection conn = null;
        try {
            URL url = new URL((String)data);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000*10);
//            BitmapFactory.de
            return BitmapFactory.decodeStream(conn.getInputStream());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @Override
    public Bitmap getItem(int index, boolean isBack, Object data) {
        return (Bitmap)super.getItem(index, isBack, data);
    }
}
