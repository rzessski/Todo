package com.rzessskidesign.todo.API;

import com.google.gson.annotations.SerializedName;

public class Todo {

    @SerializedName("id")
    public String id;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    private String description;
    @SerializedName("dataOD")
    private String CreateDate;

    public Todo(String id, String title, String description, String createDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        CreateDate = createDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreateDate() {
        return CreateDate;
    }

}
