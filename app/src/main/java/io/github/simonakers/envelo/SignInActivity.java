package io.github.simonakers.envelo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;

public class SignInActivity extends AppCompatActivity {
    private boolean splashOn = true;

    /** A callback for when the user finishes signing in */
    private final ActivityResultCallback<ActivityResult> onActivityResult = result -> {
        if (result.getResultCode() == RESULT_OK) {
            // If not logged in somehow, say that login failed but proceed to next activity anyways
            if (!isLoggedIn()) showLoginFailed();

            showMain();
        } else {
            showLoginFailed();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showSplashScreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        // Register a callback for when the user finishes sign in
        ActivityResultLauncher<Intent> signIn = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            onActivityResult
        );

        // Create the sign in options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
            .build();

        // Set the callback for the sign in button
        GoogleSignInClient gsc = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.btn_sign_in).setOnClickListener(v -> signIn.launch(gsc.getSignInIntent()));

        findViewById(R.id.btn_skip).setOnClickListener(v -> skipLogin());
    }

    @Override
    protected void onStart() {
        super.onStart();

        // If already logged in, skip to main menu
        if (isLoggedIn()) showMain();
    }

    /**
     * Shows the splash screen for the configured amount of time.
     */
    private void showSplashScreen() {
        SplashScreen splash = SplashScreen.installSplashScreen(this);

        // Set splashOn as the condition to keep the splash screen showing
        splash.setKeepOnScreenCondition(() -> splashOn);

        // Get the delay for the splash screen (how long it's shown)
        int splashDuration = getResources().getInteger(R.integer.splash_duration);

        // Change splashOn to false after a delay
        Handler h = new Handler();
        h.postDelayed(() -> splashOn = false, splashDuration);
    }

    /**
     * Checks if the user is already logged in.
     *
     * @return true if the user is already logged in, false if not
     */
    private boolean isLoggedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    /**
     * Displays a toast to the user saying that something went wrong during sign in.
     */
    private void showLoginFailed() {
        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_LONG).show();
    }

    private void skipLogin() {
        // TODO: Save that the user skipped as a preference so they don't have to skip every time
        new AlertDialog.Builder(this)
            .setMessage(R.string.login_skip_msg)
            .setTitle(R.string.login_skip_title)
                .setIcon(R.drawable.ic_cloud_off)
            .setPositiveButton(R.string.yes, (di, i) -> showMain())
            .setNegativeButton(R.string.no, null)
            .show();
    }

    /**
     * Shows the main activity and closes this activity.
     */
    private void showMain() {
        // Start main activity and clear activity backstack
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}