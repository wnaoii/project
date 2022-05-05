/*
package com.icbc.sh.sukura.bo;

import com.google.gson.annotations.SerializedName;

public class ApiTransferRequest {

    */
/**
     * public : {"servface":"12","zoneno":"01001","project_id":"","tellerno":"00121","product_id":"","brno":"1907"}
     * commcitr : {"summary":"taojunyu转账测试","trxcode":"81117","drmdcardno":"6212881001000210168","amount":"1","agentno":"","busi_info":"","iftrxsernb":"20100775","req_time":"095639","termid":"tty01","cashexf":"0","req_date":"20201001","agenttyp":"","revtranf":"0","crmdcardno":"10012155093003764","currtype":"1"}
     * ctrlcomm : {"ckpin_f":"0"}
     * channel : {"oapp":"F-RFBP","chan_type":"502","chan_serialno":"94041001016400895266527453"}
     * spexcomm : {"vhquota":"","statcode":"","settmode":"","vouhtype":"","vouhno":"","vouhdate":"1900-01-01","vouhpwd":""}
     * infocomm : {"authcode":"","authpwd":"","vagen_level":"","authamt":"","authtlno":"","dutyno":"","cardno":""}
     * fee52019 : {"manuamt_f":"1"}
     * draccinf : {"obflag":"0","chknamf":"0","accname":"在弧"}
     * craccinf : {}
     *//*


    @SerializedName("public")
    private PublicBean publicX;
    private CommcitrBean commcitr;
    private CtrlcommBean ctrlcomm;
    private ChannelBean channel;
    private SpexcommBean spexcomm;
    private InfocommBean infocomm;
    private Fee52019Bean fee52019;
    private DraccinfBean draccinf;
    private CraccinfBean craccinf;

    public PublicBean getPublicX() {
        return publicX;
    }

    public void setPublicX(PublicBean publicX) {
        this.publicX = publicX;
    }

    public CommcitrBean getCommcitr() {
        return commcitr;
    }

    public void setCommcitr(CommcitrBean commcitr) {
        this.commcitr = commcitr;
    }

    public CtrlcommBean getCtrlcomm() {
        return ctrlcomm;
    }

    public void setCtrlcomm(CtrlcommBean ctrlcomm) {
        this.ctrlcomm = ctrlcomm;
    }

    public ChannelBean getChannel() {
        return channel;
    }

    public void setChannel(ChannelBean channel) {
        this.channel = channel;
    }

    public SpexcommBean getSpexcomm() {
        return spexcomm;
    }

    public void setSpexcomm(SpexcommBean spexcomm) {
        this.spexcomm = spexcomm;
    }

    public InfocommBean getInfocomm() {
        return infocomm;
    }

    public void setInfocomm(InfocommBean infocomm) {
        this.infocomm = infocomm;
    }

    public Fee52019Bean getFee52019() {
        return fee52019;
    }

    public void setFee52019(Fee52019Bean fee52019) {
        this.fee52019 = fee52019;
    }

    public DraccinfBean getDraccinf() {
        return draccinf;
    }

    public void setDraccinf(DraccinfBean draccinf) {
        this.draccinf = draccinf;
    }

    public CraccinfBean getCraccinf() {
        return craccinf;
    }

    public void setCraccinf(CraccinfBean craccinf) {
        this.craccinf = craccinf;
    }

    public static class PublicBean {
        */
/**
         * servface : 12
         * zoneno : 01001
         * project_id :
         * tellerno : 00121
         * product_id :
         * brno : 1907
         *//*


        private String servface;
        private String zoneno;
        private String project_id;
        private String tellerno;
        private String product_id;
        private String brno;

        public String getServface() {
            return servface;
        }

        public void setServface(String servface) {
            this.servface = servface;
        }

        public String getZoneno() {
            return zoneno;
        }

        public void setZoneno(String zoneno) {
            this.zoneno = zoneno;
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = project_id;
        }

        public String getTellerno() {
            return tellerno;
        }

        public void setTellerno(String tellerno) {
            this.tellerno = tellerno;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getBrno() {
            return brno;
        }

        public void setBrno(String brno) {
            this.brno = brno;
        }
    }

    public static class CommcitrBean {
        */
/**
         * summary : taojunyu转账测试
         * trxcode : 81117
         * drmdcardno : 6212881001000210168
         * amount : 1
         * agentno :
         * busi_info :
         * iftrxsernb : 20100775
         * req_time : 095639
         * termid : tty01
         * cashexf : 0
         * req_date : 20201001
         * agenttyp :
         * revtranf : 0
         * crmdcardno : 10012155093003764
         * currtype : 1
         *//*


        private String summary;
        private String trxcode;
        private String drmdcardno;
        private String amount;
        private String agentno;
        private String busi_info;
        private String iftrxsernb;
        private String req_time;
        private String termid;
        private String cashexf;
        private String req_date;
        private String agenttyp;
        private String revtranf;
        private String crmdcardno;
        private String currtype;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTrxcode() {
            return trxcode;
        }

        public void setTrxcode(String trxcode) {
            this.trxcode = trxcode;
        }

        public String getDrmdcardno() {
            return drmdcardno;
        }

        public void setDrmdcardno(String drmdcardno) {
            this.drmdcardno = drmdcardno;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAgentno() {
            return agentno;
        }

        public void setAgentno(String agentno) {
            this.agentno = agentno;
        }

        public String getBusi_info() {
            return busi_info;
        }

        public void setBusi_info(String busi_info) {
            this.busi_info = busi_info;
        }

        public String getIftrxsernb() {
            return iftrxsernb;
        }

        public void setIftrxsernb(String iftrxsernb) {
            this.iftrxsernb = iftrxsernb;
        }

        public String getReq_time() {
            return req_time;
        }

        public void setReq_time(String req_time) {
            this.req_time = req_time;
        }

        public String getTermid() {
            return termid;
        }

        public void setTermid(String termid) {
            this.termid = termid;
        }

        public String getCashexf() {
            return cashexf;
        }

        public void setCashexf(String cashexf) {
            this.cashexf = cashexf;
        }

        public String getReq_date() {
            return req_date;
        }

        public void setReq_date(String req_date) {
            this.req_date = req_date;
        }

        public String getAgenttyp() {
            return agenttyp;
        }

        public void setAgenttyp(String agenttyp) {
            this.agenttyp = agenttyp;
        }

        public String getRevtranf() {
            return revtranf;
        }

        public void setRevtranf(String revtranf) {
            this.revtranf = revtranf;
        }

        public String getCrmdcardno() {
            return crmdcardno;
        }

        public void setCrmdcardno(String crmdcardno) {
            this.crmdcardno = crmdcardno;
        }

        public String getCurrtype() {
            return currtype;
        }

        public void setCurrtype(String currtype) {
            this.currtype = currtype;
        }
    }

    public static class CtrlcommBean {
        */
/**
         * ckpin_f : 0
         *//*


        private String ckpin_f;

        public String getCkpin_f() {
            return ckpin_f;
        }

        public void setCkpin_f(String ckpin_f) {
            this.ckpin_f = ckpin_f;
        }
    }

    public static class ChannelBean {
        */
/**
         * oapp : F-RFBP
         * chan_type : 502
         * chan_serialno : 94041001016400895266527453
         *//*


        private String oapp;
        private String chan_type;
        private String chan_serialno;

        public String getOapp() {
            return oapp;
        }

        public void setOapp(String oapp) {
            this.oapp = oapp;
        }

        public String getChan_type() {
            return chan_type;
        }

        public void setChan_type(String chan_type) {
            this.chan_type = chan_type;
        }

        public String getChan_serialno() {
            return chan_serialno;
        }

        public void setChan_serialno(String chan_serialno) {
            this.chan_serialno = chan_serialno;
        }
    }

    public static class SpexcommBean {
        */
/**
         * vhquota :
         * statcode :
         * settmode :
         * vouhtype :
         * vouhno :
         * vouhdate : 1900-01-01
         * vouhpwd :
         *//*


        private String vhquota;
        private String statcode;
        private String settmode;
        private String vouhtype;
        private String vouhno;
        private String vouhdate;
        private String vouhpwd;

        public String getVhquota() {
            return vhquota;
        }

        public void setVhquota(String vhquota) {
            this.vhquota = vhquota;
        }

        public String getStatcode() {
            return statcode;
        }

        public void setStatcode(String statcode) {
            this.statcode = statcode;
        }

        public String getSettmode() {
            return settmode;
        }

        public void setSettmode(String settmode) {
            this.settmode = settmode;
        }

        public String getVouhtype() {
            return vouhtype;
        }

        public void setVouhtype(String vouhtype) {
            this.vouhtype = vouhtype;
        }

        public String getVouhno() {
            return vouhno;
        }

        public void setVouhno(String vouhno) {
            this.vouhno = vouhno;
        }

        public String getVouhdate() {
            return vouhdate;
        }

        public void setVouhdate(String vouhdate) {
            this.vouhdate = vouhdate;
        }

        public String getVouhpwd() {
            return vouhpwd;
        }

        public void setVouhpwd(String vouhpwd) {
            this.vouhpwd = vouhpwd;
        }
    }

    public static class InfocommBean {
        */
/**
         * authcode :
         * authpwd :
         * vagen_level :
         * authamt :
         * authtlno :
         * dutyno :
         * cardno :
         *//*


        private String authcode;
        private String authpwd;
        private String vagen_level;
        private String authamt;
        private String authtlno;
        private String dutyno;
        private String cardno;

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }

        public String getAuthpwd() {
            return authpwd;
        }

        public void setAuthpwd(String authpwd) {
            this.authpwd = authpwd;
        }

        public String getVagen_level() {
            return vagen_level;
        }

        public void setVagen_level(String vagen_level) {
            this.vagen_level = vagen_level;
        }

        public String getAuthamt() {
            return authamt;
        }

        public void setAuthamt(String authamt) {
            this.authamt = authamt;
        }

        public String getAuthtlno() {
            return authtlno;
        }

        public void setAuthtlno(String authtlno) {
            this.authtlno = authtlno;
        }

        public String getDutyno() {
            return dutyno;
        }

        public void setDutyno(String dutyno) {
            this.dutyno = dutyno;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }
    }

    public static class Fee52019Bean {
        */
/**
         * manuamt_f : 1
         *//*


        private String manuamt_f;

        public String getManuamt_f() {
            return manuamt_f;
        }

        public void setManuamt_f(String manuamt_f) {
            this.manuamt_f = manuamt_f;
        }
    }

    public static class DraccinfBean {
        */
/**
         * obflag : 0
         * chknamf : 0
         * accname : 在弧
         *//*


        private String obflag;
        private String chknamf;
        private String accname;

        public String getObflag() {
            return obflag;
        }

        public void setObflag(String obflag) {
            this.obflag = obflag;
        }

        public String getChknamf() {
            return chknamf;
        }

        public void setChknamf(String chknamf) {
            this.chknamf = chknamf;
        }

        public String getAccname() {
            return accname;
        }

        public void setAccname(String accname) {
            this.accname = accname;
        }
    }

    public static class CraccinfBean {
    }
}

*/
