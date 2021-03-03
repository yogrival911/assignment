
package com.yogdroidtech.assignment.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yogdroidtech.assignment.response.ItemChoice;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("max_qty")
    @Expose
    private String maxQty;
    @SerializedName("item_choice")
    @Expose
    private List<ItemChoice> itemChoice = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(String maxQty) {
        this.maxQty = maxQty;
    }

    public List<ItemChoice> getItemChoice() {
        return itemChoice;
    }

    public void setItemChoice(List<ItemChoice> itemChoice) {
        this.itemChoice = itemChoice;
    }

}
