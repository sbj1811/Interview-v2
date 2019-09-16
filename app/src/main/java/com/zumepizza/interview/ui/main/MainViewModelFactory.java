package com.zumepizza.interview.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zumepizza.interview.data.DataRepository;
import com.zumepizza.interview.ui.main.MainViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataRepository repository;

    public MainViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}
