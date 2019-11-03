package com.example.score;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    // private MutableLiveData<Integer> aTeamScore;
    // private MutableLiveData<Integer> bTeamScore;
    private SavedStateHandle handle;
    String akey = getApplication().getResources().getString(R.string.aKey),
            bkey = getApplication().getResources().getString(R.string.bKey),
            spfname = getApplication().getResources().getString(R.string.spfName);
    private int aBack, bBack;

    public MyViewModel(Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if (!handle.contains(akey) || !handle.contains(bkey)) {
            load();
        }
    }

    public void load() {
        SharedPreferences spf = getApplication().getSharedPreferences(spfname, Context.MODE_PRIVATE);
        handle.set(akey,spf.getInt(akey,0));
        handle.set(bkey,spf.getInt(bkey,0));
    }
    public void save(){
        SharedPreferences spf=getApplication().getSharedPreferences(spfname,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=spf.edit();
        editor.putInt(akey,getaTeamScore().getValue());
        editor.putInt(bkey,getbTeamScore().getValue());
        editor.apply();
    }

    public MutableLiveData<Integer> getaTeamScore() {
        if (!handle.contains(akey)) {
            handle.set(akey, 0);
        }
        return handle.getLiveData(akey);
//        if (aTeamScore == null) {
//            aTeamScore = new MutableLiveData<>();
//            aTeamScore.setValue(0);
//        }
//        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (!handle.contains(bkey)) {
            handle.set(bkey, 0);
        }
        return handle.getLiveData(bkey);
//        if (bTeamScore == null) {
//            bTeamScore = new MutableLiveData<>();
//            bTeamScore.setValue(0);
//        }
//        return bTeamScore;
    }

    public void addA(int n) {
        aBack = (int) handle.getLiveData(akey).getValue();
        bBack = (int) handle.getLiveData(bkey).getValue();
        handle.set(akey, aBack + n);
        //save();
    }

    public void addB(int n) {
        aBack = (int) handle.getLiveData(akey).getValue();
        bBack = (int) handle.getLiveData(bkey).getValue();
        handle.set(bkey, bBack + n);
        //save();
    }

    public void reset() {
        aBack = (int) handle.getLiveData(akey).getValue();
        bBack = (int) handle.getLiveData(bkey).getValue();
        handle.set(akey, 0);
        handle.set(bkey, 0);
        //save();
    }

    public void undo() {
        handle.set(akey, aBack);
        handle.set(bkey, bBack);
        //save();
    }

}
