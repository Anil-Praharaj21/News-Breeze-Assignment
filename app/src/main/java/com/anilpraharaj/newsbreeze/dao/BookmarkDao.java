package com.anilpraharaj.newsbreeze.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;

import java.util.List;

/**
 * @author anilpraharaj on 05/12/21
 */
@Dao
public interface BookmarkDao {

    @Query("SELECT * FROM bookmark ORDER BY uid DESC")
    LiveData<List<BookmarkArticle>> getAllBookmark();

    @Query("SELECT * FROM bookmark WHERE title LIKE :title")
    BookmarkArticle findByName(String title);

    @Insert
    void insert(BookmarkArticle... article);

    @Insert
    void insertAll(List<BookmarkArticle> articles);

    @Delete
    void delete(BookmarkArticle article);

    @Query("DELETE FROM bookmark")
    void deleteAll();

    @Update
    void update(BookmarkArticle article);
}
