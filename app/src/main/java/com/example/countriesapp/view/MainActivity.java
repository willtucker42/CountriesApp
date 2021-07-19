package com.example.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countryListRecyclerView)
    RecyclerView countryList;
    @BindView(R.id.listErrorTextView)
    TextView listErrorTextView;

    @BindView(R.id.loadingSpinner)
    ProgressBar loadingView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    private ListViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });

        observerViewModel();
    }

    private void observerViewModel(){
        viewModel.countries.observe(this, countryModels -> {
            if(countryModels != null){
                countryList.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });
        viewModel.countryLoadError.observe(this, isError -> {
            if (isError != null){
                listErrorTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null){
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading){
                    listErrorTextView.setVisibility(View.GONE);
                    countryList.setVisibility(View.GONE);
                }
            }
        });
    }
}
