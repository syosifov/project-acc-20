package net.yosifov.filipov.training.accounting.acc20.security;

import net.yosifov.filipov.training.accounting.acc20.security.interfaces.IUserManagement;
import net.yosifov.filipov.training.accounting.acc20.system.serialization.JSonParam;
import net.yosifov.filipov.training.accounting.acc20.system.serialization.JSonResult;

public class UserManagementService implements IUserManagement {

    @Override
    public JSonResult CreateUser(String userName, String Password, JSonParam param) {
        return null;
    }

    @Override
    public JSonResult CreateUser(String userName, String Password, String Name) {
        return null;
    }

    @Override
    public JSonResult DeleteUser(String userName) {
        return null;
    }

    @Override
    public JSonResult DisableUser(String userName) {
        return null;
    }
}