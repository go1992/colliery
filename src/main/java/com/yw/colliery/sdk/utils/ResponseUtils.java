package com.yw.colliery.sdk.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 响应工具类
 * @Date 2019-05-05
 **/
public class ResponseUtils {

    public static<T>  WrapResponse<T> wrapResponse(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            WrapResponse<T> response = new WrapResponse(0, null);
            return response;
        }

        int total = list.size();
        List<T> rows = list;
        return new WrapResponse(total, rows);
    }

    public static<T>  WrapResponse<T> wrapResponse(T data) {
        if (data == null) {
            WrapResponse<T> response = new WrapResponse(0, null);
            return response;
        }

        int total = 1;
        List<T> rows = Lists.newArrayList(data);
        return new WrapResponse(total, rows);
    }


    static class WrapResponse<T> implements Serializable {

        private static final long serialVersionUID = 8191775120233384606L;
        private int total;
        private List<T> rows;

        public WrapResponse(int total, List<T> rows) {
            this.total = total;
            this.rows = rows;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }
    }
}
