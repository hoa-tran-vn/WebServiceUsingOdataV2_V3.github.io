package com.example.myfinalappsproject;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

public class MachineClass  implements Serializable{
    private Double MachinePower;
    private String MachineName;
    private String MachineOrigin;
    private int MachineID;
    private int MachineStatus;
    private int MachineRunTime;
    private int MachineWaitTime;
    private int MachineSetUpTime;
    private int MachineOffTime;
    private Double MachineEnergy;
    private LocalDateTime MachineDateTime;

    public int getMachineStatus() {
        return MachineStatus;
    }

    public MachineClass() {
    }

////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getMachineOrigin() {
        return MachineOrigin;
    }

    public double getMachineEnergy() {
        return MachineEnergy;
    }

    public LocalDateTime getMachineDateTime() {
        return MachineDateTime;
    }

    public int getMachineRunTime() {
        return MachineRunTime;
    }

    public int getMachineWaitTime() {
        return MachineWaitTime;
    }

    public int getMachineSetUpTime() {
        return MachineSetUpTime;
    }

    public int getMachineOffTime() {
        return MachineOffTime;
    }

    public double getMachinePower() {
        return MachinePower;
    }

    public String getMachineName() {
        return MachineName;
    }

    public int getMachineID() {
        return MachineID;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////


    public void setMachineOrigin(String machineOrigin) {
        MachineOrigin = machineOrigin;
    }

    public void setMachineDateTime(LocalDateTime machineDateTime) {
        MachineDateTime = machineDateTime;
    }

    public void setMachineEnergy(Double machineEnergy) {
        MachineEnergy = machineEnergy;
    }

    public void setMachineRunTime(int machineRunTime) {
        MachineRunTime = machineRunTime;
    }

    public void setMachineWaitTime(int machineWaitTime) {
        MachineWaitTime = machineWaitTime;
    }

    public void setMachineSetUpTime(int machineSetUpTime) {
        MachineSetUpTime = machineSetUpTime;
    }

    public void setMachineOffTime(int machineOffTime) {
        MachineOffTime = machineOffTime;
    }

    public void setMachinePower(Double machinePower) {
        MachinePower = machinePower;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public void setMachineID(int machineID) {
        MachineID = machineID;
    }

    public void setMachineStatus(int machineStatus) {
        MachineStatus = machineStatus;
    }
}
