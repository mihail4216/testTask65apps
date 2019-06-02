package com.misendem.test65apps.ui.fragments.list_emploees.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.EmployeeEntity;

import static com.misendem.test65apps.models.entity.EmployeeEntity.getYearsEmployee;

public class ViewHolderEmployee extends RecyclerView.ViewHolder {
    public ViewHolderEmployee(@NonNull View itemView) {
        super(itemView);
        mFirstName = itemView.findViewById(R.id.firstNameEmployee);
        mYears = itemView.findViewById(R.id.yearsEmployee);
        mLastName = itemView.findViewById(R.id.lastNameEmployee);
    }

    private TextView mFirstName;
    private TextView mLastName;
    private TextView mYears;


    public void onBind(EmployeeEntity employee) {

        mFirstName.setText(EmployeeEntity.formatName(employee.firstName));
        mLastName.setText(EmployeeEntity.formatName(employee.lastName));
        mYears.setText(getYearsEmployee(employee.birthday));

    }


}
