package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import edu.uncc.assignment04.R;

public class SelectMaritalStatusFragment extends Fragment {

    RadioGroup radioGroup;

    public SelectMaritalStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_marital_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);

        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                String maritalStatus = getString(R.string.not_married);

                if (radioId == R.id.radioButtonMarried)  {
                    maritalStatus = getString(R.string.married);
                } else if (radioId == R.id.radioButtonPreferNotToSay) {
                    maritalStatus = getString(R.string.prefer_not_to_say);
                }

                mListener.receiveMaritalStatus(maritalStatus);
            }
        });

        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelMaritalStatus();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectMaritalStatusFragmentInterface) {
            mListener = (SelectMaritalStatusFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement SelectMaritalStatusFragmentInterface");
        }
    }

    SelectMaritalStatusFragmentInterface mListener;

    public interface SelectMaritalStatusFragmentInterface {
        void receiveMaritalStatus(String maritalStatus); // receive marital status and send it directly to Demographics fragment to update Response and Textview
        void cancelMaritalStatus();
    }
}