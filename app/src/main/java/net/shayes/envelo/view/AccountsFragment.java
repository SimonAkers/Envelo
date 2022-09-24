package net.shayes.envelo.view;

import android.content.Intent;
import android.view.View;

public class AccountsFragment extends CategoryFragment {
    @Override
    public void onClickFab(View view) {
        Intent intent = new Intent(getContext(), NewCategoryActivity.class);
        startActivity(intent);
    }
}
