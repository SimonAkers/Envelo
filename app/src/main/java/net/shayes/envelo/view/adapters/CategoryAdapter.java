package net.shayes.envelo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import net.shayes.envelo.R;
import net.shayes.envelo.database.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> data;
    private ItemClickListener itemClickListener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            view = itemView;
        }

        public View getView() {
            return view;
        }

        @Override
        public void onClick(View view) {
            if (CategoryAdapter.this.itemClickListener != null) {
                itemClickListener.onClick(view, getLayoutPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onClick(View v, int i);
    }

    public CategoryAdapter(Context context, List<Category> data) {
        this.context = context;
        this.data = data;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        View view = holder.getView();
        Category category = data.get(i);

        TextView textCategory = view.findViewById(R.id.text_category);
        TextView textAmount = view.findViewById(R.id.text_amount);

        textCategory.setText(category.getName());
        textAmount.setText(String.valueOf(category.getAmount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
