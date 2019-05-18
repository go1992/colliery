package com.yw.colliery.sdk.utils;

import com.yw.colliery.sdk.config.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 响应工具类
 * @Date 2019-05-05
 **/
public class ResponseUtils {

    public static<T>  WrapResponse<T> wrapResponse(PageBean<T> pageBean) {

        return new WrapResponse(pageBean.getTotal(), pageBean.getList());
    }


    static class WrapResponse<T> implements Serializable {

        private static final long serialVersionUID = 8191775120233384606L;
        private long total;
        private List<T> rows;

        public WrapResponse(long total, List<T> rows) {
            this.total = total;
            this.rows = rows;
        }

        public static long getSerialVersionUID() {
            return serialVersionUID;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }

        @Override
        public String toString() {
            return "WrapResponse{" +
                    "total=" + total +
                    ", rows=" + rows +
                    '}';
        }
    }
}
