/*
package com.icbc.sh.sukura.bo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiTransferResponse {

    */
/**
     * instalment : {"trxflag":"","confirm_flag":"","opencfm_flag":""}
     * public : {"info_msg":"E052117-8OQ91010 tableName is RTHHKJL1","recv_timestamp":"20200801163402217","host_retcode":"0","host_retmsg":"主机处理成功","workdate":"2020-08-01","service_serno":"083176350381596270842216034","remark":"","ret_msg":"","worktime":"16:34:03","ret_code":"0","resp_timestamp":"20200801163403425"}
     * channel : {"tritmsp":"2020-07-02-16.34.02.104854","chan_serialno":"940410010184008930066527453"}
     * return_msg : 交易成功
     * return_code : 0
     * draccinf : {"zoneno":"1001","balance":"","phybrno":"2155","actbrno":"2155","accname":"妒程驿竿涛痴臼鸣标周化僵侦柱"}
     * craccinf : {"zoneno":"1001","balance":"","phybrno":"523","actbrno":"2786","accname":"在弧"}
     *//*


    private InstalmentBean instalment;
    @SerializedName("public")
    private PublicBean publicX;
    private ChannelBean channel;
    private String return_msg;
    private String return_code;
    private DraccinfBean draccinf;
    private CraccinfBean craccinf;

    public InstalmentBean getInstalment() {
        return instalment;
    }

    public void setInstalment(InstalmentBean instalment) {
        this.instalment = instalment;
    }

    public PublicBean getPublicX() {
        return publicX;
    }

    public void setPublicX(PublicBean publicX) {
        this.publicX = publicX;
    }

    public ChannelBean getChannel() {
        return channel;
    }

    public void setChannel(ChannelBean channel) {
        this.channel = channel;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
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

    public static class InstalmentBean {
        */
/**
         * trxflag :
         * confirm_flag :
         * opencfm_flag :
         *//*


        private String trxflag;
        private String confirm_flag;
        private String opencfm_flag;

        public String getTrxflag() {
            return trxflag;
        }

        public void setTrxflag(String trxflag) {
            this.trxflag = trxflag;
        }

        public String getConfirm_flag() {
            return confirm_flag;
        }

        public void setConfirm_flag(String confirm_flag) {
            this.confirm_flag = confirm_flag;
        }

        public String getOpencfm_flag() {
            return opencfm_flag;
        }

        public void setOpencfm_flag(String opencfm_flag) {
            this.opencfm_flag = opencfm_flag;
        }
    }

    public static class PublicBean {
        */
/**
         * info_msg : E052117-8OQ91010 tableName is RTHHKJL1
         * recv_timestamp : 20200801163402217
         * host_retcode : 0
         * host_retmsg : 主机处理成功
         * workdate : 2020-08-01
         * service_serno : 083176350381596270842216034
         * remark :
         * ret_msg :
         * worktime : 16:34:03
         * ret_code : 0
         * resp_timestamp : 20200801163403425
         *//*


        private String info_msg;
        private String recv_timestamp;
        private String host_retcode;
        private String host_retmsg;
        private String workdate;
        private String service_serno;
        private String remark;
        private String ret_msg;
        private String worktime;
        private String ret_code;
        private String resp_timestamp;

        public String getInfo_msg() {
            return info_msg;
        }

        public void setInfo_msg(String info_msg) {
            this.info_msg = info_msg;
        }

        public String getRecv_timestamp() {
            return recv_timestamp;
        }

        public void setRecv_timestamp(String recv_timestamp) {
            this.recv_timestamp = recv_timestamp;
        }

        public String getHost_retcode() {
            return host_retcode;
        }

        public void setHost_retcode(String host_retcode) {
            this.host_retcode = host_retcode;
        }

        public String getHost_retmsg() {
            return host_retmsg;
        }

        public void setHost_retmsg(String host_retmsg) {
            this.host_retmsg = host_retmsg;
        }

        public String getWorkdate() {
            return workdate;
        }

        public void setWorkdate(String workdate) {
            this.workdate = workdate;
        }

        public String getService_serno() {
            return service_serno;
        }

        public void setService_serno(String service_serno) {
            this.service_serno = service_serno;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRet_msg() {
            return ret_msg;
        }

        public void setRet_msg(String ret_msg) {
            this.ret_msg = ret_msg;
        }

        public String getWorktime() {
            return worktime;
        }

        public void setWorktime(String worktime) {
            this.worktime = worktime;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getResp_timestamp() {
            return resp_timestamp;
        }

        public void setResp_timestamp(String resp_timestamp) {
            this.resp_timestamp = resp_timestamp;
        }
    }

    public static class ChannelBean {
        */
/**
         * tritmsp : 2020-07-02-16.34.02.104854
         * chan_serialno : 940410010184008930066527453
         *//*


        private String tritmsp;
        private String chan_serialno;

        public String getTritmsp() {
            return tritmsp;
        }

        public void setTritmsp(String tritmsp) {
            this.tritmsp = tritmsp;
        }

        public String getChan_serialno() {
            return chan_serialno;
        }

        public void setChan_serialno(String chan_serialno) {
            this.chan_serialno = chan_serialno;
        }
    }

    public static class DraccinfBean {
        */
/**
         * zoneno : 1001
         * balance :
         * phybrno : 2155
         * actbrno : 2155
         * accname : 妒程驿竿涛痴臼鸣标周化僵侦柱
         *//*


        private String zoneno;
        private String balance;
        private String phybrno;
        private String actbrno;
        private String accname;

        public String getZoneno() {
            return zoneno;
        }

        public void setZoneno(String zoneno) {
            this.zoneno = zoneno;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getPhybrno() {
            return phybrno;
        }

        public void setPhybrno(String phybrno) {
            this.phybrno = phybrno;
        }

        public String getActbrno() {
            return actbrno;
        }

        public void setActbrno(String actbrno) {
            this.actbrno = actbrno;
        }

        public String getAccname() {
            return accname;
        }

        public void setAccname(String accname) {
            this.accname = accname;
        }
    }

    public static class CraccinfBean {
        */
/**
         * zoneno : 1001
         * balance :
         * phybrno : 523
         * actbrno : 2786
         * accname : 在弧
         *//*


        private String zoneno;
        private String balance;
        private String phybrno;
        private String actbrno;
        private String accname;

        public String getZoneno() {
            return zoneno;
        }

        public void setZoneno(String zoneno) {
            this.zoneno = zoneno;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getPhybrno() {
            return phybrno;
        }

        public void setPhybrno(String phybrno) {
            this.phybrno = phybrno;
        }

        public String getActbrno() {
            return actbrno;
        }

        public void setActbrno(String actbrno) {
            this.actbrno = actbrno;
        }

        public String getAccname() {
            return accname;
        }

        public void setAccname(String accname) {
            this.accname = accname;
        }
    }
}
*/
