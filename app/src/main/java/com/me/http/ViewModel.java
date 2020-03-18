package com.me.http;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    List<City> list = new ArrayList<City>();

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    public ViewModel(@NonNull Application application) {
        super(application);

    }
}
