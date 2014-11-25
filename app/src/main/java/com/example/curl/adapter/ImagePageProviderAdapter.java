package com.example.curl.adapter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.curl.CurlRenderer;

import java.util.ArrayList;

/**
 * Created by fc on 14-11-21.
 */
public class ImagePageProviderAdapter extends BasePageProviderAdapter {
    private boolean isRotation = true;
    private ArrayList<Bitmap> datas;
    private ArrayList<Bitmap> backDatas;

    public ImagePageProviderAdapter(int margin, int padding, int border, int borderColor, int background, ArrayList<Bitmap> datas, ArrayList<Bitmap> backDatas) {
        super(margin, padding, border, borderColor, background);
        this.datas = datas;
        this.backDatas = backDatas;
    }

    public void add(Bitmap data) {
        datas.add(data);
    }

    public void addBackData(Bitmap backData) {
        backDatas.add(backData);
    }

    public void setRotation(boolean isRotation) {
        this.isRotation = isRotation;
    }

    public void setDatas(ArrayList<Bitmap> datas) {
        this.datas = datas;
    }

    public void setBackDatas(ArrayList<Bitmap> backDatas) {
        this.backDatas = backDatas;
    }

    public ImagePageProviderAdapter(ArrayList<Bitmap> data) {
        this.datas = data;
    }

    public ImagePageProviderAdapter() {

    }

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    @Override
    public boolean isEnaleDrawed(int index, boolean isBack) {
//        if (isBack) {
//            return backDatas != null && backDatas.size() > index && backDatas.get(index) != null;
//        } else {
//            return datas != null && datas.size() > index && datas.get(index) != null;
//        }
        return super.isEnaleDrawed(index, isBack);
    }

    public Bitmap getItem(int index, boolean isBack) {
        if (isBack) {
            return backDatas.get(index);
        } else {
            return datas.get(index);
        }

    }

    public void drawBitmap(Canvas c, Rect r, int index, boolean isBack) {
        Log.i("CURLVIEW", "drawBitmap");
        Bitmap d = getItem(index, isBack);
        if (d == null) {
            return ;
        }

        int imageWidth = r.width() - (border * 2);
        int imageHeight = imageWidth * d.getHeight()/ d.getWidth();
        if (imageHeight > r.height() - (border * 2)) {
            imageHeight = r.height() - (border * 2);
            imageWidth = imageHeight * d.getWidth()/ d.getHeight();
        }

        r.left += (r.width() - imageWidth) / 2;
        r.right = r.left + imageWidth;
        r.top += (r.height() - imageHeight) / 2;
        r.bottom = r.top + imageHeight;

        if (isShouldRotation(isBack)) {
            updateCanvasLRSymmetry(c);
        }

        c.drawBitmap(d, null, r, new Paint());
    }

    @Override
    public int getPageCount() {
        return datas == null ? 0 : datas.size();
    }
}
