package com.soco.msg;

import java.io.Serializable;

public interface MsgObject extends Serializable{

    /**
     * 
     */
    
    public boolean save();
    
    public String toJsonString();
    
    public boolean parseJsonStr(String jsonStr);
}
