package com.anilpraharaj.newsbreeze.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.anilpraharaj.newsbreeze.dao.ArticleDao;
import com.anilpraharaj.newsbreeze.dao.BookmarkDao;
import com.anilpraharaj.newsbreeze.database.AppDatabase;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.ArticleResponse;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;
import com.anilpraharaj.newsbreeze.remoteDataSource.ArticleDataSource;
import com.anilpraharaj.newsbreeze.utils.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author anilpraharaj on 05/12/21
 */
public class NewsBreezeRepository {

    private ArticleDao mArticleDao;
    private BookmarkDao mBookmarkDao;

    private LiveData<List<Article>> mAllArticle;
    private LiveData<List<BookmarkArticle>> mAllBookmark;

    private ArticleDataSource articleDataSource;
    private LiveData<String> errorData;

    public NewsBreezeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mArticleDao = db.articleDao();
        mBookmarkDao = db.bookmarkDao();
        mAllArticle = mArticleDao.getAll();
        mAllBookmark = mBookmarkDao.getAllBookmark();

        articleDataSource = new ArticleDataSource();
    }

    public LiveData<List<Article>> getAllArticle() {
        return mAllArticle;
    }

    public LiveData<List<BookmarkArticle>> getAllBookmark() {
        return mAllBookmark;
    }

    public void insertAll(List<Article> articles, boolean deletePrevious) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (deletePrevious) {
                mArticleDao.deleteAll();
            }
            mArticleDao.insertAll(articles);
        });
    }

    public void getAllArticleData(String country, String pageSize, String page, boolean isOnline) {
        if (isOnline) {
            articleDataSource.getArticles(country, pageSize, page, new Callback<ArticleResponse>() {
                @Override
                public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                    if (response.body() != null && response.body().getArticles() != null) {
                        insertAll(response.body().getArticles(), page.equals("0"));
                    }
                }

                @Override
                public void onFailure(Call<ArticleResponse> call, Throwable t) {
                }
            });
        }
    }

    public void addRemoveBookmark(Article article) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (mArticleDao.getArticleByText(article.getTitle()) != null) {
                mArticleDao.update(mArticleDao.getArticleByText(article.getTitle()));
            }

            if (article.isBookmarked()) {
                BookmarkArticle bookmarkArticle = new BookmarkArticle(article.getAuthor(), article.getSource(), article.getTitle(), article.getDescription(), article.getUrl(), article.getUrlToImage(), article.getPublishedAt(), article.getContent(), 0);
                mBookmarkDao.insert(bookmarkArticle);
            } else {
                if (mBookmarkDao.findByName(article.getTitle()) != null) {
                    mBookmarkDao.delete(mBookmarkDao.findByName(article.getTitle()));
                }
            }
        });
    }

    public void getArticleByTitle(String search) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mArticleDao.getArticleByText(search);
        });
    }

}
