package com.example.curl.adapter.handler;

import java.util.ArrayList;

/**
 * Created by rjhy on 14-11-25.
 */
public interface PageProviderHandler {

    public Object onLoading(int index, boolean isBack);

    public Object onError(int index, boolean isBack);

    public Object fetchData(int index, boolean isBack, Object data) throws Exception;

    public void loadData(int index, boolean isBack, Object data);

    public Object getItem(int index, boolean isBack, Object data);

}
