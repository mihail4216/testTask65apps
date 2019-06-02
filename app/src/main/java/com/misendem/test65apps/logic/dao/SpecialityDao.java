package com.misendem.test65apps.logic.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.misendem.test65apps.models.entity.SpecialityEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SpecialityDao {


    @Query("select * from specialityentity")
    Flowable<List<SpecialityEntity>> getAllSpecialitys();

    @Query("SELECT * from SpecialityEntity where id = :specialityId")
    Single<SpecialityEntity> getSpecialityById(Integer specialityId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(SpecialityEntity entity);

    @Delete
    int delete(SpecialityEntity entity);

}
