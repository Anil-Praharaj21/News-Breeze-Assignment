package com.anilpraharaj.newsbreeze.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.anilpraharaj.newsbreeze.entity.Article;

import java.util.List;

/**
 * @author anilpraharaj on 05/12/21
 */
@Dao
public interface ArticleDao {

    @Query("SELECT * FROM Article")
    LiveData<List<Article>> getAll();

    @Query("SELECT * FROM Article WHERE title LIKE :search")
    Article getArticleByText(String search);

    @Insert
    void insert(Article article);

    @Insert
    void insertAll(List<Article> articles);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM Article")
    void deleteAll();

    @Update
    void update(Article article);
}
