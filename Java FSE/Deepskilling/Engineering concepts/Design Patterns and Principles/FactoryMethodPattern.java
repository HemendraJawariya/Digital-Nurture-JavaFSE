public class FactoryMethodPattern {

    interface Document {
        void open();
    }

    static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Word document");
        }
    }

    static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening PDF document");
        }
    }

    static class ExcelDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Excel document");
        }
    }

    abstract static class DocumentFactory {
        abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory {
        @Override
        Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory {
        @Override
        Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        Document createDocument() {
            return new ExcelDocument();
        }
    }

    public static void main(String[] args) {
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            factory.createDocument().open();
        }
    }
}