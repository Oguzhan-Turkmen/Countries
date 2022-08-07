package com.oguzhanturkmen.countries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oguzhanturkmen.countries.model.Countries.Country

@Database(
    entities =[Country::class],
    version = 1
)
abstract class countriesDatabase:RoomDatabase() {
    abstract fun getCountriesDao():countriesDao
    companion object{

        @Volatile
        private var instance : countriesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance= it}
        }
        fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                countriesDatabase::class.java,
                "movie_db.db"
            ).build()

    }

}