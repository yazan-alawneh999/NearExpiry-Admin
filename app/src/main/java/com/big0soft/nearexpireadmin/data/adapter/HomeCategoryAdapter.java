package com.big0soft.nearexpireadmin.data.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.domain.interfaces.OnRVItemClickedListener;
import com.big0soft.nearexpireadmin.data.models.HomeCategoryModel;
import com.big0soft.nearexpireadmin.databinding.FragmentHomeServiceViewHolderBinding;
import com.big0soft.resource.adapter.DiffUtilItemCallbackImpl;
import com.big0soft.resource.adapter.ViewHolder;

public class HomeCategoryAdapter extends ListAdapter<HomeCategoryModel, HomeCategoryAdapter.HomeCategoryViewHolder> {
    static DiffUtil.ItemCallback<HomeCategoryModel> DIFF_CALLBACK = new DiffUtilItemCallbackImpl<>();
    private OnRVItemClickedListener<HomeCategoryModel> onRVItemClickedListener;

    public HomeCategoryAdapter(OnRVItemClickedListener<HomeCategoryModel> onRVItemClickedListener) {
        super(DIFF_CALLBACK);
        this.onRVItemClickedListener = onRVItemClickedListener;
    }

    public HomeCategoryAdapter() {
        super(DIFF_CALLBACK);
        this.onRVItemClickedListener = null;
    }


    @NonNull
    @Override
    public HomeCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_home_service_view_holder, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull HomeCategoryViewHolder holder, int position) {
        if (position == RecyclerView.NO_POSITION) {
            return;
        }
        holder.bindView(getItem(position));
    }

    public void setOnItemClickListener(OnRVItemClickedListener<HomeCategoryModel> onRVItemClickedListener) {
        this.onRVItemClickedListener = onRVItemClickedListener;
    }

    public class HomeCategoryViewHolder extends ViewHolder<HomeCategoryModel> {
        private final FragmentHomeServiceViewHolderBinding binding;

        public HomeCategoryViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            binding = (FragmentHomeServiceViewHolderBinding) viewDataBinding;
        }

        @Override
        public void bindView(HomeCategoryModel model) {
            binding.setModel(model);
            binding.getRoot().setOnClickListener(v -> {
                if (onRVItemClickedListener == null) return;
                onRVItemClickedListener.onItemClicked(model);

            });

        }

    }
}