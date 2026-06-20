public class BuilderPattern {

    public static final class Computer {
        private final String cpu;
        private final String ram;
        private final String storage;
        private final String gpu;

        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.gpu = builder.gpu;
        }

        @Override
        public String toString() {
            return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                '}';
        }

        public static final class Builder {
            private final String cpu;
            private String ram = "8 GB";
            private String storage = "512 GB SSD";
            private String gpu = "Integrated";

            public Builder(String cpu) {
                this.cpu = cpu;
            }

            public Builder ram(String ram) {
                this.ram = ram;
                return this;
            }

            public Builder storage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder gpu(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        Computer computer = new Computer.Builder("Intel Core i7")
            .ram("16 GB")
            .storage("1 TB SSD")
            .gpu("NVIDIA RTX 4060")
            .build();

        System.out.println(computer);
    }
}