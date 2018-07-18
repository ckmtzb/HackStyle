package haxballTools.HTScripts;

import haxballTools.HTGui;
import haxballTools.HTScript;

import static haxballTools.HTRobot.sendToChat;
import static haxballTools.HTRobot.sleep;

public final class Spam {

    private Spam() {

    }

    public static HTScript create(String...s) {
        return new HTScript() {
            private String[] strings;
            private boolean running;
            private int duration;

            @Override
            public void start(int d) {
                this.running = true;
                this.duration = d;
                this.strings = s;

                new Thread(() -> {
                    int i = 0;
                    while (running) {
                        sendToChat(strings[(i++)]);
                        sleep(duration);
                        i%= strings.length;
                    }
                }).start();
            }

            @Override
            public void stop() {
                running = false;
            }
        };
    }

    public static HTScript fromGUI() {
        return new HTScript() {
            HTScript scriptX;

            @Override
            public void start(int d) {
                scriptX = create(HTGui.getTextFieldText());
                scriptX.start(d);
            }

            @Override
            public void stop() {
                scriptX.stop();
            }
        };
    }

    public static HTScript splitFromGUI(){
        return new HTScript() {
            HTScript scriptX;

            @Override
            public void start(int d) {
                scriptX = create(HTGui.getTextFieldText().split("/"));
                scriptX.start(d);
            }

            @Override
            public void stop() {
                scriptX.stop();
            }
        };
    }

}
