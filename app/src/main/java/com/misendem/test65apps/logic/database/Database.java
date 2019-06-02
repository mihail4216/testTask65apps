package com.misendem.test65apps.logic.database;


import android.arch.persistence.room.RoomDatabase;

import com.misendem.test65apps.logic.dao.EmployeeDao;
import com.misendem.test65apps.logic.dao.RelationDao;
import com.misendem.test65apps.logic.dao.SpecialityDao;
import com.misendem.test65apps.models.entity.EmployeeEntity;
import com.misendem.test65apps.models.entity.EmployeeSpecialityEntity;
import com.misendem.test65apps.models.entity.SpecialityEntity;

@android.arch.persistence.room.Database(entities =
        {
                EmployeeEntity.class,
                SpecialityEntity.class,
                EmployeeSpecialityEntity.class
        }, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract EmployeeDao employeeDao();
    public abstract SpecialityDao specialityDao();
    public abstract RelationDao relationDao();
}
