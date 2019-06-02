package com.misendem.test65apps.logic.repository;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.misendem.test65apps.App;
import com.misendem.test65apps.models.entity.EmployeeEntity;

import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private static CompositeDisposable disposable = new CompositeDisposable();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void getEmployees(Consumer<EmployeeEntity> consumer) {

        disposable.add(App.getInstance().database.employeeDao()
                .getAllEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        v -> {
                            consumer.accept(v.get(0));
                            test();
                            test();
                        }
                        ,
                        e -> test(),
                        Repository::test
                ));
    }

    private static void test() {

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void test1() {
        getEmployees(
                w->{
                   w.birthday.concat("dd");
                   test();
                }
        );
    }
}
