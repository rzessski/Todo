package com.rzessskidesign.todo.ui.todo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rzessskidesign.todo.API.Todo;
import com.rzessskidesign.todo.R;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private List<Todo> adslist = new ArrayList<>();
    public TodoAdapter() {}
    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TodoViewHolder(layoutInflater.inflate(R.layout.item, parent, false));
    }
    @Override
    public void onBindViewHolder(final TodoViewHolder holder, int position) {
        holder.setNews(adslist.get(position));
    }
    @Override
    public int getItemCount() {
        return adslist.size();
    }
    public void setAdslist(List<Todo> results) {
        adslist.addAll(results);
        notifyDataSetChanged();
    }
}
class TodoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.TodoItemTitle)
    TextView TodoItemTitle;
    @BindView(R.id.TodoItemData)
    TextView TodoItemData;
    @BindView(R.id.card_view)
    CardView cardView;
    private Todo todo;
    @Inject
     TodoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    void setNews(Todo todo) {
        this.todo = todo;
        TodoItemTitle.setText(todo.title);
        TodoItemData.setText(todo.getCreateDate());
    }
}

