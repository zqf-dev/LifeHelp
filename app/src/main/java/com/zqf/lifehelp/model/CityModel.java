package com.zqf.lifehelp.model;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/10 15:21
 */

public class CityModel {

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * city : [{"city":"北京","district":[{"district":"北京"},{"district":"海淀"},{"district":"朝阳"},{"district":"顺义"},{"district":"怀柔"},{"district":"通州"},{"district":"昌平"},{"district":"延庆"},{"district":"丰台"},{"district":"石景山"},{"district":"大兴"},{"district":"房山"},{"district":"密云"},{"district":"门头沟"},{"district":"平谷"}]}]
         * province : 北京
         */

        private String province;
        private List<CityBean> city;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * city : 北京
             * district : [{"district":"北京"},{"district":"海淀"},{"district":"朝阳"},{"district":"顺义"},{"district":"怀柔"},{"district":"通州"},{"district":"昌平"},{"district":"延庆"},{"district":"丰台"},{"district":"石景山"},{"district":"大兴"},{"district":"房山"},{"district":"密云"},{"district":"门头沟"},{"district":"平谷"}]
             */

            private String city;
            private List<DistrictBean> district;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public List<DistrictBean> getDistrict() {
                return district;
            }

            public void setDistrict(List<DistrictBean> district) {
                this.district = district;
            }

            public static class DistrictBean {
                /**
                 * district : 北京
                 */

                private String district;

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }
            }
        }
    }
}
