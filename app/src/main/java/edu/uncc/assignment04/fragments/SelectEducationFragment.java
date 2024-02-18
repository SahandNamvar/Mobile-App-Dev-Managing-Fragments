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

public class SelectEducationFragment extends Fragment {

    RadioGroup radioGroup;

    public SelectEducationFragment() {
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
        return inflater.inflate(R.layout.fragment_select_education, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);

        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();

                String education = getString(R.string.high_school);

                if (radioId == R.id.radioButtonBHS) {
                    education = getString(R.string.below_high_school);
                } else if (radioId == R.id.radioButtonBS) {
                    education = getString(R.string.bachelor_s_degree);
                } else if (radioId == R.id.radioButtonMS) {
                    education = getString(R.string.master_s_degree);
                } else if (radioId == R.id.radioButtonPHD) {
                    education = getString(R.string.ph_d_or_higher);
                } else if (radioId == R.id.radioButtonTS) {
                    education = getString(R.string.trade_school);
                } else if (radioId == R.id.radioButtonPreferNotToSay) {
                    education = getString(R.string.prefer_not_to_say);
                }

                // Send the education selected via the Interface to Main Activity. From Main Activity,
                //      add the education to the Response object inside Demographics Fragment and set the Textview!
                mListener.receiveEducation(education);
            }
        });

        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to cancel education and pop the backstack (in Main)
                mListener.cancelEducation();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectEducationFragmentInterface){
            mListener = (SelectEducationFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement SelectEducationFragmentInterface");
        }
    }

    SelectEducationFragmentInterface mListener;

    public interface SelectEducationFragmentInterface {
        void receiveEducation(String education);
        void cancelEducation();
    }
}