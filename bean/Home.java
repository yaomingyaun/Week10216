package com.bw.ymy.zy1.bean;

import java.util.List;

public class Home {

    /**
     * result : [{"commodityId":97,"commodityName":"秋季新款韩版女装卫衣秋冬装春秋休闲宽松字母套头连帽加绒长袖卫衣","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/5/1.jpg","price":139,"saleNum":0},{"commodityId":94,"commodityName":"秋冬新款宽松套头绣花纯色连帽加绒加厚卫衣女装外套","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/2/1.jpg","price":129,"saleNum":0},{"commodityId":99,"commodityName":"字母绣花宽松女款卫衣","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/7/1.jpg","price":179,"saleNum":0},{"commodityId":96,"commodityName":"秋季新款韩版女装卫衣秋装学院风街头刺绣基础百搭连帽套头宽松女款卫衣","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/4/1.jpg","price":129,"saleNum":0},{"commodityId":93,"commodityName":"秋季新款韩版女装秋装甜美通勤字母印花宽松长袖女款卫衣女","masterPic":"http://172.17.8.100/images/small/commodity/nz/wy/1/1.jpg","price":94,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 97
         * commodityName : 秋季新款韩版女装卫衣秋冬装春秋休闲宽松字母套头连帽加绒长袖卫衣
         * masterPic : http://172.17.8.100/images/small/commodity/nz/wy/5/1.jpg
         * price : 139
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
