package edu.it10.dangquangwatch.spring.entity.response;

import java.util.List;

public class SearchResponse<T> extends ApiResponse {
  List<T> searchResults;

  public SearchResponse(boolean result, String message) {
    super(result, message);
  }

  public SearchResponse(boolean result, String message, List<T> searchResults) {
    super(result, message);
    this.searchResults = searchResults;
  }

  public List<T> getSearchResults() {
    return searchResults;
  }

  public void setSearchResults(List<T> searchResults) {
    this.searchResults = searchResults;
  }
}
