package com.morningnightdream.clone_twitter.ui.search

sealed class SearchState {
    object Loading: SearchState()
    class Success(val searchData: List<SearchTwitter>): SearchState()
}