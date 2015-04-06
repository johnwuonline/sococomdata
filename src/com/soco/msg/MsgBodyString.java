package com.soco.msg;

import org.json.JSONException;
import org.json.JSONObject;

import com.soco.log.Log;

public class MsgBodyString implements MsgBody{

    /**
     * 
     */
    private static final long serialVersionUID = 8282289833736192654L;

    private String _msg_str;
    private String _body_type;
    
    public MsgBodyString(){
        this.setMsgStr("");
        this._body_type = "STRING";
    }

    public String getMsgStr() {
        return this._msg_str;
    }

    public MsgBodyString setMsgStr(String msg_str) {
        this._msg_str = msg_str;
        return this;
    }

    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toJsonString() {
        JSONObject json = new JSONObject();
        String jsonStr = null;
        try {
            json.put("BODYID", this.getBodyType());
            json.put("MESSAGE", this.getMsgStr());
            jsonStr = json.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return jsonStr;
    }

    @Override
    public boolean parseJsonStr(String jsonStr) {
        boolean ret = false;
        try{
            JSONObject json = new JSONObject(jsonStr);
            ret = true;
            if(ret && !json.isNull("BODYID")){
                this._body_type = json.getString("BODYID");
            } else {
                ret = false;
            }
            
            if(ret && !json.isNull("MESSAGE")){
                this.setMsgStr(json.getString("MESSAGE"));
            } else {
                ret = false;
            }
            
        }catch(Exception e){
            Log.log(e.getMessage());
        }
        return ret;
    }

    @Override
    public String getBodyType() {
        // TODO Auto-generated method stub
        return this._body_type;
    }
}
