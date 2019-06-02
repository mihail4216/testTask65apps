package com.misendem.test65apps.ui.fragments.list_speciality.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.SpecialityEntity;

public class ViewHolderSpeciality extends RecyclerView.ViewHolder {
    public ViewHolderSpeciality(@NonNull View itemView) {
        super(itemView);
        mSpecialityName = itemView.findViewById(R.id.nameSpeciality);
    }

    private TextView mSpecialityName;


    public void onBind(SpecialityEntity entity) {

        mSpecialityName.setText(entity.name);

    }
}
