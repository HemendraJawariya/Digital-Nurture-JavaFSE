import java.util.Arrays;

public class LibraryManagementSystem {

    public static class Book {
        private final String title;
        private final String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return title + " by " + author;
        }
    }

    private final Book[] books;

    public LibraryManagementSystem(Book[] books) {
        this.books = books.clone();
        Arrays.sort(this.books, (left, right) -> left.getTitle().compareToIgnoreCase(right.getTitle()));
    }

    public Book searchByTitle(String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int comparison = books[middle].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[middle];
            }
            if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }

    public Book[] searchByAuthor(String author) {
        Book[] matches = new Book[books.length];
        int count = 0;

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                matches[count++] = book;
            }
        }

        return Arrays.copyOf(matches, count);
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Searching by title with binary search is O(log n) after sorting the catalog.");
        System.out.println("Searching by author with a scan is O(n) time and O(k) result space.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem(
            new Book[] {
                new Book("Clean Code", "Robert Martin"),
                new Book("Effective Java", "Joshua Bloch"),
                new Book("Design Patterns", "Erich Gamma"),
                new Book("Refactoring", "Martin Fowler")
            }
        );

        System.out.println(library.searchByTitle("Effective Java"));
        for (Book book : library.searchByAuthor("Martin Fowler")) {
            System.out.println(book);
        }

        library.printAnalysis();
    }
}