package com.soco.msg;

import com.soco.log.Log;
import com.soco.utility.Utility;
import org.json.JSONObject;

public class MsgHeader implements MsgObject{

    /**
     * message json format
     * {    TO:{SENDER_TYPE: type, SENDER_ID: sender},
     *      FROM:{RECEIVER_TYPE: type, RECEIVER_ID: receiver},
     *      DATETIME: time,
     *      BODY_TYPE: type
     * }
     * reference: RFC 3862
     */
    
    public static final int USER_TYPE_NULL = 0;
    public static final int USER_TYPE_USER_NAME = 1; 
    public static final int USER_TYPE_USER_EMAIL = 2; 
    public static final int USER_TYPE_ACTIVITY_ID = 3;
    public static final int USER_TYPE_GROUP_ID = 4;
    
    public static final int BODY_TYPE_NULL = 0;
    public static final int BODY_TYPE_STRING = 1;
    public static final int BODY_TYPE_IMAGE = 2;
    public static final int BODY_TYPE_VOICE = 3;
    public static final int BODY_TYPE_VIDEO = 4;
    
    private static final long serialVersionUID = 8050963148671756203L;

    /* type: 1: username, 1:user email, 3: activity id */
    private int _sender_type;
    private String _sender_id;
    
    /* type: 1: username, 1:user email, 3: activity id */
    private int _receiver_type;
    /* id: parse by type */
    private String _receiver_id;
    
    private String _send_time;
    
    /* body type: 1:string message, 2:image, 3:voice, 4:video */
    private int _body_type;
    
    public MsgHeader(){
        this._sender_type = 0;
        this._sender_id = "";
        this._receiver_type = 0;
        this._receiver_id = "";
        this._body_type = 0;
        this._send_time = Utility.getCurrentDateToString();
    }
    
    public int getSenderType() {
        return this._sender_type;
    }
    
    public MsgHeader setSenderType(int sender_type) {
        this._sender_type = sender_type;
        return this;
    }
    
    public String getSenderID() {
        return this._sender_id;
    }
    
    public MsgHeader setSenderID(String sender_id) {
        this._sender_id = sender_id;
        return this;
    }
    
    
    public int getReceiverType() {
        return this._receiver_type;
    }
    
    public MsgHeader setReceiverType(int receiver_type) {
        this._receiver_type = receiver_type;
        return this;
    }
    
    public String getReceiverID() {
        return this._receiver_id;
    }
    
    public MsgHeader setReceiverID(String _receiver_id) {
        this._receiver_id = _receiver_id;
        return this;
    }

    public int getBodyType() {
        return this._body_type;
    }

    public MsgHeader setBodyType(int body_type) {
        this._body_type = body_type;
        return this;
    }

    public String getSendTime() {
        return _send_time;
    }

    public MsgHeader setSendTime(String send_time) {
        this._send_time = send_time;
        return this;
    }
    
    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public String toJsonString() {
        String jsonStr = null;
        JSONObject json = new JSONObject();
        try{
            JSONObject subJsonTo = new JSONObject();
            JSONObject subJsonFrom = new JSONObject();
            subJsonTo.put("SENDERTYPE", this.getSenderType());
            subJsonTo.put("SENDERID", this.getSenderID());
            subJsonFrom.put("RECEIVERTYPE", this.getReceiverType());
            subJsonFrom.put("RECEIVERID", this.getReceiverID());
            json.put("TO", subJsonTo.toString());
            json.put("FROM", subJsonFrom.toString());
            json.put("DATETIME", this.getSendTime());
            json.put("BODYTYPE", this.getBodyType());
            
            jsonStr = json.toString();
        }catch(Exception e){
            Log.log(e.getMessage());
        }
        return jsonStr;
    }

    @Override
    public boolean parseJsonStr(String jsonStr) {
        boolean ret = false;
        try{
            JSONObject json = new JSONObject(jsonStr);
            ret = true;
            if(ret && !json.isNull("TO")){
                JSONObject subJson = new JSONObject(json.getString("TO"));
                if(ret && !subJson.isNull("SENDERTYPE")){
                    this.setSenderType(subJson.getInt("SENDERTYPE"));
                } else {
                    ret = false;
                }
                if(ret && !subJson.isNull("SENDERID")){
                    this.setSenderID(subJson.getString("SENDERID"));
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }
            
            if(ret && !json.isNull("FROM")){
                JSONObject subJson = new JSONObject(json.getString("FROM"));
                if(ret && !subJson.isNull("RECEIVERTYPE")){
                    this.setReceiverType(subJson.getInt("RECEIVERTYPE"));
                } else {
                    ret = false;
                }
                if(ret && !subJson.isNull("RECEIVERID")){
                    this.setReceiverID(subJson.getString("RECEIVERID"));
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }
            
            if(ret && !json.isNull("DATETIME")){
                this.setSendTime(json.getString("DATETIME"));
            } else {
                ret = false;
            }
            
            if(ret && !json.isNull("BODYTYPE")){
                this.setBodyType(json.getInt("BODYTYPE"));
            } else {
                ret = false;
            }
            
            
        }catch(Exception e){
            Log.log(e.getMessage());
        }
        return ret;
    }
}
