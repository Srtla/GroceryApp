package com.example.groceryapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    SharedPreferences sharedpreferences;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View RootView = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText password= RootView.findViewById(R.id.et_password); //Password
        final EditText rePassword=RootView.findViewById(R.id.et_repassword);
        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 4){
                    int number = Integer.parseInt(password.getText().toString());
                    ArrayList<Integer> digitizedNum = new ArrayList<Integer>();
                    while (number > 0) {
                        digitizedNum.add(number % 10);
                        number = number / 10;
                    }
                    if (digitizedNum.get(0) == digitizedNum.get(1) && digitizedNum.get(1) == digitizedNum.get(2) && digitizedNum.get(2) == digitizedNum.get(3)) {
                        password.setError("Warning! Password too easy");
                    }
                    if (digitizedNum.get(0) - digitizedNum.get(1) == 1 && digitizedNum.get(1) - digitizedNum.get(2) == 1 && digitizedNum.get(2) - digitizedNum.get(3) == 1) {
                        password.setError("Warning! Password too easy");
                    }
                }
            }
        });

        rePassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 4){
                    if (!password.getText().toString().matches(rePassword.getText().toString())) {
                        rePassword.setError("Warning! Password do not match");
                    }
                }
            }
        });


        sharedpreferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Button registerButton=RootView.findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Password", password.getText().toString());
                editor.commit();

                Intent intent = new Intent(getActivity(), GroceryView.class);
                startActivity(intent);
            }
        });

        return RootView;
    }

}
