package com.misendem.test65apps.ui.fragments.emploee_info;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.EmployeeEntity;
import com.misendem.test65apps.models.entity.SpecialityEntity;
import com.misendem.test65apps.ui.fragments.list_speciality.adapter.AdapterSpecyality;

import java.util.Objects;


public class FragmentEmployeeInfo extends MvpAppCompatFragment implements ViewEmployeeInfo, AdapterSpecyality.SpecialityListListener {


    public static String EXTRA_EMPLOYEE = "EXTRA_EMPLOYEE";


    public static FragmentEmployeeInfo newInstance(EmployeeEntity employee) {
        FragmentEmployeeInfo fragment = new FragmentEmployeeInfo();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_EMPLOYEE, employee);
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    public PresenterEmployeeInfo mPresentor;

    private RecyclerView mSpecialitysList;
    private TextView mLastName;
    private TextView mFirstName;
    private TextView mBirthday;
    private TextView mYears;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_info, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        mFirstName = view.findViewById(R.id.firstNameEmployee);
        mLastName = view.findViewById(R.id.lastNameEmployee);
        mBirthday = view.findViewById(R.id.dateBirthday);
        mYears = view.findViewById(R.id.years);
        mLastName = view.findViewById(R.id.lastNameEmployee);
        mSpecialitysList = view.findViewById(R.id.listSpecialityEmployee);
        view.findViewById(R.id.containerInfo).setOnClickListener(v -> {
        });
        mSpecialitysList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.VERTICAL, false));
        mSpecialitysList.setAdapter(new AdapterSpecyality(this));
        if (getArguments() != null) {
            EmployeeEntity employee = (EmployeeEntity) getArguments().getSerializable(EXTRA_EMPLOYEE);
            mPresentor.initEmployee(employee);
        }

    }


    @Override
    public void addSpeciality(SpecialityEntity entity) {
        ((AdapterSpecyality) Objects.requireNonNull(mSpecialitysList.getAdapter())).addSpeciality(entity);
    }

    @Override
    public void setLastName(String name) {
        mLastName.setText(name);
    }

    @Override
    public void setFirstName(String name) {
        mFirstName.setText(name);
    }

    @Override
    public void setYearsEmployee(String yearsEmployee) {
        mYears.setText(yearsEmployee);
    }

    @Override
    public void setDateBirthdayEmployee(String formatDate) {
        mBirthday.setText(formatDate);
    }


    @Override
    public void onClickSpeciality(SpecialityEntity speciality) {
    }
}
