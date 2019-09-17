package com.zumepizza.interview.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.zumepizza.interview.BR;
import com.zumepizza.interview.R;
import com.zumepizza.interview.model.PizzaItem;
import com.zumepizza.interview.ui.details.DetailsActivity;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private static final String TAG = ListAdapter.class.getSimpleName();
    private static final String ID = "is";
    private int layoutId;
    private MainViewModel viewModel;
    private List<PizzaItem> pizzaItemList;

    public ListAdapter(@LayoutRes int layoutId, MainViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(viewModel, position);
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
        if (pizzaItemList == null) {
            return 0;
        }
        return pizzaItemList.size();
    }

    public void swapResults(List<PizzaItem> result) {
        this.pizzaItemList = result;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MainViewModel viewModel, Integer position) {
            PizzaItem pizzaItem = viewModel.getPizzaItem(position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(ID, pizzaItem.getId());
                context.startActivity(intent);
            });
            Button button = binding.getRoot().findViewById(R.id.add_button);
            Vibrator vib = (Vibrator) binding.getRoot().getContext().getSystemService(Context.VIBRATOR_SERVICE);
            button.setOnClickListener(click -> {
                viewModel.savePizzaItem(viewModel.getPizzaItem(position));
                vib.vibrate(50);
                button.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            });
        }


    }
}
