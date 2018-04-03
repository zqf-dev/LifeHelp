package com.zqf.lifehelp.model;

/**
 * Created by zqf on 2018/4/3.
 * ID 的bean
 */

public class QueryIDBean {


    /**
     * msg : success
     * result : {"area":"贵州省","birthday":"1990年02月02日","sex":"男"}
     * retCode : 200
     */

    private String msg;
    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        /**
         * area : 贵州省
         * birthday : 1992年09月02日
         * sex : 男
         */

        private String area;
        private String birthday;
        private String sex;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
