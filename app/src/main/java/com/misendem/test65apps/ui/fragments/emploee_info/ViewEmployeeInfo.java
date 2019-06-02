package com.misendem.test65apps.ui.fragments.emploee_info;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.misendem.test65apps.models.entity.SpecialityEntity;

@StateStrategyType(OneExecutionStateStrategy.class)
interface ViewEmployeeInfo extends MvpView {
    void addSpeciality(SpecialityEntity entity);
    void setLastName(String name);
    void setFirstName(String name);

    void setYearsEmployee(String yearsEmployee);

    void setDateBirthdayEmployee(String formatDate);
}
