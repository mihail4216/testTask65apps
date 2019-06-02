package com.misendem.test65apps.ui.fragments.list_emploees;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.misendem.test65apps.models.entity.EmployeeEntity;

@StateStrategyType(OneExecutionStateStrategy.class)
interface ViewEmployeesList extends MvpView {
    void addEmployee(EmployeeEntity employeeEntity);

    void showInfoEmployee(EmployeeEntity employee);
}
