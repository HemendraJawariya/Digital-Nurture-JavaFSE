public class MVCPattern {

    static class Student {
        private final int id;
        private final String name;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    static class StudentView {
        public void showStudentDetails(String studentName, int studentId) {
            System.out.println("Student: " + studentName + ", ID: " + studentId);
        }
    }

    static class StudentController {
        private final Student model;
        private final StudentView view;

        StudentController(Student model, StudentView view) {
            this.model = model;
            this.view = view;
        }

        public void updateView() {
            view.showStudentDetails(model.getName(), model.getId());
        }
    }

    public static void main(String[] args) {
        Student student = new Student(101, "Anika");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        controller.updateView();
    }
}