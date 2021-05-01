package com.teamblunder.endgame.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.teamblunder.endgame.R;
import com.teamblunder.endgame.ResultActivity;

public class TextFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getActivity().getCurrentFocus() != null) {
            manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            getActivity().getCurrentFocus().clearFocus();
        }

        TextInputLayout inputLayout = view.findViewById(R.id.filledTextField);
        EditText editText = view.findViewById(R.id.editText);
        inputLayout.setError(null);
        editText.setText(HomeFragment.resultText);
        Button continueButton = view.findViewById(R.id.continueButton);
        continueButton.setOnClickListener(v -> {
            if (editText.getText().toString().isEmpty()){
                inputLayout.setError("Field cannot be empty");
            }else {
                inputLayout.setError(null);
                InputMethodManager manager1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager1.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                editText.clearFocus();
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("text", editText.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }
}