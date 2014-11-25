package com.example.curl;

import com.example.curl.adapter.TextPageProviderAdapter;
import com.example.curl.adapter.handler.BasePageProviderHandler;

/**
 * Created by rjhy on 14-11-24.
 */
public class AsynTextPageProviderAdapter extends TextPageProviderAdapter {

    public void setBasePageProviderHandler(BasePageProviderHandler basePageProviderHandler) {
        this.basePageProviderHandler = basePageProviderHandler;
    }

    private BasePageProviderHandler basePageProviderHandler;

    public AsynTextPageProviderAdapter() {

    }

    @Override
    public String getItem(int index, boolean isBack) {
//        Object object = basePageProviderHandler.getItem(index, isBack);
//        if (object != null) {
//            return String.valueOf(object);
//        }
        return super.getItem(index, isBack);
    }

    @Override
    public int getPageCount() {
        return 10;
    }

    @Override
    public boolean isEnaleDrawed(int index, boolean isBack) {
        return true;
    }

}
