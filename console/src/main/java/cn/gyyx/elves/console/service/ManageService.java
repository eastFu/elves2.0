package cn.gyyx.elves.console.service;


import cn.gyyx.elves.console.domain.User;

public interface ManageService {
	

    
    public User valUserCenter(User user) throws Exception;



	public void reCordUserLogin(User user) throws Exception;
}
