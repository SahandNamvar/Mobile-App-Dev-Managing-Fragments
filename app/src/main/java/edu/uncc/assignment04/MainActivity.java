package edu.uncc.assignment04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import edu.uncc.assignment04.fragments.DemographicFragment;
import edu.uncc.assignment04.fragments.IdentificationFragment;
import edu.uncc.assignment04.fragments.MainFragment;
import edu.uncc.assignment04.fragments.ProfileFragment;
import edu.uncc.assignment04.fragments.SelectEducationFragment;
import edu.uncc.assignment04.fragments.SelectIncomeFragment;
import edu.uncc.assignment04.fragments.SelectLivingStatusFragment;
import edu.uncc.assignment04.fragments.SelectMaritalStatusFragment;

public class MainActivity extends AppCompatActivity implements
        IdentificationFragment.IdentificationFragmentInterface,
        DemographicFragment.DemographicFragmentInterface,
        SelectEducationFragment.SelectEducationFragmentInterface,
        SelectMaritalStatusFragment.SelectMaritalStatusFragmentInterface,
        SelectLivingStatusFragment.SelectLivingStatusFragmentInterface,
        SelectIncomeFragment.SelectIncomeFragmentInterface
{

    private FragmentManager fragmentManager; // Used for managing fragments

    public static final String TAG = "RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up fragment manager
        fragmentManager = getSupportFragmentManager();
        // Load MainFragment
        fragmentManager.beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    // ------------------------ Override method from Interface Implemented in Identification Fragment ------------------------
    // Called from Identification Fragment to go to Demographics Fragment and send data (Implemented interface from Identification Fragment)
    @Override
    public void gotoDemographicsFragment(Response r) {
        // Find the fragment by Tag
        IdentificationFragment fragment = (IdentificationFragment) getSupportFragmentManager().findFragmentByTag("IdentificationFragment");
        if(fragment!=null){
            // There is no need to create a local response variable in Main Activity. The Response object sent from Identification Fragment
            //       can be passed to the .newInstance of the Demographic Fragment directly.
            // Replace current fragment (Identification Fragment) to a new instance of Demographic Fragment & add to Back Stack.
            // .newInstance --> pass the current response (with attributes) to the newInstance of the DemographicFragment.
            fragmentManager.beginTransaction()
                    .replace(R.id.rootView, DemographicFragment.newInstance(r), "DemographicFragment") // Send r (the Response sent from Identification Fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    // ------------------------ Override method from Interface Implemented in Demographic Fragment ------------------------
    @Override // Open education fragment
    public void selectEducation() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectEducationFragment(), "SelectEducationFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectMaritalStatus() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectMaritalStatusFragment(), "SelectMaritalStatusFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectLivingStatus() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectLivingStatusFragment(), "SelectLivingStatusFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectIncome() {
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, new SelectIncomeFragment(), "SelectIncomeFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoProfile(Response r) { // Receive the finalized version of Response, create a new instance of Profile Fragment and send it Response.
        fragmentManager.beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(r), "ProfileFragment")
                .addToBackStack(null)
                .commit();
    }

    // ------------------------ Override method from Interface Implemented in Select Education Fragment ------------------------
    @Override // Receive the education from Select Education Fragment and update Response in Demographics Fragment
    public void receiveEducation(String education) {
        // TODO: When education is selected, update the local Response object in Demographic Fragment and set Textview
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("DemographicFragment");
        if(fragment!=null) {
            fragment.setEducationStatus(education);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelEducation() {
        fragmentManager.popBackStack();
    }

    // ------------------------ Override method from Interface Implemented in Select Marital Status Fragment ------------------------
    @Override
    public void receiveMaritalStatus(String maritalStatus) {
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("DemographicFragment");
        if (fragment != null) {
            fragment.setMaritalStatus(maritalStatus);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelMaritalStatus() {
        fragmentManager.popBackStack();
    }

    // ------------------------ Override method from Interface Implemented in Select Living Status Fragment ------------------------
    @Override
    public void receiveLivingStatus(String livingStatus) {
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("DemographicFragment");
        if(fragment!=null) {
            fragment.setLivingStatus(livingStatus);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelLivingStatus() {
        fragmentManager.popBackStack();
    }

    // ------------------------ Override method from Interface Implemented in Income Fragment ------------------------
    @Override
    public void receiveIncome(String income) {
        DemographicFragment fragment = (DemographicFragment) getSupportFragmentManager().findFragmentByTag("DemographicFragment");
        if(fragment!=null) {
            fragment.setIncome(income);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelIncome() {
        fragmentManager.popBackStack();
    }
}