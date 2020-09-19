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
public class LoginFragment extends Fragment {

    SharedPreferences sharedpreferences;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText password= (EditText) RootView.findViewById(R.id.et_password); //Password
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
                        password.setError("Warning! Password Incorrect");
                    }
                    if (digitizedNum.get(0) - digitizedNum.get(1) == 1 && digitizedNum.get(1) - digitizedNum.get(2) == 1 && digitizedNum.get(2) - digitizedNum.get(3) == 1) {
                        password.setError("Warning! Password Incorrect");
                    }
                }
            }
        });


        sharedpreferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        Button loginButton=RootView.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = sharedpreferences.getString("Password", "No name defined");//"No name defined" is the default value.

                if(name.matches(password.getText().toString())){
                    Intent intent = new Intent(getActivity(), GroceryView.class);
                    startActivity(intent);
                }else{
                    password.setError("Warning! Incorrect password");
                }
            }
        });

        return RootView;
    }

}
