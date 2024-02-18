package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.uncc.assignment04.R;

public class SelectIncomeFragment extends Fragment {

    TextView textViewHouseHoldIncome;
    SeekBar seekBar;
    String incomeLevel = "<$25K";

    public SelectIncomeFragment() {
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
        return inflater.inflate(R.layout.fragment_select_income, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewHouseHoldIncome = view.findViewById(R.id.textViewHouseHoldIncome);
        seekBar = view.findViewById(R.id.seekBar);

        textViewHouseHoldIncome.setText(incomeLevel);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0){
                    incomeLevel = "<$25K";
                    textViewHouseHoldIncome.setText(incomeLevel);
                } else if (progress == 1) {
                    incomeLevel = "$25K to <$50K";
                    textViewHouseHoldIncome.setText(incomeLevel);
                } else if (progress == 2) {
                    incomeLevel = "$50K to <$100K";
                    textViewHouseHoldIncome.setText(incomeLevel);
                } else if (progress == 3) {
                    incomeLevel = "$100K to <$200K";
                    textViewHouseHoldIncome.setText(incomeLevel);
                } else if (progress == 4) {
                    incomeLevel = ">$200K";
                    textViewHouseHoldIncome.setText(incomeLevel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.receiveIncome(incomeLevel);
            }
        });

        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelIncome();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectIncomeFragmentInterface) {
            mListener = (SelectIncomeFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement SelectIncomeFragmentInterface");
        }
    }

    SelectIncomeFragmentInterface mListener;

    public interface SelectIncomeFragmentInterface {
        void receiveIncome(String income);
        void cancelIncome();
    }
}