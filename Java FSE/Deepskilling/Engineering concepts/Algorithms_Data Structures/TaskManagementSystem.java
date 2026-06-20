public class TaskManagementSystem {

    public static class Node {
        private final String taskName;
        private Node next;

        public Node(String taskName) {
            this.taskName = taskName;
        }
    }

    private Node head;

    public void addTask(String taskName) {
        Node node = new Node(taskName);
        if (head == null) {
            head = node;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public boolean deleteTask(String taskName) {
        if (head == null) {
            return false;
        }

        if (head.taskName.equals(taskName)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.taskName.equals(taskName)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.taskName);
            current = current.next;
        }
    }

    public void printAnalysis() {
        System.out.println("Analysis:");
        System.out.println("Linked lists support efficient insertions and deletions with O(1) pointer changes once the node is found.");
        System.out.println("Traversal and search are O(n) time, with O(n) space for n tasks.");
    }

    public static void main(String[] args) {
        TaskManagementSystem system = new TaskManagementSystem();
        system.addTask("Design database schema");
        system.addTask("Review API endpoints");
        system.addTask("Deploy release build");

        system.deleteTask("Review API endpoints");
        system.traverseTasks();
        system.printAnalysis();
    }
}