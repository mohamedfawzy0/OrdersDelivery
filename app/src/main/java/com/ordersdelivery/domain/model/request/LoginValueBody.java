package com.ordersdelivery.domain.model.request;


import android.content.Context;

public class LoginValueBody {
    private Value Value;

    public LoginValueBody() {
        this.Value = new Value();
    }

    public LoginValueBody(Value Value) {
        this.Value = Value;
    }

    public Value getValue() {
        return Value;
    }

    public void setValue(Value Value) {
        this.Value = Value;
    }

    public static class Value {

        private String P_LANG_NO;
        private String P_DLVRY_NO;
        private String P_PSSWRD;

        public Value() {
            this.P_LANG_NO = "";
            this.P_DLVRY_NO = "";
            this.P_PSSWRD = "";
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

        public String getP_PSSWRD() {
            return P_PSSWRD;
        }

        public void setP_PSSWRD(String P_PSSWRD) {
            this.P_PSSWRD = P_PSSWRD;
        }

        public Boolean isDataValid(Context context) {

            if (!P_DLVRY_NO.trim().isEmpty() && !P_PSSWRD.trim().isEmpty()
            ) {
                return true;
            } else {
                if (P_DLVRY_NO.trim().isEmpty() || P_PSSWRD.trim().isEmpty()) {
                    return false;
                }
                return false;
            }
        }
    }
}
