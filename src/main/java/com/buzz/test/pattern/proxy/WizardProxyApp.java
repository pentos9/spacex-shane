package com.buzz.test.pattern.proxy;

public class WizardProxyApp {
    public static void main(String[] args) {
        WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());

        proxy.enter(new Wizard("Wizard-1"));
        proxy.enter(new Wizard("Wizard-2"));
        proxy.enter(new Wizard("Wizard-3"));
        proxy.enter(new Wizard("Wizard-4"));
        proxy.enter(new Wizard("Wizard-5"));
    }
}
