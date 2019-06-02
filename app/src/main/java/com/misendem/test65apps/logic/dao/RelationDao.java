package com.misendem.test65apps.logic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.misendem.test65apps.models.entity.EmployeeSpecialityEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RelationDao {

    @Query("select * from employeespecialityentity")
    Flowable<List<EmployeeSpecialityEntity>> getAllRelation();

    @Query("SELECT employeeId FROM EmployeeSpecialityEntity WHERE specialityId = :idSpeciality")
    Flowable<List<Integer>> getEmployeesBySpeciality(Integer idSpeciality);

    @Query("SELECT specialityId FROM EmployeeSpecialityEntity WHERE employeeId = :employeeId")
    Flowable<List<Integer>> getSpecyalityByEmployee(Integer employeeId);

    @Insert
    long insert(EmployeeSpecialityEntity relation);

}
