package com.example.myweb.result;

public class ResultVO<T> extends ResultStatus {

    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVO(T data){
        this.code=SUCCESS_CODE;
        this.msg=SUCCESS_MSG;
        this.data=data;
    }
    public ResultVO(Integer code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public ResultVO(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public ResultVO(){

    }

//    成功返回
    public static ResultVO success(){
        return new ResultVO(SUCCESS_CODE,SUCCESS_MSG);
    }

    public static ResultVO success(int code,String msg){
        return new ResultVO(code,msg);
    }

    public static ResultVO success(Object data){
        return new ResultVO(SUCCESS_CODE,SUCCESS_MSG,data);
    }
//    异常返回
    public static ResultVO error(String msg){
        return new ResultVO(ERROR_CODE,msg);
    }
    public static ResultVO error(int code,String msg){
        return new ResultVO(code,msg);
    }



}
