package com.misendem.test65apps.logic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.misendem.test65apps.models.entity.EmployeeEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface EmployeeDao {

    //    @Transaction
    @Query("SELECT * FROM employeeentity")
    Flowable<List<EmployeeEntity>> getAllEmployees();



    @Query("SELECT * from employeeentity where id = :employeeId")
    Single<EmployeeEntity> getEmployeeById(Integer employeeId);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(EmployeeEntity employee);

    @Delete
    int delete(EmployeeEntity employeeEntity);


}
