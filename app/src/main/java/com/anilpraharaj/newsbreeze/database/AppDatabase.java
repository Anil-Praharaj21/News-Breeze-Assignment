package com.anilpraharaj.newsbreeze.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.converter.SourceConverter;
import com.anilpraharaj.newsbreeze.dao.ArticleDao;
import com.anilpraharaj.newsbreeze.dao.BookmarkDao;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author anilpraharaj on 05/12/21
 */
@Database(entities = {Article.class, BookmarkArticle.class}, version = 1)
@TypeConverters({SourceConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();
    public abstract BookmarkDao bookmarkDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constant.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
