package com.tisen.titans.service;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by tisen on 2017/9/14.
 */

public class Results {


    /**
     * result : successful
     * page : 0
     * reason : search success
     * pageSize : 0
     * objects : [{"amount":1,"model":"Vanzo_n28atcl_k16","status":true},{"amount":3,"model":"Vanzo_n38acl_j5702","status":false}]
     * size : 2
     */

    private String result;
    private String reason;
    private int page;
    private int pageSize;
    private int size;
    /**
     * amount : 1
     * model : Vanzo_n28atcl_k16
     * status : true
     */

    private List<ObjectsBean> objects;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        private int amount;
        private String model;
        private boolean status;

        public ObjectsBean(int amount, String model, boolean status) {
            this.amount = amount;
            this.model = model;
            this.status = status;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ObjectsBean{" +
                    "amount=" + amount +
                    ", model='" + model + '\'' +
                    ", status=" + status +
                    '}';
        }

    }
}
