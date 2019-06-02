package com.misendem.test65apps.ui.fragments.emploee_info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.misendem.test65apps.App;
import com.misendem.test65apps.logic.dao.RelationDao;
import com.misendem.test65apps.logic.dao.SpecialityDao;
import com.misendem.test65apps.models.entity.EmployeeEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterEmployeeInfo extends MvpPresenter<ViewEmployeeInfo> {

    private CompositeDisposable disposable = new CompositeDisposable();

    private SpecialityDao specialityDao = App.getInstance().database.specialityDao();
    private RelationDao relationDao = App.getInstance().database.relationDao();


    void initEmployee(EmployeeEntity employee) {
//        показываем данные о работнике
        getViewState().setLastName(EmployeeEntity.formatName(employee.lastName));
        getViewState().setFirstName(EmployeeEntity.formatName(employee.firstName));
        getViewState().setYearsEmployee(EmployeeEntity.getYearsEmployee(employee.birthday));
        getViewState().setDateBirthdayEmployee(EmployeeEntity.formatDate(employee.birthday));

//        загружаем специальности этого работника
        disposable.add(relationDao.getSpecyalityByEmployee(employee.id)
                .subscribe(
                        listIdSpeciality -> {
                            //  загружаем каждую специальнсть по ид
                            for (int idSpeciality : listIdSpeciality)
                                loadSpecialityFromDbById(idSpeciality);
                        },
                        Throwable::printStackTrace
                ));
    }


    private void loadSpecialityFromDbById(int idSpeciality) {
//        загрузка из бд специальности из бд
        disposable.add(specialityDao.getSpecialityById(idSpeciality)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((entity, throwable) -> {
                    getViewState().addSpeciality(entity);
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
