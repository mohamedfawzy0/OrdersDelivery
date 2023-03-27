package com.ordersdelivery.domain.model.request;


public class OrdersValueBody {
    private Value Value;

    public OrdersValueBody() {
        this.Value = new Value();
    }

    public OrdersValueBody(Value Value) {
        this.Value = Value;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value Value) {
        this.Value = Value;
    }

    public static class Value {

        private String P_DLVRY_NO;
        private String P_LANG_NO;
        private String P_BILL_SRL;
        private String P_PRCSSD_FLG;

        public Value() {
            this.P_DLVRY_NO = "";
            this.P_LANG_NO = "";
            this.P_BILL_SRL = "";
            this.P_PRCSSD_FLG = "";
        }

        public Value(String P_DLVRY_NO, String P_LANG_NO, String P_BILL_SRL, String P_PRCSSD_FLG) {
            this.P_DLVRY_NO = P_DLVRY_NO;
            this.P_LANG_NO = P_LANG_NO;
            this.P_BILL_SRL = P_BILL_SRL;
            this.P_PRCSSD_FLG = P_PRCSSD_FLG;
        }

        public String getP_LANG_NO() {
            return P_LANG_NO;
        }

        public void setP_LANG_NO(String P_LANG_NO) {
            this.P_LANG_NO = P_LANG_NO;
        }

        public String getP_DLVRY_NO() {
            return P_DLVRY_NO;
        }

        public void setP_DLVRY_NO(String P_DLVRY_NO) {
            this.P_DLVRY_NO = P_DLVRY_NO;
        }

        public String getP_PRCSSD_FLG() {
            return P_PRCSSD_FLG;
        }

        public void setP_PRCSSD_FLG(String P_PSSWRD) {
            this.P_PRCSSD_FLG = P_PSSWRD;
        }

        public String getP_BILL_SRL() {
            return P_BILL_SRL;
        }

        public void setP_BILL_SRL(String p_BILL_SRL) {
            P_BILL_SRL = p_BILL_SRL;
        }
    }
}
