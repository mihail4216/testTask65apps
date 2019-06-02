package com.misendem.test65apps.ui.fragments.list_speciality;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.misendem.test65apps.R;
import com.misendem.test65apps.models.entity.SpecialityEntity;
import com.misendem.test65apps.ui.MainActivity;
import com.misendem.test65apps.ui.fragments.list_emploees.FragmentListEmployees;
import com.misendem.test65apps.ui.fragments.list_speciality.adapter.AdapterSpecyality;

import java.util.List;
import java.util.Objects;

public class FragmentListSpecialitys extends MvpAppCompatFragment implements ViewSpecialitysList, AdapterSpecyality.SpecialityListListener {

    public static FragmentListSpecialitys newInstance() {
        return new FragmentListSpecialitys();
    }

    @InjectPresenter
    PresenterSpecialitysList mPresenter;

    RecyclerView mSpecialitysList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialitys_list, container, false);

        initUi(view);
        return view;
    }

    private void initUi(View view) {
        mSpecialitysList = view.findViewById(R.id.listSpecialityEmployee);
        mSpecialitysList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayout.VERTICAL, false));
        mSpecialitysList.setAdapter(new AdapterSpecyality(this));
    }


    @Override
    public void addSpecialitys(List<SpecialityEntity> data) {
        ((AdapterSpecyality) Objects.requireNonNull(mSpecialitysList.getAdapter())).addAllSpeciality(data);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(getContext(), "Error, check self network", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showListEmployees(SpecialityEntity speciality) {
//        FragmentListEmployees.newInstance(speciality);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, FragmentListEmployees.newInstance(speciality))
                .addToBackStack(MainActivity.BACK_STACK)
                .commit();
    }

    @Override
    public void addSpeciality(SpecialityEntity speciality) {
        ((AdapterSpecyality) Objects.requireNonNull(mSpecialitysList.getAdapter())).addSpeciality(speciality);
    }

    @Override
    public void onClickSpeciality(SpecialityEntity speciality) {
        mPresenter.onClickSpeciality(speciality);
    }
}
