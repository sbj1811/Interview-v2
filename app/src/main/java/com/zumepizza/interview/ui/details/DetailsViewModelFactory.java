package com.zumepizza.interview.ui.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zumepizza.interview.data.DataRepository;

public class DetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DataRepository repository;

    public DetailsViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsViewModel(repository);
    }

}
