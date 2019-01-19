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

function areBookAtributesOk(isbn, title, listOfAuthors, numberOfPages, genre) {
	if (isbn === "") {
		$("#error-msg").text("ISBN must be populated");
		return false;
	}
	if (title === "") {
		$("#error-msg").text("Title must be populated");
		return false;
	}
	if (listOfAuthors === "") {
		$("#error-msg").text("List Of Authors must be populated");
		return false;
	}
	if (numberOfPages === "") {
		$("#error-msg").text("Number of pages must be populated");
		return false;
	}

	if ((!$.isNumeric(numberOfPages)) || numberOfPages <= 0) {
		$("#error-msg").text("Number of pages must be number0 bigger then 0");
		return false;
	}

	if (genre === "") {
		$("#error-msg").text("Genre must be populated");
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
		let trHTML;
		data.forEach(function(item) {
			let book = getBook(item);
			trHTML += '<tr><td>' + book.isbn + '</td><td>' + book.title
					+ '</td><td>' + book.listOfAuthors + '</td><td>'
					+ book.numberOfPages + '</td><td>' + book.genre
					+ '</td></tr>';
		});
		$('#books').append(trHTML);

	});
}

function getBooks() {
	$.get("/api/books", function(data, status) {
		let trHTML;
		data.forEach(function(item) {
			let book = getBook(item);
			trHTML += '<tr><td>' + book.isbn + '</td><td>' + book.title
					+ '</td><td>' + book.listOfAuthors + '</td><td>'
					+ book.numberOfPages + '</td><td>' + book.genre
					+ '</td></tr>';
		});
		$('#books').append(trHTML);

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
					$("#error-msg").text(message);
				else
					$("#error-msg").text(
							"Unknown error occured. Please check logs.");
			} else {
				$("#error-msg").text(
						"Unknown error occured. Please check logs.");
			}
		}
	});

}

function insertBook() {
	$("#error-msg").text("");
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
function goToGetBooks() {
	$(location).attr("href", "/html/get-all-books.html");
}
function goToIndexPage() {
	$(location).attr("href", "../index.html");
}