package edu.uncc.assignment04.fragments;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.uncc.assignment04.R;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateView() --> Inflate the fragment's layout and initialize views.
    // Primarily used for setting up the layout and finding views.
    // Suitable for tasks involving inflating the layout and basic UI setup.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // onViewCreated() --> Perform additional setup after the fragment's view hierarchy has been created.
    // Called immediately after onCreateView().
    // Suitable for tasks requiring access to the fully initialized view hierarchy,
    // such as setting up event listeners or interacting with views post-initialization.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // onClick() Listener
        Button buttonStart = view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fragment transaction to replace the current fragment with a new instance of IdentificationFragment
                // Since no data is passed, there is no need for an interface
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.rootView, new IdentificationFragment(), "IdentificationFragment")
                        .commit();
            }
        });

    }
}