package com.sh.shdemo.Config;

import com.sh.shdemo.dao.SysRoleRepository;
import com.sh.shdemo.dao.SysUserRepository;
import com.sh.shdemo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;
    @Autowired
    SysRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        System.out.println("username:"+username);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());

        return new User(user.getUsername(), user.getPassword(), true, true, true, true,
                user.getAuthorities());
    }
}
