package mk.templateApp.commonData.persistent

import androidx.room.Delete
import androidx.room.Insert

interface BaseDao<T> {

    @Insert
    fun insert(obj: T): Long

    @Insert
    fun insertAll(objs: MutableList<T>): List<Long>

    @Delete
    fun delete(obj: T)
}
