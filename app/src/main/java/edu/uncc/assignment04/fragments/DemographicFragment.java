package edu.uncc.assignment04.fragments;

import android.content.Context;
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

public class DemographicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RESPONSE_KEY = "RESPONSE_KEY";

    // TODO: Rename and change types of parameters
    private Response response; // To be received by Identification Fragment

    // Methods for adding attributes to Response
    public void setEducationStatus(String educationStatus) { // This method is called from Main Activity when the education string is received by the Main Activity from Select Education Fragment.
        this.response.setEducation(educationStatus);
        Log.d(MainActivity.TAG, response.toString());
    }

    public void setMaritalStatus(String maritalStatus) {
        this.response.setMaritalStatus(maritalStatus);
        Log.d(MainActivity.TAG, response.toString());
    }

    public void setLivingStatus(String livingStatus) {
        this.response.setLivingStatus(livingStatus);
        Log.d(MainActivity.TAG, response.toString());
    }

    public void setIncome(String income) {
        this.response.setIncome(income);
        Log.d(MainActivity.TAG, response.toString());
    }

    public DemographicFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    // Receive the Response object sent from Main Activity and parse it
    public static DemographicFragment newInstance(Response r) {
        DemographicFragment fragment = new DemographicFragment();
        Bundle args = new Bundle();
        args.putSerializable(RESPONSE_KEY, r);
        fragment.setArguments(args);
        return fragment;
    }

    // onCreate, if the object is received successfully, create a new local response object and set it equal to the Serializable received.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            response = new Response();
            response = (Response) getArguments().getSerializable(RESPONSE_KEY);
            Log.d(MainActivity.TAG, response.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demographic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // --------------------------------- Buttons - onClick() ---------------------------------
        view.findViewById(R.id.buttonSelectEducation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectEducation(); // Call the selectEducation method from the interface DemographicFragmentInterface.
            }
        });
        view.findViewById(R.id.buttonSelectMarital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectMaritalStatus();
            }
        });
        view.findViewById(R.id.buttonSelectLiving).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectLivingStatus();
            }
        });
        view.findViewById(R.id.buttonSelectIncome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectIncome();
            }
        });
        view.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Check if response is complete
                if (response.getEducation() == null) {
                    Toast.makeText(getActivity(), "Select Education!", Toast.LENGTH_SHORT).show();
                } else if (response.getMaritalStatus() == null) {
                    Toast.makeText(getActivity(), "Select Marital Status!", Toast.LENGTH_SHORT).show();
                } else if (response.getLivingStatus() == null) {
                    Toast.makeText(getActivity(), "Select Living Status!", Toast.LENGTH_SHORT).show();
                } else if (response.getIncome() == null) {
                    Toast.makeText(getActivity(), "Select Income!", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.gotoProfile(response); // finalized version
                }
            }
        });

        // --------------------------------- Update Textview ---------------------------------

        TextView textViewEducation = view.findViewById(R.id.textViewEducation);
        if (response.getEducation() != null) {
            textViewEducation.setText(response.getEducation());
        }

        TextView textViewMaritalStatus = view.findViewById(R.id.textViewMaritalStatus);
        if (response.getMaritalStatus() != null) {
            textViewMaritalStatus.setText(response.getMaritalStatus());
        }

        TextView textViewLivingStatus = view.findViewById(R.id.textViewLivingStatus);
        if (response.getLivingStatus() != null) {
            textViewLivingStatus.setText(response.getLivingStatus());
        }

        TextView textViewIncomeStatus = view.findViewById(R.id.textViewIncomeStatus);
        if (response.getIncome() != null) {
            textViewIncomeStatus.setText(response.getIncome());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DemographicFragmentInterface) {
            mListener = (DemographicFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement DemographicFragmentInterface");
        }
    }

    DemographicFragmentInterface mListener;

    public interface DemographicFragmentInterface {
        void selectEducation(); // Launch education fragment
        void selectMaritalStatus(); // launch marital status fragment
        void selectLivingStatus(); // launch living status fragment
        void selectIncome(); // launch income fragment
        void gotoProfile(Response r); // launch profile fragment
    }
}