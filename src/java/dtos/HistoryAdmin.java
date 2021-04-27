/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author SE140066
 */
public class HistoryAdmin {
    private Date updateDate;
    private String content;
    private String userID;

    public HistoryAdmin() {
    }

    public HistoryAdmin(Date updateDate, String content, String userID) {
        this.updateDate = updateDate;
        this.content = content;
        this.userID = userID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
