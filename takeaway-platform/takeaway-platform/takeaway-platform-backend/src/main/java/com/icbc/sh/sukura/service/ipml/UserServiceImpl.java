package com.icbc.sh.sukura.service.ipml;

import com.icbc.sh.sukura.bo.AddressBo;
import com.icbc.sh.sukura.bo.User;
import com.icbc.sh.sukura.entity.AddressEntity;
import com.icbc.sh.sukura.entity.UserEntity;
import com.icbc.sh.sukura.repository.AddressRepository;
import com.icbc.sh.sukura.repository.UserRepository;
import com.icbc.sh.sukura.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public User getUserInfo(String userId) {
        return UserServiceImpl.E2B(userRepository.findOne(userId));
    }

    @Override
    public void addAddress(AddressBo addressBo) {
        String addressId = "A-" + UUID.randomUUID().toString();
        AddressEntity entity = AddressEntity.builder()
                .addressId(addressId)
                .province(addressBo.getProvince())
                .city(addressBo.getCity())
                .district(addressBo.getDistrict())
                .street(addressBo.getStreet())
                .label(addressBo.getLabel())
                .build();
        addressRepository.save(entity);
    }


    public static User E2B (UserEntity userEntity) {
        return User.builder().addressId(userEntity.getAddressId())
                .avarId(userEntity.getAvarId())
                .phone(userEntity.getPhone())
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .build();
    }

    public static UserEntity B2E (User user) {
        return UserEntity.builder().addressId(user.getAddressId())
                .avarId(user.getAvarId())
                .phone(user.getPhone())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }
}
