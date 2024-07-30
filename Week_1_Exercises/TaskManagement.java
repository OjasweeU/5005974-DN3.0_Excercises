public class TaskManagement {
    private Task head;

    public void addTask(Task newTask) {
        newTask.next = head;
        head = newTask;
    }

    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) return current;
            current = current.next;
        }
        return null;
    }

    public void traverse() {
        Task current = head;
        while (current != null) {
            System.out.println(current.taskName);
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) return;
        if (head.taskId == taskId) {
            head = head.next;
            return;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
}
