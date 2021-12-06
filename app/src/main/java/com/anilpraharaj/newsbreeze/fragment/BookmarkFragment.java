package com.anilpraharaj.newsbreeze.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anilpraharaj.newsbreeze.activity.MainActivity;
import com.anilpraharaj.newsbreeze.adapter.BookmarkAdapter;
import com.anilpraharaj.newsbreeze.baseClass.BaseActivity;
import com.anilpraharaj.newsbreeze.baseClass.BaseFragment;
import com.anilpraharaj.newsbreeze.callback.AdapterItemOnClickCb;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.entity.BookmarkArticle;
import com.anilpraharaj.newsbreeze.viewModel.ArticleViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * @author anilpraharaj on 05/12/21
 */
public class BookmarkFragment extends BaseFragment implements AdapterItemOnClickCb {

    private ArticleViewModel articleViewModel;

    private RecyclerView bookmarkRecyclerView;
    private TextView noDataFound;
    private MaterialButton backButton;

    private BookmarkAdapter bookmarkAdapter;

    @Override
    protected void init(View view) {

        bookmarkRecyclerView = view.findViewById(Constant.BOOKMARK_LIST_ID);
        noDataFound = view.findViewById(Constant.NO_DATA_FOUND);
        backButton = view.findViewById(Constant.BOOKMARK_BACK_BUTTON_ID);

        bookmarkAdapter = new BookmarkAdapter();

        articleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(((MainActivity) getActivity()).getApplication()).create(ArticleViewModel.class);

        bookmarkRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookmarkRecyclerView.setAdapter(bookmarkAdapter);

    }

    @Override
    protected void addListeners() {

        articleViewModel.getBookmarks().observe(this, bookmarkArticles -> {
            if (bookmarkArticles != null && bookmarkArticles.size() > 0) {
                bookmarkAdapter.setmBookmarkArrayList((ArrayList<BookmarkArticle>) bookmarkArticles, this::onClickListener);
                noDataFound.setVisibility(View.GONE);
            } else {
                noDataFound.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).onBackPressed();
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
        return Constant.BOOKMARK_FRAGMENT_LAYOUT_ID;
    }

    @Override
    protected boolean onBackPressed() {
        return true;
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
