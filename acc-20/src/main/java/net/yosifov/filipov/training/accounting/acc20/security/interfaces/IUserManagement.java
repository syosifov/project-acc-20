package net.yosifov.filipov.training.accounting.acc20.security.interfaces;

import net.yosifov.filipov.training.accounting.acc20.system.serialization.JSonParam;
import net.yosifov.filipov.training.accounting.acc20.system.serialization.JSonResult;

public interface IUserManagement {
    /// this is good for when you need universal jdetails
    JSonResult CreateUser(String userName, String Password, JSonParam param);
    JSonResult CreateUser(String userName, String Password, String Name);
    JSonResult DeleteUser(String userName);
    JSonResult DisableUser(String userName);
    //// Get delcaration for structure
    JSonResult GetDeclRegistrationForm();
}
