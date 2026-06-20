public class ProxyPattern {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String fileName;

        RealImage(String fileName) {
            this.fileName = fileName;
            loadFromRemoteServer();
        }

        private void loadFromRemoteServer() {
            System.out.println("Loading image from remote server: " + fileName);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + fileName);
        }
    }

    static class ImageProxy implements Image {
        private final String fileName;
        private RealImage realImage;

        ImageProxy(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image = new ImageProxy("warehouse-dashboard.png");
        image.display();
        image.display();
    }
}