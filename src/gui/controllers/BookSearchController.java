package gui.controllers;

	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.List;

import gui.models.BookSearchModel;
import gui.views.BookSearchView;

	public class BookSearchController {
	    private BookSearchModel model;
	    private BookSearchView view;

	    public BookSearchController(BookSearchModel model, BookSearchView view) {
	        this.model = model;
	        this.view = view;
	        initView();
	    }

	    private void initView() {
	        view.addSearchButtonActionListener(e -> performSearch());
	        view.setVisible(true);
	    }

	    private void performSearch() {
	        String searchType = view.getSelectedSearchType();
	        String searchValue = view.getSearchValue().toLowerCase();
	        List<String[]> results = model.readAndFilterBooksFromCsv(searchType, searchValue);
	        view.updateSearchResultsTable(results);
	    }

	    
	}
