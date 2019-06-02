package com.misendem.test65apps.ui.fragments.list_emploees;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.misendem.test65apps.App;
import com.misendem.test65apps.logic.dao.EmployeeDao;
import com.misendem.test65apps.logic.dao.RelationDao;
import com.misendem.test65apps.models.entity.EmployeeEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterEmployeesList extends MvpPresenter<ViewEmployeesList> {


    private CompositeDisposable disposable = new CompositeDisposable();

    private EmployeeDao employeeDao = App.getInstance().database.employeeDao();
    private RelationDao relationDao = App.getInstance().database.relationDao();


    void loadEmployeesFromDbBySpeciality(int idSpeciality) {
//      загрузка из бд айдишники работников
        disposable.add(relationDao.getEmployeesBySpeciality(idSpeciality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listEmployeesId -> {
//                            из списка айдишников загружаем каждую специальность
                            for (int idEmployee : listEmployeesId) {
                                loadEmployeesFromDbById(idEmployee);
                            }
                        }
                        , Throwable::printStackTrace)
        );
    }

    private void loadEmployeesFromDbById(int idEmployee) {
//        загружаем работника  из бд по айди
        disposable.add(employeeDao.getEmployeeById(idEmployee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employeeEntity -> {
                            getViewState().addEmployee(employeeEntity);
                        }
                        , Throwable::printStackTrace)
        );

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose();
    }

    private void dispose() {
        disposable.dispose();
    }

    public void onClickEmployee(EmployeeEntity employee) {
        getViewState().showInfoEmployee(employee);
    }
}
