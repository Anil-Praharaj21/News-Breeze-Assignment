package com.anilpraharaj.newsbreeze.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;
import com.anilpraharaj.newsbreeze.repository.NewsBreezeRepository;

import java.util.List;

/**
 * @author anilpraharaj on 5/12/21
 */
public class ArticleViewModel extends AndroidViewModel {

    private NewsBreezeRepository mRepository;

    private LiveData<List<Article>> mAllArticles;
    private LiveData<List<BookmarkArticle>> mBookmarkArticles;
    private LiveData<String> errorData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        mRepository = new NewsBreezeRepository(getApplication());
        mAllArticles = mRepository.getAllArticle();
        mBookmarkArticles = mRepository.getAllBookmark();
    }

    public LiveData<List<Article>> getArticles() {
        return mAllArticles;
    }

    public LiveData<List<BookmarkArticle>> getBookmarks() {
        return mBookmarkArticles;
    }

    public void getArticleData(String country, String pageSize, String page, boolean isOnline) {
        mRepository.getAllArticleData(country, pageSize, page, isOnline);
    }

    public void addRemoveBookmark(Article article) {
        mRepository.addRemoveBookmark(article);
    }

    public void getArticleByTitle(String title) {
        mRepository.getArticleByTitle(title);
    }

}
