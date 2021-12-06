package com.anilpraharaj.newsbreeze.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anilpraharaj.newsbreeze.activity.MainActivity;
import com.anilpraharaj.newsbreeze.adapter.ArticleListAdapter;
import com.anilpraharaj.newsbreeze.baseClass.BaseActivity;
import com.anilpraharaj.newsbreeze.baseClass.BaseFragment;
import com.anilpraharaj.newsbreeze.callback.AdapterItemOnClickCb;
import com.anilpraharaj.newsbreeze.callback.BookmarkCb;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;
import com.anilpraharaj.newsbreeze.utils.EndlessScrollRecyclerListener;
import com.anilpraharaj.newsbreeze.utils.Tools;
import com.anilpraharaj.newsbreeze.viewModel.ArticleViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

/**
 * @author anilpraharaj on 05/12/21
 */
public class DashboardFragment extends BaseFragment implements BookmarkCb, AdapterItemOnClickCb {

    private int pageSize = Constant.PAGE_SIZE;

    private String countryCode = Constant.COUNTRY_CODE;

    private ArticleViewModel articleViewModel;

    private RecyclerView articleRecyclerView;
    private TextView noDataFound;
    private MaterialButton bookmarkButton;
    private TextInputEditText searchEditText;

    private ArticleListAdapter articleAdapter;

    @Override
    protected void init(View view) {

        articleRecyclerView = view.findViewById(Constant.ARTICLE_LIST_ID);
        noDataFound = view.findViewById(Constant.NO_DATA_FOUND);
        bookmarkButton = view.findViewById(Constant.BOOKMARK_BUTTON_ID);
        searchEditText = view.findViewById(Constant.SEARCH_EDIT_TEXT_ID);

        articleAdapter = new ArticleListAdapter(this::onClickBookmarkButton, this::onClickListener);

        articleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(((MainActivity) getActivity()).getApplication()).create(ArticleViewModel.class);

        articleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleRecyclerView.setAdapter(articleAdapter);

        callArticleAPI(0);
    }

    @Override
    protected void addListeners() {

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseFragment fragment = new BookmarkFragment();
                ((BaseActivity) getActivity()).loadFragment(Constant.BASE_CONTAINER_ID, fragment, BookmarkFragment.class.getSimpleName());
            }
        });

        articleViewModel.getArticles().observe(this, articles -> {
            if (articles != null && articles.size() > 0) {
                articleAdapter.setmArticleArrayList((ArrayList<Article>) articles);
                noDataFound.setVisibility(View.GONE);
            } else {
                articleAdapter.notifyDataSetChanged();
                noDataFound.setVisibility(View.VISIBLE);
            }
        });

        articleViewModel.getBookmarks().observe(this, bookmarkArticles -> {
            if (bookmarkArticles != null && bookmarkArticles.size() > 0) {
                ArrayList<String> bookmarkTitles = new ArrayList<>();
                for (BookmarkArticle bookmark: bookmarkArticles) {
                    bookmarkTitles.add(bookmark.getTitle());
                }
                articleAdapter.setmBookmarkArticles(bookmarkTitles);
            } else {
                articleAdapter.setmBookmarkArticles(null);
            }
        });

        articleRecyclerView.setOnScrollListener(new EndlessScrollRecyclerListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                callArticleAPI(page);
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                articleViewModel.getArticleByTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onResumeCall() {

    }

    @Override
    protected int getContentView() {
        return Constant.DASHBOARD_FRAGMENT_LAYOUT_ID;
    }

    @Override
    protected boolean onBackPressed() {
        return true;
    }

    private void callArticleAPI(int page) {
        articleViewModel.getArticleData(countryCode, String.valueOf(pageSize), String.valueOf(page), Tools.isOnline(((MainActivity) getActivity())));
    }

    @Override
    public void onClickBookmarkButton(Article article) {
        articleViewModel.addRemoveBookmark(article);
    }

    @Override
    public void onClickListener(Article article) {
        BaseFragment fragment = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.ARTICLE_BUNDLE_KEY, article);
        fragment.setArguments(bundle);
        ((BaseActivity) getActivity()).loadFragment(Constant.BASE_CONTAINER_ID, fragment, ArticleDetailsFragment.class.getSimpleName());
    }
}
