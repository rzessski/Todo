package com.rzessskidesign.todo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.rzessskidesign.todo.API.Api;
import com.rzessskidesign.todo.API.Todo;
import com.rzessskidesign.todo.API.TodoResponse;
import com.rzessskidesign.todo.ui.LoginRegister.LogReg;
import com.rzessskidesign.todo.ui.todo.RecyclerViewClickListener;
import com.rzessskidesign.todo.ui.todo.TodoAdapter;
import com.rzessskidesign.todo.ui.todo.TodoManager;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    Api api;
    @Inject
    TodoManager todoManager;
    @Inject
    UserProfile userProfile;
    @BindView(R.id.RecyclerViewMain)
    RecyclerView RecyclerViewMain;
    @BindView(R.id.fabCustom)
    FloatingActionButton fabCustom;
    private TodoAdapter adapter;
    Call<TodoResponse> call;
    private List<Todo> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.component.inject(this);
        if (userProfile.ifLogin()) {
            startActivity(new Intent(this, LogReg.class));
        }

        fabCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, v);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.addToDo:
                                onShowDialog("add",0);
                                break;
                            case R.id.logout:
                           userProfile.logout();
                                startActivity(new Intent(MainActivity.this, LogReg.class));
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerViewMain.setLayoutManager(linearLayoutManager);
        adapter = new TodoAdapter();
        RecyclerViewMain.setAdapter(adapter);

        LoadAll();
        RecyclerViewMain.addOnItemTouchListener(
                new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        onShowDialog("view",position);
                    }
                })
        );

    }
    @Override
    protected void onResume() {
        super.onResume();
        todoManager.onAttach(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        todoManager.onStop();
    }
    public void LoadAll() {
        call = api.getAllTodo(userProfile.getId());
        call.enqueue(new Callback<TodoResponse>() {

            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                if (response.isSuccessful()) {
                    todoList = response.body().results;
                    showTodo(response.body().results);
                }
            }
            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
            }
        });
    }
    public void showTodo(List<Todo> results) {
        todoList = results;
        adapter.setAdslist(results);
    }
    public void onShowDialog(String type, final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_detail);
        dialog.setTitle("...");

        final EditText dialogTitleToDo = (EditText) dialog.findViewById(R.id.DialogTitleToDo);
        TextView  dialogDataToDo = (TextView) dialog.findViewById(R.id.DialogDataToDo);
        final EditText dialogDescriptionTodo = (EditText) dialog.findViewById(R.id.DialogDescriptionTodo);
        ImageButton remove = (ImageButton) dialog.findViewById(R.id.ImageButtonDelete);
        ImageButton save = (ImageButton) dialog.findViewById(R.id.ImageButtonSave);
        ImageButton back = (ImageButton) dialog.findViewById(R.id.ImageButtonBack);

        if (type.equals("view")){
             save.setEnabled(false);
            dialogDataToDo.setVisibility(View.VISIBLE);
            dialogTitleToDo.setText(todoList.get(position).getTitle());
            dialogDescriptionTodo.setText(todoList.get(position).getDescription());
            String date = getString(R.string.datadodania)+todoList.get(position).getCreateDate();
            dialogDataToDo.setText(date);
            dialogTitleToDo.setKeyListener( null );
            dialogTitleToDo.setFocusable( false );
            dialogTitleToDo.setCursorVisible(false);
            dialogDescriptionTodo.setKeyListener( null );
            dialogDescriptionTodo.setFocusable( false );
            dialogDescriptionTodo.setCursorVisible(false);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              todoManager.removeToDo(Integer.parseInt(todoList.get(position).getId()));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoManager.save(dialogTitleToDo.getText().toString(),dialogDescriptionTodo.getText().toString());
            }
        });
        dialog.show();
    }
}
