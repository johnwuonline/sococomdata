package com.soco.msg;

import org.json.JSONException;
import org.json.JSONObject;

import com.soco.log.Log;

public class MsgData implements MsgObject{

    /**
     * 
     */
    private static final long serialVersionUID = 3650805157577370546L;

    private MsgHeader _header;
    private MsgBody _body;
    
    public MsgData(){
    }
    
    public MsgData(String json){
        this.parseJsonStr(json);
    }

    public MsgHeader getHeader() {
        return this._header;
    }

    public MsgData setHeader(MsgHeader _header) {
        this._header = _header;
        return this;
    }

    public MsgBody getBody() {
        return this._body;
    }

    public MsgData setBody(MsgBody _body) {
        this._body = _body;
        return this;
    }
    
    public boolean save(){
        try{
            
        }catch(Exception e){
            Log.log(e.getMessage());
        }
        return true;
    }

    @Override
    public String toJsonString() {
        JSONObject json = new JSONObject();
        String jsonStr = null;
        try {
            json.put("HEADER", this.getHeader().toJsonString());
            json.put("BODY", this.getBody().toJsonString());
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
            if(ret && !json.isNull("HEADER")){
                if(this.getHeader() == null){
                    this._header = new MsgHeader();
                }
                ret = this.getHeader().parseJsonStr(json.getString("HEADER"));
            } else {
                ret = false;
            }
            
            if(ret && !json.isNull("BODY")){
                if(this.getHeader().getBodyType() == MsgHeader.BODY_TYPE_STRING 
                        && !MsgBodyString.class.isInstance(this.getBody())){
                    this._body = new MsgBodyString();
                } else if(this.getHeader().getBodyType() == MsgHeader.BODY_TYPE_IMAGE 
                        && !MsgBodyString.class.isInstance(this.getBody())){
                    this._body = new MsgBodyImage();
                } else {
                    this._body = new MsgBodyString();
                }
                
                ret = this.getBody().parseJsonStr(json.getString("BODY"));
            } else {
                ret = false;
            }
            
        }catch(Exception e){
            Log.log(e.getMessage());
        }
        return ret;
    }
}
