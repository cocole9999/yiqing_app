package com.me.http;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Data extends Fragment {
    private List<City> list = new ArrayList<City>();
    private RadioGroup radioGroup;
    private RadioButton province, city, country,shi;
    private EditText queryDate, name;
    private Button queryButton;
    private Button chartButton;
    private TextView textView;
    private String method, name1, date;
    private ViewModel viewModel;
    public Data() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_data, container, false);
        textView = inflate.findViewById(R.id.textView);
        radioGroup = inflate.findViewById(R.id.radioGroup);
        province = inflate.findViewById(R.id.province);
        city = inflate.findViewById(R.id.city);
        country = inflate.findViewById(R.id.country);
        queryDate = inflate.findViewById(R.id.queryDate);
        name = inflate.findViewById(R.id.name);
        queryButton = inflate.findViewById(R.id.queryButton);
        chartButton = inflate.findViewById(R.id.chartButton);
        viewModel = ViewModelProviders.of(requireActivity()).get(ViewModel.class);
        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.province:
                        method = "p";
                        break;
                    case R.id.country:
                        method = "w";
                        break;
                    case R.id.city:
                        method = "s";
                        break;
                    case R.id.shi:
                        method = "d";
                        break;
                    default:
                        break;
                }
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = String.valueOf(name.getText().toString().trim());
                date = String.valueOf(queryDate.getText().toString());
//                String url = "http://192.168.1.17:8080/YQ/data?method=getWorldData" ;
                String url = "http://192.168.1.17:8080/YQ/data?method="+method+"&province="+name1+"&time="+date;
                RequestQueue queue = Volley.newRequestQueue(getActivity());
//                Toast.makeText(getActivity(),url,Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Gson gson = new Gson();
                                Type cityType = new TypeToken<ArrayList<City>>() {}.getType();
                                list = gson.fromJson(response, cityType);
                                String temp="";

                                if(list!=null){
                                    for (City city1 : list) {
                                        temp+=city1.getName()+" 确诊："+city1.getConfirm()+" 治愈："+city1.getHeal()
                                                +"\n 死亡："+city1.getDead()+"\n";
                                    }
                                    viewModel.setList(list);
                                }

                                textView.setText(temp);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("myTAG", "onErrorResponse: " + error);
                            }
                        });
                queue.add(stringRequest);
            }
        });
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_data_to_chart);
            }
        });
    }
}
