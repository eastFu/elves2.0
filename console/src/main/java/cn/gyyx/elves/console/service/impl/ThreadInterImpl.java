package cn.gyyx.elves.console.service.impl;


import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.filter.AuthFilter;
import cn.gyyx.elves.console.service.ThreadInter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ThreadInterImpl implements ThreadInter {

    @Override
    public Map<String, Object> get() throws Exception {
        Map<String,Object> map = new HashMap<>(  );

        User user = (User) AuthFilter.THREADLOCAL.get();
        map.put("currentUser",user);

        return map;
    }
}
