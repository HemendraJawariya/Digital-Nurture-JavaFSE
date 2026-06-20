import java.util.ArrayList;
import java.util.List;

public class CommandPattern {

    interface Command {
        void execute();
    }

    static class Light {
        public void on() {
            System.out.println("Light turned on");
        }

        public void off() {
            System.out.println("Light turned off");
        }
    }

    static class Fan {
        public void on() {
            System.out.println("Fan turned on");
        }

        public void off() {
            System.out.println("Fan turned off");
        }
    }

    static class LightOnCommand implements Command {
        private final Light light;

        LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.on();
        }
    }

    static class LightOffCommand implements Command {
        private final Light light;

        LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.off();
        }
    }

    static class FanOnCommand implements Command {
        private final Fan fan;

        FanOnCommand(Fan fan) {
            this.fan = fan;
        }

        @Override
        public void execute() {
            fan.on();
        }
    }

    static class RemoteControl {
        private final List<Command> commands = new ArrayList<>();

        public void addCommand(Command command) {
            commands.add(command);
        }

        public void pressButton() {
            for (Command command : commands) {
                command.execute();
            }
        }
    }

    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();

        RemoteControl remote = new RemoteControl();
        remote.addCommand(new LightOnCommand(light));
        remote.addCommand(new FanOnCommand(fan));
        remote.addCommand(new LightOffCommand(light));
        remote.pressButton();
    }
}