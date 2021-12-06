package com.anilpraharaj.newsbreeze.callback;

import com.anilpraharaj.newsbreeze.entity.Article;

/**
 * @author anilpraharaj on 6/12/21
 */
public interface BookmarkCb {

    void onClickBookmarkButton(Article article); // Callback for Bookmark button click
}
