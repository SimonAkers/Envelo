package io.github.simonakers.envelo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

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
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.fragment_settings, rootKey);

            PreferenceCategory pCategory = findPreference("category_backup");

            if (pCategory != null) {
                Preference pref;
                Context ctx = getContext();

                if (ctx != null && GoogleSignIn.getLastSignedInAccount(ctx) != null) {
                    pref = pCategory.findPreference("sign_in");
                } else {
                    pCategory.findPreference("backup").setEnabled(false);
                    pref = pCategory.findPreference("sign_out");
                }

                if (pref != null) pCategory.removePreference(pref);
            }
        }

        @Override
        public boolean onPreferenceTreeClick (Preference preference) {
            String key = preference.getKey();

            if (key.equals("sign_in")) {
                Intent intent = new Intent(getContext(), SignInActivity.class);
                intent.putExtra("show_main", false);
                startActivity(intent);
            } else if (key.equals("sign_out")) {
                // sign out
            }

            return true;
        }
    }
}