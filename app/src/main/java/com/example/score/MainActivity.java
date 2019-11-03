package com.example.score;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateVMFactory;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.score.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    ActivityMainBinding binding;
//    public final static String aKey = "aTeam";
//    public final static String bKey = "bTeam";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this,new SavedStateVMFactory(this)).get(MyViewModel.class);

//        if (savedInstanceState != null) {
//            myViewModel.getaTeamScore().setValue(savedInstanceState.getInt(aKey));
//            myViewModel.getbTeamScore().setValue(savedInstanceState.getInt(bKey));
//        }
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(aKey, myViewModel.getaTeamScore().getValue());
//        outState.putInt(bKey, myViewModel.getbTeamScore().getValue());
//    }


    @Override
    protected void onPause() {
        super.onPause();
        myViewModel.save();
    }
}
