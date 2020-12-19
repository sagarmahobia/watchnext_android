package com.sagar.watchnext.activities.persondetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.watchnext.R;
import com.sagar.watchnext.databinding.ActivityPersonBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class PersonActivity extends AppCompatActivity implements PersonActivityHandler {

    @Inject
    PersonActivityViewModelFactory viewModelFactory;

    private ActivityPersonBinding binding;

    private PersonActivityViewModel viewModel;

    private PersonActivityModel activityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_person);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonActivityViewModel.class);
        activityModel = viewModel.getActivityModel();
        binding.setModel(activityModel);
        binding.setHandler(this);
    }
}
