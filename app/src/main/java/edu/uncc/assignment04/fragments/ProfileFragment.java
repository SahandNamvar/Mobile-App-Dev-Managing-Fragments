package edu.uncc.assignment04.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.uncc.assignment04.MainActivity;
import edu.uncc.assignment04.R;
import edu.uncc.assignment04.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    TextView textViewName, textViewEmail, textViewEdu, textViewMaritalStatus, textViewLivingStatus, textViewIncomeValue;

    private static final String Final_Res_Key = "Final_Res_Key";

    private Response response; // To receive the final response object from Demographic Fragment

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Response r) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(Final_Res_Key, r);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            response = new Response();
            response = (Response) getArguments().getSerializable(Final_Res_Key);
            Log.d(MainActivity.TAG, response.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity(), "Profile Completed!", Toast.LENGTH_SHORT).show();

        textViewName = view.findViewById(R.id.textViewName);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewEdu = view.findViewById(R.id.textViewEdu);
        textViewMaritalStatus = view.findViewById(R.id.textViewMaritalStatus);
        textViewLivingStatus = view.findViewById(R.id.textViewLivingStatus);
        textViewIncomeValue = view.findViewById(R.id.textViewIncomeValue);

        textViewName.setText(response.getName());
        textViewEmail.setText(response.getEmail());
        textViewEdu.setText(response.getEducation());
        textViewMaritalStatus.setText(response.getMaritalStatus());
        textViewLivingStatus.setText(response.getLivingStatus());
        textViewIncomeValue.setText(response.getIncome());

    }
}