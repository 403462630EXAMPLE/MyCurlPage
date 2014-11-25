package com.example.curl.adapter.handler;

import android.os.Handler;
import android.os.Looper;

import com.example.curl.CurlView;

import java.util.ArrayList;

/**
 * Created by rjhy on 14-11-25.
 */
public abstract class BasePageProviderHandler implements PageProviderHandler {
    protected ProviderData providerData = new ProviderData();
    protected CurlView curlView;

    public BasePageProviderHandler(CurlView curlView) {
        this.curlView = curlView;
    }

    @Override
    public void loadData(final int index, final boolean isBack, final Object oldData) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Object data = null;
                boolean isError = false;
                try {
                    data = fetchData(index, isBack, oldData);
                } catch (Exception e) {
                    e.printStackTrace();
                    data = onError(index, isBack);
                    isError = true;
                }

                int currentIndex = curlView.getCurrentIndex();
                if (index >= (currentIndex - 2) && index <= (currentIndex + 1)) {
                    providerData.put(index, isBack, data, isError);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (!curlView.isCurling) {
                                curlView.requestRenderPage(index);
                            } else {
                                curlView.addRequestRenderPage(index);
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    private void saveData() {

    }

    @Override
    public Object getItem(int index, boolean isBack, Object data) {
        Object value = providerData.get(index, isBack);
        if (value == null) {
            loadData(index, isBack, data);
            return onLoading(index, isBack);
        }
        return value;
    }


    class ProviderData{
        private ArrayList<DataEntity> tempData = new ArrayList<DataEntity>();

        public ProviderData() {
        }

        public void put(Integer key, boolean isBack, Object value, boolean isError) {
            DataEntity entity = new DataEntity(key, isBack, value, isError);
            tempData.add(entity);
            if (tempData.size() > 4) {
                tempData.remove(0);
            }
        }

        public Object get(int index, boolean isBack) {
            Object value = null;
            if (tempData != null && tempData.size() > 0) {
                for (DataEntity entity : tempData) {
                    if (entity.getKey() == index && isBack == entity.isBack()) {
                        value = entity.getValue();
                        if (entity.isError()) {
                            tempData.remove(entity);
                        }
                    }
                }
            }
            return value;
        }

        private class DataEntity{
            Integer key;
            Object value;

            public boolean isBack() {
                return isBack;
            }

            public void setBack(boolean isBack) {
                this.isBack = isBack;
            }

            boolean isBack;

            public boolean isError() {
                return isError;
            }

            public void setError(boolean isError) {
                this.isError = isError;
            }

            boolean isError;

            public Integer getKey() {
                return key;
            }

            public void setKey(Integer key) {
                this.key = key;
            }

            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
                this.value = value;
            }

            private DataEntity(Integer key, boolean isBack, Object value, boolean isError) {
                this.key = key;
                this.isBack = isBack;
                this.value = value;
                this.isError = isError;
            }
        }
    }
}
