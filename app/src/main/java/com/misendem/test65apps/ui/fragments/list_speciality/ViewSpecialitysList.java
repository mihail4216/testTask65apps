package com.misendem.test65apps.ui.fragments.list_speciality;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.misendem.test65apps.models.entity.SpecialityEntity;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
interface ViewSpecialitysList extends MvpView {

    void addSpecialitys(List<SpecialityEntity> data);

    void showErrorLoading();

    void showListEmployees(SpecialityEntity speciality);

    void addSpeciality(SpecialityEntity speciality);
}
