package cn.gyyx.elves.console.service.impl;



import cn.gyyx.elves.console.dao.ManageDao;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.service.ManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManageServiceImpl implements ManageService {

	@Resource
	private ManageDao manageDao;
	

	@Override
	public User valUserCenter(User user) throws Exception {
		return manageDao.valUserCenter(user);
	}



	@Override
	public void reCordUserLogin(User user) throws Exception {
		manageDao.reCordUserLogin(user);
	}

}
