package io.github.simonakers.envelo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.simonakers.envelo.R;
import io.github.simonakers.envelo.controller.App;
import io.github.simonakers.envelo.database.BudgetDatabase;
import io.github.simonakers.envelo.database.Category;
import io.github.simonakers.envelo.view.adapters.CategoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements Toolbar.OnMenuItemClickListener {
    private final View.OnClickListener onClickFab = v -> {
        //Intent intent = new Intent(getContext(), NewTransactionActivity.class);
        //startActivity(intent);
    };

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CategoryFragment.
     */
    public static CategoryFragment newInstance(int categoryType) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();

        args.putInt("categoryType", categoryType);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler_fab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        view.findViewById(R.id.fab).setOnClickListener(onClickFab);

        RecyclerView recycler = view.findViewById(R.id.recycler);

        BudgetDatabase budget = ((App) requireActivity().getApplication()).getBudget();
        int categoryType = requireArguments().getInt("categoryType");
        List<Category> items = budget.categoryDao().getAll(categoryType);

        CategoryAdapter adapter = new CategoryAdapter(getContext(), items);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_bar_search, menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}