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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.uncc.assignment04.MainActivity;
import edu.uncc.assignment04.R;
import edu.uncc.assignment04.Response;

public class IdentificationFragment extends Fragment {

    private EditText editTextName, editTextEmail;
    private RadioGroup radioGroup;
    private Response response; // Local response object to hold user entered attributes

    public IdentificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        radioGroup = view.findViewById(R.id.radioGroup);

        // onClick
        view.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();

                String role = getString(R.string.student);

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter name!", Toast.LENGTH_SHORT).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter email!", Toast.LENGTH_SHORT).show();
                } else {
                    if (radioId == R.id.radioButtonEmployee) {
                        role = getString(R.string.employee);
                    } else if (radioId == R.id.radioButtonOther) {
                        role = getString(R.string.other);
                    }
                    // Create new a local response object and set attributes
                    response = new Response();
                    response.setName(name);
                    response.setEmail(email);
                    response.setRole(role);
                    Log.d(MainActivity.TAG, response.toString());
                    // To send this response object to the next fragment (Demographic), implement an interface,
                    // create a local instance of it, initialize the local instance inside the onAttach() method and link it to context (main activity).
                    // Then in Main Activity, implement the interface and override its methods
                    mListener.gotoDemographicsFragment(response); // Pass the response object created to Demographics Fragment (without the need for
                                                                  // initializing it in Main Activity when received. It can be passed to the next fragment directly.)  )
                }

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof  IdentificationFragmentInterface) {
            mListener = (IdentificationFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " Must implement IdentificationFragmentInterface");
        }
    }

    IdentificationFragmentInterface mListener;

    // Implement an interface... (interface takes in a Response object to send to Demographics Fragment)
    public interface IdentificationFragmentInterface {
        void gotoDemographicsFragment(Response r);
    }

}