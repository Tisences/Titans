package com.tisen.titans.api;

import java.util.List;

/**
 * Created by tisen on 2016/12/16 14:53.
 * Email tisences@qq.com
 */
public class SmileResult {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"给同事介绍了一个对象。  第二天她一来公司，愤怒的对我大吼道：\u201c什么朋友，你给我介绍的是神马玩意儿啊！\u201d   我一脸无辜的追问：\u201c咋了，不合心意吗？\u201d   她气愤的大吼道：\u201c什么鸟人，我问他有房吗？你知道他说什么？！\u201d   \u201c他...他说什么！\u201d   \u201c他说已经开好了，走吧！\u201d","hashId":"dd63dc9f77cadc9f980e0ecf443bee5c","unixtime":1482226430,"updatetime":"2016-12-20 17:33:50"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 给同事介绍了一个对象。  第二天她一来公司，愤怒的对我大吼道：“什么朋友，你给我介绍的是神马玩意儿啊！”   我一脸无辜的追问：“咋了，不合心意吗？”   她气愤的大吼道：“什么鸟人，我问他有房吗？你知道他说什么？！”   “他...他说什么！”   “他说已经开好了，走吧！”
             * hashId : dd63dc9f77cadc9f980e0ecf443bee5c
             * unixtime : 1482226430
             * updatetime : 2016-12-20 17:33:50
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
