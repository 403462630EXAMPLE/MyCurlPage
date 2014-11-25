package com.example.curl;

import com.example.curl.adapter.handler.BasePageProviderHandler;

/**
 * Created by rjhy on 14-11-25.
 */
public class TextPageProviderHandler extends BasePageProviderHandler {

    public TextPageProviderHandler(CurlView curlView) {
        super(curlView);
    }

    @Override
    public Object onLoading(int index, boolean isBack) {
        return "数据正在加载中，请稍等......";
    }

    @Override
    public Object onError(int index, boolean isBack) {
        return "数据加载出错";
    }

    @Override
    public Object fetchData(int index, boolean isBack, Object data) throws Exception {
        return null;
    }
}
