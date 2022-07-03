package net.shayes.envelo.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.shayes.envelo.R;
import net.shayes.envelo.controller.App;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment();

    }

    protected void showFragment() {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.settings_container, new SettingsFragment())
            .commit();
    }

    /**
     * Sets up the toolbar.
     */
    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return true;
    }

    /**
     * A class representing a fragment for user settings.
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {
        private App app;

        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.fragment_settings, rootKey);
            app = (App) requireActivity().getApplication();

            PreferenceCategory pCategory = findPreference("category_backup");

            if (pCategory != null) {
                Preference pref;
                Context ctx = getContext();

                if (ctx != null && GoogleSignIn.getLastSignedInAccount(ctx) != null) {
                    pref = pCategory.findPreference("sign_in");
                } else {
                    Preference backupPref = pCategory.findPreference("backup");
                    if (backupPref != null) backupPref.setEnabled(false);

                    pref = pCategory.findPreference("sign_out");
                }

                if (pref != null) pCategory.removePreference(pref);
            }
        }

        @Override
        public boolean onPreferenceTreeClick (Preference preference) {
            String key = preference.getKey();

            if (key.equals("sign_in")) {
                promptSignIn();
            } else if (key.equals("sign_out")) {
                promptSignOut();
            }

            return true;
        }

        private void promptSignIn() {
            Intent intent = new Intent(getContext(), SignInActivity.class);
            intent.putExtra("show_main", false);
            startActivity(intent);
        }

        private void promptSignOut() {
            new AlertDialog.Builder(requireContext())
                .setMessage(R.string.logout_msg)
                .setTitle(R.string.logout_title)
                .setIcon(R.drawable.ic_cloud_off)
                .setPositiveButton(R.string.yes, (di, i) -> signOut())
                .setNegativeButton(R.string.no, null)
                .show();
        }

        private void signOut() {
            GoogleSignInClient gsc = app.getSignInClient();

            gsc.signOut().addOnCompleteListener(task -> {
                // Disable backups
                app.prefs().edit()
                        .putBoolean("backup", false)
                        .apply();

                FragmentActivity activity = getActivity();

                if (activity instanceof SettingsActivity) {
                    ((SettingsActivity) activity).showFragment();
                }
            });
        }
    }
}