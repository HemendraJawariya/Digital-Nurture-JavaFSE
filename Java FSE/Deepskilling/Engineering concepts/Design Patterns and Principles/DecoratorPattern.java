public class DecoratorPattern {

    interface Notification {
        void send();
    }

    static class EmailNotification implements Notification {
        @Override
        public void send() {
            System.out.println("Sending email notification");
        }
    }

    static abstract class NotificationDecorator implements Notification {
        protected final Notification notification;

        protected NotificationDecorator(Notification notification) {
            this.notification = notification;
        }
    }

    static class SmsDecorator extends NotificationDecorator {
        SmsDecorator(Notification notification) {
            super(notification);
        }

        @Override
        public void send() {
            notification.send();
            System.out.println("Sending SMS notification");
        }
    }

    static class PushDecorator extends NotificationDecorator {
        PushDecorator(Notification notification) {
            super(notification);
        }

        @Override
        public void send() {
            notification.send();
            System.out.println("Sending push notification");
        }
    }

    public static void main(String[] args) {
        Notification notification = new PushDecorator(new SmsDecorator(new EmailNotification()));
        notification.send();
    }
}