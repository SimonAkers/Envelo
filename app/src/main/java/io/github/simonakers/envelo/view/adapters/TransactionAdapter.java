package io.github.simonakers.envelo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.simonakers.envelo.R;
import io.github.simonakers.envelo.database.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> data;
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
            if (TransactionAdapter.this.itemClickListener != null) {
                itemClickListener.onClick(view, getLayoutPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onClick(View v, int i);
    }

    public TransactionAdapter(Context context, List<Transaction> data) {
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
                .inflate(R.layout.adapter_transaction, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        View view = holder.getView();
        Transaction transaction = data.get(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
