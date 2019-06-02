package com.misendem.test65apps.ui.fragments.list_speciality;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.misendem.test65apps.App;
import com.misendem.test65apps.logic.dao.EmployeeDao;
import com.misendem.test65apps.logic.dao.RelationDao;
import com.misendem.test65apps.logic.dao.SpecialityDao;
import com.misendem.test65apps.logic.network.ApiManager;
import com.misendem.test65apps.models.Employee;
import com.misendem.test65apps.models.entity.EmployeeSpecialityEntity;
import com.misendem.test65apps.models.entity.SpecialityEntity;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterSpecialitysList extends MvpPresenter<ViewSpecialitysList> {

    private CompositeDisposable disposable = new CompositeDisposable();

    private EmployeeDao employeeDao = App.getInstance().database.employeeDao();
    private SpecialityDao specialityDao = App.getInstance().database.specialityDao();
    private RelationDao relationDao = App.getInstance().database.relationDao();

    private ApiManager apiManager = App.getInstance().apiManager;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
//        инитим данные в ui
        initUi();
    }

    private void initUi() {
        //загрузка всех специальностей из дб , если нет в дб ничего, тогда из сети загружаем
        disposable.add(specialityDao.getAllSpecialitys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dataSpeciality -> {
                            if (dataSpeciality.isEmpty()) {
                                loadingDataEmployees();
                            } else {
                                getViewState().addSpecialitys(dataSpeciality);
                            }
                        },
                        Throwable::printStackTrace
                )
        );
    }

    private void loadingDataEmployees() {
//        загрузка из сети данных
        disposable.add(apiManager.loadAllEmployees()
                .flatMap(response -> Flowable.just(response.employees))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loadedDataEmployees -> {
                            if (!loadedDataEmployees.isEmpty()) {
                                saveDataInDB(loadedDataEmployees);
                            } else {
//                                показываем ошибку
                                getViewState().showErrorLoading();

                            }
                        },
                        Throwable::printStackTrace
                )
        );

    }

    private void saveDataInDB(ArrayList<Employee> loadedDataEmployees) {
//        сохранияем данные работников в бд
        for (Employee employee : loadedDataEmployees) {
            employee.id = loadedDataEmployees.indexOf(employee) + 1;
            saveEmployees(employee);
            saveSpecialitys(employee);
        }

    }

    private void saveSpecialitys(Employee employee) {
//        сохраняем специальность
        if (employee.specialityEntities != null)
            for (SpecialityEntity speciality : employee.specialityEntities) {
                disposable.add(Flowable.fromCallable(() ->
                        specialityDao.insert(speciality))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                        }));
                saveRelation(employee.id, speciality.id);
            }
    }

    private void saveRelation(int employeeId, int specialityId) {
//        сохраняем зависимость между работником и спецальностью
        Flowable.fromCallable(() -> relationDao.insert(new EmployeeSpecialityEntity(employeeId, specialityId)))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void saveEmployees(Employee employee) {
//        сохраняем работника
        disposable.add(employeeDao.getEmployeeById(employee.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        employeeEntity -> {
                        },
                        throwable -> Flowable.fromCallable(() -> employeeDao.insert(Employee.parceEmployeeToEntity(employee)))
                                .subscribeOn(Schedulers.io())
                                .subscribe()
                ));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    void onClickSpeciality(SpecialityEntity speciality) {
        getViewState().showListEmployees(speciality);
    }
}
