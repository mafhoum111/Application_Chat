package com.example.mafhoumfz;

public abstract class Manager {
    String name;
    String id;
    float hours;

    public Manager(String name, String id, float hours) {
        this.name = name;
        this.id = id;
        this.hours = hours;
    }
    abstract float calculerCout();
}
class ManagerSenior extends Manager{
    public ManagerSenior(String name, String id, float hours) {
        super(name, id, hours);
    }

    @Override
    float calculerCout() {
        if (this.hours>2000)
            return 2500*2000+(this.hours-2000)*3500;
        else if (this.hours<2000)
            return this.hours*2000;
        else
            return 2500*2000;

    }
}
class ManagerJunior extends Manager{
    public ManagerJunior(String name, String id, float hours) {
        super(name, id, hours);
    }

    @Override
    float calculerCout() {
        if (this.hours>2500)
            return 2000*2000+(this.hours-2000)*3000;
        else if (this.hours<2500)
            return this.hours*1500;
        else
            return 2500*2000;

    }
}