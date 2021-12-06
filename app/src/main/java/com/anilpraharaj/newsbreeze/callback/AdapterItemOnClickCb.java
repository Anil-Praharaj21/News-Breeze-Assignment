package com.anilpraharaj.newsbreeze.callback;

import com.anilpraharaj.newsbreeze.entity.Article;

/**
 * @author anilpraharaj on 05/12/21
 */
public interface AdapterItemOnClickCb {

    void onClickListener(Article article); // Provide Article Data on Click of Article Item from Recycler View
}
