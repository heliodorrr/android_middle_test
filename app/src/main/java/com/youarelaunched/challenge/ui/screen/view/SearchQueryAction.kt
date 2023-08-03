package com.youarelaunched.challenge.ui.screen.view

sealed class SearchQueryAction(
    val query: String
) {

    /**
     * Indicates that search query has changed but not forced to start search by user
     */
    class Auto(query: String): SearchQueryAction(query)

    /**
     * Indicates that search query has changed and user wants to use it
     */
    class Forced(query: String): SearchQueryAction(query)
}