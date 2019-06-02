package com.misendem.test65apps.ui.fragments.list_emploees;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.EmployeeEntity;
import com.misendem.test65apps.models.entity.SpecialityEntity;
import com.misendem.test65apps.ui.MainActivity;
import com.misendem.test65apps.ui.fragments.emploee_info.FragmentEmployeeInfo;
import com.misendem.test65apps.ui.fragments.list_emploees.adapter.AdapterEmployee;

import java.util.Objects;

public class FragmentListEmployees extends MvpAppCompatFragment implements ViewEmployeesList, AdapterEmployee.ListenerEmployeesList {

    private static final String EXTRA_SPECIALITY = "EXTRA_SPECIALITY";

    public static FragmentListEmployees newInstance(SpecialityEntity speciality) {
        FragmentListEmployees fragment = new FragmentListEmployees();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SPECIALITY, speciality.id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    PresenterEmployeesList mPresenter;

    RecyclerView recyclerViewEmployees;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees_list, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        recyclerViewEmployees = view.findViewById(R.id.listEmployee);
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.VERTICAL, false));
        recyclerViewEmployees.setAdapter(new AdapterEmployee(this));
        if (getArguments() != null)
            mPresenter.loadEmployeesFromDbBySpeciality(getArguments().getInt(EXTRA_SPECIALITY, 0));
    }

    @Override
    public void addEmployee(EmployeeEntity employeeEntity) {
        ((AdapterEmployee) Objects.requireNonNull(recyclerViewEmployees.getAdapter())).addEmployee(employeeEntity);
    }

    @Override
    public void showInfoEmployee(EmployeeEntity employee) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, FragmentEmployeeInfo.newInstance(employee))
                .addToBackStack(MainActivity.BACK_STACK)
                .commit();
    }

    @Override
    public void onClickEmployee(EmployeeEntity employee) {
        mPresenter.onClickEmployee(employee);
    }


}
