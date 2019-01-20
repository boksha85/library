$(function() {
	$("#include-header").load("../html/header.html");
	$("#table-of-books").load("../html/table-of-books.html");
});
function getBook(data) {
	let book = {
		"id" : data.id,
		"isbn" : data.isbn,
		"title" : data.title,
		"listOfAuthors" : data.listOfAuthors,
		"numberOfPages" : data.numberOfPages,
		"genre" : data.genre
	};
	return book;
}
function displayError(error) {
	$("#error-msg").text("ERROR: " + error);
};

function populateHtmlTable(data) {
	let trHTML;
	data.forEach(function(item) {
		let book = getBook(item);
		trHTML += '<tr id="row-book" class="books-table"><td>' + book.isbn
				+ '</td><td>' + book.title + '</td><td>' + book.listOfAuthors
				+ '</td><td>' + book.numberOfPages + '</td><td>' + book.genre
				+ '</td></tr>';
	});
	$('#books').append(trHTML);
}
function removeBooksFromTable() {
	while ($("#row-book").text()) {
		$("#row-book").remove();
	}
}

function areBookAtributesOk(isbn, title, listOfAuthors, numberOfPages, genre) {
	if (isbn === "") {
		displayError("ISBN must be populated");
		return false;
	}
	if (title === "") {
		displayError("Title must be populated");
		return false;
	}
	if (listOfAuthors === "") {
		displayError("List Of Authors must be populated");
		return false;
	}
	if (numberOfPages === "") {
		displayError("Number of pages must be populated");
		return false;
	}

	if ((!$.isNumeric(numberOfPages)) || numberOfPages <= 0) {
		displayError("Number of pages must be number0 bigger then 0");
		return false;
	}

	if (genre === "") {
		displayError("Genre must be populated");
		return false;
	}

	return true;

};

function createBookForServer(isbn, title, listOfAuthors, numberOfPages, genre) {
	let book = {
		"isbn" : isbn,
		"title" : title,
		"listOfAuthors" : listOfAuthors,
		"numberOfPages" : numberOfPages,
		"genre" : genre
	};
	return book;
};

function getLastFiveBooks() {
	$.get("/api/books/lastfive", function(data, status) {
		populateHtmlTable(data);
	});
}

function getBooks() {
	$.get("/api/books", function(data, status) {
		populateHtmlTable(data);
	});
}

function searchBook() {
	removeBooksFromTable();
	let title = $("#search-title").val();
	let api = "/api/books/get/" + title;
	$.get(api, function(data, status) {
		populateHtmlTable(data);
	});
}

function addBook(book) {
	$.ajax({
		url : "/api/books/add",
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify(book),
		dataType : 'json',
		success : function(response) {
			goToIndexPage();
		},
		error : function(error) {
			if (typeof (error) !== undefined
					&& typeof (error.responseText) !== undefined) {
				let message = jQuery.parseJSON(error.responseText).message;
				if (message)
					displayError(message);
				else
					displayError("Unknown error occured. Please check logs.");
			} else {
				displayError("Unknown error occured. Please check logs.");
			}
		}
	});

}

function insertBook() {
	displayError("");
	let isbn = $("#isbn").val();
	let title = $("#title").val();
	let listOfAuthors = $("#list-of-authors").val();
	let numberOfPages = $("#number-of-pages").val();
	let genre = $("#genre").val();
	if (areBookAtributesOk(isbn, title, listOfAuthors, numberOfPages, genre)) {
		let book = createBookForServer(isbn, title, listOfAuthors,
				numberOfPages, genre);
		addBook(book);
	}
};

function goToAddNewBook() {
	$(location).attr("href", "/html/insert-book.html");
}
function goToGetBook() {
	$(location).attr("href", "/html/get-book.html");
}
function goToGetBooks() {
	$(location).attr("href", "/html/get-all-books.html");
}
function goToIndexPage() {
	$(location).attr("href", "../index.html");
}