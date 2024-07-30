
public interface Document {
    void create();
}

public class WordDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a Word Document.");
    }
}

public class PdfDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating a PDF Document.");
    }
}

public class ExcelDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating an Excel Document.");
    }
}

public abstract class DocumentFactory {
    public abstract Document createDocument();
}

public class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class TestFactoryMethod {
    public static void main(String[] args) {
        DocumentFactory factory;

        factory = new WordDocumentFactory();
        Document doc1 = factory.createDocument();
        doc1.create();

        factory = new PdfDocumentFactory();
        Document doc2 = factory.createDocument();
        doc2.create();

        factory = new ExcelDocumentFactory();
        Document doc3 = factory.createDocument();
        doc3.create();
    }
}
