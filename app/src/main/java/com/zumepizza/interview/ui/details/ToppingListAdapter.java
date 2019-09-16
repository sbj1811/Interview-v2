package com.zumepizza.interview.ui.details;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.zumepizza.interview.BR;
import com.zumepizza.interview.model.PizzaItem;
import com.zumepizza.interview.model.Topping;
import com.zumepizza.interview.ui.main.MainViewModel;

import java.util.List;

public class ToppingListAdapter extends RecyclerView.Adapter<ToppingListAdapter.ViewHolder> {

    private DetailsViewModel viewModel;
    private List<Topping> toppings;
    private int layoutId;

    public ToppingListAdapter(DetailsViewModel viewModel, int layoutId) {
        this.viewModel = viewModel;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,viewType,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(viewModel,position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        if (toppings == null) {
            return 0;
        }
        return toppings.size();
    }

    public void swapResults(List<Topping> result) {
        this.toppings = result;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(DetailsViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }



    }

}
