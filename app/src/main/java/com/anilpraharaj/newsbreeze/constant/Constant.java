package com.anilpraharaj.newsbreeze.constant;

import com.anilpraharaj.newsbreeze.R;

/**
 * @author anilpraharaj on 05/12/21
 */
public interface Constant {

    // ID Constant
    int BASE_CONTAINER_ID = R.id.frame_layout_container;
    int ARTICLE_LIST_ID = R.id.article_list;
    int NO_DATA_FOUND = R.id.no_data_found;
    int SEARCH_EDIT_TEXT_ID = R.id.search_edit_text;
    int BOOKMARK_BUTTON_ID = R.id.bookmark_button;
    int BOOKMARK_LIST_ID = R.id.bookmark_list;
    int BOOKMARK_BACK_BUTTON_ID = R.id.bookmark_back_button;
    int ARTICLE_DETAILS_IMAGE_ID = R.id.article_details_image;
    int ARTICLE_DETAILS_BACK_BUTTON_ID = R.id.article_details_back_button;
    int ARTICLE_DETAILS_BOOKMARK_ID = R.id.article_details_bookmark;
    int ARTICLE_DETAILS_PUBLISH_DATE_ID = R.id.article_publish_date;
    int ARTICLE_DETAILS_TITLE_ID = R.id.article_details_title;
    int ARTICLE_DETAILS_AUTHOR_IMAGE_ID = R.id.article_details_author_image;
    int ARTICLE_DETAILS_AUTHOR_NAME_ID = R.id.article_author_name;
    int ARTICLE_DETAILS_AUTHOR_SOURCE_ID = R.id.article_details_source;
    int ARTICLE_DETAILS_SAVE_BUTTON_ID = R.id.article_details_save_button;
    int ARTICLE_DETAILS_DESCRIPTION_ID = R.id.article_details_description;
    int ARTICLE_LIST_IMAGE_ID = R.id.article_image;
    int ARTICLE_LIST_TITLE_ID = R.id.article_title;
    int ARTICLE_LIST_DESCRIPTION_ID = R.id.article_description;
    int ARTICLE_LIST_DATE_ID = R.id.article_date;
    int ARTICLE_LIST_CHECK_BOX_ID = R.id.bookmark_check_box;
    int ARTICLE_LIST_READ_BUTTON_ID = R.id.article_read_button;
    int ARTICLE_LIST_SAVE_BUTTON = R.id.article_save_button;
    int BOOKMARK_ITEM_IMAGE = R.id.bookmark_image;
    int BOOKMARK_ITEM_TITLE = R.id.bookmark_title;
    int BOOKMARK_ITEM_DESCRIPTION = R.id.bookmark_description;
    int BOOKMARK_ITEM_DATE = R.id.bookmark_date;

    // Layout Constant
    int MAIN_ACTIVITY_LAYOUT_ID = R.layout.activity_main;
    int DASHBOARD_FRAGMENT_LAYOUT_ID = R.layout.fragment_dashboard;
    int BOOKMARK_FRAGMENT_LAYOUT_ID = R.layout.fragment_bookmark;
    int ARTICLE_DETAILS_FRAGMENT_LAYOUT_ID = R.layout.fragment_article_details;
    int ARTICLE_LIST_ITEM_ID = R.layout.article_list_item;
    int BOOKMARK_LIST_ITEM_ID = R.layout.bookmark_list_item;

    // Integer Constant
    int PAGE_SIZE = 20;

    // String Constant
    String BASE_URL = "https://newsapi.org/";
    String COUNTRY_CODE = "in";
    String ARTICLE_BUNDLE_KEY = "article";
    String DATABASE_NAME = "news_article_database";
    String NA = "NA";
    String API_KEY = "apiKey";
    String API_KEY_VALUE = "1670eb439dee4c58820183f5af84e7b8";

}
