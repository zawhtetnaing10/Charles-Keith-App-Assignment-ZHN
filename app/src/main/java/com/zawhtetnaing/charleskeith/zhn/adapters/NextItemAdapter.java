package com.zawhtetnaing.charleskeith.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.charleskeith.zhn.R;
import com.zawhtetnaing.charleskeith.zhn.viewholders.NextItemViewHolder;


public class NextItemAdapter extends RecyclerView.Adapter<NextItemViewHolder> {
    @NonNull
    @Override
    public NextItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_next_items, parent, false);
        return new NextItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NextItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
