package com.anilpraharaj.newsbreeze.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.anilpraharaj.newsbreeze.R;
import com.anilpraharaj.newsbreeze.activity.MainActivity;
import com.anilpraharaj.newsbreeze.baseClass.BaseFragment;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.entity.Article;
import com.anilpraharaj.newsbreeze.utils.DateFormatter;
import com.anilpraharaj.newsbreeze.viewModel.ArticleViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

/**
 * @author anilpraharaj on 05/12/21
 */
public class ArticleDetailsFragment extends BaseFragment {

    private ImageView articleImageView, authorImageView;
    private MaterialButton backButton, saveButton;
    private CheckBox bookmarkButton;
    private TextView titleTextView, publishDateTextView, authorNameTextView, authorSourceTextView, descriptionTextView;

    private ArticleViewModel articleViewModel;

    @Override
    protected void init(View view) {

        articleImageView = view.findViewById(Constant.ARTICLE_DETAILS_IMAGE_ID);
        authorImageView = view.findViewById(Constant.ARTICLE_DETAILS_AUTHOR_IMAGE_ID);

        backButton = view.findViewById(Constant.ARTICLE_DETAILS_BACK_BUTTON_ID);
        saveButton = view.findViewById(Constant.ARTICLE_DETAILS_SAVE_BUTTON_ID);

        bookmarkButton = view.findViewById(Constant.ARTICLE_DETAILS_BOOKMARK_ID);

        titleTextView = view.findViewById(Constant.ARTICLE_DETAILS_TITLE_ID);
        publishDateTextView = view.findViewById(Constant.ARTICLE_DETAILS_PUBLISH_DATE_ID);
        authorNameTextView = view.findViewById(Constant.ARTICLE_DETAILS_AUTHOR_NAME_ID);
        authorSourceTextView = view.findViewById(Constant.ARTICLE_DETAILS_AUTHOR_SOURCE_ID);
        descriptionTextView = view.findViewById(Constant.ARTICLE_DETAILS_DESCRIPTION_ID);

        articleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(((MainActivity) getActivity()).getApplication()).create(ArticleViewModel.class);
    }

    @Override
    protected void addListeners() {

        if (getArguments() != null) {
            Article article = getArguments().getParcelable(Constant.ARTICLE_BUNDLE_KEY);
            if (article != null) {
                Glide.with(this)
                        .load(article.getUrlToImage())
                        .placeholder(R.drawable.image_placeholder)
                        .centerCrop()
                        .into(articleImageView);
                publishDateTextView.setText(new DateFormatter(article.getPublishedAt(), DateFormatter.DEFAULT_FORMAT).format(DateFormatter.STANDARD_DATE_FORMAT));
                titleTextView.setText(article.getTitle());
                authorNameTextView.setText(article.getAuthor() != null ? article.getAuthor() : Constant.NA);
                authorSourceTextView.setText(article.getSource().name != null ? article.getSource().name : Constant.NA);
                descriptionTextView.setText(article.getDescription());
                bookmarkButton.setChecked(article.isBookmarked());

                bookmarkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        article.setBookmarked(!article.isBookmarked());
                        articleViewModel.addRemoveBookmark(article);
                    }
                });

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        article.setBookmarked(!article.isBookmarked());
                        articleViewModel.addRemoveBookmark(article);
                    }
                });
            }
        }

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
        return Constant.ARTICLE_DETAILS_FRAGMENT_LAYOUT_ID;
    }

    @Override
    protected boolean onBackPressed() {
        return true;
    }
}
