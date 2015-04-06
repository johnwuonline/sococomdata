package com.soco.msg;

public class MsgBodyImage implements MsgBody{

    /**
     * 
     */
    private static final long serialVersionUID = 3405247358028757648L;
    private String _body_type;
    
    public MsgBodyImage(){
        this._body_type = "IMAGE";
    }
    
    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toJsonString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean parseJsonStr(String jsonStr) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getBodyType() {
        // TODO Auto-generated method stub
        return this._body_type;
    }

}
