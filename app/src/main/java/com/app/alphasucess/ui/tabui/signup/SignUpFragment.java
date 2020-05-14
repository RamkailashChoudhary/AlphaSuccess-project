package com.app.alphasucess.ui.tabui.signup;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.app.alphasucess.R;
import com.app.alphasucess.service.NetworkServiceLayer;
import com.app.alphasucess.service.RestServiceLayer;
import com.app.alphasucess.ui.ForgotPasswordActivity;
import com.app.alphasucess.ui.HomeActivity;
import com.app.alphasucess.ui.tabui.login.LoginActivity;

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

    private void signupApiService(String Email,String Name,String Password,String Phone,String StateID,String Address,boolean isReffered){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.signUpApi(Email,Name,Password,Phone,StateID,Address,isReffered).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

//                loadingProgressBar.setVisibility(View.VISIBLE);
                Intent forgotPassword1 = new Intent(getActivity(), HomeActivity.class);
                startActivity(forgotPassword1);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

//                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void stateListData(){

        RestServiceLayer restServiceLayer = (RestServiceLayer) NetworkServiceLayer.newInstance(RestServiceLayer.class);
        restServiceLayer.stateListData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("StateList","List Data :"+response.body().toString());
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(
                                getActivity(),
                                R.layout.dropdown_menu_popup_item,
                                states);
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
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

}
