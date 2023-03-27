package com.ordersdelivery.domain.model.response;

import java.util.ArrayList;

public class OrdersModel {

    private Result Result;

    public Result getResult() {
        return Result;
    }

    private Data Data;

    public Data getData() {
        return Data;
    }

    public class Data {
        private ArrayList<DeliveryBill> DeliveryBills;

        public ArrayList<DeliveryBill> getDeliveryBills() {
            return DeliveryBills;
        }

        public class DeliveryBill {
            private String BILL_AMT;
            private String BILL_DATE;
            private String BILL_NO;
            private String BILL_SRL;
            private String BILL_TIME;
            private String BILL_TYPE;
            private String CSTMR_ADDRSS;
            private String CSTMR_APRTMNT_NO;
            private String CSTMR_BUILD_NO;
            private String CSTMR_FLOOR_NO;
            private String CSTMR_NM;
            private String DLVRY_AMT;
            private String DLVRY_STATUS_FLG;
            private String LATITUDE;
            private String LONGITUDE;
            private String MOBILE_NO;
            private String RGN_NM;
            private String TAX_AMT;

            public String getBILL_AMT() {
                return BILL_AMT;
            }

            public DeliveryBill() {
            }

            public void setBILL_AMT(String BILL_AMT) {
                this.BILL_AMT = BILL_AMT;
            }

            public String getBILL_DATE() {
                return BILL_DATE;
            }

            public void setBILL_DATE(String BILL_DATE) {
                this.BILL_DATE = BILL_DATE;
            }

            public String getBILL_NO() {
                return BILL_NO;
            }

            public void setBILL_NO(String BILL_NO) {
                this.BILL_NO = BILL_NO;
            }

            public String getBILL_SRL() {
                return BILL_SRL;
            }

            public void setBILL_SRL(String BILL_SRL) {
                this.BILL_SRL = BILL_SRL;
            }

            public String getBILL_TIME() {
                return BILL_TIME;
            }

            public void setBILL_TIME(String BILL_TIME) {
                this.BILL_TIME = BILL_TIME;
            }

            public String getBILL_TYPE() {
                return BILL_TYPE;
            }

            public void setBILL_TYPE(String BILL_TYPE) {
                this.BILL_TYPE = BILL_TYPE;
            }

            public String getCSTMR_ADDRSS() {
                return CSTMR_ADDRSS;
            }

            public void setCSTMR_ADDRSS(String CSTMR_ADDRSS) {
                this.CSTMR_ADDRSS = CSTMR_ADDRSS;
            }

            public String getCSTMR_APRTMNT_NO() {
                return CSTMR_APRTMNT_NO;
            }

            public void setCSTMR_APRTMNT_NO(String CSTMR_APRTMNT_NO) {
                this.CSTMR_APRTMNT_NO = CSTMR_APRTMNT_NO;
            }

            public String getCSTMR_BUILD_NO() {
                return CSTMR_BUILD_NO;
            }

            public void setCSTMR_BUILD_NO(String CSTMR_BUILD_NO) {
                this.CSTMR_BUILD_NO = CSTMR_BUILD_NO;
            }

            public String getCSTMR_FLOOR_NO() {
                return CSTMR_FLOOR_NO;
            }

            public void setCSTMR_FLOOR_NO(String CSTMR_FLOOR_NO) {
                this.CSTMR_FLOOR_NO = CSTMR_FLOOR_NO;
            }

            public String getCSTMR_NM() {
                return CSTMR_NM;
            }

            public void setCSTMR_NM(String CSTMR_NM) {
                this.CSTMR_NM = CSTMR_NM;
            }

            public String getDLVRY_AMT() {
                return DLVRY_AMT;
            }

            public void setDLVRY_AMT(String DLVRY_AMT) {
                this.DLVRY_AMT = DLVRY_AMT;
            }

            public String getDLVRY_STATUS_FLG() {
                return DLVRY_STATUS_FLG;
            }

            public void setDLVRY_STATUS_FLG(String DLVRY_STATUS_FLG) {
                this.DLVRY_STATUS_FLG = DLVRY_STATUS_FLG;
            }

            public String getLATITUDE() {
                return LATITUDE;
            }

            public void setLATITUDE(String LATITUDE) {
                this.LATITUDE = LATITUDE;
            }

            public String getLONGITUDE() {
                return LONGITUDE;
            }

            public void setLONGITUDE(String LONGITUDE) {
                this.LONGITUDE = LONGITUDE;
            }

            public String getMOBILE_NO() {
                return MOBILE_NO;
            }

            public void setMOBILE_NO(String MOBILE_NO) {
                this.MOBILE_NO = MOBILE_NO;
            }

            public String getRGN_NM() {
                return RGN_NM;
            }

            public void setRGN_NM(String RGN_NM) {
                this.RGN_NM = RGN_NM;
            }

            public String getTAX_AMT() {
                return TAX_AMT;
            }

            public void setTAX_AMT(String TAX_AMT) {
                this.TAX_AMT = TAX_AMT;
            }
        }
    }
}
