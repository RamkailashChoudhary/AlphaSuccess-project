package com.app.alphasucess.ui.tabui.signup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.VerifyOtpActivity;
import com.app.alphasucess.ui.data.model.ResoureData;
import com.app.alphasucess.ui.data.model.StateResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFragment extends DialogFragment {


    private ArrayList<String> states;
    private int statesId = 0;
    AutoCompleteTextView edtxt_drop_state;
    public static UpdateFragment newInstance() {
        return new UpdateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.update_fragment, container, false);

        ImageView img_profile=view.findViewById(R.id.img_profile);
        ImageView img_edit=view.findViewById(R.id.imageView2);
        TextInputEditText edtxt_name=view.findViewById(R.id.edtxt_name);
        TextInputEditText edtxt_email=view.findViewById(R.id.txt_email);
        TextInputEditText edtxt_mobile=view.findViewById(R.id.txt_mobile_number);
        TextInputEditText edtxt_address=view.findViewById(R.id.txtx_address);
        edtxt_drop_state=view.findViewById(R.id.drop_state);
        TextInputEditText edtxt_txt_password=view.findViewById(R.id.txt_password);
        TextInputEditText edtxt_txt_confirm_password=view.findViewById(R.id.txt_confirm_password);
        TextView txt_referral=view.findViewById(R.id.txt_referral);
        TextView btn_update=view.findViewById(R.id.btn_update);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtxt_name.getText().toString().length()>0 &&edtxt_email.getText().toString().length()>0
                        &&edtxt_mobile.getText().toString().length()>0 &&edtxt_address.getText().toString().length()>0
                        &&edtxt_drop_state.getText().toString().length()>0 &&edtxt_txt_password.getText().toString().length()>0
                        &&edtxt_txt_confirm_password.getText().toString().length()>0){
                    if (edtxt_txt_password.getText().toString().equals(edtxt_txt_confirm_password.getText().toString())){
                        updateApiService(edtxt_address.getText().toString(),edtxt_email.getText().toString(),
                                edtxt_name.getText().toString(),statesId,edtxt_txt_password.getText().toString());
                    }else {
                        Toast.makeText(getActivity(),"Password and Confirm Password Should be Same",Toast.LENGTH_LONG).show();
                    }

                   /* signupApiService(txt_email.getText().toString(),txt_name.getText().toString(),txt_password.getText().toString(),txt_mobile.getText().toString(),"2",
                            txt_address.getText().toString(),false);*/

                }else Toast.makeText(getActivity(),"Fill All Required Info",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }catch (Exception e){
            e.printStackTrace();
        }
        stateListData();
        getProfile();

    }

    private void updateApiService(String Address,String Email,String Name,int StateID,String Password){


        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.updateProfile(Address,Email,Name,StateID,Password).enqueue(new Callback<ResoureData>() {
            @Override
            public void onResponse(Call<ResoureData> call, Response<ResoureData> response) {

//                loadingProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ResoureData> call, Throwable t) {

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void stateListData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.stateListData().enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                Log.d("StateList","List Data :"+response.body().toString());
                states=new ArrayList<>();
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(
                                getActivity(),
                                R.layout.dropdown_menu_popup_item,
                                states);
                for (int i=0;i<response.body().getData().size();i++){
                    states.add(response.body().getData().get(i).getName());
                }
                edtxt_drop_state.setAdapter(adapter);
                edtxt_drop_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        edtxt_drop_state.setText(states.get(position));
                        statesId = response.body().getData().get(position).getId();
                        Toast.makeText(getActivity(),states.get(position),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });
    }

    private void getProfile(){
        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN,getContext());
        restServiceLayer.getProfileDetails().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                loadingProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


}
