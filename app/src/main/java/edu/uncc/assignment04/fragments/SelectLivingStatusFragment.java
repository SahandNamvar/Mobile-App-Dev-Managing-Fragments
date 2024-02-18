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

public class SelectLivingStatusFragment extends Fragment {

    RadioGroup radioGroup;

    public SelectLivingStatusFragment() {
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
        return inflater.inflate(R.layout.fragment_select_living_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);

        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                String livingStatus = getString(R.string.home_owner);

                if (radioId == R.id.radioButtonRenter)  {
                    livingStatus = getString(R.string.renter);
                } else if (radioId == R.id.radioButtonLessee) {
                    livingStatus = getString(R.string.lessee);
                } else if (radioId == R.id.radioButtonOther) {
                    livingStatus = getString(R.string.other);
                } else if (radioId == R.id.radioButtonPreferNotToSay) {
                    livingStatus = getString(R.string.prefer_not_to_say);
                }

                mListener.receiveLivingStatus(livingStatus);
            }
        });

        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelLivingStatus();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectLivingStatusFragmentInterface) {
            mListener = (SelectLivingStatusFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement SelectLivingStatusFragmentInterface");
        }
    }

    SelectLivingStatusFragmentInterface mListener;

    public interface SelectLivingStatusFragmentInterface {
        void receiveLivingStatus(String livingStatus);
        void cancelLivingStatus();
    }
}