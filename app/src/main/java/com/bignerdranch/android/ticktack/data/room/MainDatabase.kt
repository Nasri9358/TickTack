package com.bignerdranch.android.ticktack.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bignerdranch.android.ticktack.data.room.dao.TaskDao
import com.bignerdranch.android.ticktack.data.room.dao.TaskGroupDao
import com.bignerdranch.android.ticktack.data.room.entity.TaskEntity
import com.bignerdranch.android.ticktack.data.room.entity.TaskGroupEntity


val MIGRATION_1_2: Migration = object: Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE task_table ADD COLUMN completionDateInMillis BIGINT DEFAULT NULL")
    }
}

@Database(entities = [TaskEntity::class, TaskGroupEntity::class], version = 2)
abstract class MainDatabase: RoomDatabase() {
    abstract fun TaskDao(): TaskDao
    abstract fun TaskGroupDao(): TaskGroupDao

    companion object {
        private var db: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            if (db == null) {
                synchronized(MainDatabase::class) {
                    db = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "task_db"
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return db!!
        }
    }
}