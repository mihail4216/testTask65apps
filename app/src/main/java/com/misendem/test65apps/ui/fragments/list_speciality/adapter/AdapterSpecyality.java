package com.misendem.test65apps.ui.fragments.list_speciality.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.SpecialityEntity;
import com.misendem.test65apps.ui.fragments.list_speciality.view.ViewHolderSpeciality;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpecyality extends RecyclerView.Adapter<ViewHolderSpeciality> {

    private ArrayList<SpecialityEntity> mListSpeciality = new ArrayList<>();
    private SpecialityListListener listListener;

    public AdapterSpecyality(SpecialityListListener listListener) {
        this.listListener = listListener;
    }

    @NonNull
    @Override
    public ViewHolderSpeciality onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderSpeciality(
                LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.view_item_speciality,
                        viewGroup,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSpeciality viewHolderSpeciality, int i) {
        viewHolderSpeciality.onBind(mListSpeciality.get(i));
        viewHolderSpeciality.itemView.setOnClickListener(
                v -> listListener.onClickSpeciality(mListSpeciality.get(i))
        );
    }

    @Override
    public int getItemCount() {
        return mListSpeciality.size();
    }

    public void addAllSpeciality(List<SpecialityEntity> specialitys) {
        mListSpeciality.clear();
        mListSpeciality.addAll(specialitys);
        notifyDataSetChanged();
    }

    public void addSpeciality(SpecialityEntity speciality) {
        mListSpeciality.add(speciality);
        notifyItemChanged(mListSpeciality.size() - 1);
    }

    public interface SpecialityListListener {
        void onClickSpeciality(SpecialityEntity speciality);
    }
}
