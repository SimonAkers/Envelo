package net.shayes.envelo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;

import net.shayes.envelo.R;
import net.shayes.envelo.database.Category;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, MenuProvider {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addMenuProvider(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar_top));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayShowTitleEnabled(false);

        NavigationBarView tbBottom = findViewById(R.id.toolbar_bottom);
        tbBottom.setOnItemSelectedListener(this);
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.top_bar_main, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return true;
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.tb_funds) {
            fragment = FundsFragment.newInstance();
        } else if (id == R.id.tb_accounts) {
            fragment = CategoryFragment.newInstance(Category.TYPE_ACCOUNT);
        } else if (id == R.id.tb_envelopes) {
            fragment = CategoryFragment.newInstance(Category.TYPE_ENVELOPE);
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment, null)
                .setReorderingAllowed(true)
                .commit();
        }

        return true;
    }
}