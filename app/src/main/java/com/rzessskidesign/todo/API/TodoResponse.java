package com.rzessskidesign.todo.API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class TodoResponse {

    @SerializedName("Todo")
    public List<Todo> results = new ArrayList<>();

}
