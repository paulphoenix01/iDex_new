package phoenix.idex.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import phoenix.idex.R;
import phoenix.idex.SignUpActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment implements View.OnClickListener {
    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private Button bRegister;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private View v;

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayWelcomeMessage(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
    public LoginActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        // Track facebook users here
        AccessTokenTracker tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayWelcomeMessage(newProfile);
            }
        };


        tracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mTokenTracker != null) {
            mTokenTracker.stopTracking();
            mProfileTracker.stopTracking();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_login, container, false);
        bRegister = (Button) v.findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));

        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                Intent signUpIntent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(signUpIntent);
                break;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("basic_info");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager,mCallback);

        // Change title font
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "Starjhol.ttf" );
        TextView idexTitle = (TextView) view.findViewById(R.id.idexTitle);
        idexTitle.setTypeface(myTypeface);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    private void displayWelcomeMessage(Profile profile){
        if(profile != null){
            mTextDetails.setText("Welcome " + profile.getName());
        }
    }
}
