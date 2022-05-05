package com.icbc.sh.sukura.service;

import com.icbc.sh.sukura.bo.AddressBo;
import com.icbc.sh.sukura.bo.User;

public interface UserService {

    User getUserInfo(String userid);

    void addAddress(AddressBo addressBo);
}
