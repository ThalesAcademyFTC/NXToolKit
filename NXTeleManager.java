package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;

/**
 * Created by dcrenshaw on 6/27/18.
 *
 * NXTeleManager is a convenience class for managing telemetry data and keeping it up to date.
 *
 * TeleManager is a singleton. You do not need to know what that means or why that is, but you do
 * need to know that TeleManager is available on a global scope without being a global variable.
 * Get an instance of TeleManager as follows: private NXTeleManager t = NXTeleManager.getInstance();
 */

public class NXTeleManager {

    //Central data containers
    private Telemetry telemetry;
    private Telemetry.Log log;
    private HashMap<String, Telemetry.Item> lineList = new HashMap<>();

    public String name = "Telemetry Manager";

    private static NXTeleManager Instance;

    public static NXTeleManager getInstance() {
        return Instance;
    }

    public static NXTeleManager init(Telemetry t) {
        Instance = new NXTeleManager(t);
        return Instance;
    }

    //Central methods
    private NXTeleManager(Telemetry telem) {
        telemetry = telem;
        Telemetry.Line l = telemetry.addLine();
        lineList.put("status", l.addData("Telemetry Manager: ", "Online"));
        log = telemetry.log();
    }
    public void create(String accessor, String caption, String value) {
        if (lineList.containsKey(accessor)) update(accessor, value);
        Telemetry.Line l = telemetry.addLine();
        lineList.put(accessor, l.addData(caption, value));
    }
    public void update(String accessor, String value) {
        try {
            lineList.get(accessor).setValue(value);
        }
        catch (Throwable e) {
            //if the line doesn't exist, we need to report that instead of crashing
            log_e(name, e.getMessage());
        }
    }
    public void create(String caption, String value) {
        if (lineList.containsKey(caption)) update(caption, value);
        Telemetry.Line l = telemetry.addLine();
        lineList.put(caption, l.addData(caption, value));
    }
    public void updateCaption(String accessor, String caption) {
        try {
            lineList.get(accessor).setCaption(caption);
        }
        catch (Throwable e) {
            //report when there is no line instead of crashing
            log_e(name, e.getMessage());
        }
    }
    public void log(String subsystem, String message) {
        log.add(subsystem + ": " + message);
    }
    public void log_e(String subsystem, String message) {
        log.add("Error in " + subsystem + ": " + message);
    }
}
