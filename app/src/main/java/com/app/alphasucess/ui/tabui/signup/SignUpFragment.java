package com.app.alphasucess.ui.tabui.signup;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.app.alphasucess.MyApplication;
import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.ForgotPasswordActivity;
import com.app.alphasucess.ui.HomeActivity;
import com.app.alphasucess.ui.VerifyOtpActivity;
import com.app.alphasucess.ui.data.model.StateResponse;
import com.app.alphasucess.ui.tabui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private ArrayList<String> states;
    AutoCompleteTextView editTextFilledExposedDropdown;
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sign_up_fragment, container, false);

        TextInputEditText txt_name=view.findViewById(R.id.txt_name);
        TextInputEditText txt_mobile=view.findViewById(R.id.txt_mobile_number);
        //TextInputEditText txt_address=view.findViewById(R.id.txtx_address);
        TextInputEditText txt_password=view.findViewById(R.id.txt_password);
        TextInputEditText txt_refer=view.findViewById(R.id.txt_refer);
        Button btn_signup=view.findViewById(R.id.btn_signup);

<<<<<<< HEAD
            btn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txt_name.getText().toString().length()>0 &&txt_email.getText().toString().length()>0
                            &&txt_mobile.getText().toString().length()>0 &&txt_address.getText().toString().length()>0
                            &&txt_password.getText().toString().length()>0 &&txt_conf_password.getText().toString().length()>0 ){
                        signupApiService(txt_email.getText().toString(),txt_name.getText().toString(),txt_password.getText().toString(),txt_mobile.getText().toString(),"2",
                                txt_address.getText().toString(),false);

                    }else Toast.makeText(getActivity(),"Fill All Required Info",Toast.LENGTH_LONG).show();
                }
            });
=======
btn_signup.setOnClickListener(v -> {
    System.out.println("NAME :"+txt_name.getText().toString());
    System.out.println("Mobile :"+txt_mobile.getText().toString());
    System.out.println("password :"+txt_password.getText().toString());
    System.out.println("NAME :"+txt_password.getText().toString());
    if (txt_name.getText().toString().length()>0 &&txt_mobile.getText().toString().length()>0
            &&txt_password.getText().toString().length()>0 ){
        signupApiService(txt_name.getText().toString(),txt_password.getText().toString(),txt_mobile.getText().toString(),"2",
                false);

    }else Toast.makeText(getActivity(),"Fill All Required Info",Toast.LENGTH_LONG).show();
});
>>>>>>> 3e477c2d97d013c4917c9d3a0f506c528acdc1e2
         editTextFilledExposedDropdown =
                view.findViewById(R.id.drop_state);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
        stateListData();

    }

    private void signupApiService(String Name,String Password,String Phone,String StateID,boolean isReffered){

         String androidIdd = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class, MyApplication.REFRESH_TOKEN);
        restServiceLayer.signUpApi(Name,Password,Phone,StateID,isReffered,androidIdd,"Android").enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

//                loadingProgressBar.setVisibility(View.VISIBLE);
                try {

                    JSONObject responseData = new JSONObject(response.body().toString());
                    if(responseData.optString("replycode").equalsIgnoreCase("1")) {
                        Intent forgotPassword1 = new Intent(getActivity(), VerifyOtpActivity.class);
                        forgotPassword1.putExtra("phoneNumber", Phone);
                        startActivity(forgotPassword1);
                        getActivity().finish();
                    }else
                      Toast.makeText(getContext(),""+responseData.optString("message"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void stateListData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class,MyApplication.REFRESH_TOKEN);
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
                editTextFilledExposedDropdown.setAdapter(adapter);
                editTextFilledExposedDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

}
