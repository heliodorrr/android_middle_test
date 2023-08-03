package com.youarelaunched.challenge.ui.screen.view

sealed class SearchQueryAction(
    val query: String
) {

    /**
     * Indicates that search query has changed but user didn't force search with it
     */
    class Auto(query: String): SearchQueryAction(query)

    /**
     * Indicates that search query has changed and user wants to use it right now
     */
    class Forced(query: String): SearchQueryAction(query)
}