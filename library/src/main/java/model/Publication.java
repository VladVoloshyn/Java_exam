package model;

import java.util.List;

public abstract class Publication {
    protected String title;
    protected List<String> authors;
    protected String publicationYear;
    protected List<String> keywords;
    protected List<Publication> references; // Список посилань на інші публікації

    public Publication() {
    }

    public Publication(String title, List<String> authors, String publicationYear, List<String> keywords, List<Publication> references) {
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.keywords = keywords;
        this.references = references;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<Publication> getReferences() {
        return references;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void setReferences(List<Publication> references) {
        this.references = references;
    }
}

class Article extends Publication {
    private String journalName;
    private String doi;

    public Article(String journalName, String doi) {
        this.journalName = journalName;
        this.doi = doi;
    }
    public Article() {

    }

    public String getJournalName() {
        return journalName;
    }

    public String getDoi() {
        return doi;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
}

class Book extends Publication {
    private String publisher;
    private String isbn;

    public Book() {
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}