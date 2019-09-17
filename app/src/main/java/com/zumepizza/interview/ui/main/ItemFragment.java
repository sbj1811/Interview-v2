package com.zumepizza.interview.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.zumepizza.interview.R;
import com.zumepizza.interview.databinding.FragmentItemListBindingImpl;
import com.zumepizza.interview.utils.FactoryUtils;

public class ItemFragment extends Fragment {


    private static final String TAG = ItemFragment.class.getSimpleName();
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String POSITION = "position";

    private int mColumnCount = 1;
    private int position = 0;
    private MainViewModel mainViewModel;
    private FragmentItemListBindingImpl binding;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }


    public static ItemFragment newInstance(int columnCount, int position) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            position = getArguments().getInt(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false);
        MainViewModelFactory factory = FactoryUtils.getMainViewModelFactory(getContext());
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        mainViewModel.init();
        binding.setViewModel(mainViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.getPizzaItems(position).observe(getViewLifecycleOwner(), pizzaItems -> {
            mainViewModel.setPizzaItemInAdapter(pizzaItems);
        });

    }

}
