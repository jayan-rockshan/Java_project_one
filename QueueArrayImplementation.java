import java.util.Scanner;

public class QueueArrayImplementation {
    private int front;
    private int rear;
    private int capacity;
    private String[] queue;

    public QueueArrayImplementation() {
        front = -1;
        rear = -1;
        capacity = 10;
        queue = new String[capacity];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public void enqueue(String data) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (isEmpty()) {
                front = 0;
            }
            rear = (rear + 1) % capacity;
            queue[rear] = data;
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            String data = queue[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % capacity;
            }
            return data;
        }
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            return queue[front];
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Patients' Dashboard:");
            int i = front;
            while (i != rear) {
                System.out.println(queue[i]);
                i = (i + 1) % capacity;
            }
            System.out.println(queue[rear]);
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select user type: Doctor or Patient? or 'Exit' option to exit the system");
        String select = scanner.nextLine();
        select = select.toLowerCase();

        if (select.equals("doctor")) {
            doctor();
        } else if (select.equals("patient")) {
            patient();
        } else if (select.equals("exit")) {
            System.out.println("You have exited the system. See you again!");
        } else if (select.equals("back")) {
            System.out.println("Going back one step");
            login();
        } else {
            System.out.println("Invalid user type");
            login();
        }

        scanner.close();
    }

    // patient
    public void patient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select user option: 'OPD' to get medicine or 'Book' to get your appointment or 'Back' to the main page");
        String select = scanner.nextLine();
        select = select.toLowerCase();

        if (select.equals("opd")) {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            enqueue(name);
            System.out.println("Dear " + name + ", you are in the queue. Pay the bill to the cashier and wait until the doctor calls you.");
            display();
            patient();
        } else if (select.equals("book")) {
            System.out.println("Please enter your name:");
            String name = scanner.nextLine();
            enqueue(name);
            System.out.println("Dear " + name + ", you have booked the appointment. Please check the patient list below");
            display();
            patient();
        } else if (select.equals("back")) {
            System.out.println("You back to the main page");
	    login();

        } else {
            System.out.println("Invalid selection");
            patient();
        }

        scanner.close();
    }

    // doctor
    public void doctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select user option: 'Remove' to treat a patient or 'Display' to view the Dashboard or 'Exit' to leave the system");
        String select = scanner.nextLine();
        select = select.toLowerCase();

        if (select.equals("remove")) {
            String name = dequeue();
            if (name != null) {
                System.out.println("Patient " + name + " treated.");
            }
            doctor();
        } else if (select.equals("display")) {
            display();
            doctor();
        } else if (select.equals("exit")) {
            System.out.println("You have exited the system. See you again!");
        } else {
            System.out.println("Invalid selection");
            doctor();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        QueueArrayImplementation queue = new QueueArrayImplementation();
        System.out.println("Hello! Welcome to the Arogya Hospital");
        queue.login();
    }
}
