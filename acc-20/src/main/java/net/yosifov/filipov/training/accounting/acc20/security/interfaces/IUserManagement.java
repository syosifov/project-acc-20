package net.yosifov.filipov.training.accounting.acc20.security.interfaces;

import net.yosifov.filipov.training.accounting.acc20.system.serialization.JSonResult;

public interface IUserManagement {
    JSonResult CreateUser(String userName, String Password, String Name);
    JSonResult DeleteUser(String userName);
    JSonResult DisableUser(String userName);
}
