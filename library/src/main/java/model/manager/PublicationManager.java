package model.manager;

import model.Publication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PublicationManager {
    private Map<String, Publication> publications = new HashMap<>();

    public void addPublication(Publication pub) {
        publications.put(pub.getTitle(), pub);
    }

    public Publication findPublicationByTitle(String title) {
        return publications.get(title);
    }

    public List<Publication> searchByAuthor(String author) {
        return publications.values().stream()
                .filter(pub -> pub.getAuthors().contains(author))
                .collect(Collectors.toList());
    }

    public List<Publication> searchByKeyWords(List<String> keyWords) {
        return publications.values().stream()
                .filter(pub -> pub.getKeywords().stream().anyMatch(keyWords::contains))
                .collect(Collectors.toList());
    }

    public List<Publication> searchAndSortByRelevance(String query) {
        return publications.values().stream()
                .filter(pub -> pub.getTitle().contains(query) || pub.getKeywords().contains(query))
                .sorted((pub1, pub2) -> // Власна логіка сортування за релевантністю
                        Integer.compare(pub2.getTitle().compareTo(query), pub1.getTitle().compareTo(query)))
                .collect(Collectors.toList());
    }

    public List<Publication> getReferences(String title) {
        Publication pub = findPublicationByTitle(title);
        if (pub != null) {
            return pub.getReferences();
        }
        return new ArrayList<>();
    }

}
